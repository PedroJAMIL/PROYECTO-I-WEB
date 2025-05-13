package beans;

import java.util.Date;

public class AsignacionFormulario {
    private Integer idAsignacionFormularios;
    private Integer idEncuetador;    // Foreign Key (Encuestador)
    private Integer idFormulario;    // Foreign Key
    private Date fechaAsignacion;
    private String estado;

    public AsignacionFormulario() {
    }

    public AsignacionFormulario(Integer idAsignacionFormularios, Integer idEncuetador, Integer idFormulario, Date fechaAsignacion, String estado) {
        this.idAsignacionFormularios = idAsignacionFormularios;
        this.idEncuetador = idEncuetador;
        this.idFormulario = idFormulario;
        this.fechaAsignacion = fechaAsignacion;
        this.estado = estado;
    }

    public Integer getIdAsignacionFormularios() {
        return idAsignacionFormularios;
    }

    public void setIdAsignacionFormularios(Integer idAsignacionFormularios) {
        this.idAsignacionFormularios = idAsignacionFormularios;
    }

    public Integer getIdEncuetador() {
        return idEncuetador;
    }

    public void setIdEncuetador(Integer idEncuetador) {
        this.idEncuetador = idEncuetador;
    }

    public Integer getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Integer idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
