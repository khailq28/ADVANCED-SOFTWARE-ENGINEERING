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

/**
 *
 * @author khai
 */
@WebServlet(urlPatterns = {"/User"})
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get action
        HttpSession session = request.getSession();
        String sAction = request.getParameter("action");
        if (sAction == null) {
            return;
        }
        /**
         * handle action login
         * @author Khailq
         * @created 2020/10/28
         *
         */
        if (sAction.equals("login")) {
            try {
                UserDAO dao = new UserDAO();
                String sUsername = request.getParameter("username");
                String sPass = request.getParameter("pass");
                if (dao.checkLogin(sUsername, sPass).equals("")) {
                    session.setAttribute("message", "Incorrect username or password.");
                    response.sendRedirect("index.jsp");
                    return;
                }else {
                    session.removeAttribute("message");
                    response.sendRedirect("main.jsp");
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /**
         * handle action signUp
         * @author Khailq
         * @created 2020/10/28
         *
         */
        if (sAction.equals("signUp")) {
            try {
                UserDAO dao = new UserDAO();
                String sUsername = request.getParameter("username");
                String sPassword = request.getParameter("pass");
                String sName = request.getParameter("name");
                String sEmail = request.getParameter("email");
                
                //validate back-end
                String sValidate = dao.validateSignUp(sUsername, sPassword, sEmail);
                if (!sValidate.equals("")) {
                    session.setAttribute("errorSignUp", sValidate);
                    response.sendRedirect("sign_up.jsp");
                    return;
                }
                //insert data into db
                final User oUser = new User();
                oUser.setName(sName);
                oUser.setUsername(sUsername);
                oUser.setPassword(sPassword);
                oUser.setEmail(sEmail);
                dao.add(oUser);
                session.setAttribute("successSignUp", "success");
                response.sendRedirect("index.jsp");
                return;
            } catch (SQLException ex) {
                session.setAttribute("errorSignUp", "Username or email have already taken.");
                response.sendRedirect("sign_up.jsp");
                return;
            }
        }
        
        response.sendRedirect("index.jsp");
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
