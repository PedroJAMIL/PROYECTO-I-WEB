package com.example.webproyecto.beans;

import java.util.Date;

public class Formulario {
    private Integer idFormulario;
    private String titulo;
    private String descripcion;
    private Date fechaCreacion;
    private Integer idCoordinador; // Foreign Key
    private Integer idCarpeta;     // Foreign Key
    private String formularioscol;

    public Formulario() {
    }

    public Formulario(Integer idFormulario, String titulo, String descripcion, Date fechaCreacion, Integer idCoordinador, Integer idCarpeta, String formularioscol) {
        this.idFormulario = idFormulario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.idCoordinador = idCoordinador;
        this.idCarpeta = idCarpeta;
        this.formularioscol = formularioscol;
    }

    public Integer getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Integer idFormulario) {
        this.idFormulario = idFormulario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(Integer idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public Integer getIdCarpeta() {
        return idCarpeta;
    }

    public void setIdCarpeta(Integer idCarpeta) {
        this.idCarpeta = idCarpeta;
    }

}
