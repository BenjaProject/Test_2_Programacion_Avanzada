
<%@page import="modelo.Inasistencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- RUTA AJUSTADA: Usa ruta relativa para los estilos -->
        <link href="../../../css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="../../../css/estilos_varios.css" rel="stylesheet" type="text/css"/>
        <title>Modificar Inasistencia</title>
        <style>
            .main-content {
                max-width: 500px;
                margin: 50px auto;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            }
            .checkbox-custom {
                height: 1.5rem;
                width: 1.5rem;
                margin-top: 0.2rem;
            }
        </style>
    </head>
    <body>
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
        
        <div class="container main-content">
            <h1>Modificar Inasistencia</h1>
            <br>
            
            <%
                // El controlador debe poner el objeto Inasistencia en el request con el nombre "inasistencia"
                Inasistencia i = (Inasistencia) request.getAttribute("inasistencia");
                String idCurso = (String) request.getAttribute("idCurso");
            %>
            
            <!-- ACTION CORREGIDA: Usando ruta relativa para el Controlador -->
            <form action="../../../ControladorInasistencia" method="post">
                <!-- ACCIONES DE CONTROLADOR -->
                <input type="hidden" name="accion" value="ActualizarInasistencia">
                <input type="hidden" name="idCurso" value="<%= idCurso != null ? idCurso : "" %>">

                <!-- CAMPOS DE LA INASISTENCIA -->
                
                <label>ID Inasistencia:</label><br>
                <input class="form-control" type="text" name="txtIdInasistencia" 
                       value="<%= i != null ? i.getIdInasistencia() : "" %>" readonly><br>
                
                <label>ID Alumno:</label><br>
                <input class="form-control" type="text" name="txtIdAlumno" 
                       value="<%= i != null ? i.getIdAlumno() : "" %>" readonly><br>
                
                <label>Fecha:</label><br>
                <input class="form-control" type="date" name="txtFecha" 
                       value="<%= (i != null && i.getFecha() != null) ? i.getFecha() : "" %>" required><br>
                
                <label>Justificada:</label>
                <!-- Checkbox corregido y adaptado a Bootstrap form-check -->
                <div class="form-check">
                    <input class="form-check-input checkbox-custom" type="checkbox" name="chkJustificada" value="true" id="chkJustificada"
                           <% if (i != null && i.isJustificada()) { %> checked <% } %>>
                    <label class="form-check-label" for="chkJustificada">
                        Marcar Inasistencia como Justificada
                    </label>
                </div>
                <br>
                <br>

                <input class="btn btn-primary" type="submit" value="Actualizar"> 
                <a href="../../../ControladorInasistencia?accion=listarAtrasos&idCurso=<%= idCurso != null ? idCurso : "" %>" class="btn btn-secondary">Regresar</a>
            </form>
        </div>
    </body>
</html>