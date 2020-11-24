/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author khai
 */
@WebServlet(urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    /**
     * get user info by id and send it into ajax (dataType: json)
     *
     * @author Khailq
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     * @created 2020/11/07
     *
     */
    protected void getUserInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        UserDAO dao = new UserDAO();
        User oUser = dao.getInfoById((int) session.getAttribute("id"));

//        JSONArray a = dao.getTopUser();
        JSONObject obj = new JSONObject();
        obj.put("name", oUser.getName());
        obj.put("coin", oUser.getCoin());
        obj.put("exp", oUser.getExp());
        obj.put("lv", oUser.getLv());
        obj.put("username", oUser.getUsername());

        //used in room.jsp
        session.setAttribute("name", oUser.getName());

        obj.put("TopUser", dao.getTopUser());
        //return json into ajax
        try ( PrintWriter writer = response.getWriter()) {
            writer.append(obj.toString());
        }
    }
    
    /**
     * Handles the HTTP <code>POST</code> method. handles event ajax in main
     * layout
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sAction = request.getParameter("action");
        if (sAction == null) {
            return;
        }
        //get my info and top rank
        if (sAction.equals("userInfo")) {
            try {
                getUserInfo(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //get info user
        if (sAction.equals("uifInGame")) {
            try {
                HttpSession session = request.getSession();
                UserDAO dao = new UserDAO();
                User oUser = dao.getInfoById((int) session.getAttribute("id"));

                JSONObject obj = new JSONObject();
                obj.put("name", oUser.getName());
                obj.put("coin", oUser.getCoin());
                obj.put("exp", oUser.getExp());
                obj.put("lv", oUser.getLv());
                obj.put("username", oUser.getUsername());
                //return json into ajax
                try ( PrintWriter writer = response.getWriter()) {
                    writer.append(obj.toString());
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
