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
import modelo.ventas;

/**
 *
 * @author Renato
 */
public class sr_venta extends HttpServlet {

  
  ventas venta;  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_venta</title>");            
            out.println("</head>");
            out.println("<body>");
                venta= new ventas();  
           venta.setNofactura(Integer.parseInt(request.getParameter("txtNfactura")));
           venta.setSerie( request.getParameter("txt_serie"));
           venta.setFecha_factura(request.getParameter("txtfechfac"));
           venta.setNit(request.getParameter("txt_nit"));
           venta.setUser(request.getParameter("txt_empleado"));
           venta.setIdproducto(Integer.parseInt(request.getParameter("txt_producto")));
           venta.setCantidad(Integer.parseInt(request.getParameter("txt_cantidad")));
           
            
            if("agregar0".equals(request.getParameter("btn_agregar0"))){
                 
           if( venta.agregarventa()>0 && venta.agregarventadetalle()>0&&venta.modificar()>0){
           out.println("<script>alert ('se ingresaron los datos correcatmente')</script>");
           
           response.sendRedirect("idx_ventas.jsp");
           }else{
            out.println("<script>alert ('no se agrego nada')</script>");
            out.println("<a href='idx_ventas.jsp'>REGRESAR</a>");
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
