package com.example.webproyecto.daos;

import com.example.webproyecto.beans.Distrito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DistritoDAO {
    Distrito getDistritoById(Integer idDistrito) throws SQLException;

    List<Distrito> getAllDistritos() throws SQLException;

    void addDistrito(Distrito distrito) throws SQLException;

    void updateDistrito(Distrito distrito) throws SQLException;

    void deleteDistrito(Integer idDistrito) throws SQLException;

    List<Distrito> getDistritosByZonaId(Integer idZona) throws SQLException;
}

