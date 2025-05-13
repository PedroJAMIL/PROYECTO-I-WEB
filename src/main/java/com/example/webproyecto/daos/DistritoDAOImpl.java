package com.example.webproyecto.daos;

import com.example.webproyecto.beans.Distrito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistritoDAOImpl implements DistritoDAO {

    private Connection getConnection() throws SQLException {
        // Implementa tu lógica para obtener la conexión a la base de datos
        return null; // Reemplazar con tu implementación real
    }

    @Override
    public Distrito getDistritoById(Integer idDistrito) throws SQLException {
        String sql = "SELECT * FROM distritos WHERE idDistrito = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDistrito);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Distrito(rs.getInt("idDistrito"), rs.getString("nombreDistrito"), rs.getInt("idZonas"));
                }
                return null;
            }
        }
    }

    @Override
    public List<Distrito> getAllDistritos() throws SQLException {
        String sql = "SELECT * FROM distritos";
        List<Distrito> distritos = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                distritos.add(new Distrito(rs.getInt("idDistrito"), rs.getString("nombreDistrito"), rs.getInt("idZonas")));
            }
        }
        return distritos;
    }

    @Override
    public void addDistrito(Distrito distrito) throws SQLException {
        String sql = "INSERT INTO distritos (nombreDistrito, idZonas) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, distrito.getNombreDistrito());
            pstmt.setInt(2, distrito.getIdZonas());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateDistrito(Distrito distrito) throws SQLException {
        String sql = "UPDATE distritos SET nombreDistrito = ?, idZonas = ? WHERE idDistrito = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, distrito.getNombreDistrito());
            pstmt.setInt(2, distrito.getIdZonas());
            pstmt.setInt(3, distrito.getIdDistrito());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteDistrito(Integer idDistrito) throws SQLException {
        String sql = "DELETE FROM distritos WHERE idDistrito = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDistrito);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Distrito> getDistritosByZonaId(Integer idZona) throws SQLException {
        String sql = "SELECT * FROM distritos WHERE idZonas = ?";
        List<Distrito> distritos = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idZona);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    distritos.add(new Distrito(rs.getInt("idDistrito"), rs.getString("nombreDistrito"), rs.getInt("idZonas")));
                }
            }
        }
        return distritos;
    }
}
