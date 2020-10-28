
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
            "insert into users(username, password, email, name, created)"
                    + " values(?,?,?,?,?,?)");
        stm.setString(1, u.getUsername());
        stm.setString(2, u.getPassword());
        stm.setString(3, u.getEmail());
        stm.setString(4, u.getName());
        
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date dateobj = new Date(System.currentTimeMillis());
        stm.setString(6, df.format(dateobj));
        stm.executeUpdate();
        stm.close();
    }
    
    public List<User> getAll() throws SQLException{
        List<User> kq=new ArrayList<User>();
        stmt = con.createStatement();
        rs = stmt.executeQuery("select id, username, password, email, name, created from users");
        while(rs.next()){
            User st = new User();
            st.setId(rs.getInt(1));
            st.setUsername(rs.getString(2));
            st.setPassword(rs.getString(3));
            st.setEmail(rs.getString(4));
            st.setName(rs.getString(5));
            st.setCreated(rs.getDate(7));
            kq.add(st);
        }
        rs.close();
        stmt.close();
        return kq;
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
    public String checkLogin(String sUsername, String sPass) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("select name from users where username = '" + sUsername + 
                "' and password = '" + sPass + "'");
        String sName = "";
        while (rs.next()) {
            sName = rs.getString(1);
        }
        rs.close();
        stmt.close();
        return sName;
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
}
