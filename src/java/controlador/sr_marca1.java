package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Marcas;

public class sr_marca1 extends HttpServlet {

    Marcas marca;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            marca = new Marcas();
            marca.setIdmarca(Integer.parseInt(request.getParameter("txt_idmarca")));
            marca.setMarca(request.getParameter("txt_marca"));

            if ("agregar".equals(request.getParameter("btn_agregar_marca"))) {
                if (marca.agregar() > 0) {
                    response.sendRedirect("idx_marcas.jsp");
                } else {
                    out.print("<h1>Error al agregar la marca.</h1>");
                    out.print("<a href='idx_marcas.jsp'>Regresar</a>");
                }
            }
            if ("modificar".equals(request.getParameter("btn_modificar_marca"))) {
                if (marca.modificar() > 0) {
                    response.sendRedirect("idx_marcas.jsp");
                } else {
                    out.print("<h1>No se pudo modificar la marca.</h1>");
                    out.print("<a href='idx_marcas.jsp'>Regresar</a>");
                }
            }
            if ("eliminar".equals(request.getParameter("btn_eliminar_marca"))) {
                if (marca.eliminar() > 0) {
                    response.sendRedirect("idx_marcas.jsp");
                } else {
                    out.print("<h1>No se pudo eliminar la marca.</h1>");
                    out.print("<a href='idx_marcas.jsp'>Regresar</a>");
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
