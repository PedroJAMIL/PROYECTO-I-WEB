package beans;

import java.sql.Blob;

public class Usuario {
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    private Byte idRol;       // Foreign Key
    private Integer idDistritos; // Foreign Key
    private Integer idEstado;    // Foreign Key
    private Blob foto;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nombre, String apellido, String dni, String direccion, Byte idRol, Integer idDistritos, Integer idEstado, Blob foto) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.idRol = idRol;
        this.idDistritos = idDistritos;
        this.idEstado = idEstado;
        this.foto = foto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Byte getIdRol() {
        return idRol;
    }

    public void setIdRol(Byte idRol) {
        this.idRol = idRol;
    }

    public Integer getIdDistritos() {
        return idDistritos;
    }

    public void setIdDistritos(Integer idDistritos) {
        this.idDistritos = idDistritos;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }
}
