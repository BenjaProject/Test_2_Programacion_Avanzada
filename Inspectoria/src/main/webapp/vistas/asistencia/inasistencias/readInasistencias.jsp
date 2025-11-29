<%-- 
    Document   : readInasistencias
    Created on : 25-11-2025
    Author     : Gemini (Corregido: Estética y Rutas)
    Descripción: Muestra la tabla de inasistencias y permite filtrar por estudiante y fecha.
--%>

<%@page import="dto.AlumnoDTO"%>
<%@page import="dto.InasistenciaDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Listado de Inasistencias</title>
        <style>

            .card-body .form-inline {
                justify-content: flex-start;
                flex-wrap: wrap;
            }
            .card-body .form-inline .form-control,
            .card-body .form-inline select,
            .card-body .form-inline .btn {
                margin-right: 15px;
                margin-bottom: 5px;
            }
            .filter-actions {
                white-space: nowrap;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">Listado de Inasistencias</h1>

            <%

                Integer idCursoObj = (Integer) request.getAttribute("idCurso");
                int idCurso = (idCursoObj != null) ? idCursoObj : 0;
            %>


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


        <div class="card mb-4">
            <div class="card-header bg-light">Filtros de Búsqueda</div>
            <div class="card-body">

                <form action="ControladorInasistencia" method="GET" class="form-inline">
                    <input type="hidden" name="accion" value="listarInasistencias">
                    <input type="hidden" name="idCurso" value="<%= idCurso%>">


                    <label class="mr-2">Estudiante:</label>
                    <select name="filtroIdAlumno" class="form-control mr-3">
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
                        <option value="<%= al.getId()%>" <%= selected%>>
                            <%= al.getNombreAlumno() + " " + al.getApellidoPAlumno()%>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>


                    <label class="mr-2">Desde:</label>
                    <input type="date" name="filtroFechaInicio" class="form-control mr-3" 
                           value="<%= (request.getAttribute("filtroFechaInicio") != null) ? request.getAttribute("filtroFechaInicio") : ""%>">


                    <label class="mr-2">Hasta:</label>
                    <input type="date" name="filtroFechaFin" class="form-control mr-3"
                           value="<%= (request.getAttribute("filtroFechaFin") != null) ? request.getAttribute("filtroFechaFin") : ""%>">



                    <div class="filter-actions">
                        <button type="submit" class="btn btn-primary mr-2">Filtrar</button>

                        <a href="ControladorInasistencia?accion=listarInasistencias&idCurso=<%= idCurso %>" class="btn btn-outline-secondary ml-2">Limpiar</a>
                    </div>

                </form>
            </div>
        </div>


        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th class="text-center">ID INASISTENCIA</th>
                    <th class="text-center">ESTUDIANTE</th> 
                    <th class="text-center">FECHA</th>
                    <th class="text-center">JUSTIFICADA</th>
                    <th class="text-center">ACCIONES</th>
                </tr>
            </thead>
            <tbody>
                <%

                    ArrayList registros = (ArrayList) request.getAttribute("aRInasistencias");

                    if (registros != null && !registros.isEmpty()) {
                        for (Object registro : registros) {
                            InasistenciaDTO iDTO = (InasistenciaDTO) registro;
                %>
                <tr>
                    <td class="text-center"><%= iDTO.getIdInasistencia()%></td>
                    <td class="text-center"><%= iDTO.getNombreAlumno()%></td>
                    <td class="text-center"><%= iDTO.getFecha()%></td>
                    <td class="text-center">
                        <%= iDTO.isJustificada() ? "Justificada" : "Injustificada"%>
                    </td>
                    <td class="text-center">

                        <a href="ControladorInasistencia?accion=editarInasistencia&idInasistencia=<%= iDTO.getIdInasistencia()%>&idCurso=<%= idCurso%>"
                           class="btn btn-warning btn-sm">
                            Editar
                        </a>
                        <a href="ControladorInasistencia?accion=eliminarInasistencia&idInasistencia=<%= iDTO.getIdInasistencia()%>&idCurso=<%= idCurso%>"
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
                    <td colspan="5" class="text-center">No hay inasistencias registradas o el filtro no arrojó resultados.</td>
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