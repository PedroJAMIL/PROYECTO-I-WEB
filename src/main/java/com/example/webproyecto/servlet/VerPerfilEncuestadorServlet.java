package com.example.webproyecto.servlet;// encuestador/VerPerfilEncuestadorServlet.java
import com.example.webproyecto.beans.Usuario;
import com.example.webproyecto.daos.UsuarioDAO;
import com.example.webproyecto.daos.UsuarioDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/encuestador/verPerfil")
public class VerPerfilEncuestadorServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDAO = new UsuarioDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Integer idUsuario = (Integer) session.getAttribute("idUsuario"); // Obtener el ID del usuario de la sesión

        if (idUsuario == null) {
            // El usuario no ha iniciado sesión, redirigir a la página de inicio de sesión
            response.sendRedirect("../loginEncuestador.html"); // Ajusta la ruta según tu estructura
            return;
        }

        try {
            Usuario usuario = usuarioDAO.verPerfil(idUsuario);

            if (usuario != null) {
                // Mostrar la información del perfil
                out.println("<html><body>");
                out.println("<h1>Perfil del Encuestador</h1>");
                out.println("<p>ID: " + usuario.getIdUsuario() + "</p>");
                out.println("<p>Nombre: " + usuario.getNombre() + "</p>");
                out.println("<p>Apellido: " + usuario.getApellido() + "</p>");
                out.println("<p>DNI: " + usuario.getDni() + "</p>");
                out.println("<p>Dirección: " + usuario.getDireccion() + "</p>");
                // ... (mostrar otros datos del perfil)
                out.println("</body></html>");
            } else {
                out.println("<html><body><h1>Error al obtener el perfil.</h1></body></html>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><h1>Error en la base de datos.</h1></body></html>");
        }
    }
}
