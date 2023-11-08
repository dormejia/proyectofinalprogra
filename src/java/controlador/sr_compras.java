/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.compras;

/**
 *
 * @author Renato
 */
public class sr_compras extends HttpServlet {

    compras compra ;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_compras</title>");            
            out.println("</head>");
            out.println("<body>");
            compra = new compras();
            compra.setNo_orden(Integer.parseInt(request.getParameter("txtnorden")));
            compra.setIdprovedor(Integer.parseInt(request.getParameter("txt_pro")));
            compra.setFecha_orden(request.getParameter("txtForden"));
            compra.setIdproducto(Integer.parseInt(request.getParameter("txt_producto")));
            compra.setCantidad(Integer.parseInt(request.getParameter("txt_cantidad")));
            compra.setPrecio_costo(Double.parseDouble(request.getParameter("txt_preci_costo")));
            
             if("agregar4".equals(request.getParameter("btn_agregar4"))){
                 
           if( compra.agregarcompra()>0 && compra.agregarcompradetalle()>0 && compra.modificar()>0){
               
            out.println("<a href='idx_compras.jsp'>REGRESAR</a>");
           
           }else{
            out.println("<script>alert ('no se agrego nada')</script>");
            out.println("<a href='idx_compras.jsp'>REGRESAR</a>");
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
