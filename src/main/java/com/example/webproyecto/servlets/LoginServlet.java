package com.example.webproyecto.servlets;

import com.example.webproyecto.beans.Usuario;
import com.example.webproyecto.daos.CredencialDao;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String contrasenha = request.getParameter("contrasenha");

        CredencialDao dao = new CredencialDao();
        Usuario usuario = dao.validarLogin(correo, contrasenha);

        if (usuario != null) {
            // Guardar datos esenciales en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("idUsuario", usuario.getIdUsuario());
            session.setAttribute("rolId", usuario.getIdRol());
            session.setAttribute("nombre", usuario.getNombre());

            // Redirigir a servlets según el rol
            switch (usuario.getIdRol()) {
                case 1 -> response.sendRedirect("InicioAdminServlet");
                case 2 -> response.sendRedirect("InicioCoordinadorServlet");
                case 3 -> response.sendRedirect("InicioEncuestadorServlet"); // IMPORTANTE: esta ruta debe coincidir con @WebServlet del servlet de encuestador
                default -> response.sendRedirect("login.jsp");
            }

        } else {
            // Si falla el login
            request.setAttribute("error", "Credenciales inválidas o cuenta inactiva.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
