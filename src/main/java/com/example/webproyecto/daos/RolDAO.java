package com.example.webproyecto.daos;

import com.example.webproyecto.beans.Rol;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//-------------------- Roles DAO --------------------
public interface RolDAO {
    Rol getRolById(Byte idRol) throws SQLException;

    List<Rol> getAllRoles() throws SQLException;

    void addRol(Rol rol) throws SQLException;

    void updateRol(Rol rol) throws SQLException;

    void deleteRol(Byte idRol) throws SQLException;

    Rol getRolByNombre(String nombreRol) throws SQLException; // Nuevo método
}

public class RolDAOImpl implements RolDAO {

    private Connection getConnection() throws SQLException {
        // Implementa tu lógica para obtener la conexión a la base de datos
        // (p.ej., usando un DataSource o una clase de utilidad)
        return null; // Reemplazar con tu implementación real
    }

    @Override
    public Rol getRolById(Byte idRol) throws SQLException {
        String sql = "SELECT * FROM roles WHERE idRol = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setByte(1, idRol);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Rol(rs.getByte("idRol"), rs.getString("nombreRol"));
                }
                return null;
            }
        }
    }

    @Override
    public List<Rol> getAllRoles() throws SQLException {
        String sql = "SELECT * FROM roles";
        List<Rol> roles = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                roles.add(new Rol(rs.getByte("idRol"), rs.getString("nombreRol")));
            }
        }
        return roles;
    }

    @Override
    public void addRol(Rol rol) throws SQLException {
        String sql = "INSERT INTO roles (nombreRol) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, rol.getNombreRol());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateRol(Rol rol) throws SQLException {
        String sql = "UPDATE roles SET nombreRol = ? WHERE idRol = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, rol.getNombreRol());
            pstmt.setByte(2, rol.getIdRol());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteRol(Byte idRol) throws SQLException {
        String sql = "DELETE FROM roles WHERE idRol = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setByte(1, idRol);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Rol getRolByNombre(String nombreRol) throws SQLException {
        String sql = "SELECT * FROM roles WHERE nombreRol = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombreRol);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Rol(rs.getByte("idRol"), rs.getString("nombreRol"));
                }
                return null;
            }
        }
    }


}
