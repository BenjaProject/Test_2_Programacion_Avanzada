<%@page import="dto.AlumnoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Tomar Asistencia</title>
        <style>
            .radio-group label {
                margin-right: 15px;
                cursor: pointer;
            }
            .radio-presente {
                color: green;
                font-weight: bold;
            }
            .radio-ausente {
                color: red;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">Registro de Asistencia</h1>
            <!-- Botonera superior para navegación rápida -->
            <div class="mb-4 text-center">
                <button class="btn btn-info" type="button" onclick="location.href = 'ControladorAsistencia?accion=ListarCurso'">Elegir Curso</button>
                <button class="btn btn-primary" type="button" onclick="location.href = 'ControladorAtrasos?accion=listarAtrasos&idCurso=<%= request.getAttribute("idCurso") %>'">Listar Atrasos</button>
                <button class="btn btn-success" type="button" 
                        onclick="location.href = 'ControladorAsistencia?accion=add&idCurso=<%= request.getParameter("idCurso")%>'">
                    Agregar Estudiante
                </button>
                <button class="btn btn-secondary" type="button" onclick="location.href = 'ControladorAsistencia?accion=menu'">Volver al Menú</button>
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

            <form action="ControladorAsistencia" method="POST">
                <input type="hidden" name="accion" value="guardarAsistencia">
                <input type="hidden" name="idCurso" value="<%= request.getParameter("idCurso")%>">

                <table class="table table-bordered table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th class="text-center">ID</th>
                            <th class="text-center">RUT</th>
                            <th class="text-center">NOMBRE COMPLETO</th>
                            <th class="text-center">ESTADO ASISTENCIA</th>
                            <th class="text-center">CANTIDAD INASISTENCIAS</th>
                            <th class="text-center">CANTIDAD ATRASOS</th>

                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList registros = (ArrayList) request.getAttribute("aREstudiantes");
                            if (registros != null && registros.size() != 0) {
                                for (Object registro : registros) {
                                    AlumnoDTO a = (AlumnoDTO) registro;
                        %>
                        <tr>
                            <td class="text-center"><%= a.getId()%></td>
                            <td class="text-center"><%= a.getRut()%></td> 
                                <td><%= a.getNombreAlumno() + " " + a.getApellidoPAlumno()
                                        + " " + a.getApellidoMAlumno()%></td>

                            <td class="text-center radio-group">
                                <input type="radio" name="estado_<%= a.getId()%>" value="N" hidden>
                                <label class="radio-presente">
                                    <input type="radio" name="estado_<%= a.getId()%>" value="P"> 
                                    Presente
                                </label>

                                <label class="radio-ausente">
                                    <input type="radio" name="estado_<%= a.getId()%>" value="A"> 
                                    Ausente
                                </label>
                            </td>
                            <td class="text-center"><%= a.getCantidadInasistencias()%></td>
                            <td class="text-center"><%= a.getCantidadAtrasos()%></td>
                            
                

                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="4" class="text-center">No hay alumnos registrados en este curso.</td> 
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>

                <div class="text-right mb-5">
                    <a class="btn btn-secondary" href="index.jsp">Cancelar</a>
                    <button type="submit" class="btn btn-primary btn-lg">Guardar Lista de Asistencia</button>
                </div>
            </form>
        </div>
    </body>
</html>