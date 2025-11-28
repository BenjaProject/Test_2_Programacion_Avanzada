<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="modelo.Inasistencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="<%=request.getContextPath()%>/css/estilos_varios.css" rel="stylesheet" type="text/css"/>
        <title>Modificar Atraso</title>
    </head>
    <body>
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


        <div class="container">
            <h1>Modificar Inasistencias</h1>
            <br>

            <form action="ControladorInasistencia">
                <%
                    Inasistencia a = (Inasistencia) request.getAttribute("inasistencia");
                    String idCurso = (String) request.getAttribute("idCurso");
                %>

                <input type="hidden" name="accion" value="ActualizarInasistencia">
                <input type="hidden" name="idCurso" value="<%= idCurso%>">

                ID Inasistencia:<br>
                <input class="form-control" type="text" name="txtIdInasistencia" value="<%= a != null ? a.getIdInasistencia() : ""%>" readonly><br>

                ID Alumno:<br>
                <input class="form-control" type="text" name="txtIdAlumno" value="<%= a != null ? a.getIdAlumno() : ""%>" readonly><br>

                Fecha:<br>
                <input class="form-control" type="date" name="txtFecha" 
                       value="<%= (a != null && a.getFecha() != null) ? a.getFecha() : ""%>" required><br>


                <label>Justificada:</label>
                <input class="checkbox-inline checkbox-grande " type="checkbox" name="chkJustificada" value="true" <%= (a != null && a.isJustificada()) ? "checked" : ""%>>
                <br>
                <br>

                <input class="btn btn-primary" type="submit" value="Actualizar"> 
                <a href="ControladorInasistencia?accion=listarInasistencias&idCurso=<%= idCurso%>">Regresar</a>
            </form>
        </div>
    </body>
</html>