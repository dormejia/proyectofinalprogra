<%-- 
    Document   : login_trabajador
    Created on : 17/10/2023, 20:55:12
    Author     : Renato
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Puesto" %>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel"%>
<!DOCTYPE html>
<html>
    <head>
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOG TRABAJADOR</title>
    </head>
    <body>
          <%
       //  HttpSession sesion = request.getSession();
       //  sesion.invalidate();
    %>
         <h1>LOGIN TRABAJADOR</h1>
  <form action="sr_logintrabajador" method="POST" class="form-group" >
  <div class="form-group">
    <label for="exampleInputEmail1">Usuario</label>
    <input type="email" size=40 style="width:300px" class="form-control" name="txt_usuario"  id="txt_usuario" aria-describedby="emailHelp" placeholder="Usuario de Plataforma">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Contraseña</label>
    <input type="password" size=40 style="width:300px" class="form-control" name="txt_pass"  id="txt_pass" placeholder="Contraseña de Usuario" >
  </div>
            <br>
             <button name="btn_login1" id="btn_login1" value="login1" class="btn btn-outline-success">INGRESAR</button>
  
</form>
    </body>
</html>
