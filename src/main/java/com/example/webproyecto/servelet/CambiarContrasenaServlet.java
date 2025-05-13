import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/encuestador/cambiarContrasena")
public class CambiarContrasenaServlet extends HttpServlet {

    private CredencialDAO credencialDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        credencialDAO = new CredencialDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        String contrasenaActual = request.getParameter("contrasenaActual");
        String nuevaContrasena = request.getParameter("nuevaContrasena");

        try {
            credencialDAO.cambiarContrasena(idUsuario, nuevaContrasena, contrasenaActual);
            out.println("<html><body><h1>Contraseña cambiada exitosamente.</h1>");
            out.println("<a href='perfilEncuestador.jsp'>Volver al Perfil</a></body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><h1>Error al cambiar la contraseña. Verifique la contraseña actual.</h1></body></html>");
        }
    }
}