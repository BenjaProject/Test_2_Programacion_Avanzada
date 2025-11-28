<%-- 
    Document   : irAlCRUDinspectoria
    Created on : 23-11-2025, 19:29:04
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page hola <%=request.getContextPath()%></title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession(false);
            if (sesion == null || sesion.getAttribute("usuarioLogeado") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <br><br>
        <div class="container">

            <div class="container mt-5">
                <div class="jumbotron">
                    <h1 class="display-4">Sistema de Inspectoria</h1>
                    <p class="lead">Selecciona el módulo con el que quieres trabajar.</p>
                    <hr class="my-4">

                    <a class="btn btn-primary btn-lg mb-2" 
                       href="<%=request.getContextPath()%>/ControladorAsistencia?accion=menu"
                       style="margin-left:0px;">
                        Gestión de Inasistencia y Atrasos
                    </a>

                </div>
            </div>
        </div>
    </body>
</html>

