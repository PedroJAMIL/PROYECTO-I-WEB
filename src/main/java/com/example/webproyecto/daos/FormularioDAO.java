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
public interface FormularioDAO {
    Formulario getFormularioById(Integer idFormulario) throws SQLException;

    List<Formulario> getAllFormularios() throws SQLException;

    void addFormulario(Formulario formulario) throws SQLException;

    void updateFormulario(Formulario formulario) throws SQLException;

    void deleteFormulario(Integer idFormulario) throws SQLException;

    List<Formulario> getFormulariosByCoordinadorId(Integer idCoordinador) throws SQLException; // New method
}

