
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author khai
 */
public class UserDAO {

    Connection con;
    Statement stmt;
    ResultSet rs;

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * connect DB
     *
     * @throws SQLException
     * @author Khailq
     * @created 2020/10/27
     *
     */
    public UserDAO() throws SQLException {
        con = DriverManager.getConnection(
                "jdbc:sqlserver://localhost;databaseName=gameCard;", "sa", "12345678");
    }

    /**
     * add user
     *
     * @param u
     * @throws SQLException
     * @author Khailq
     * @created 2020/10/27
     *
     */
    public void add(final User u) throws SQLException {
        final PreparedStatement stm = con.prepareStatement(
                "insert into users(username, password, email, name)"
                + " values(?,?,?,?)");
        stm.setString(1, u.getUsername());
        stm.setString(2, u.getPassword());
        stm.setString(3, u.getEmail());
        stm.setString(4, u.getName());
        stm.executeUpdate();
        stm.close();
    }

    /**
     * check login
     *
     * @param sUsername
     * @param sPass
     * @return iId
     * @throws SQLException
     * @author Khailq
     * @created 2020/10/28
     *
     */
    public int checkLogin(String sUsername, String sPass) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("select id from users where username = '" + sUsername
                + "' and password = '" + sPass + "'");
        int iId = 0;
        while (rs.next()) {
            iId = rs.getInt(1);
        }
        rs.close();
        stmt.close();
        return iId;
    }

    /**
     * validate back-end form sign up
     *
     * @param sUsername
     * @param sPass
     * @param sEmail
     * @return sError
     * @throws SQLException
     * @author Khailq
     * @created 2020/10/28
     *
     */
    public String validateSignUp(String sUsername, String sPass, String sEmail)
            throws SQLException {
        String sError = "";
        if (sUsername.length() < 5 || sUsername.length() > 20) {
            sError += "Username should be between 5-20 characters\n";
        }
        if (sPass.length() < 5 || sPass.length() > 20) {
            sError += "Password should be between 5-20 characters\n";
        }
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!sEmail.matches(regex)) {
            sError += "Please include an '@' in the email address.\n";
        }
        return sError;
    }

    /**
     * Check if email exists in db or not
     *
     * @param sEmail
     * @return boolean
     * @throws SQLException
     * @author Khailq
     * @created 2020/10/28
     *
     */
    public boolean checkEmail(String sEmail) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("select * from users where email = '" + sEmail + "'");
        if (rs.next()) {
            return true;
        }
        rs.close();
        stmt.close();
        return false;
    }

    /**
     *
     * @param sPass
     * @param sEmail
     * @throws SQLException
     * @author Khailq
     * @created 2020/10/30
     *
     */
    public void updatePassword(String sPass, String sEmail) throws SQLException {
        final PreparedStatement stm = con.prepareStatement(
                "update users set password = '" + sPass + "'"
                + "where email = '" + sEmail + "'");
        stm.executeUpdate();
        stm.close();
    }

    /**
     * get info by id
     *
     * @param iId
     * @return oUser
     * @throws SQLException
     * @author Khailq
     * @created 2020/11/02
     *
     */
    public User getInfoById(int iId) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("select name, coin, exp, lv, username from users where id = '" + iId + "'");
        User oUser = new User();
        if (rs.next()) {
            oUser.setName(rs.getString(1));
            oUser.setCoin(rs.getInt(2));
            oUser.setExp(rs.getInt(3));
            oUser.setLv(rs.getInt(4));
            oUser.setUsername(rs.getString(5));
        }
        rs.close();
        stmt.close();
        return oUser;
    }

    /**
     * get top 7 user with the highest amount of money
     *
     * @author Khailq
     * @return JSONArray
     * @throws java.sql.SQLException
     * @created 2020/11/07
     *
     */
    public JSONArray getTopUser() throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("select top(7) name, coin, lv from users ORDER BY coin DESC");
        JSONArray jarray = new JSONArray();
        while (rs.next()) {
            JSONObject oUser = new JSONObject();
            oUser.put("name", rs.getString(1));
            oUser.put("coin", rs.getInt(2));
            oUser.put("lv", rs.getInt(3));
            jarray.put(oUser);
        }
        rs.close();
        stmt.close();
        return jarray;
    }

    /**
     * change coin when finish game
     *
     * @author Khailq
     * @param iUserId
     * @param iCoin
     * @throws java.sql.SQLException
     * @created 2020/11/24
     *
     */
    public void changeCoin(int iUserId, int iCoin, String sWhoWon) throws SQLException {
        if (sWhoWon.equals("Computer")) {
            final PreparedStatement stm = con.prepareStatement(
                    "UPDATE users SET coin = "
                    + "(SELECT coin FROM users WHERE id = " + iUserId + ") - " + iCoin
                    + "where id = " + iUserId);
            stm.executeUpdate();
            stm.close();
        } else if (sWhoWon.equals("Player")) {
            final PreparedStatement stm = con.prepareStatement(
                    "UPDATE users SET coin = "
                    + "(SELECT coin FROM users WHERE id = " + iUserId + ") + " + iCoin
                    + "where id = " + iUserId);
            stm.executeUpdate();
            stm.close();
        }
    }

    /**
     * increase exp
     *
     * @author Khailq
     * @param iUserId
     * @param sWhoWon
     * @throws java.sql.SQLException
     * @created 2020/11/24
     *
     */
    public void increaseExp(int iUserId, String sWhoWon) throws SQLException {
        if (sWhoWon.equals("Computer")) {
            final PreparedStatement stm = con.prepareStatement(
                    "UPDATE users SET exp = "
                    + "(SELECT exp FROM users WHERE id = " + iUserId + ") + " + 15
                    + "where id = " + iUserId);
            stm.executeUpdate();
            stm.close();
        } else if (sWhoWon.equals("Player")) {
            final PreparedStatement stm = con.prepareStatement(
                    "UPDATE users SET exp = "
                    + "(SELECT exp FROM users WHERE id = " + iUserId + ") + " + 25
                    + "where id = " + iUserId);
            stm.executeUpdate();
            stm.close();
        }
        increaseLv(iUserId, getExpById(iUserId));
    }

    /**
     * increase lv when exp >= 100
     *
     * @author Khailq
     * @param iUserId
     * @param iExp
     * @throws java.sql.SQLException
     * @created 2020/11/24
     *
     */
    public void increaseLv(int iUserId, int iExp) throws SQLException {
        if (iExp >= 100) {
            final PreparedStatement stm = con.prepareStatement(
                    "update users "
                    + "set lv = (select lv from users where id = " + iUserId + ") + 2, "
                    + "exp = (select exp from users where id = " + iUserId + ") - 100 "
                    + "where id = " + iUserId);
            stm.executeUpdate();
            stm.close();
        }
    }

    /**
     * get lv by userId
     *
     * @author Khailq
     * @param iUserId
     * @return
     * @throws java.sql.SQLException
     * @created 2020/11/24
     *
     */
    public int getExpById(int iUserId) throws SQLException {
        int sLv = 0;
        stmt = con.createStatement();
        rs = stmt.executeQuery("select exp from users where id = " + iUserId);
        if (rs.next()) {
            sLv = rs.getInt(1);
        }
        rs.close();
        stmt.close();
        return sLv;
    }
}
