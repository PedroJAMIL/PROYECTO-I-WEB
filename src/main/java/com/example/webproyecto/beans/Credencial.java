package beans;

public class Credencial {
    private Integer idCredenciales;
    private String correo;
    private String contrasenha;
    private Integer idUsuario; // Foreign Key

    public Credencial() {
    }

    public Credencial(Integer idCredenciales, String correo, String contrasenha, Integer idUsuario) {
        this.idCredenciales = idCredenciales;
        this.correo = correo;
        this.contrasenha = contrasenha;
        this.idUsuario = idUsuario;
    }

    public Integer getIdCredenciales() {
        return idCredenciales;
    }

    public void setIdCredenciales(Integer idCredenciales) {
        this.idCredenciales = idCredenciales;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
