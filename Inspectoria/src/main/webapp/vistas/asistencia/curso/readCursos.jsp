<%-- 
    Document   : readCurso
    Created on : 23-11-2025, 18:32:53
    Author     : Benjamín
--%>

<%@page import="dto.CursoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Listado de Cursos</title>
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">Listado de Cursos</h1>

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

            <table class="table table-bordered table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th class="text-center">ID CURSO</th>
                        <th class="text-center">NOMBRE CURSO</th>
                        <th class="text-center">NIVEL</th>
                        <th class="text-center">AÑO ACADÉMICO</th>
                        <th class="text-center">ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList registros = (ArrayList) request.getAttribute("aRCursos");

                        if (registros != null && registros.size() != 0) {
                            for (Object registro : registros) {
                                CursoDTO c = (CursoDTO) registro;
                    %>
                    <tr>
                        <td class="text-center"><%= c.getIdCurso()%></td>
                        <td class="text-center"><%= c.getNombreCurso()%></td>
                        <td class="text-center"><%= c.getNivel()%></td>
                        <td class="text-center"><%= c.getAnioAcademico()%></td>
                        <td class="text-center">
                            <!-- Botón para ir a tomar asistencia de este curso -->
                            <a href="<%=request.getContextPath()%>/ControladorAsistencia?accion=listarEst&idCurso=<%= c.getIdCurso()%>" 
                               class="btn btn-success btn-sm">
                                Tomar Asistencia
                            </a>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="5" class="text-center">No hay cursos registrados.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <div class="mb-5">
                <a class="btn btn-secondary" href="index.jsp">Volver al Inicio</a>
                <a class="btn btn-success" href="ControladorAsistencia?accion=addCurso">Crear nuevo curso</a>
                <!-- Ajusta el href anterior según cómo estés navegando entre vistas -->
            </div>
        </div>
    </body>
</html>
