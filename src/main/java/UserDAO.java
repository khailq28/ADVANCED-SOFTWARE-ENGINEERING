
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
            "insert into users(username, password, email, name, phone_number, created)"
                    + " values(?,?,?,?,?,?)");
        stm.setString(1, u.getUsername());
        stm.setString(2, u.getPassword());
        stm.setString(3, u.getEmail());
        stm.setString(4, u.getName());
        stm.setString(5, u.getPhoneNumber());
        
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date dateobj = new Date(System.currentTimeMillis());
        stm.setString(6, df.format(dateobj));
        stm.executeUpdate();
        stm.close();
    }
    
    public List<User> getAll() throws SQLException{
        List<User> kq=new ArrayList<User>();
        stmt = con.createStatement();
        rs = stmt.executeQuery("select id, username, password, email, name, phone_number, created from users");
        while(rs.next()){
            User st = new User();
            st.setId(rs.getInt(1));
            st.setUsername(rs.getString(2));
            st.setPassword(rs.getString(3));
            st.setEmail(rs.getString(4));
            st.setName(rs.getString(5));
            st.setPhoneNumber(rs.getString(6));
            st.setCreated(rs.getDate(7));
            kq.add(st);
        }
        rs.close();
        stmt.close();
        return kq;
    }
    
    /**
     * check login
     * @params sUsername
     * @params sPass
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
}
