package dao;

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
interface RolDAO {
    Rol getRolById(Byte idRol) throws SQLException;

    List<Rol> getAllRoles() throws SQLException;

    void addRol(Rol rol) throws SQLException;

    void updateRol(Rol rol) throws SQLException;

    void deleteRol(Byte idRol) throws SQLException;

    Rol getRolByNombre(String nombreRol) throws SQLException; // Nuevo método
}

class RolDAOImpl implements RolDAO {

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

//-------------------- Zonas DAO --------------------
interface ZonaDAO {
    Zona getZonaById(Integer idZona) throws SQLException;

    List<Zona> getAllZonas() throws SQLException;

    void addZona(Zona zona) throws SQLException;

    void updateZona(Zona zona) throws SQLException;

    void deleteZona(Integer idZona) throws SQLException;
}

class ZonaDAOImpl implements ZonaDAO {

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

//-------------------- Distritos DAO --------------------
interface DistritoDAO {
    Distrito getDistritoById(Integer idDistrito) throws SQLException;

    List<Distrito> getAllDistritos() throws SQLException;

    void addDistrito(Distrito distrito) throws SQLException;

    void updateDistrito(Distrito distrito) throws SQLException;

    void deleteDistrito(Integer idDistrito) throws SQLException;

    List<Distrito> getDistritosByZonaId(Integer idZona) throws SQLException;
}

class DistritoDAOImpl implements DistritoDAO {

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

//-------------------- Estados DAO --------------------
interface EstadoDAO {
    Estado getEstadoById(Integer idEstado) throws SQLException;

    List<Estado> getAllEstados() throws SQLException;

    void addEstado(Estado estado) throws SQLException;

    void updateEstado(Estado estado) throws SQLException;

    void deleteEstado(Integer idEstado) throws SQLException;
}

class EstadoDAOImpl implements EstadoDAO {

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

//-------------------- Usuarios DAO --------------------
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
        String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, dni = ?, direccion = ?, idRol = ?, idDistritos = ?, idEstado = ?, foto