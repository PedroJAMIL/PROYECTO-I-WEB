// encuestador/LoginEncuestadorServlet.java
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/encuestador/login") // URL para el inicio de sesión del encuestador
public class LoginEncuestadorServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;
    private RolDAO rolDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDAO = new UsuarioDAOImpl();
        rolDAO = new RolDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // Importante: Especificar la codificación
        PrintWriter out = response.getWriter();

        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        try {
            // Obtener el rol de Encuestador
            Rol encuestadorRol = rolDAO.getRolByNombre("Encuestador");
            if (encuestadorRol == null) {
                out.println("<html><body><h1>Error: Rol 'Encuestador' no encontrado.</h1></body></html>");
                return; // Detener la ejecución si el rol no existe
            }

            Usuario usuario = usuarioDAO.iniciarSesion(correo, contrasena, encuestadorRol.getIdRol());

            if (usuario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("idUsuario", usuario.getIdUsuario());
                session.setAttribute("nombreUsuario", usuario.getNombre());
                session.setAttribute("idRol", usuario.getIdRol());

                response.sendRedirect("inicioEncuestador.jsp"); // Redirigir a la página de inicio del encuestador
            } else {
                out.println("<html><body><h1>Inicio de sesión fallido. Credenciales incorrectas.</h1>");
                out.println("<a href='loginEncuestador.html'>Volver a intentar</a></body></html>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><h1>Error en la base de datos.</h1></body></html>");
        }
    }
}