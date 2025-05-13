// encuestador/CrearRespuestaFormularioServlet.java
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/encuestador/crearRespuesta")
public class CrearRespuestaFormularioServlet extends HttpServlet {

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
        Integer idFormulario = Integer.parseInt(request.getParameter("idFormulario")); // Obtener el ID del formulario

        try {
            // 1. Insertar en sesionesRespuesta
            SesionRespuesta nuevaSesion = new SesionRespuesta();
            nuevaSesion.setIdUsuario(idUsuario);
            nuevaSesion.setIdFormulario(idFormulario);
            nuevaSesion.setFechaInicio(new Timestamp(new Date().getTime())); // Fecha y hora actual
            nuevaSesion.setEstadoTerminado((byte) 1); // "completado"

            int idSesion = sesionRespuestaDAO.addSesionRespuesta(nuevaSesion);

            // 2. Insertar en respuestas (para cada pregunta)
            String[] idPreguntas = request.getParameterValues("idPregunta"); // Obtener array de IDs de preguntas
            String[] respuestaTextos = request.getParameterValues("respuestaTexto");
            String[] idOpciones = request.getParameterValues("idOpcion");

            if (idPreguntas != null) {
                for (int i = 0; i < idPreguntas.length; i++) {
                    Respuesta nuevaRespuesta = new Respuesta();
                    nuevaRespuesta.setIdSesion(idSesion);
                    nuevaRespuesta.setIdPregunta(Integer.parseInt(idPreguntas[i]));
                    nuevaRespuesta.setRespuestaTexto(respuestaTextos[i]);
                    if (idOpciones != null && idOpciones.length > i && !idOpciones[i].isEmpty()) {
                        nuevaRespuesta.setIdOpcion(Integer.parseInt(idOpciones[i]));
                    }

                    respuestaDAO.addRespuesta(nuevaRespuesta);
                }
            }

            out.println("<html><body><h1>Respuestas del formulario guardadas exitosamente.</h1>");
            out.println("<a href='inicioEncuestador.jsp'>Volver al Inicio</a></body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body><h1>Error al guardar las respuestas del formulario.</h1></body></html>");
        }
    }
}