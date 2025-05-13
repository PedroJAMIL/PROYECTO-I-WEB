package com.example.webproyecto.daos;

import com.example.webproyecto.beans.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EstadoDAO {
    Estado getEstadoById(Integer idEstado) throws SQLException;

    List<Estado> getAllEstados() throws SQLException;

    void addEstado(Estado estado) throws SQLException;

    void updateEstado(Estado estado) throws SQLException;

    void deleteEstado(Integer idEstado) throws SQLException;
}

