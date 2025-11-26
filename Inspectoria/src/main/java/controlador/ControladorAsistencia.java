/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dto.AlumnoDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
import modelo.Inasistencia;

/**
 *
 * @author benja
 */
@WebServlet(name = "ControladorAsistencia", urlPatterns = {"/ControladorAsistencia"})
public class ControladorAsistencia extends HttpServlet {

    private final String urlAgregarCurso = "vistas/asistencia/curso/createCurso.jsp";
    private final String urlListarCurso = "vistas/asistencia/curso/readCursos.jsp";
    private final String urlActualizarCurso = "vistas/asistencia/curso/updateCurso.jsp";
    private final String urlAgregarEstudiante = "vistas/asistencia/estudiantes/createEstudiante.jsp";
    private final String urlListarEstudiante = "vistas/asistencia/estudiantes/readEstudiantes.jsp";
    private final String urlEditarEstudiante = "vistas/asistencia/estudiantes/updateEstudiante.jsp";

    private Alumno modeloAlumno = new Alumno();
    private Inasistencia modeloInasistencia = new Inasistencia();
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
            out.println("<title>Servlet ControladorAsistencia</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorAsistencia at " + request.getContextPath() + "</h1>");
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
            //ir al menu
            if (action.equalsIgnoreCase("menu")) {
                url = "/vistas/asistencia/index_asistencia.jsp";
            }
            //Listar Cursos
            if (action.equalsIgnoreCase("ListarCurso")) {
                url = urlListarCurso;
                ArrayList aRCursos = (ArrayList) modeloCurso.listarCursos();
                request.setAttribute("aRCursos", aRCursos);
            }
            //ir a la pestaña de agregar curso;
            if (action.equalsIgnoreCase("addCurso")) {
                url = urlAgregarCurso;
            }
            //Crear Cursos
            if (action.equalsIgnoreCase("AgregarCurso")) {
                url = urlAgregarCurso;
                String nom = request.getParameter("txtNombreCurso");
                int nivel = Integer.parseInt(request.getParameter("txtNivel"));
                int anioacad = Integer.parseInt(request.getParameter("txtAnioAcademico"));

                modeloCurso.setNombreCurso(nom);
                modeloCurso.setNivel(nivel);
                modeloCurso.setAnioAcademico(anioacad);
                boolean result = modeloCurso.agregarCurso();
                if (result) {
                    request.getSession().setAttribute("alertaMensaje", "Se agrego curso correctamente.");
                } else {
                    request.getSession().setAttribute("alertaMensaje", "Error al agregar curso.");
                }
                response.sendRedirect("ControladorAsistencia?accion=ListarCurso");
                return;
            }
            //Listar estudiantes
            if (action.equalsIgnoreCase("listarEst")) {
                url = urlListarEstudiante;

                String idStr = request.getParameter("idCurso");

                if (idStr != null) {
                    int idCurso = Integer.parseInt(idStr);

                    ArrayList listaFiltrada = (ArrayList) modeloAlumno.listarAlumnosPorCurso(idCurso);
                    request.setAttribute("aREstudiantes", listaFiltrada);
                    
                    request.setAttribute("idCurso", idCurso);
                } else {

                    request.setAttribute("alertaMensaje", "Error: No se seleccionó un curso válido.");
                    request.setAttribute("aREstudiantes", new ArrayList<>());
                }
            }

            //Ir a pestaña agregar
            if (action.equalsIgnoreCase("add")) {
                url = urlAgregarEstudiante;

                String idCursoStr = request.getParameter("idCurso");
                int idCurso = (idCursoStr != null) ? Integer.parseInt(idCursoStr) : 0;

                
                request.setAttribute("idCursoPreseleccionado", idCurso);

                
                String nombreCursoMostrar = ""; 

                if (idCurso > 0) {
                    Curso cursoEncontrado = modeloCurso.obtenerCursoPorId(idCurso);
                    if (cursoEncontrado != null) {
                        nombreCursoMostrar = cursoEncontrado.getNombreCurso();
                    }
                }
                request.setAttribute("nombreCursoMostrar", nombreCursoMostrar);
            }
            //Agregar al estudiante
            if (action.equalsIgnoreCase("AgregarEst")) {
                url = urlAgregarEstudiante;
                int rut = Integer.parseInt(request.getParameter("txtRut"));
                String nom = request.getParameter("txtNom");
                String apepa = request.getParameter("txtApepa");
                String apema = request.getParameter("txtApema");
                String dir = request.getParameter("txtDir");
                int idCurso = Integer.parseInt(request.getParameter("cmbCur"));
                int cantInas = 0;
                int cantAtr = 0;
                modeloAlumno.setRut(rut);
                modeloAlumno.setNombre(nom);
                modeloAlumno.setApellidoPaterno(apepa);
                modeloAlumno.setApellidoMaterno(apema);
                modeloAlumno.setDireccion(dir);
                modeloAlumno.setIdCurso(idCurso);
                modeloAlumno.setCantidadInasistencias(cantInas);
                modeloAlumno.setCantidadAtrasos(cantAtr);
                int result = modeloAlumno.agregarAlumno();

                if (result==1) {
                    request.getSession().setAttribute("alertaMensaje", "Se agrego alumno correctamente.");
                } else {
                    request.getSession().setAttribute("alertaMensaje", "Error al agregar alumno.");
                }
                url = urlListarEstudiante;
                ArrayList aREstudiantes = (ArrayList) modeloAlumno.listarAlumnos();
                request.setAttribute("aREstudiantes", aREstudiantes);

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
            //Guardar asistencia
            if (action.equalsIgnoreCase("guardarAsistencia")) {
                ArrayList listaAlumnos = (ArrayList) modeloAlumno.listarAlumnos();

                int contadorAusentes = 0;

                for (Object obj : listaAlumnos) {

                    AlumnoDTO alumno = (AlumnoDTO) obj;

                    String nombreInput = "estado_" + alumno.getId();
                    String estado = request.getParameter(nombreInput);

                    if (estado != null && estado.equals("A")) {
                        Inasistencia nuevaInasistencia = new Inasistencia();

                        nuevaInasistencia.setIdAlumno(alumno.getId());
                        nuevaInasistencia.setFecha(java.time.LocalDate.now());
                        nuevaInasistencia.setJustificada(false);

                        nuevaInasistencia.crearInasistencia();
                        contadorAusentes++;
                    }

                }

                request.getSession().setAttribute("alertaMensaje", "Asistencia guardada. Se registraron " + contadorAusentes + " ausentes.");

                String idCursoStr = request.getParameter("idCurso");
                response.sendRedirect("ControladorAsistencia?accion=listarEst&idCurso=" + idCursoStr);
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
