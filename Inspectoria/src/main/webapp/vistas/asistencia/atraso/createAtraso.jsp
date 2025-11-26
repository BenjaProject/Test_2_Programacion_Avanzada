<%@page import="java.time.LocalTime"%>
<%@page import="java.time.LocalDate"%>
<%@page import="dto.AlumnoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Registrar Nuevo Atraso</title>
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6 mx-auto mt-4">
                <div class="card">
                    
                        <h3>Registrar Atraso</h3>
       
                    <div class="card-body">

                        <script type="text/javascript">
                            <%
                                String mensaje = (String) request.getAttribute("alertaMensaje");
                                if (mensaje != null && !mensaje.isEmpty()) {
                            %>
                                alert('<%= mensaje%>');
                            <%
                                }
                            %>
                        </script>

                        <form action="ControladorAtrasos" method="POST">
                            <input type="hidden" name="accion" value="guardarAtrasoIndividual">
                            
                            <input type="hidden" name="idCurso" value="<%= request.getParameter("idCurso") %>">

                            <div class="form-group mb-3">
                                <label class="form-label">Estudiante:</label>
                                <select class="form-control" name="txtIdEst" required>
                                    <option value="" selected disabled>-- Seleccione un Alumno --</option>
                                    <%
                                        ArrayList listaEst = (ArrayList) request.getAttribute("aREstudiantes");
                                        if (listaEst != null && !listaEst.isEmpty()) {
                                            for (Object obj : listaEst) {
                                                AlumnoDTO a = (AlumnoDTO) obj;
                                    %>
                                            <option value="<%= a.getId() %>">
                                                <%= a.getRut() %> - <%= a.getNombreAlumno() %> <%= a.getApellidoPAlumno() %>
                                            </option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="form-group mb-3">
                                <label class="form-label">Fecha:</label>
                                <input class="form-control" type="date" name="txtFecha" 
                                       value="<%= LocalDate.now() %>" required>
                            </div>

                            <div class="form-group mb-3">
                                <label class="form-label">Hora de llegada:</label>
                                <input class="form-control" type="time" name="txtHora" 
                                       value="<%= LocalTime.now().truncatedTo(java.time.temporal.ChronoUnit.MINUTES) %>" required>
                            </div>

                            <div class="form-group mb-3">
                                <label class="form-label">Razón / Motivo:</label>
                                <textarea class="form-control" name="txtRazon" rows="2" placeholder="Ej: Problemas de locomoción..."></textarea>
                            </div>

                            <br>
                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <a class="btn btn-secondary me-md-2" 
                                   href="ControladorAtrasos?accion=listarAtrasos&idCurso=<%= request.getParameter("idCurso") %>">
                                   Cancelar
                                </a>
                                <button class="btn btn-primary" type="submit">Registrar Atraso</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>