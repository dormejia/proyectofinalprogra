<%-- 
    Document   : idx_proveedores
    Created on : 20/10/2023, 18:35:26
    Author     : Renato
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.clsproveedor" %>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>proveedores</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
  
    </head>
    <body>
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
        <h1>PROVEEDORES</h1>
        
          <form action="sr_proveedor" method="post" class="form-group">
                <label for="lbl_idprov"><b>IDproveedor</b></label>
                <input type="text"size=40 style="width:500px" name="txt_idprov" id="txt_idprov" class="form-control" readonly value="0">
                
                <label for="lbl_nombres"><b>Nombres del Proveedor:</b></label>
                <input type="text" size=40 style="width:500px" name="txt_nomprov" id="txt_nomprov" class="form-control" placeholder="Ejemplo: IMPORTADORA LA UNION" required>
                
                <label for="lbl_apellidos"><b>Nit:</b></label>
                <input type="text" size=40 style="width:500px" name="txt_nitprov" id="txt_nitprov" class="form-control" placeholder="Ejemplo: 45487974-1" required>
                
                <label for="lbl_nit"><b>Dirección:</b></label>
                <input type="text" size=40 style="width:500px" name="txt_direprov" id="txt_direprov" class="form-control" placeholder="Ejemplo: GUATEMALA / ZONA 7 BOCA DEL MONTE" required>
                      
                <label for="lbl_telefono"><b>Telefono:</b></label>
                <input type="text" size=40 style="width:500px" name="txt_telprov" id="txt_telprov" class="form-control" placeholder="Ejemplo: 46758987" required>
                
                
                <br>
              
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-outline-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-outline-success">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-outline-danger" onclick="javascript:if(!confirm('Desea Eliminar?'))return false">Eliminar</button>
                
                
            </form>
        
          <table class="table" size=40 style="width:1000px">
  <thead>
    <tr>
     
      
       <th scope="col">PROVEEDOR</th>
      <th scope="col">NIT</th>
      <th scope="col">DIRECCION</th>
      <th scope="col">TELEFONO</th>
    </tr>
  </thead>
  <tbody id="tbl_productos">
   <%
       
   clsproveedor producto1= new clsproveedor();
   DefaultTableModel tabla = new DefaultTableModel();
   
    tabla = producto1.buscar();
    for(int t =0; t<tabla.getRowCount();t++){
       out.println("<tr data-id="+tabla.getValueAt(t,0)+">");
       out.println("<td>"+tabla.getValueAt(t,1)+"</td>");
       out.println("<td>"+tabla.getValueAt(t,2)+"</td>");
       out.println("<td>"+tabla.getValueAt(t,3)+"</td>");
        out.println("<td>"+tabla.getValueAt(t,4)+"</td>");
       out.println("</tr>");
       }
   %>
  
  </tbody>
</table>
        
         <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
         <script type="text/javascript">
   $('#tbl_productos').on('click','tr td',function(evt){
      var target,id,provedor,nit,direccion,tel;
      target = $(event.target);
      id = target.parent().data('id');
      provedor = target.parents("tr").find("td").eq(0).html();
       nit = target.parent("tr").find("td").eq(1).html();
        direccion = target.parent("tr").find("td").eq(2).html();
       tel =  target.parents("tr").find("td").eq(3).html();      
    

      // asignar a los campos 
      
      $("#txt_idprov").val(id);
      $("#txt_nomprov").val( provedor );
      $("#txt_nitprov").val( nit);
      $("#txt_direprov").val(direccion);
      $("#txt_telprov").val(tel);
     
    //  $("#modal_producto").modal('show');
   });
   </script>          
        
    </body>
</html>
