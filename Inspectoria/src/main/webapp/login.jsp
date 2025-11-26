<%-- 
    Document   : login
    Created on : 12-11-2025, 08:54:44
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
                  
        <script type="text/javascript">
            <% 
                String mensaje = (String) request.getAttribute("alertaMensaje");
                if (mensaje != null && !mensaje.isEmpty())
                {
            %>
                    alert('<%= mensaje %>');
            <% 
                } 
            %>
        </script>
            <h1>Login</h1>
            <form action="ControladorLogin" method="POST">
                <label>Usuario</label>
                <input class="form-control" type="text" name="user"><br>

                <label>Contraseña</label>
                <input class="form-control" type="password" name="password"><br>

                <input class="btn btn-primary" type="submit" name="accion" value="Verificar">
            </form>

        </div>    
    </body>
</html>
