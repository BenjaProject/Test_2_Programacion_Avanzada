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
        <!-- RUTA DEL CSS AJUSTADA A 3 NIVELES SUPERIORES (ASUMIENDO QUE CSS ESTÁ EN LA RAÍZ) -->
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Listado de Inasistencias</title>
        <style>
            /* Añadir estilos para asegurar que los elementos se separen como en Atrasos */
            .card-body .form-inline {
                justify-content: flex-start;
                flex-wrap: wrap; /* Permite que los elementos bajen en móvil */
            }
            .card-body .form-inline .form-control, 
            .card-body .form-inline select,
            .card-body .form-inline .btn {
                margin-right: 15px;
                margin-bottom: 5px; 
            }
            .filter-actions {
                white-space: nowrap; /* Evita que el botón Limpiar se rompa */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">Listado de Inasistencias</h1>

            <%
                // Recuperar el ID del curso para mantener el contexto
                Integer idCursoObj = (Integer) request.getAttribute("idCurso");
                int idCurso = (idCursoObj != null) ? idCursoObj : 0;
            %>

            <!-- Botones de Navegación -->
            <!-- Nota: Los botones se muestran centrados por el div contenedor -->
            <div class="mb-4 text-center">
                <button class="btn btn-info mx-2" type="button"
                        onclick="location.href = '../../../ControladorAsistencia?accion=ListarCurso'">
                    Elegir Otro Curso
                </button>
                
                <!-- BOTÓN AGREGAR INASISTENCIA -->
                <button class="btn btn-primary mx-2" type="button"
                        onclick="location.href = '../../../ControladorInasistencia?accion=agregarInasistencia&idCurso=<%= idCurso%>'">
                    Agregar Inasistencia
                </button>

                <button class="btn btn-secondary mx-2" type="button"
                        onclick="location.href = '../../../ControladorAsistencia?accion=listarEst&idCurso=<%= idCurso%>'">
                    Volver a Estudiantes
                </button>
            </div>

            <!-- Manejo de Alertas de Sesión -->
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
            
            <!-- Bloque de Filtros -->
            <div class="card mb-4">
                <div class="card-header bg-light">Filtros de Búsqueda</div>
                <div class="card-body">
                    <!-- ACTION: RUTA EXPLÍCITA AL CONTROLADOR -->
                    <form action="../../../ControladorInasistencia" method="GET" class="form-inline">
                        <input type="hidden" name="accion" value="FiltrarInasistencias">
                        <input type="hidden" name="idCurso" value="<%= idCurso %>">

                        <!-- Elemento 1: Estudiante -->
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
                                        <option value="<%= al.getId() %>" <%= selected %>>
                                            <%= al.getNombreAlumno() + " " + al.getApellidoPAlumno() %>
                                        </option>
                            <%
                                    } 
                                } 
                            %>
                        </select>

                        <!-- Elemento 2: Desde -->
                        <label class="mr-2">Desde:</label>
                        <input type="date" name="filtroFechaInicio" class="form-control mr-3" 
                               value="<%= (request.getAttribute("filtroFechaInicio") != null) ? request.getAttribute("filtroFechaInicio") : "" %>">

                        <!-- Elemento 3: Hasta -->
                        <label class="mr-2">Hasta:</label>
                        <input type="date" name="filtroFechaFin" class="form-control mr-3"
                               value="<%= (request.getAttribute("filtroFechaFin") != null) ? request.getAttribute("filtroFechaFin") : "" %>">

                        
                        <!-- Botones de Acción de Filtro -->
                        <div class="filter-actions">
                            <button type="submit" class="btn btn-primary mr-2">Filtrar</button>
                            <!-- Limpiar Filtros: llama a la acción original de listado de Inasistencias -->
                            <a href="../../../ControladorInasistencia?accion=listarAtrasos&idCurso=<%= idCurso %>" class="btn btn-outline-secondary">Limpiar</a>
                        </div>
                        
                    </form>
                </div>
            </div>

            <!-- Tabla de Resultados -->
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
                        // La lista de Inasistencias DTO se llama aRInasistencias
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
                            <%= iDTO.isJustificada() ? "Justificada" : "Injustificada" %>
                        </td>
                        <td class="text-center">
                            <!-- ACCIONES DE EDICIÓN Y ELIMINACIÓN -->
                            <a href="../../../ControladorInasistencia?accion=editarInasistencia&idInasistencia=<%= iDTO.getIdInasistencia()%>&idCurso=<%= idCurso%>"
                               class="btn btn-warning btn-sm">
                                Editar
                            </a>
                            <a href="../../../ControladorInasistencia?accion=eliminarInasistencia&idInasistencia=<%= iDTO.getIdInasistencia()%>&idCurso=<%= idCurso%>"
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
                <!-- Se ajusta la ruta del botón Volver al Inicio (2 niveles arriba) -->
                <a class="btn btn-secondary" href="../../index.jsp">Volver al Inicio</a>
            </div>
        </div>
    </body>
</html>