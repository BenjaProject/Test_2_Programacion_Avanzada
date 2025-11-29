/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dto.AlumnoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Alumno;
import modelo.Atraso;
import modelo.Curso;

/**
 *
 * @author Lester
 */
@WebServlet(name = "ControladorAtrasos", urlPatterns = {"/ControladorAtrasos"})
public class ControladorAtrasos extends HttpServlet {

    private final String urlAgregarAtraso = "vistas/asistencia/atraso/createAtraso.jsp";
    private final String urlEditarAtraso = "vistas/asistencia/atraso/updateAtrasos.jsp";
    private final String urlListarAtrasos = "vistas/asistencia/atraso/readAtrasos.jsp";

    private Alumno modeloAlumno = new Alumno();
    private Atraso modeloAtraso = new Atraso();
    private Curso modeloCurso = new Curso();
    int id;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorAtrasos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorAtrasos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        String action = request.getParameter("accion");

        try {
            if (action.equalsIgnoreCase("menu")) {
                url = "vistas/asistencia/index_asistencia.jsp";
            }
            //Listar Atrasos
            if (action.equalsIgnoreCase("listarAtrasos")) {
                url = urlListarAtrasos;
                String idStr = request.getParameter("idCurso");

                if (idStr != null && !idStr.isEmpty()) {
                    try {
                        int idCurso = Integer.parseInt(idStr);

                        ArrayList listaAlumnos = (ArrayList) modeloAlumno.listarAlumnosPorCurso(idCurso);
                        request.setAttribute("listaAlumnosCombo", listaAlumnos);

                        String filtroAlumno = request.getParameter("filtroIdAlumno");
                        String filtroFInicio = request.getParameter("filtroFechaInicio");
                        String filtroFFin = request.getParameter("filtroFechaFin");

                        ArrayList listaResultados;

                        if (filtroAlumno != null || filtroFInicio != null || filtroFFin != null) {
                            int idAlum = (filtroAlumno != null && !filtroAlumno.isEmpty()) ? Integer.parseInt(filtroAlumno) : 0;
                            listaResultados = modeloAtraso.filtrarAtrasos(idCurso, idAlum, filtroFInicio, filtroFFin);

                            request.setAttribute("filtroIdAlumno", idAlum);
                            request.setAttribute("filtroFechaInicio", filtroFInicio);
                            request.setAttribute("filtroFechaFin", filtroFFin);

                        } else {
                            listaResultados = modeloAtraso.listarAtrasosPorCurso(idCurso);
                        }

            request.setAttribute("aRAtrasos", listaResultados);
            request.setAttribute("idCurso", idCurso);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("aRAtrasos", new ArrayList<>());
        }
    }
            }
            //Agregar Atraso
            if (action.equalsIgnoreCase("agregarAtrasos")) {
                url = urlAgregarAtraso; // createAtraso.jsp

                String idCursoStr = request.getParameter("idCurso");

                if (idCursoStr != null) {
                    int idCurso = Integer.parseInt(idCursoStr);

                    ArrayList lista = (ArrayList) modeloAlumno.listarAlumnosPorCurso(idCurso);
                    request.setAttribute("aREstudiantes", lista);

                    request.setAttribute("idCurso", idCurso);
                }
            }
            //Editar Atraso
           if (action.equalsIgnoreCase("editarAtraso")) {
                url = urlEditarAtraso; 
                
                int idAtraso = Integer.parseInt(request.getParameter("idAtraso"));
                String idCurso = request.getParameter("idCurso"); 
                
        
                Atraso atrasoEncontrado = modeloAtraso.obtenerAtrasoPorId(idAtraso);
                
               
                request.setAttribute("atraso", atrasoEncontrado);
                request.setAttribute("idCurso", idCurso);
            }

           //Actualizar Atraso
            if (action.equalsIgnoreCase("ActualizarAtraso")) {
                
              
                int idAtraso = Integer.parseInt(request.getParameter("txtIdAtraso"));
                int idAlumno = Integer.parseInt(request.getParameter("txtIdAlumno"));
                
                
                java.time.LocalDate fecha = java.time.LocalDate.parse(request.getParameter("txtFecha"));
                java.time.LocalTime hora = java.time.LocalTime.parse(request.getParameter("txtHora"));
                
                String razon = request.getParameter("txtRazon");
                String idCursoStr = request.getParameter("idCurso"); 
                
                modeloAtraso.setIdAtraso(idAtraso);
                modeloAtraso.setIdAlumno(idAlumno); 
                modeloAtraso.setFecha(fecha);
                modeloAtraso.setHora(hora);
                modeloAtraso.setRazon(razon);
                
                
                int result = modeloAtraso.editarAtraso(); 
                
               
                if (result == 1) {
                    request.getSession().setAttribute("alertaMensaje", "Atraso modificado correctamente.");
                } else {
                    request.getSession().setAttribute("alertaMensaje", "Error: No se pudo modificar el atraso.");
                }
               
                response.sendRedirect("ControladorAtrasos?accion=listarAtrasos&idCurso=" + idCursoStr);
                return;
            }
            if(action.equalsIgnoreCase("eliminarAtraso")){
                id=Integer.parseInt(request.getParameter("idAtraso"));
                String idCursoStr = request.getParameter("idCurso");
                modeloAtraso.setIdAtraso(id);
                int result = modeloAtraso.eliminarAtraso();
                if(result==1){
                    request.getSession().setAttribute("alertaMensaje", "Atraso eliminado correctamente");
                }
                else{
                    request.getSession().setAttribute("alertaMensaje", "Error al eliminar atraso");
                }
                response.sendRedirect("ControladorAtrasos?accion=listarAtrasos&idCurso=" + idCursoStr);
                return;
                
            }
        } catch (Exception e) {

        }
        RequestDispatcher vista = request.getRequestDispatcher(url);
        vista.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        String url = "";
        try {
            if (action.equalsIgnoreCase("guardarAtrasoIndividual")) {

                int idEst = Integer.parseInt(request.getParameter("txtIdEst"));
                LocalDate fecha = LocalDate.parse(request.getParameter("txtFecha"));
                LocalTime hora = LocalTime.parse(request.getParameter("txtHora"));
                String razon = request.getParameter("txtRazon");
                String idCursoStr = request.getParameter("idCurso");

                modeloAtraso.setIdAlumno(idEst);
                modeloAtraso.setFecha(fecha);
                modeloAtraso.setHora(hora);
                modeloAtraso.setRazon(razon);

                int result = modeloAtraso.agregarAtraso();

                if (result == 1) {
                    request.getSession().setAttribute("alertaMensaje", "Atraso agregado exitosamente.");
                } else {
                    request.getSession().setAttribute("alertaMensaje", "Error al agregar el atraso.");
                }

                response.sendRedirect("ControladorAtrasos?accion=listarAtrasos&idCurso=" + idCursoStr);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("alertaMensaje", "Error: " + e.getMessage());
        }

        RequestDispatcher vista = request.getRequestDispatcher(url);
        vista.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
