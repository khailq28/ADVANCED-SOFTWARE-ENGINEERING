/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
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
         *
         * @author Khailq
         * @created 2020/10/28
         *
         */
        if (sAction.equals("login")) {
            try {
                UserDAO dao = new UserDAO();
                String sUsername = request.getParameter("username");
                String sPass = request.getParameter("pass");
                if (dao.checkLogin(sUsername, sPass) == 0) {
                    session.setAttribute("message", "Incorrect username or password.");
                    response.sendRedirect("index.jsp");
                    return;
                } else {
                    session.removeAttribute("message");
                    session.setAttribute("login" + dao.checkLogin(sUsername, sPass), "true");
                    session.setAttribute("id", dao.checkLogin(sUsername, sPass));
                    response.sendRedirect("main.jsp");
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /**
         * handle action signUp
         *
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
                session.setAttribute("success", "Successfully create account. Login now.");
                response.sendRedirect("index.jsp");
                return;
            } catch (SQLException ex) {
                session.setAttribute("errorSignUp", "Username or email have already taken.");
                response.sendRedirect("sign_up.jsp");
                return;
            }
        }
        /**
         * handle action forgot password
         *
         * @author Khailq
         * @created 2020/10/30
         *
         */
        if (sAction.equals("update")) {
            String sOtp = request.getParameter("otp");
            String sPass = request.getParameter("pass");
            String sRePass = request.getParameter("repeatPass");
            String sEmail = request.getParameter("email");

            try {
                if (session.getAttribute("otp") == null) {
                    session.setAttribute("errorupdate", "otp is not send to your email.");
                    response.sendRedirect("forgot.jsp");
                    return;
                } else {
                    if (sOtp.equals(session.getAttribute("otp"))
                            && sPass.equals(sRePass)) {
                        UserDAO dao = new UserDAO();
                        dao.updatePassword(sPass, sEmail);
                        session.setAttribute("success", "Successfully reset password. Login now.");
                        session.removeAttribute("otp");
                        response.sendRedirect("index.jsp");
                        return;
                    } else {
                        session.setAttribute("errorupdate", "otp is not correct.");
                        response.sendRedirect("forgot.jsp");
                        return;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                session.setAttribute("success", ex);
            }
        }
        response.sendRedirect("index.jsp");
    }

    /**
     * action log out
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws java.io.IOException
     * @author Khailq
     * @created 2020/11/02
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("login" + session.getAttribute("id"));
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
