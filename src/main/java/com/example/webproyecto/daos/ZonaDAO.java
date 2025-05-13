package com.example.webproyecto.daos;

import com.example.webproyecto.beans.Zona;

import java.sql.SQLException;
import java.util.List;

public interface ZonaDAO {
    Zona getZonaById(Integer idZona) throws SQLException;

    List<Zona> getAllZonas() throws SQLException;

    void addZona(Zona zona) throws SQLException;

    void updateZona(Zona zona) throws SQLException;

    void deleteZona(Integer idZona) throws SQLException;
}
