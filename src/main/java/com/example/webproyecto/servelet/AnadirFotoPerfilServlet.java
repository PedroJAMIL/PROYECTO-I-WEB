// encuestador/AnadirFotoPerfilServlet.java
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

@WebServlet("/encuestador/anadirFotoPerfil")
@MultipartConfig(maxFileSize = 16177215)    // Tamaño máximo del archivo: ~16MB
public class AnadirFotoPerfilServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDAO = new UsuarioDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        try {
            Part filePart = request.getPart("foto"); // Obtener la parte del archivo del formulario
            InputStream inputStream = filePart.getInputStream();

            // Convertir el InputStream a Blob
            Blob fotoBlob = null;
            if (inputStream != null) {
                fotoBlob = new SerialBlob(inputStream.readAllBytes());
            }

            usuarioDAO.actualizarFotoPerfil(idUsuario, fotoBlob);
            out.println("<html><body><h1>Foto de perfil actualizada exitosamente.</h1>");
            out.println("<a href='perfilEncuestador.jsp'>Volver al Perfil</a></body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><h1>Error al actualizar la foto de perfil.</h1></body></html>");
        } catch (ServletException e) {
            e.printStackTrace();
            out.println("<html><body><h1>Error al procesar el archivo.</h1></body></html>");
        }
    }
}
