<%-- 
    Document   : readInasistencias
    Created on : 25-11-2025, 14:06:50
    Author     : usuario
--%>

<%@page import="dto.InasistenciaDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Listado de Atrasos</title>
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">Listado de Inasistencias</h1>

            <%
               
                Integer idCursoObj = (Integer) request.getAttribute("idCurso");
                int idCurso = (idCursoObj != null) ? idCursoObj : 0;
            %>

            <div class="mb-4 text-center">
                <button class="btn btn-info" type="button"
                        onclick="location.href = 'ControladorAsistencia?accion=ListarCurso'">
                    Elegir Otro Curso
                </button>

            

                <button class="btn btn-secondary" type="button"
                        onclick="location.href = 'ControladorAsistencia?accion=listarEst&idCurso=<%= idCurso%>'">
                    Volver a Estudiantes
                </button>
            </div>

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
                        <th class="text-center">ID INASISTENCIA</th>
                        <th class="text-center">ESTUDIANTE</th> <th class="text-center">
                        <th class="text-center">FECHA</th>
                        <th class="text-center">Justificada</th>
                        
                        <th class="text-center">ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList registros = (ArrayList) request.getAttribute("aRInasistencias");

                        if (registros != null && !registros.isEmpty()) {
                            for (Object registro : registros) {
                                InasistenciaDTO at = (InasistenciaDTO) registro;
                    %>
                    <tr>
                        <td class="text-center"><%= at.getIdInasistencia()%></td>

                        <td class="text-center"><%= at.getNombreAlumno()%></td>

                        <td class="text-center"><%= at.getFecha()%></td>
                        <td class="text-center"><%= at.isJustificada()%></td>
                       

                        <td class="text-center">
                            <a href="ControladorAtrasos?accion=editarAtraso&idAtraso=<%= at.getIdInasistencia()%>&idCurso=<%= idCurso%>"
                               class="btn btn-warning btn-sm">
                                Editar
                            </a>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">No hay inasistencias registrados para este curso.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <div class="mb-5">
                <a class="btn btn-secondary" href="index.jsp">Volver al Inicio</a>
            </div>
        </div>
    </body>
</html>
