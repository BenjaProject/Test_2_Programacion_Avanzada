/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Inasistencia;

/**
 *
 * @author benja
 */
@WebServlet(name = "ControladorInasistencia", urlPatterns = {"/ControladorInasistencia"})
public class ControladorInasistencia extends HttpServlet {
    private final String urlListarInasistencias = "vistas/asistencia/inasistencias/readInasistencia.jsp";
    private final String urlActualizarInasistencia = "vistas/asistencia/inasistencias/updateInasistencia.jsp";
    
    private Inasistencia modeloInasistencia = new Inasistencia();
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
        processRequest(request, response);
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
        String url="";
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listarAtrasos")) {
                url = urlListarInasistencias;
                String idStr = request.getParameter("idCurso");

                if (idStr != null && !idStr.isEmpty() && !idStr.equals("null")) {
                    try {
                        int idCurso = Integer.parseInt(idStr);

                        ArrayList listaAtrasos = (ArrayList) modeloInasistencia.listarInasistenciasPorCurso(idCurso);

                        request.setAttribute("aRAtrasos", listaAtrasos);
                        request.setAttribute("idCurso", idCurso);

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        request.setAttribute("aRAtrasos", new ArrayList<>());
                    }
                } else {
                    request.setAttribute("alertaMensaje", "Error: No se identificó el curso.");
                    request.setAttribute("aRAtrasos", new ArrayList<>());
                }
            }
        
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
