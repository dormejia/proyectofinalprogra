/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;
import modelo.clsproveedor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Renato
 */
public class sr_proveedor extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
              out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_producto</title>");            
            out.println("</head>");
            out.println("<body>");
           clsproveedor proveedor0 = new clsproveedor(); 
           // obj.setCodigo(txtcodi.getText());
            proveedor0.setIdproveedor(Integer.parseInt(request.getParameter("txt_idprov")));
            proveedor0.setProveedro(request.getParameter("txt_nomprov"));
           proveedor0.setNit(request.getParameter("txt_nitprov"));
           proveedor0.setDireccion(request.getParameter("txt_direprov"));
           proveedor0.setTelefono(request.getParameter("txt_telprov"));
           
           //boton agregar
           if("agregar".equals(request.getParameter("btn_agregar"))){
           if( proveedor0.agregar()>0){
           out.println("<script>alert ('se ingresaron los datos correcatmente')</script>");
           
           response.sendRedirect("idx_proveedores.jsp");
           }else{
            out.println("<script>alert ('no se agrego nada')</script>");
           }
           }
           //actualizar
            if("modificar".equals(request.getParameter("btn_modificar"))){
           if( proveedor0.modificar()>0){
           out.println("<script>alert ('se actualizaron los datos correcatmente')</script>");
          response.sendRedirect("idx_proveedores.jsp");
           }else{
            out.println("<script>alert ('no se agrego nada')</script>");
           }
           }
             //eliminar
            if("eliminar".equals(request.getParameter("btn_eliminar"))){
           if( proveedor0.eliminar()==0){
           out.println("<script>alert ('hay un error para eliminar')</script>");
          
           //response.sendRedirect("idx_trab.jsp");redireciona la pagina
           }else{
            out.println("<script>alert ('se eliminaron los registros')</script>");
            response.sendRedirect("idx_proveedores.jsp");
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
