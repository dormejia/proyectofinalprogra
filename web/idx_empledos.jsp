<%-- 
    Document   : index
    Created on : 2/10/2023, 21:15:35
    Author     : Pablo
--%>
<%@page import="modelo.Clientes"%>
<%@page import="modelo.clsPuesto" %>
<%@page import="modelo.Empleado" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page import="java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleados</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
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
        
        <h1>Empleados</h1>
        
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal_empleado" onclick="limpiar()" >Nuevo</button>
          <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal_empleado1" onclick="limpiar()" >CREAR CREDENCIALES</button>
        
        <div class="container">
            
            <div class="modal" id="modal_empleado">
                <div class="modal-dialog">
                    <div class="modal-content">
      
                    <!-- Modal Header -->
                    <div class="modal-header">
                    <h4 class="modal-title">FORMULARIO EMPLEADO</h4>
                     <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
        
                    <!-- Modal body -->
                     <div class="modal-body">
                         
                        <form action="sr_empleado" method="post" class="form-group">
                        <label for="lbl_id"><b>ID:</b></label>
                        <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                        <label for="lbl_nombres"><b>Nombres:</b></label>
                        <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Ejemplo: Nombre 1 Nombre 2" required>
                        <label for="lbl_apellidos"><b>Apellidos:</b></label>
                        <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Ejemplo: Apellido 1 Apellido 2" required>
                        <label for="lbl_direccion"><b>Direccion:</b></label>
                        <input type="text" name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Ejemplo: #Casa Calle Zona Ciudad" required>
                        <label for="lbl_telefono"><b>Telefono:</b></label>
                        <input type="number" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ejemplo: 44562347" required>
                        
                        <label for="lbl_dpi"><b>DPI:</b></label>
                        <input type="text" name="txt_dpi" id="txt_dpi" class="form-control" placeholder="Ejemplo: 1234567891234" required>
                        
                        <label for="lbl_genero"><b>Genero:</b></label>
                         <select name="txt_genero"  id="txt_genero" class="from-control">
                   <%
                   Clientes producto = new Clientes();
                   HashMap<String,String> drop1 = producto.genero();
                   for(String j:drop1.keySet()){
                       out.println("<option value='"+j+"'>"+drop1.get(j)+"</option>");
                       }
                   %>
                </select>
                            
                        <label for="lbl_fn"><b>Fecha Nacimiento:</b></label>
                        <input type="date" name="txt_fn" id="txt_fn" class="form-control" required>
                        
                        <label for="lbl_fecha_inicio"><b>Fecha Inicio:</b></label>
                        <input type="date" name="txt_fecha_inicio" id="txt_fecha_inicio" class="form-control" required>
                        
                       
                        
                        <label for="lbl_puesto"><b>Puesto:</b></label>
                        <select name="drop_puesto" id="drop_puesto" class="form-control"> 
                            
                            <%
                                clsPuesto puesto = new clsPuesto();
                                HashMap<String, String> drop = puesto.drop_sangre();
                                for(String i: drop.keySet()){
                                    out.println("<option value='"+ i +"'>"+ drop.get(i)+" </option>");
                                    System.out.println("EL i es: "+ drop.get(i));
                                }
                            %>
                        </select>
                            <br>
                            <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-primary btn-lg">Agregar</button>
                            <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-success btn-lg">Modificar</button>
                            <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-warning btn-lg" onclick="javascript:if(!confirm('¿DESEA ELIMINAR?'))return false" >Eliminar</button>
                        </form>
                    </div>
                     
                    <!-- Modal footer -->
                    <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                    </div>
                </div>
            </div>
      
            <div class="modal" id="modal_empleado1">
                <div class="modal-dialog">
                    <div class="modal-content">
      
                    <!-- Modal Header -->
                    <div class="modal-header">
                    <h4 class="modal-title">CREDENCIALES</h4>
                     <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
        
                    <!-- Modal body -->
                     <div class="modal-body">
                          <form action="sr_userempledo" method="post" class="form-group" >
  <div class="form-group">
      <input type="text" name="txt_nomb" id="txt_nomb" class="form-control" value="0" readonly>
                        
    <label for="exampleInputEmail1">Usuario</label>
    <input type="email" size=40 style="width:300px" class="form-control" name="txt_usuario"  id="txt_usuario" aria-describedby="emailHelp" placeholder="Usuario de Plataforma">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Contraseña</label>
    <input type="password" size=40 style="width:300px" class="form-control" name="txt_pass"  id="txt_pass" placeholder="Contraseña de Usuario" >
    <br><!-- roles -->
     <label for ="lbl_codigo">VENDEDOR</label>
                <input type="number" name="ROLVENTA" id="ROLVENTA" value="0" min="0" max="1">
                <br><!-- roles -->
      <label for ="lbl_codigo">ADMINISTRADOR</label>
               <input type="number" name="ROLADMINI" id="ROLADMIN" value="0" min="0" max="1">        
  </div>
            <br>
             <button name="btn_login2" id="btn_login2" value="login2" class="btn btn-outline-success">CREAR USUARIO</button>
  
</form>
                      <table class="table table-striped">
    <thead>
      <tr>
        <th>EMPLEADO</th>
         <th>PUESTO</th>
      </tr>
    </thead>
    <tbody id="tbl_si_usuario">
        <% 
            Empleado empleado1 = new Empleado();
            DefaultTableModel tabla1 = new DefaultTableModel();
            tabla1 = empleado1.leersitieneusuario();
            for(int t=0;t<tabla1.getRowCount();t++){
                //System.out.println("TAMANIO DE TABLA: "+tabla.getRowCount());
                    out.println("<tr data-id="+tabla1.getValueAt(t,1)+" data-id_m="+tabla1.getValueAt(t,2)+">"); 
                     out.println("<td>"+tabla1.getValueAt(t,0)+"</td>");
                      out.println("<td>"+tabla1.getValueAt(t,3)+"</td>");
                    
                out.println("</tr>");
            }
        %>     
    </tbody>
  </table>
                       
                    </div>
                     
                    <!-- Modal footer -->
                    <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                    </div>
                </div>
            </div>
            
                
    <table class="table table-striped">
    <thead>
      <tr>
        <th>nombres</th>
        <th>apellidos</th>
        <th>direccion</th>
        <th>telefono</th>
        <th>dpi</th>
        <th>genero</th>
        <th>nacimiento</th>
        <th>puesto</th>
        <th>fecha inicio</th>
        <th>fecha ingreso</th>
      </tr>
    </thead>
    <tbody id="tbl_empleados">
        <% 
            Empleado empleado = new Empleado();
            DefaultTableModel tabla = new DefaultTableModel();
            tabla = empleado.leer();
            for(int t=0;t<tabla.getRowCount();t++){
                //System.out.println("TAMANIO DE TABLA: "+tabla.getRowCount());
                    out.println("<tr data-id="+tabla.getValueAt(t,0)+" data-id_p="+tabla.getValueAt(t,11)+" data-id_g="+tabla.getValueAt(t,12)+">");
                    out.println("<td>"+tabla.getValueAt(t, 1)+"</td>");
                    out.println("<td>"+tabla.getValueAt(t, 2)+"</td>");
                    out.println("<td>"+tabla.getValueAt(t, 3)+"</td>");
                    out.println("<td>"+tabla.getValueAt(t, 4)+"</td>");
                    out.println("<td>"+tabla.getValueAt(t, 5)+"</td>");
                    out.println("<td>"+tabla.getValueAt(t, 6)+"</td>");
                    out.println("<td>"+tabla.getValueAt(t, 7)+"</td>");
                    out.println("<td>"+tabla.getValueAt(t, 8)+"</td>");
                    out.println("<td>"+tabla.getValueAt(t, 9)+"</td>");
                    out.println("<td>"+tabla.getValueAt(t, 10)+"</td>");
                    
                out.println("</tr>");
            }
        %>     
    </tbody>
  </table>
        
        </div>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
        <script type="text/javascript">
            
            function limpiar(){
                $("#txt_id").val(0);
                $("#txt_nombres").val('');
                $("#txt_apellidos").val('');
                $("#txt_direccion").val('');
                $("#txt_telefono").val('');
                $("#txt_dpi").val('');
                $("#txt_genero").val('');
                $("#txt_fn").val('');
                $("#drop_puesto").val(1);
                $("#txt_fecha_inicio").val('');
                $("#modal_empleado").val('');

                
            }
            
            $('#tbl_empleados').on('click','tr td',function(evt){
                var target,id,id_p,id_g,nombres,apellidos,direccion,telefono,dpi, genero, nacimiento, fecha_inicio, fecha_ingreso;
                target = $(event.target);
                id = target.parent().data('id');
                id_p = target.parent().data('id_p');
                id_g = target.parent().data('id_g');
                nombres = target.parent("tr").find("td").eq(0).html();
                apellidos = target.parent("tr").find("td").eq(1).html();
                direccion = target.parent("tr").find("td").eq(2).html();
                telefono = target.parent("tr").find("td").eq(3).html();
                dpi = target.parent("tr").find("td").eq(4).html();
                genero = target.parent("tr").find("td").eq(5).html();
                nacimiento = target.parent("tr").find("td").eq(6).html();
                fecha_inicio = target.parent("tr").find("td").eq(8).html();
                fecha_ingreso = target.parent("tr").find("td").eq(9).html();
                
                $("#txt_id").val(id);
                $("#txt_nombres").val(nombres);
                $("#txt_apellidos").val(apellidos);
                $("#txt_direccion").val(direccion);
                $("#txt_telefono").val(telefono);
                $("#txt_dpi").val(dpi);
                $("#txt_genero").val(id_g);
                $("#txt_fn").val(nacimiento);
                $("#drop_puesto").val(id_p);
                $("#txt_fecha_inicio").val(fecha_inicio);
                $("#txt_fecha_ingreso").val(fecha_ingreso);
                $("#modal_empleado").modal('show');
            });
            
        </script>
        <script type="text/javascript">
            
            function limpiar1(){
                $("#txt_nomb").val(0);
                $("#txt_usuario").val('');
                $("#txt_pass").val('');
            }
            
            $('#tbl_si_usuario').on('click','tr td',function(evt){
                var target,id;
                target = $(event.target);
                id = target.parent().data('id_m');
             
                
                $("#txt_nomb").val(id);
               
                
            });
            
        </script>
    </body>
</html>
