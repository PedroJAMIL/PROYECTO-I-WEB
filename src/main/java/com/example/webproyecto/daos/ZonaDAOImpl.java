package com.example.webproyecto.daos;

import com.example.webproyecto.beans.Zona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZonaDAOImpl implements ZonaDAO {

    private Connection getConnection() throws SQLException {
        // Implementa tu lógica para obtener la conexión a la base de datos
        return null; // Reemplazar con tu implementación real
    }

    @Override
    public Zona getZonaById(Integer idZona) throws SQLException {
        String sql = "SELECT * FROM zonas WHERE idZona = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idZona);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Zona(rs.getInt("idZona"), rs.getString("nombreZona"));
                }
                return null;
            }
        }
    }

    @Override
    public List<Zona> getAllZonas() throws SQLException {
        String sql = "SELECT * FROM zonas";
        List<Zona> zonas = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                zonas.add(new Zona(rs.getInt("idZona"), rs.getString("nombreZona")));
            }
        }
        return zonas;
    }

    @Override
    public void addZona(Zona zona) throws SQLException {
        String sql = "INSERT INTO zonas (nombreZona) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, zona.getNombreZona());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateZona(Zona zona) throws SQLException {
        String sql = "UPDATE zonas SET nombreZona = ? WHERE idZona = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, zona.getNombreZona());
            pstmt.setInt(2, zona.getIdZona());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteZona(Integer idZona) throws SQLException {
        String sql = "DELETE FROM zonas WHERE idZona = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idZona);
            pstmt.executeUpdate();
        }
    }
}
