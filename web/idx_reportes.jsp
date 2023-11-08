<%-- 
    Document   : idx_repostes
    Created on : 25/10/2023, 20:45:59
    Author     : Renato
--%>

<%@page import="modelo.compras"%>
<%@page import="modelo.ventas"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>reportes</title>
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
        
        
      
      
      
        <h1>Reportes</h1>
        
        <div class="container" style="text-align: center; width: 100%">
            
        </div>
        <div class="container" style="text-align: center; width: 100%">
            <div class="row" style="width: 100%">
             <!-- TABLA VENTAS -->
             <div class="col-1" style="width: 50%">
                 
 <table class="table"  >
  <thead>
      <tr>
      <th scope="col" class="table-dark">VENTAS</th>
    </tr>
    <tr>
      <th scope="col" class="table-secondary">SERIE</th>
      <th scope="col" class="table-secondary">NO.FACTURA</th>
      <th scope="col" class="table-secondary">CLIENTE</th>
      <th scope="col" class="table-secondary">FECHA</th>
      <th scope="col" class="table-secondary">TOTAL PAGADO</th>
    </tr>
  </thead>
  <tbody id="tbl_ventas">
    <%
       
   ventas informeventa= new ventas();
   DefaultTableModel tabla = new DefaultTableModel();
   
    tabla = informeventa.leer();
    for(int t =0; t<tabla.getRowCount();t++){
       out.println("<tr data-empleado="+tabla.getValueAt(t,4)+" data-producto="+tabla.getValueAt(t,5)+" data-cantidad="+tabla.getValueAt(t,6)+" data-prciou="+tabla.getValueAt(t,7)+" data-ftransaxion="+tabla.getValueAt(t,9)+">");
       out.println("<td class='table-danger'>"+tabla.getValueAt(t,1)+"</td>");
       out.println("<td>"+tabla.getValueAt(t,0)+"</td>");
       out.println("<td>"+tabla.getValueAt(t,3)+"</td>");
       out.println("<td>"+tabla.getValueAt(t,2)+"</td>");
       out.println("<td>"+tabla.getValueAt(t,8)+"</td>");
     
       
       out.println("</tr>");
       }
   %>
  </tbody>
</table>
             </div>
             <div class="col-1" style="width: 50%">
<table class="table"  >
  <thead>
      <tr>
      <th scope="col" class="table-secondary">COMPRAS</th>
    </tr>
    <tr class="table-primary">
      <th scope="col">NO. ORDEN</th>
      <th scope="col">FECHA DE ORDEN</th>
      <th scope="col">PROVEEDOR</th>
      <th scope="col">PRECIO</th>
    </tr>
  </thead>
  <tbody id="tbl_compras" >
     <%
       
   compras infocompra= new compras();
   DefaultTableModel tabla1 = new DefaultTableModel();
   
    tabla1 = infocompra.leer();
    for(int j =0; j<tabla1.getRowCount();j++){
       out.println("<tr class='table-info' data-producto="+tabla1.getValueAt(j,3)+" data-cantidad="+tabla1.getValueAt(j,4)+" data-fningreso="+tabla1.getValueAt(j,6)+">");
       out.println("<td>"+tabla1.getValueAt(j,0)+"</td>");
       out.println("<td>"+tabla1.getValueAt(j,1)+"</td>");
       out.println("<td>"+tabla1.getValueAt(j,2)+"</td>");
       out.println("<td>"+tabla1.getValueAt(j,5)+"</td>");
       out.println("</tr>");
       }
   %>
   
  </tbody>
</table>
             </div>
 
      
            
        </div>
        </div>
        

        <!-- Button trigger modal ventas -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalVentas" >
  Reporte de ventas
</button>

<!-- Modal -->
<div class="modal fade" id="modalVentas" tabindex="-2" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">REPORTE DE VENTAS</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        
          <label for ="lbl_codigo">No.FACTURA: </label>
          <input type="text" size=40 style="width:100px"readonly id="nfac" name="nfac" >
          <br><br>
          
          <label for ="lbl_codigo">SERIE: </label>
          <input type="text" size=40 style="width:30px"readonly id="serie" name="serie" >
          <br><br>
          
          <label for ="lbl_codigo">FECHA DE FACTURACION: </label>
          <input type="text" size=40 style="width:100px"readonly id="fnfac" name="fnfac" >
          <br><br>
          
          <label for ="lbl_codigo">CLIENTE: </label>
          <input type="text" size=40 style="width:100px"readonly id="clien" name="clien" >
          <br><br>
          
          <label for ="lbl_codigo">EMPLEADO: </label>
          <input type="text" size=40 style="width:100px"readonly id="empleado" name="empleado" >
          <br><br>
          
          <label for ="lbl_codigo">PRODUCTO: </label>
          <input type="text" size=40 style="width:100px"readonly id="producto" name="producto" >
          <br><br>
          
          <label for ="lbl_codigo">CANTIDAD: </label>
          <input type="text" size=40 style="width:30px"readonly id="cantidad" name="cantidad" >
          <br><br>
          
          <label for ="lbl_codigo">PRECIO: </label>
          <input type="text" size=40 style="width:100px"readonly id="precio" name="precio" >
          <br><br>
          
          <label for ="lbl_codigo">TOTAL A PAGAR: </label>
          <input type="text" size=40 style="width:100px"readonly id="pago1" name="pago1" >
          <br><br>
          
          <label for ="lbl_codigo">FEHCA Y HORA DE COMPRA: </label>
          <input type="text" size=40 style="width:100px"readonly id="fncompra" name="fncompra" >
          <br><br>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">SALIR</button>
      </div>
    </div>
  </div>
</div>

        <!-- Button trigger modal ventas -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalCompra">
  Reporte de compras
</button>

<!-- Modal Compras-->
<div class="modal fade" id="modalCompra" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">REPORTE DE COMPRAS</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
                  <label for ="lbl_codigo">No.ORDEN: </label>
          <input type="text" size=40 style="width:100px"readonly id="norden" name="norden" >
          <br><br>
          
          <label for ="lbl_codigo">PROVEEDOR: </label>
          <input type="text" size=40 style="width:100px"readonly id="prove" name="prove" >
          <br><br>
          
          <label for ="lbl_codigo">PRODUCTO: </label>
          <input type="text" size=40 style="width:100px"readonly id="pro" name="pro" >
          <br><br>
          
          <label for ="lbl_codigo">CANTIDAD: </label>
          <input type="text" size=40 style="width:100px"readonly id="cant" name="cant" >
          <br><br>
          
          <label for ="lbl_codigo">PRCIO COSTO: </label>
          <input type="text" size=40 style="width:100px"readonly id="pre" name="pre" >
          <br><br>
          
          <label for ="lbl_codigo">FECHA DE ORDEN: </label>
          <input type="text" size=40 style="width:100px"readonly id="fechorden" name="fechorden" >
          <br><br>
          
          <label for ="lbl_codigo">FECHA DE INGRESO: </label>
          <input type="text" size=40 style="width:100px"readonly id="cechingreso" name="cechingreso" >
          <br><br>
          
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

 <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
      
<script type="text/javascript">
   $('#tbl_ventas').on('click','tr td',function(evt){
      var target,nfactura,serie,fnfac,cliente,empleado,producto,cantidad,precio,preciototal,fntrx;
      target = $(event.target);
      
      nfactura = target.parents("tr").find("td").eq(1).html();
      serie =  target.parents("tr").find("td").eq(0).html(); 
      fnfac = target.parents("tr").find("td").eq(3).html();
      cliente = target.parents("tr").find("td").eq(2).html();
      preciototal = target.parents("tr").find("td").eq(4).html();
      empleado = target.parent().data('empleado');
      producto = target.parent().data('producto');
      cantidad = target.parent().data('cantidad');
      precio = target.parent().data('prciou');
     fntrx = target.parent().data('ftransaxion');
      // asignar a los campos 
      $("#nfac").val( nfactura);
      $("#serie").val(serie);
      $("#fnfac").val(fnfac);
      $("#clien").val(cliente);
      $("#empleado").val(empleado);
      $("#producto").val(producto);
      $("#cantidad").val(cantidad);
      $("#precio").val(precio);
      $("#pago1").val(preciototal);
      $("#fncompra").val(fntrx);
      
      $("#modalVentas").modal('show');
   });
   
   </script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
      
<script type="text/javascript">
   $('#tbl_compras').on('click','tr td',function(evt){
      var target1,o1,o2,o3,o4,o5,o6,o7;
      target1 = $(event.target);
      
      o1 = target1.parents("tr").find("td").eq(0).html();
      o2 =  target1.parents("tr").find("td").eq(1).html(); 
      o3 = target1.parents("tr").find("td").eq(2).html();
      o4 = target1.parents("tr").find("td").eq(3).html();
      o5 = target1.parent().data('producto');
      o6 = target1.parent().data('cantidad');
      o7 = target1.parent().data('fningreso');
     
      // asignar a los campos 
      $("#norden").val(o1);
      $("#prove").val(o3);
      $("#pro").val(o5);
      $("#cant").val(o6);
      $("#pre").val(o4);
      $("#fechorden").val(o2);
      $("#cechingreso").val(o7);
     
      $("#modalCompra").modal('show');
   });
   </script>




    </body>
</html>
