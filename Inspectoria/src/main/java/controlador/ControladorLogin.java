/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author benja
 */
@WebServlet(name = "ControladorLogin", urlPatterns = {"/ControladorLogin"})
public class ControladorLogin extends HttpServlet {
    private final String urlIndex = "index.jsp";
    private final String urlLogin = "login.jsp";
    


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorLogin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario modelo = new Usuario();
        String url = urlLogin;
        String action = request.getParameter("accion");
        try {
             if(action.equalsIgnoreCase("Verificar")){
               String usuario = request.getParameter("user");
               String password = request.getParameter("password");
               modelo.setUsuario(usuario);
               modelo.setPass(password);
           
               Usuario u = modelo.obtenerUsuario(usuario, password);
               if(u!=null){
                   url = urlIndex;
                   request.setAttribute("alertaMensaje", "Inicio correctamente la sesión");   
                   HttpSession sesion = request.getSession();
                   sesion.setAttribute("usuarioLogeado", u);

               }
               else{
                   
                   url = urlLogin;
                  request.setAttribute("alertaMensaje", "Mal ahi");  
               }
            }
        
            
        } catch (Exception e) {
            e.printStackTrace();
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
