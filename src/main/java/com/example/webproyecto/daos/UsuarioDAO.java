
package com.example.webproyecto.daos;

import beans.Usuario;
import beans.AsignacionFormulario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

interface UsuarioDAO {
    Usuario getUsuarioById(Integer idUsuario) throws SQLException;

    List<Usuario> getAllUsuarios() throws SQLException;

    void addUsuario(Usuario usuario) throws SQLException;

    void updateUsuario(Usuario usuario) throws SQLException;

    void deleteUsuario(Integer idUsuario) throws SQLException;

    Usuario iniciarSesionEncuestador(String correo, String contrasena) throws SQLException; // Query 1 (Encuestador)

    void crearPerfilEncuestador(Usuario usuario) throws SQLException; // Query 2

    void actualizarEstadoPorCorreo(String correo, Integer idEstado) throws SQLException; // Query 3

    Usuario verPerfil(Integer idUsuario) throws SQLException; // Query 5

    void actualizarFotoPerfil(Integer idUsuario, Blob foto) throws SQLException; // Query 7

    List<AsignacionFormulario> verFormulariosAsignados(Integer idUsuario) throws SQLException; // Query 8 (requiere AsignacionFormularioDAO)

    void crearNuevoEncuestador(Usuario usuario, String correo, String contrasena) throws SQLException; // Query 11

    void desactivarUsuario(Integer idUsuario) throws SQLException; // Query 12 (para Encuestador o Coordinador)

    List<Usuario> gestionarEncuestadores() throws SQLException; // Query 15

    Usuario iniciarSesion(String correo, String contrasena, Byte idRol) throws SQLException; // Query 1 (Coordinador/Admin)
}

class UsuarioDAOImpl implements UsuarioDAO {

    private Connection getConnection() throws SQLException {
        // Implementa tu lógica para obtener la conexión a la base de datos
        return null; // Reemplazar con tu implementación real
    }

    @Override
    public Usuario getUsuarioById(Integer idUsuario) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUsuario);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("idUsuario"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("dni"),
                            rs.getString("direccion"),
                            rs.getByte("idRol"),
                            rs.getInt("idDistritos"),
                            rs.getInt("idEstado"),
                            rs.getBlob("foto")
                    );
                }
                return null;
            }
        }
    }

    @Override
    public List<Usuario> getAllUsuarios() throws SQLException {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("direccion"),
                        rs.getByte("idRol"),
                        rs.getInt("idDistritos"),
                        rs.getInt("idEstado"),
                        rs.getBlob("foto")
                ));
            }
        }
        return usuarios;
    }

    @Override
    public void addUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, apellido, dni, direccion, idRol, idDistritos, idEstado, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setString(3, usuario.getDni());
            pstmt.setString(4, usuario.getDireccion());
            pstmt.setByte(5, usuario.getIdRol());
            pstmt.setInt(6, usuario.getIdDistritos());
            pstmt.setInt(7, usuario.getIdEstado());
            pstmt.setBlob(8, usuario.getFoto());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, dni = ?, direccion = ?, idRol = ?, idDistritos = ?, idEstado = ?, foto = ? WHERE idUsuario = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setString(3, usuario.getDni());
            pstmt.setString(4, usuario.getDireccion());
            pstmt.setByte(5, usuario.getIdRol());
            pstmt.setInt(6, usuario.getIdDistritos());
            pstmt.setInt(7, usuario.getIdEstado());
            pstmt.setBlob(8, usuario.getFoto());
            pstmt.setInt(9, usuario.getIdUsuario()); // Add this line!
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteUsuario(Integer idUsuario) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE idUsuario = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUsuario);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Usuario iniciarSesionEncuestador(String correo, String contrasena) throws SQLException {
        // Implementación...
        return null;
    }

    @Override
    public void crearPerfilEncuestador(Usuario usuario) throws SQLException {
        // Implementación...
    }

    @Override
    public void actualizarEstadoPorCorreo(String correo, Integer idEstado) throws SQLException {
        // Implementación...
    }

    @Override
    public Usuario verPerfil(Integer idUsuario) throws SQLException {
        // Implementación...
        return null;
    }

    @Override
    public void actualizarFotoPerfil(Integer idUsuario, Blob foto) throws SQLException {
        // Implementación...
    }

    @Override
    public List<AsignacionFormulario> verFormulariosAsignados(Integer idUsuario) throws SQLException {
        // Implementación...
        return null;
    }

    @Override
    public void crearNuevoEncuestador(Usuario usuario, String correo, String contrasena) throws SQLException {
        // Implementación...
    }

    @Override
    public void desactivarUsuario(Integer idUsuario) throws SQLException {
        // Implementación...
    }

    @Override
    public List<Usuario> gestionarEncuestadores() throws SQLException {
        // Implementación...
        return null;
    }

    @Override
    public Usuario iniciarSesion(String correo, String contrasena, Byte idRol) throws SQLException {
        // Implementación...
        return null;
    }
}
