<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="modelo.Atraso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css"/>
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
            <h1>Modificar Atraso</h1>
            <br>
            
            <form action="ControladorAtrasos">
                <%
                    Atraso a = (Atraso) request.getAttribute("atraso");
                    String idCurso = (String) request.getAttribute("idCurso");
                %>
                
                <input type="hidden" name="accion" value="ActualizarAtraso">
                <input type="hidden" name="idCurso" value="<%= idCurso %>">

                ID Atraso:<br>
                <input class="form-control" type="text" name="txtIdAtraso" value="<%= a != null ? a.getIdAtraso() : "" %>" readonly><br>
                
                ID Alumno:<br>
                <input class="form-control" type="text" name="txtIdAlumno" value="<%= a != null ? a.getIdAlumno() : "" %>" readonly><br>
                
                Fecha:<br>
                <input class="form-control" type="date" name="txtFecha" 
                       value="<%= (a != null && a.getFecha() != null) ? a.getFecha() : "" %>" required><br>
                
                Hora:<br>
                <input class="form-control" type="time" name="txtHora" 
                       value="<%= (a != null && a.getHora() != null) ? a.getHora().truncatedTo(ChronoUnit.MINUTES) : "" %>" required><br>
                
                Razón:<br>
                <input class="form-control" type="text" name="txtRazon" value="<%= a != null ? a.getRazon() : "" %>"><br>
                <br>

                <input class="btn btn-primary" type="submit" value="Actualizar"> 
                <a href="ControladorAtrasos?accion=listarAtrasos&idCurso=<%= idCurso %>">Regresar</a>
            </form>
        </div>
    </body>
</html>