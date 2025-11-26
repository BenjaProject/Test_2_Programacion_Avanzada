<%-- 
    Document   : createCurso
    Created on : 23-11-2025, 18:31:13
    Author     : Benjamín
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Crear Curso</title>
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <h1>Crear Curso</h1>

                <script type="text/javascript">
                    <% 
                        String mensaje = (String) request.getAttribute("alertaMensaje");
                        if (mensaje != null && !mensaje.isEmpty()) {
                    %>
                            alert('<%= mensaje %>');
                            history.back();
                    <% 
                        } 
                    %>
                </script>

                <form action="ControladorAsistencia">
                    Nombre del curso:
                    <br>
                    
                    <input class="form-control" type="text" name="txtNombreCurso">
                    <br>

                    Nivel:
                    <br>
                    
                    <input class="form-control" type="text" name="txtNivel" placeholder="Ej: 1, 2, 3 o 4 Medio">
                    <br>

                    Año académico:
                    <br>
                    
                    <input class="form-control" type="number" name="txtAnioAcademico" placeholder="Ej: 2025">
                    <br>

                    <input class="btn btn-primary" type="submit" name="accion" value="AgregarCurso">
                    <a class="btn btn-secondary" href="ControladorAsistencia?accion=ListarCurso">Regresar</a>
                </form>
            </div>
        </div>
    </body>
</html>
