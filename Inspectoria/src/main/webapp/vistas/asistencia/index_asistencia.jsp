<%-- 
    Document   : index_asistencia
    Created on : 23-11-2025, 7:26:00 p. m.
    Author     : super
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Index Asistencia</title>
    </head>
    <body>

        <div class="container mt-5">
            <div class="col-lg-6 mx-auto text-center">

                <h1 class="mb-4">Asistencia</h1>

                <p class="text-muted mb-4">
                    Selecciona una de las opciones para continuar.
                </p>

                <form>

                    <button class="btn btn-info mb-3 w-100" type="button" 
                            onclick="location.href='<%=request.getContextPath()%>/ControladorAsistencia?accion=ListarCurso'">
                        Elegir Curso
                    </button>

                    <button class="btn btn-secondary w-100" type="button" 
                            onclick="location.href='<%=request.getContextPath()%>/vistas/irAlCRUDinspectoria.jsp'">
                        Volver al Menú
                    </button>

                </form>

            </div>
        </div>

    </body>
</html>
