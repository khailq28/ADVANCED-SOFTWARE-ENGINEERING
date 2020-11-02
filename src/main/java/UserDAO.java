
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * @throws SQLException
     * @author Khailq
     * @created 2020/10/27
     *
     */
    public UserDAO() throws SQLException  {
        con = DriverManager.getConnection(
            "jdbc:sqlserver://localhost;databaseName=gameCard;","sa","12345678");
    }
    
    /**
     * add user
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
     * @param sUsername
     * @param sPass
     * @return sName
     * @throws SQLException 
     * @author Khailq
     * @created 2020/10/28
     *
     */
    public User checkLogin(String sUsername, String sPass) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("select name from users where username = '" + sUsername + 
                "' and password = '" + sPass + "'");
        User u = new User();
        String sName = "";
        while (rs.next()) {
            u.setName(rs.getString(1));
        }
        rs.close();
        stmt.close();
        return u;
    }
    /**
     * validate back-end form sign up
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
}
