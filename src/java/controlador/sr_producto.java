/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import modelo.clsproductos;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;




@MultipartConfig
public class sr_producto extends HttpServlet {

static final int CHUNK_SIZE=1024*4;

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
           clsproductos producto0 = new clsproductos(); 
           
          
          
            producto0.setId_producto(Integer.parseInt(request.getParameter("txt_id")));
            String ruta ="C:\\Users\\Renato\\Desktop\\d\\revision2-main\\proyecto_final_prograII\\IMG\\";
          String nimagen = request.getParameter("txt_imagen");
          
            String ring;
            ring=ruta+nimagen;
            producto0.setImagen(ring);
           producto0.setProducto(request.getParameter("txt_producto"));
           producto0.setId_marca (Integer.parseInt(request.getParameter("drop_sangre")));
           producto0.setDecripcion(request.getParameter("txt_desc"));
           producto0.setQcosto (Double.parseDouble(request.getParameter("txt_preci_costo")));
           producto0.setQventa (Double.parseDouble(request.getParameter("txt_preci_venta")));
           producto0.setExistencia(Integer.parseInt(request.getParameter("txt_cantidad")));
           //boton agregar
           if("agregar".equals(request.getParameter("btn_agregar"))){
              ///

               ///
           if( producto0.agregar()>0){
           out.println("<script>alert ('se ingresaron los datos correcatmente')</script>");
           
           response.sendRedirect("idx_trab.jsp");
           }else{
            out.println("<script>alert ('no se agrego nada')</script>");
           }
           }
           //actualizar
            if("modificar".equals(request.getParameter("btn_modificar"))){
           if( producto0.modificar()>0){
           out.println("<script>alert ('se actualizaron los datos correcatmente')</script>");
          
           response.sendRedirect("idx_trab.jsp");
           }else{
            out.println("<script>alert ('no se agrego nada')</script>");
           }
           }
             //eliminar
            if("eliminar".equals(request.getParameter("btn_eliminar"))){
           if( producto0.elininar()==0){
           out.println("<script>alert ('hay un error para eliminar')</script>");
          
           response.sendRedirect("idx_trab.jsp");
           }else{
            response.sendRedirect("idx_trab.jsp");
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
