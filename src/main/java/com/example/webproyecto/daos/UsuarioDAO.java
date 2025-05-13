
package com.example.webproyecto.daos;
import com.example.webproyecto.beans.AsignacionFormulario;
import com.example.webproyecto.beans.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface UsuarioDAO {
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

