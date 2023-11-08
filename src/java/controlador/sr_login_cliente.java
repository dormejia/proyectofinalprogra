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
import javax.servlet.http.HttpSession;
import modelo.Clientes;

/**
 *
 * @author Renato
 */
public class sr_login_cliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     Clientes clientes;
                
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");  
      try {
            request.getRequestDispatcher("idx_cliente.jsp.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            System.out.println("Login Error: " + e.getMessage());
            e.printStackTrace();
        }
       }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
         String action=(request.getPathInfo()!=null?request.getPathInfo():"");
        HttpSession sesion = request.getSession();
        if(action.equals("/out")){
            sesion.invalidate();
            response.sendRedirect("idx_cliente.jsp");
        }else{
           
        
    }
       

        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{ 
     
            clientes = new Clientes();
           clientes.setCorreo(request.getParameter("txt_usuario"));
          clientes.setPass(request.getParameter("txt_pass"));
           HttpSession sesion = request.getSession();
         if("login1".equals(request.getParameter("btn_login1"))){
                if(clientes.login()<0){
                sesion.setAttribute("txt_usuario", clientes.getCorreo());
                request.getSession().setAttribute("txt_usuario",clientes.getCorreo());
                response.sendRedirect("idx_cliente.jsp");
                }else{
                    
                }
            }
        
        }catch(Exception ex){
        
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
