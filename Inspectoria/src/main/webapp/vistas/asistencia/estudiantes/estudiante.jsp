<%-- 
    Document   : estudiante
    Created on : 25-11-2025, 13:54:23
    Author     : usuario
--%>

<%@page import="dto.AlumnoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
                    <form action="ControladorAsistencia" method="POST">
                <input type="hidden" name="accion" value="guardarAsistencia">
                <input type="hidden" name="idCurso" value="<%= request.getParameter("id")%>">

                <table class="table table-bordered table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th class="text-center">ID</th>
                            <th class="text-center">RUT</th>
                            <th class="text-center">NOMBRE COMPLETO</th>
                            <th class="text-center">INASISTENCIAS</th>
                            <th class="text-center">ATRASOS</th>

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
    </body>
</html>
