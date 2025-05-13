package com.example.webproyecto.daos;

import com.example.webproyecto.beans.Formulario;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// ... (previous DAOs: RolDAO, ZonaDAO, DistritoDAO, EstadoDAO, UsuarioDAO)

//-------------------- Formulario DAO --------------------
interface FormularioDAO {
    Formulario getFormularioById(Integer idFormulario) throws SQLException;

    List<Formulario> getAllFormularios() throws SQLException;

    void addFormulario(Formulario formulario) throws SQLException;

    void updateFormulario(Formulario formulario) throws SQLException;

    void deleteFormulario(Integer idFormulario) throws SQLException;

    List<Formulario> getFormulariosByCoordinadorId(Integer idCoordinador) throws SQLException; // New method
}

class FormularioDAOImpl implements FormularioDAO {

    private Connection getConnection() throws SQLException {
        // Implementa tu lógica para obtener la conexión a la base de datos
        // (p.ej., usando un DataSource o una clase de utilidad)
        return null; // Reemplazar con tu implementación real
    }

    @Override
    public Formulario getFormularioById(Integer idFormulario) throws SQLException {
        String sql = "SELECT * FROM Formulario WHERE idFormulario = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idFormulario);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Formulario(
                            rs.getInt("idFormulario"),
                            rs.getString("titulo"),
                            rs.getString("descripcion"),
                            rs.getTimestamp("fechaCreacion"), // Use getTimestamp for Date/Timestamp
                            rs.getInt("idCoordinador"),
                            rs.getInt("idCarpeta"),
                            rs.getString("formularioscol")
                    );
                }
                return null;
            }
        }
    }

    @Override
    public List<Formulario> getAllFormularios() throws SQLException {
        String sql = "SELECT * FROM Formulario";
        List<Formulario> formularios = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                formularios.add(new Formulario(
                        rs.getInt("idFormulario"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getTimestamp("fechaCreacion"), // Use getTimestamp
                        rs.getInt("idCoordinador"),
                        rs.getInt("idCarpeta"),
                        rs.getString("formularioscol")
                ));
            }
        }
        return formularios;
    }

    @Override
    public void addFormulario(Formulario formulario) throws SQLException {
        String sql = "INSERT INTO Formulario (titulo, descripcion, fechaCreacion, idCoordinador, idCarpeta) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, formulario.getTitulo());
            pstmt.setString(2, formulario.getDescripcion());
            pstmt.setTimestamp(3, new Timestamp(formulario.getFechaCreacion().getTime())); // Convert Date to Timestamp
            pstmt.setInt(4, formulario.getIdCoordinador());
            pstmt.setInt(5, formulario.getIdCarpeta());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateFormulario(Formulario formulario) throws SQLException {
        String sql = "UPDATE Formulario SET titulo = ?, descripcion = ?, fechaCreacion = ?, idCoordinador = ?, idCarpeta = ? WHERE idFormulario = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, formulario.getTitulo());
            pstmt.setString(2, formulario.getDescripcion());
            pstmt.setTimestamp(3, new Timestamp(formulario.getFechaCreacion().getTime())); // Convert Date to Timestamp
            pstmt.setInt(4, formulario.getIdCoordinador());
            pstmt.setInt(5, formulario.getIdCarpeta());
            pstmt.setInt(7, formulario.getIdFormulario());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteFormulario(Integer idFormulario) throws SQLException {
        String sql = "DELETE FROM Formulario WHERE idFormulario = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idFormulario);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Formulario> getFormulariosByCoordinadorId(Integer idCoordinador) throws SQLException {
        String sql = "SELECT * FROM Formulario WHERE idCoordinador = ?";
        List<Formulario> formularios = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCoordinador);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    formularios.add(new Formulario(
                            rs.getInt("idFormulario"),
                            rs.getString("titulo"),
                            rs.getString("descripcion"),
                            rs.getTimestamp("fechaCreacion"), // Use getTimestamp
                            rs.getInt("idCoordinador"),
                            rs.getInt("idCarpeta"),
                            rs.getString("formularioscol")
                    ));
                }
            }
        }
        return formularios;
    }
}


