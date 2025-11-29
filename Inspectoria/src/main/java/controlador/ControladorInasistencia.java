/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

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
import modelo.Inasistencia;

/**
 *
 * @author benja
 */
@WebServlet(name = "ControladorInasistencia", urlPatterns = {"/ControladorInasistencia"})
public class ControladorInasistencia extends HttpServlet {
    private final String urlListarInasistencias = "vistas/asistencia/inasistencias/readInasistencias.jsp";
    private final String urlActualizarInasistencia = "vistas/asistencia/inasistencias/updateInasistencias.jsp";
    
    private Inasistencia modeloInasistencia = new Inasistencia();
    private Alumno modeloAlumno = new Alumno();
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
            out.println("<title>Servlet ControladorInasistencia</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorInasistencia at " + request.getContextPath() + "</h1>");
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
        
        String url="";
        String action = request.getParameter("accion");
        try {
          if (action.equalsIgnoreCase("listarInasistencias")) {
                url = urlListarInasistencias;
                String idStr = request.getParameter("idCurso");

                if (idStr != null && !idStr.isEmpty() && !idStr.equals("null")) {
                    try {
                        int idCurso = Integer.parseInt(idStr);

                        ArrayList listaAlumnos = (ArrayList) modeloAlumno.listarAlumnosPorCurso(idCurso);
                        request.setAttribute("listaAlumnosCombo", listaAlumnos);

                        
                        String filtroAlumnoStr = request.getParameter("filtroIdAlumno");
                        String filtroFInicio = request.getParameter("filtroFechaInicio");
                        String filtroFFin = request.getParameter("filtroFechaFin");

                        ArrayList listaResultados;

                        
                        if (filtroAlumnoStr != null || filtroFInicio != null || filtroFFin != null) {
                            
                            int idAlum = (filtroAlumnoStr != null && !filtroAlumnoStr.isEmpty()) ? Integer.parseInt(filtroAlumnoStr) : 0;
                            
                            
                            listaResultados = (ArrayList) modeloInasistencia.filtrarInasistencias(idCurso, idAlum, filtroFInicio, filtroFFin);

                            
                            request.setAttribute("filtroIdAlumno", idAlum);
                            request.setAttribute("filtroFechaInicio", filtroFInicio);
                            request.setAttribute("filtroFechaFin", filtroFFin);

                        } else {
                            
                            listaResultados = (ArrayList) modeloInasistencia.listarInasistenciasPorCurso(idCurso);
                        }

                        request.setAttribute("aRInasistencias", listaResultados);
                        request.setAttribute("idCurso", idCurso);

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        e.printStackTrace();
                        request.setAttribute("aRInasistencias", new ArrayList<>());
                    }
                } else {
                    request.setAttribute("alertaMensaje", "Error: No se identificó el curso.");
                    request.setAttribute("aRInasistencias", new ArrayList<>());
                }
            }
            
            if (action.equalsIgnoreCase("editarInasistencia")) {
                url = urlActualizarInasistencia; 
                
                int idAtraso = Integer.parseInt(request.getParameter("idInasistencia"));
                String idCurso = request.getParameter("idCurso"); 
                
        
                Inasistencia inasistenciaEncontrado = modeloInasistencia.obtenerInasistenciaPorId(idAtraso);
                
               
                request.setAttribute("inasistencia", inasistenciaEncontrado);
                request.setAttribute("idCurso", idCurso);
            }
            if (action.equalsIgnoreCase("ActualizarInasistencia")) {
                
              
                int idInasistencia = Integer.parseInt(request.getParameter("txtIdInasistencia"));
                int idAlumno = Integer.parseInt(request.getParameter("txtIdAlumno"));
                boolean justificada;
                
                java.time.LocalDate fecha = java.time.LocalDate.parse(request.getParameter("txtFecha"));
                
                String estado = request.getParameter("chkJustificada");
                if(estado!=null && estado.equalsIgnoreCase("true")){
                   justificada = true;  
                }
                else{
                   justificada = false;  
                }
                
                
                String idCursoStr = request.getParameter("idCurso"); 
                
                modeloInasistencia.setIdInasistencia (idInasistencia);
                modeloInasistencia.setIdAlumno(idAlumno); 
                modeloInasistencia.setFecha(fecha);
                modeloInasistencia.setJustificada(justificada);
               
                
                
                int result = modeloInasistencia.editarInasistencia(); 
                
               
                if (result == 1) {
                    request.getSession().setAttribute("alertaMensaje", "Inasistencia modificado correctamente.");
                } else {
                    request.getSession().setAttribute("alertaMensaje", "Error: No se pudo modificar la inasistencia.");
                }
               
                response.sendRedirect("ControladorInasistencia?accion=listarInasistencias&idCurso=" + idCursoStr);
                return;
            }
            
            if(action.equalsIgnoreCase("eliminarInasistencia")){
                id = Integer.parseInt(request.getParameter("idInasistencia"));
                String idCurso = request.getParameter("idCurso");
                modeloInasistencia.setIdInasistencia(id);
                
                int result = modeloInasistencia.eliminarInasistencia();
                if(result==1){
                    request.getSession().setAttribute("alertaMensaje", "Inasistencia eliminada correctamente");
                }
                else{
                    request.getSession().setAttribute("alertaMensaje", "Error al eliminar inasistencia");
                    
                }
                response.sendRedirect("ControladorInasistencia?accion=listarInasistencias&idCurso=" + idCurso);
                return;
                    
            }
            
        } catch (Exception e) {
            System.out.println("ERROR CRÍTICO AL EDITAR:");
            
            e.printStackTrace();
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
