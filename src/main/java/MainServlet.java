/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        //check gift everyday
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();

        obj.put("gift", oUser.getGiftdate());
        obj.put("now", df.format(dt));
        
        if (oUser.getGiftdate().equals(df.format(dt))) {
            obj.put("giftdate", false);
        } else {
            obj.put("giftdate", true);
        }
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
        //receive gift
        if (sAction.equals("received")) {
            try {
                HttpSession session = request.getSession();
                UserDAO dao = new UserDAO();

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date dt = new Date();

                int iLv = Integer.parseInt(request.getParameter("lv"));
                int iUserId = (int) session.getAttribute("id");
                String sDate = df.format(dt);
                int iCoin = 0;
                
                dao.changeGiftDate(iUserId, sDate);
                //icrease coin
                if (iLv >= 0 && iLv < 10) iCoin = 100;
                else if (iLv >= 10 && iLv < 20) iCoin = 300;
                else if (iLv >= 20 && iLv < 40) iCoin = 500;
                else if (iLv >= 40 && iLv <= 60) iCoin = 700;
                else iCoin = 1000;
                dao.changeCoin(iUserId, iCoin, "Player");
                JSONObject obj = new JSONObject();
                obj.put("status", true);
                obj.put("coinReceived", iCoin);
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
