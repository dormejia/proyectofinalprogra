<%-- 
    Document   : index_trab
    Created on : 17/10/2023, 21:16:24
    Author     : Renato
--%>
<%-- --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.clsproductos" %>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel"%>
 
<!DOCTYPE html>
<html>
    <head>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PRODUCTOS</title>
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
        
        
        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_producto">NUEVO</button>
        
        <div class ="container">
             <!-- Modal -->
  <div class="modal fade" id="modal_producto" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">FOMULARIO DE PRODUCTOS</h4>
        </div>
        <div class="modal-body">
            
            
            <form action="sr_producto" method="get" class="form-group" enctype="multipart/form-data">
                 <label for ="lbl_id">ID:</label>
                <input type="text" size=40 style="width:300px"readonly  name="txt_id" id="txt_id" readonly value="0">
                
                <label for ="lbl_codigo">PRODUCTO:</label>
                <input type="text" name="txt_producto" id="txt_producto" placeholder="PRODUCTO">
                
                <label for ="lbl_marca">MARCA:</label>
               <select name="drop_sangre"  id="drop_sangre" class="from-control">
                   <%
                   clsproductos producto = new clsproductos();
                   HashMap<String,String> drop = producto.tipomarca();
                   for(String i:drop.keySet()){
                       out.println("<option value='"+i+"'>"+drop.get(i)+"</option>");
                       }
                   %>
                </select>
                
                <label for ="lbl_descrip">DESCRIPCION:</label>
                <input type ="text" name="txt_desc" id="txt_desc" name ="txt_desc"placeholder="PARA QUE SIRVE O QUE HACE">
                 <BR>
                
                <label for ="lbl_codigo">INGRESE UNA IMAGEN:</label>
                <input type="file" name="txt_imagen" id="txt_imagen">
                <BR>
                <label for ="lbl_codigo">PRECIO COSTO:</label>
               <input  type="number" name="txt_preci_costo" id="txt_preci_costo"placeholder="00.00" step="0.01" min="0.01" max="999999.99">
                
                <label for ="lbl_codigo">PRECIO VENTA:</label>
                <input type="number" name="txt_preci_venta" id="txt_preci_venta"placeholder="00.00" step="0.01" min="0.01" max="999999.99">
                
                <label for ="lbl_codigo">EXISTENCIA:</label>
                <input type="number" name="txt_cantidad" id="txt_cantidad" placeholder="SOLO NUMEROS ENTEROS">
                <br>
                <button  class="btn btn-primary"  name="btn_agregar" value ="agregar" id="btn_agregar">AGREGAR</button>
                <button  class="btn btn-warning"  name="btn_modificar" value ="modificar" id="btn_modificar">MODIFICAR</button>
                <button  class="btn btn-danger" name="btn_eliminar" value ="eliminar" id="btn_eliminar" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >ELIMINAR PRODUCTO</button>
            
            </form>
          <button type="button" class="btn btn-default" data-dismiss="modal">SALIR</button>
    </div>
   </div>         
  </div>          
  </div>         
           
           
            
  <table class="table">
  <thead>
    <tr>
     
      <th scope="col">PRODUCTO</th>
       <th scope="col">DESCRIPCION</th>
      <th scope="col">MARCA</th>
      <th scope="col">IMAGEN</th>
      <th scope="col">COSTO</th>
      <th scope="col">VENTA</th>
      <th scope="col">EXISTENCIA</th>
      <th scope="col">FECHA DE INGRESO</th>
    </tr>
  </thead>
  <tbody id="tbl_productos">
   <%
       
   clsproductos producto1= new clsproductos();
   DefaultTableModel tabla = new DefaultTableModel();
   
    tabla = producto1.leer();
    for(int t =0; t<tabla.getRowCount();t++){
       out.println("<tr data-id="+tabla.getValueAt(t,0)+" data-id_m="+tabla.getValueAt(t,9)+">");
       out.println("<td>"+tabla.getValueAt(t,1)+"</td>");
       out.println("<td>"+tabla.getValueAt(t,2)+"</td>");
       out.println("<td>"+tabla.getValueAt(t,3)+"</td>");
       out.println("<td> <img src='"+tabla.getValueAt(t,3)+"'width='50px' height='50px' alt=''></td>");
       out.println("<td>"+tabla.getValueAt(t,5)+"</td>");
       out.println("<td>"+tabla.getValueAt(t,6)+"</td>");
       out.println("<td>"+tabla.getValueAt(t,7)+"</td>");
       out.println("<td>"+tabla.getValueAt(t,8)+"</td>");
       
       out.println("</tr>");
       }
     
   %>
  
  </tbody>
</table>
   <img src="C:\Users\Renato\Desktop\d\revision2-main\proyecto_final_prograII\IMG\bmw-m1.webp"width="50px" height="50px" alt='no se encotro imagen'>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
       
   <script type="text/javascript">
   $('#tbl_productos').on('click','tr td',function(evt){
      var target,id,id_m,producto,descripcion,imagen,costo,venta,existencia;
      target = $(event.target);
      id = target.parent().data('id');
      id_m = target.parent().data('id_m');
      
      producto = target.parents("tr").find("td").eq(0).html();
       descripcion = target.parent("tr").find("td").eq(1).html();
       
       //imagen =  target.parents("tr").find("td").eq(3).html();      
       costo = target.parents("tr").find("td").eq(4).html();
       venta = target.parents("tr").find("td").eq(5).html();
       existencia = target.parents("tr").find("td").eq(6).html();
      // asignar a los campos 
      
      $("#txt_id").val(id);
      $("#txt_producto").val(producto);
      $("#txt_desc").val(descripcion);
      $("#txt_imagen").val(imagen);
      $("#txt_preci_costo").val(costo);
      $("#txt_preci_venta").val(venta);
      $("#txt_cantidad").val(existencia);
      $("#drop_sangre").val(id_m);
      $("#modal_producto").modal('show');
   });
   </script>          
    </body>
</html>
