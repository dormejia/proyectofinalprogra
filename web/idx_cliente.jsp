

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Clientes" %>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import ="modelo.Clientes" %>
<%@page import ="javax.swing.table.DefaultTableModel" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>lista de clientes</title>
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
        
         <h1>Listado de Clientes</h1>
               <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_clientes" onclick="limpiar()">REGISTRARSE</button>
        <div class="container">
        <div class="modal fade" id="modal_clientes" role="dialog">
            <div class="modal-dialog">
        
        <div class="modal-content">
        <div class="modal-body">
            
            <form action="sr_cliente" method="post" class="form-group">
                <label for="lbl_idcliente"><b>IDCliente</b></label>
                <input type="text" name="txt_idcliente" id="txt_idcliente" class="form-control" readonly>
                <label for="lbl_nombres"><b>Nombres:</b></label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Ejemplo: Nombre1 Nombre 2" required>
                <label for="lbl_apellidos"><b>Apellidos:</b></label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Ejemplo: Apellido 1 Apellido 2" required>
                <label for="lbl_nit"><b>Nit</b></label>
                <input type="text" name="txt_nit" id="txt_nit" class="form-control" placeholder="Ejemplo: 4354452-6" required>
                 <select name="txtgenero"  id="txtgenero" class="from-control">
                   <%
                   Clientes producto = new Clientes();
                   HashMap<String,String> drop1 = producto.genero();
                   for(String i:drop1.keySet()){
                       out.println("<option value='"+i+"'>"+drop1.get(i)+"</option>");
                       }
                   %>
                </select>
                <label for="lbl_telefono"><b>Telefono:</b></label>
                <input type="text" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ejemplo: 46758987" required>
                <label for="lbl_correo"><b>Correo Electronico:</b></label>
                <input type="text" name="txt_correo" id="txt_correo" class="form-control" placeholder="Ejemplo: example@gmail.com" required>
                <label for="lbl_fn"><b>Fecha Ingreso:</b></label>
                <input type="date" name="txt_fn" id="fechaInput" class="form-control" required>
                <br>
                
                 <br>
                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-outline-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-outline-success">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-outline-danger" onclick="javascript:if(!confirm('Desea Eliminar?'))return false">Eliminar</button>
                <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Cerrar</button>
                
            </form>
                </div>
              </div>

            </div>
        </div>      
              </div>
        
         <table class="table table-dark table-hover" >
                    <thead>
                      <tr>
                        <th>Nombres</th>
                        <th>Apellidos</th>
                        <th>NIT</th>
                        <th>Genero</th>
                        <th>Telefono</th>
                        <th>Correo Electronico</th>
                        <th>Fecha Ingreso</th>
                      </tr>
                    </thead>
                    <tbody id="tbl_clientes">
                        <%
                            Clientes clientes = new Clientes();
                            DefaultTableModel tabla = new DefaultTableModel();
                            tabla = clientes.leer();
                            for(int j=0;j<tabla.getRowCount();j++){
                                out.println("<tr data-id="+ tabla.getValueAt(j,0)+">");
                                    out.println("<td>"+ tabla.getValueAt(j,1)+"</td>");
                                    out.println("<td>"+ tabla.getValueAt(j,2)+"</td>");
                                    out.println("<td>"+ tabla.getValueAt(j,3)+"</td>");
                                    out.println("<td>"+ tabla.getValueAt(j,4)+"</td>");
                                    out.println("<td>"+ tabla.getValueAt(j,5)+"</td>");
                                    out.println("<td>"+ tabla.getValueAt(j,6)+"</td>");
                                    out.println("<td>"+ tabla.getValueAt(j,7)+"</td>");
                                out.println("</tr>");
                            }
                        %>   
                        
                    </tbody>
                  </table>
        
         <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
       
         <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
        <script type="text/javascript">
            function limpiar(){
              $("#txt_idcliente").val(0);
              $("#txt_nombres").val('');
              $("#txt_apellidos").val('');
              $("#txt_nit").val('');
              $("#txt_genero").val('');
              $("#txt_telefono").val('');
              $("#txt_correo").val('');
              $("#txt_fn").val('');
            }
            
            $('#tbl_clientes').on('click','tr td',function(evt){
              var target,id,nombres,apellidos,nit,genero,telefono,correo,ingreso;
              target = $(event.target);
              id = target.parent().data('id');
              nombres = target.parent("tr").find("td").eq(0).html();
              apellidos = target.parent("tr").find("td").eq(1).html();
              nit = target.parent("tr").find("td").eq(2).html();
              genero = target.parent("tr").find("td").eq(3).html();
              telefono = target.parent("tr").find("td").eq(4).html();
              correo = target.parent("tr").find("td").eq(5).html();
              ingreso = target.parent("tr").find("td").eq(6).html();
              var fechaOriginal = ingreso.toString();
              var partesFechaHora = fechaOriginal.split(" ");
              var fechaSinHora = partesFechaHora[0];
              var partesFecha = fechaSinHora.split("-");
              var fechaISO = partesFecha[0] + "-" + partesFecha[1] + "-" + partesFecha[2];
              $("#fechaInput").val(fechaISO);
              $("#txt_idcliente").val(id);
              $("#txt_nombres").val(nombres);
              $("#txt_apellidos").val(apellidos);
              $("#txt_nit").val(nit);
              $("#txt_genero").val(genero);
              $("#txt_telefono").val(telefono);
              $("#txt_correo").val(correo);
              $("#modal_clientes").modal('show');
          });
        </script>
    
        
    </body>
</html>
