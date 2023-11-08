<%-- 
    Document   : idx_ventas
    Created on : 25/10/2023, 20:40:34
    Author     : Renato
--%>

<%@page import="modelo.clsproductos"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="java.util.HashMap"%>
<%@page import="modelo.Clientes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ventas</title>
    </head>
    
      <% 
       
      HttpSession sesion = request.getSession();
        if(sesion.getAttribute("txt_usuario") == null){
   response.sendRedirect("login_trabajador.jsp");
}else{
         
          }
        
          
       
      %>
        
      
 
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="idx_trab.jsp">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
          
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="idx_trab.jsp" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            PRODUCTO
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <li><a class="dropdown-item" href="idx_marcas.jsp">MARCA</a></li>
          </ul>
        </li>
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="idx_ventas.jsp" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            VENTAS
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <li><a class="dropdown-item" href="idx_cliente.jsp">CLIENTES</a></li>
              <li><a class="dropdown-item" href="idx_empledos.jsp">EMPLEADO</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="idx_puestos.jsp">PUESTOS</a></li>
          </ul>
        </li>
        
        
         <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="idx_compras.jsp" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            COMPRAS
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <li><a class="dropdown-item" href="idx_proveedores.jsp">PROVEEDORES</a></li>
          </ul>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="idx_reportes.jsp">REPORTES</a>
        </li>
        
        
      </ul>
    </div>
  </div>
                 <ul class="navbar-nav">
                 <li class="nav-item">
                         
                           <a class="nav-link" href="login_trabajador.jsp"> <button type="button" class="btn btn-primary">DESCONECTAR</button></a>
        </li>
             </ul>
</nav>
           <h1>ventas</h1>
           
         <!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
  NUEVA VENTA
</button>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">FOMULARIO DE VENTA</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form action="sr_venta" method="get" class="form-group" enctype="multipart/form-data">
               <label for ="lbl_id">ID:</label>
                <input type="text" name="txt_id" id="txt_id" readonly value="0">
               <br> 
                <br> 
                 <label for ="lbl_codigo">No.FACTURA:</label>
                  <input type="number" name="txtNfactura" id="txtNfactura" placeholder="##############">
                 <br>
                  <br> 
                <label for ="lbl_codigo">SERIE:</label>
                <select name="txt_serie" id="txt_serie" class="form-select" aria-label="Default select example" style="width:100px" >
                   <option value='a'>A</option>
                   <option value='b'>B</option>
                   <option value='c'>C</option>
                 </select> 
          
                 <br> <br> 
                 <label for ="lbl_codigo">FECHA FACTURA:</label>
                 <input type="date" name="txtfechfac" id="txtfechfac" placeholder="PRODUCTO">
          
                 <br> <br> 
                 <label for ="lbl_codigo">NIT DEL CLIENTE:</label>
                 <select name="txt_nit"  id="txt_nit" class="from-control">
                   <%
                   Clientes producto = new Clientes();
                   HashMap<String,String> drop = producto.tipomarca();
                   for(String j:drop.keySet()){
                       out.println("<option value='"+drop.get(j)+"' >"+drop.get(j)+"</option>");
                       }
                   %>
                </select>
                  <br> <br> 
                 <label for ="lbl_codigo">EMPLEADO: </label>
                 <input type="text" name="txt_empleado" readonly id="txt_empleado" value="<% out.print(session.getAttribute("txt_usuario")); %>">
                 <br> <br> 
                  <label for ="lbl_codigo">PRODUCTO:</label>
                  <select name="txt_producto"  id="txt_producto" class="from-control">
                   <%
                 clsproductos idproducto = new clsproductos();
                   HashMap<String,String> drop1 = idproducto.tipoproducto();
                   for(String k:drop1.keySet()){
                       out.println("<option value='"+k+"' >"+drop1.get(k)+"</option>");
                       }
                   %>
                </select>
                 <br> <br> 
                 <label for ="lbl_codigo">EXISTENCIA:</label>
                <input type="number" name="txt_cantidad" id="txt_cantidad" placeholder="SOLO NUMEROS ENTEROS">
                 <br> <br>
                 <button  class="btn btn-primary"  name="btn_agregar0" value ="agregar0" id="btn_agregar0">AGREGAR</button>
                <button  class="btn btn-warning"  name="btn_modificar" value ="modificar" id="btn_modificar">MODIFICAR</button>
                <button  class="btn btn-danger" name="btn_eliminar" value ="eliminar" id="btn_eliminar" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >ELIMINAR PRODUCTO</button>
            
          </form>
          
         
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
        
      </div>
    </div>
  </div>
</div>
           
           
           
           
           
           
           
           
           
           
    

