<%-- 
    Document   : createEstudiante
    Created on : 18-11-2025, 19:11:11
    Author     : benja
--%>

<%@page import="dto.CursoDTO"%>
<%@page import="dto.AlumnoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Crear Estudiante</title>
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <h1>Agregar Alumno</h1>

                <script type="text/javascript">
                    <%
                        
                        String mensaje = (String) session.getAttribute("alertaMensaje");

                        if (mensaje != null && !mensaje.isEmpty()) {
                    %>
                    alert('<%= mensaje%>');
                    <%
                            
                            session.removeAttribute("alertaMensaje");
                        }
                    %>
                </script>
                <form action="ControladorAsistencia">
                    Rut:
                    <br>
                    <input class="form-control" type="text" name="txtRut">
                    <br>
                    Nombre:
                    <br>
                    <input class="form-control" type="text" name="txtNom">
                    <br>
                    Apellido Paterno:
                    <br>
                    <input class="form-control" type="text" name="txtApepa">
                    <br>
                    <br>
                    Apellido Materno:
                    <br>
                    <input class="form-control" type="text" name="txtApema">
                    <br>
                    <br>
                    Dirección:
                    <br>
                    <input class="form-control" type="text" name="txtDir">
                    <br>
                    <label>Curso Asignado:</label>
                    <br>
                    <%
                        // Recuperamos las variables que envió el controlador
                        String idCurso = String.valueOf(request.getAttribute("idCursoPreseleccionado"));
                        String nombreCurso = (String) request.getAttribute("nombreCursoMostrar");
                    %>

                    <input class="form-control" type="text" value="<%= nombreCurso%>" readonly>

                    <input type="hidden" name="cmbCur" value="<%= idCurso%>">
                    <br>
                    <br>
                    <br>
                    <input class="btn btn-primary" type="submit" name="accion" value="AgregarEst">
                    <a href="ControladorAsistencia?accion=listarEst">Regresar</a>
                </form>
            </div>

        </div>
    </body>
</html>
