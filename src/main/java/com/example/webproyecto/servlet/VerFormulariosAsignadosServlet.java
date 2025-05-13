package com.example.webproyecto.servlet;// encuestador/VerFormulariosAsignadosServlet.java
import com.example.webproyecto.beans.AsignacionFormulario;
import com.example.webproyecto.daos.UsuarioDAO;
import com.example.webproyecto.daos.UsuarioDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/encuestador/verFormulariosAsignados")
public class VerFormulariosAsignadosServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO; // Necesitamos un UsuarioDAO para obtener los formularios asignados
    //private AsignacionFormularioDAO asignacionFormularioDAO; // Necesitamos un AsignacionFormularioDAO

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDAO = new UsuarioDAOImpl();
        //asignacionFormularioDAO = new AsignacionFormularioDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        if (idUsuario == null) {
            response.sendRedirect("../loginEncuestador.html"); // Redirigir si no hay sesión
            return;
        }

        try {
            List<AsignacionFormulario> formulariosAsignados = usuarioDAO.verFormulariosAsignados(idUsuario); // Usar el UsuarioDAO

            out.println("<html><body><h1>Formularios Asignados</h1>");
            if (formulariosAsignados != null && !formulariosAsignados.isEmpty()) {
                out.println("<ul>");
                for (AsignacionFormulario asignacion : formulariosAsignados) {
                    out.println("<li>Formulario ID: " + asignacion.getIdFormulario() + "</li>");
                }
                out.println("</ul>");
            } else {
                out.println("<p>No hay formularios asignados actualmente.</p>");
            }
            out.println("</body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><h1>Error al obtener los formularios asignados.</h1></body></html>");
        }
    }
}