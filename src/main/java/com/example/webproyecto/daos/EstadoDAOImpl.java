package com.example.webproyecto.daos;

import com.example.webproyecto.beans.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAOImpl implements EstadoDAO {

    private Connection getConnection() throws SQLException {
        // Implementa tu lógica para obtener la conexión a la base de datos
        return null; // Reemplazar con tu implementación real
    }

    @Override
    public Estado getEstadoById(Integer idEstado) throws SQLException {
        String sql = "SELECT * FROM estados WHERE idEstado = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idEstado);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Estado(rs.getInt("idEstado"), rs.getString("nombreEstado"));
                }
                return null;
            }
        }
    }

    @Override
    public List<Estado> getAllEstados() throws SQLException {
        String sql = "SELECT * FROM estados";
        List<Estado> estados = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                estados.add(new Estado(rs.getInt("idEstado"), rs.getString("nombreEstado")));
            }
        }
        return estados;
    }

    @Override
    public void addEstado(Estado estado) throws SQLException {
        String sql = "INSERT INTO estados (nombreEstado) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, estado.getNombreEstado());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateEstado(Estado estado) throws SQLException {
        String sql = "UPDATE estados SET nombreEstado = ? WHERE idEstado = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, estado.getNombreEstado());
            pstmt.setInt(2, estado.getIdEstado());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteEstado(Integer idEstado) throws SQLException {
        String sql = "DELETE FROM estados WHERE idEstado = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idEstado);
            pstmt.executeUpdate();
        }
    }
}
