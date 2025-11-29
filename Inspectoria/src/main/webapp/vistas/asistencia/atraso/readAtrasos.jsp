<%-- 
    Document   : readAtrasos
    Created on : 24-11-2025, 12:40:13
    Author     : Benjamín
--%>

<%@page import="dto.AlumnoDTO"%>
<%@page import="dto.AtrasoDTO"%>
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
            <h1 class="mt-4 mb-4">Listado de Atrasos</h1>

            <%
               
                Integer idCursoObj = (Integer) request.getAttribute("idCurso");
                int idCurso = (idCursoObj != null) ? idCursoObj : 0;
            %>

            <div class="mb-4 text-center">
                <button class="btn btn-info" type="button"
                        onclick="location.href = 'ControladorAsistencia?accion=ListarCurso'">
                    Elegir Otro Curso
                </button>

                <button class="btn btn-primary" type="button"
                        onclick="location.href = 'ControladorAtrasos?accion=agregarAtrasos&idCurso=<%= idCurso%>'">
                    Agregar Atraso
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
            
            <div class="card mb-4">
                <div class="card-header bg-light">Filtros de Búsqueda</div>
                <div class="card-body">
                    <form action="ControladorAtrasos" method="GET" class="form-inline justify-content-center">
                        <input type="hidden" name="accion" value="listarAtrasos">
                        <input type="hidden" name="idCurso" value="<%= idCurso %>">

                        <label class="mr-2">Estudiante:</label>
                        <select name="filtroIdAlumno" class="form-control mr-4">
                            <option value="0">-- Todos los Estudiantes --</option>
                            <%
                                ArrayList alumnosCombo = (ArrayList) request.getAttribute("listaAlumnosCombo");
                                Integer filtroIdAlumObj = (Integer) request.getAttribute("filtroIdAlumno");
                                int filtroIdAlum = (filtroIdAlumObj != null) ? filtroIdAlumObj : 0;

                                if (alumnosCombo != null) {
                                    for (Object obj : alumnosCombo) {
                                        AlumnoDTO al = (AlumnoDTO) obj;
                                        String selected = (al.getId() == filtroIdAlum) ? "selected" : "";
                            %>
                                    <option value="<%= al.getId() %>" <%= selected %>>
                                        <%= al.getNombreAlumno() + " " + al.getApellidoPAlumno() %>
                                    </option>
                            <%
                                    }
                                }
                            %>
                        </select>

                        <label class="mr-2">Desde:</label>
                        <input type="date" name="filtroFechaInicio" class="form-control mr-3" 
                               value="<%= (request.getAttribute("filtroFechaInicio") != null) ? request.getAttribute("filtroFechaInicio") : "" %>">

                        <label class="mr-2">Hasta:</label>
                        <input type="date" name="filtroFechaFin" class="form-control mr-3"
                               value="<%= (request.getAttribute("filtroFechaFin") != null) ? request.getAttribute("filtroFechaFin") : "" %>">

                        <button type="submit" class="btn btn-primary">Filtrar</button>
                        <a href="ControladorAtrasos?accion=listarAtrasos&idCurso=<%= idCurso %>" class="btn btn-outline-secondary ml-2">Limpiar</a>
                    </form>
                </div>
            </div>
            <table class="table table-bordered table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th class="text-center">ID ATRASO</th>
                        <th class="text-center">ESTUDIANTE</th> <th class="text-center">FECHA</th>
                        <th class="text-center">HORA</th>
                        <th class="text-center">RAZÓN</th>
                        <th class="text-center">ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList registros = (ArrayList) request.getAttribute("aRAtrasos");

                        if (registros != null && !registros.isEmpty()) {
                            for (Object registro : registros) {
                                AtrasoDTO at = (AtrasoDTO) registro;
                    %>
                    <tr>
                        <td class="text-center"><%= at.getIdAtraso()%></td>

                        <td class="text-center"><%= at.getNombreAlumno()%></td>

                        <td class="text-center"><%= at.getFecha()%></td>
                        <td class="text-center"><%= at.getHora()%></td>
                        <td class="text-center"><%= at.getRazon()%></td>

                        <td class="text-center">
                            <a href="ControladorAtrasos?accion=editarAtraso&idAtraso=<%= at.getIdAtraso()%>&idCurso=<%= idCurso%>"
                               class="btn btn-warning btn-sm">
                                Editar
                            </a>
                            <a href="ControladorAtrasos?accion=eliminarAtraso&idAtraso=<%= at.getIdAtraso()%>&idCurso=<%= idCurso%>"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('¿Confirmas la eliminación de esta inasistencia?')">
                            Eliminar
                            </a>   
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">No hay atrasos registrados para este curso.</td>
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