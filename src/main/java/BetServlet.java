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
@WebServlet(urlPatterns = {"/BetServlet"})
public class BetServlet extends HttpServlet {

    /**
     * change exp, coin when finish a game
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        UserDAO dao = new UserDAO();
        String sAction = request.getParameter("action");
        int iUserId = (int) session.getAttribute("id");
        String sWhoWon = request.getParameter("whoWon");

        if (sAction.equals("blackjack")) {
            int iCoin = Integer.parseInt(request.getParameter("betAmount"));

            dao.changeCoin(iUserId, iCoin, sWhoWon);
            dao.increaseExp(iUserId, sWhoWon);
        }
        
        if (sAction.equals("minigame")) {
            int iCoin = 50;
            dao.changeCoin(iUserId, iCoin, sWhoWon);
        }
    }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BetServlet.class.getName()).log(Level.SEVERE, null, ex);
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
