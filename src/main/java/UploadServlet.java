/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author khai
 */
@WebServlet(urlPatterns = {"/UploadServlet"})
@MultipartConfig
public class UploadServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            Part part = request.getPart("file");
            HttpSession session = request.getSession();
            String fileName = part.getSubmittedFileName();
            
            String path = getServletContext().getRealPath("/"+"avatar"
                    + File.separator + fileName);
            
            InputStream is = part.getInputStream();
            
            boolean succs = uploadFile(is, path);
            if(succs) {
                out.println(path);
            } else {
                out.println("error");
            }
            
        }
        
    }

    /**
     * handle upload image
     * @author Khailq
     * @param is
     * @param path
     * @return true|false
     * @created 2020/11/16
     *
     */
    public boolean uploadFile(InputStream is, String path) {
        boolean bOut = false;
        try {
            byte[] byt = new byte[is.available()];
            is.read();
            FileOutputStream fops = new FileOutputStream(path);
            fops.write(byt);
            fops.flush();
            fops.close();
            bOut = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return bOut;
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
