/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Empleado;

/**
 *
 * @author Renato
 */
public class sr_userempledo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Empleado user ;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
         out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_producto</title>");            
            out.println("</head>");
            out.println("<body>");
          user = new Empleado();
           user.setIdpuesto(Integer.parseInt(request.getParameter("txt_nomb")));
           user.setUser(request.getParameter("txt_usuario"));
           user.setPass(request.getParameter("txt_pass"));
           user.setRol1(Integer.parseInt(request.getParameter("ROLVENTA")));
           user.setRol2(Integer.parseInt(request.getParameter("ROLADMINI")));
         if("login2".equals(request.getParameter("btn_login2"))){
                if(user.agregarUsuario()>0){
              out.println("<script>alert ('se ingresaron los datos correcatmente')</script>");
               
             response.sendRedirect("idx_empledos.jsp");
                }else{
                    out.println("<script>alert ('no se pudo chavo')</script>");
                    out.println("<p>....</p>");
                 //   response.sendRedirect("idx_empledos.jsp");
                }
            }
         out.println("</body>");
            out.println("</html>");
         }
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
        
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
