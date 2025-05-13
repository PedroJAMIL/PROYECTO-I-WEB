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

