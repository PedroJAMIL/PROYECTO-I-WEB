// encuestador/ValidarCorreoServlet.java
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/encuestador/validarCorreo")
public class ValidarCorreoServlet extends HttpServlet {

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

        String correo = request.getParameter("correo"); // Obtener el correo de la URL
        int idEstado = 1; // Estado "activo"

        try {
            usuarioDAO.actualizarEstadoPorCorreo(correo, idEstado);
            out.println("<html><body><h1>Correo validado exitosamente.</h1>");
            out.println("<a href='loginEncuestador.html'>Iniciar Sesión</a></body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><h1>Error al validar el correo.</h1></body></html>");
        }
    }
}
