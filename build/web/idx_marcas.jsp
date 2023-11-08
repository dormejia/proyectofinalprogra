<%@page import="modelo.Marcas" %>
<%@page import="java.sql.ResultSet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>marcas</title>
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
     <h1>Marcas</h1>
     <br>
    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_marca" onclick="limpiar()">Nueva Marca</button>
    <div class="container">
        <div class="modal fade" id="modal_marca" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <form action="sr_marca1" method="post" class="form-group">
                            <label for="lbl_idmarca"><b>ID de Marca:</b></label>
                            <input type="text" name="txt_idmarca" id="txt_idmarca" class="form-control" value="0" readonly>
                            <label for="lbl_marca"><b>Marca:</b></label>
                            <input type="text" name="txt_marca" id="txt_marca" class="form-control" placeholder="Ejemplo: Marca" required>
                            <br>
                            <button name="btn_agregar_marca" id="btn_agregar_marca" value="agregar" class="btn btn-primary">Agregar Marca</button>
                            <button name="btn_modificar_marca" id="btn_modificar_marca" value="modificar" class="btn btn-success">Modificar Marca</button>
                            <button name="btn_eliminar_marca" id="btn_eliminar_marca" value="eliminar" class="btn btn-outline-warning" onclick="javascript:if(!confirm('¿Desea eliminar esta Marca?')) return false">Eliminar Marca</button>
                            <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cerrar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th>Marca</th>
                </tr>
            </thead>
            <tbody id="tbl_marcas">
               <%
       
  Marcas marca= new Marcas();
   DefaultTableModel tabla = new DefaultTableModel();
    tabla = marca.leer();
    for(int t =0; t<tabla.getRowCount();t++){
       out.println("<tr data-id="+tabla.getValueAt(t,0)+">");
       out.println("<td>"+tabla.getValueAt(t,1)+"</td>");
out.println("</tr>");
       }
   %>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
    <script type="text/javascript">
        function limpiar() {
            $("#txt_idmarca").val(0);
            $("#txt_marca").val('');
        }

        $('#tbl_marcas').on('click', 'tr', function (evt) {
            var target,idmarca,marca;
            var target = $(event.target);
            var idmarca = target.parent().data('id');
            var marca = target.parent("tr").find("td").eq(0).html();
            $("#txt_idmarca").val(idmarca);
            $("#txt_marca").val(marca);
            $("#modal_marca").modal('show');
        });
    </script>
</body>
</html>
