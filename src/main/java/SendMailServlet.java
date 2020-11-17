/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
@WebServlet(urlPatterns = {"/SendMail"})
public class SendMailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @param sOtp
     * @param sEmail
     * @return true|false
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void eventSendEmail(HttpServletRequest request, HttpServletResponse response,
            String sOtp, String sEmail) throws ServletException, IOException, AddressException, MessagingException {
        String to = sEmail;
        String subject = "OTP";
        String msg = "OTP: " + sOtp;
        final String from = "a06204995@gmail.com";
        final String password = "Testemail123";

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        //session.setDebug(true);   
        Transport transport = session.getTransport();
        InternetAddress addressFrom = new InternetAddress(from);

        MimeMessage message = new MimeMessage(session);
        message.setSender(addressFrom);
        message.setSubject(subject);
        message.setContent(msg, "text/plain");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        transport.connect();
        Transport.send(message);
        transport.close();
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
        HttpSession session = request.getSession();
        String sEmail = request.getParameter("email");
        try {
            UserDAO dao = new UserDAO();
            if (dao.checkEmail(sEmail)) {
                try ( PrintWriter out = response.getWriter()) {
                    //generate OTP Number with 6 digits
                    String otp = new DecimalFormat("000000").format(new Random().nextInt(999999));
                    session.setAttribute("otp", otp);
                    eventSendEmail(request, response, otp, sEmail);
                    out.println("The OTP has been sent to your mail");
                } catch (MessagingException ex) {
                    Logger.getLogger(SendMailServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try ( PrintWriter out = response.getWriter()) {
                    out.println("This email is not used");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param a
     * @return b
     * @throws exception c
     * @author Khailq
     * @created 2020/10/29
     *
     */
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
