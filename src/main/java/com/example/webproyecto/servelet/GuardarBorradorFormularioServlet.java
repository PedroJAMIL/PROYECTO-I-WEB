import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/encuestador/guardarBorrador")
public class GuardarBorradorFormularioServlet extends HttpServlet {

    private SesionRespuestaDAO sesionRespuestaDAO;
    private RespuestaDAO respuestaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        sesionRespuestaDAO = new SesionRespuestaDAOImpl();
        respuestaDAO = new RespuestaDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");
        Integer idFormulario = Integer.parseInt(request.getParameter("idFormulario"));

        try {
            SesionRespuesta sesion = sesionRespuestaDAO.getSesionRespuestaByUsuarioFormulario(idUsuario, idFormulario, (byte) 0); // Check for existing draft

            if (sesion == null) {
                // No existing draft, create a new session
                SesionRespuesta nuevaSesion = new SesionRespuesta();
                nuevaSesion.setIdUsuario(idUsuario);
                nuevaSesion.setIdFormulario(idFormulario);
                nuevaSesion.setFechaInicio(new Timestamp(new Date().getTime()));
                nuevaSesion.setFechaFin(new Timestamp(new Date().getTime()));
                nuevaSesion.setEstadoTerminado((byte) 0); // 0 for "draft"

                int idSesion = sesionRespuestaDAO.addSesionRespuesta(nuevaSesion);
                sesion = nuevaSesion; // Update sesion with the new SesionRespuesta
                sesion.setIdSesion(idSesion); // Set the generated ID
            } else {
                // Existing draft, update fechaFin
                sesion.setFechaFin(new Timestamp(new Date().getTime()));
                sesionRespuestaDAO.updateSesionRespuesta(sesion);
            }

            // Save the responses
            String[] idPreguntas = request.getParameterValues("idPregunta");
            String[] respuestaTextos = request.getParameterValues("respuestaTexto");
            String[] idOpciones = request.getParameterValues("idOpcion");

            if (idPreguntas != null) {
                for (int i = 0; i < idPreguntas.length; i++) {
                    Respuesta nuevaRespuesta = new Respuesta();
                    nuevaRespuesta.setIdSesion(sesion.getIdSesion());
                    nuevaRespuesta.setIdPregunta(Integer.parseInt(idPreguntas[i]));
                    nuevaRespuesta.setRespuestaTexto(respuestaTextos[i]);

                    // Handle null for idOpcion
                    if (idOpciones != null && idOpciones.length > i && !idOpciones[i].isEmpty()) {
                        nuevaRespuesta.setIdOpcion(Integer.parseInt(idOpciones[i]));
                    } else {
                        nuevaRespuesta.setIdOpcion(null);
                    }

                    respuestaDAO.addRespuesta(nuevaRespuesta); // Consider updateRespuesta if it exists
                }
            }

            out.println("<html><body><h1>Borrador guardado exitosamente.</h1>");
            out.println("<a href='inicioEncuestador.jsp'>Volver al Inicio</a></body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><h1>Error al guardar el borrador.</h1>");
            out.println("<p>Error: " + e.getMessage() + "</p></body></html>");
        }
    }
}