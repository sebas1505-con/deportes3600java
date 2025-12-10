package modelo;

import java.io.Serializable;

public class Administrador implements Serializable {
    private static final long serialVersionUID = 1L;

    private int pk_idAdministrador;
    private String admCorreo;
    private String usuario;
    private String contrasena; // sin ñ
    private String telefono;
    private String codigo;
    private String rol;

    // GETTERS Y SETTERS
    public int getPk_idAdministrador() { return pk_idAdministrador; }
    public void setPk_idAdministrador(int pk_idAdministrador) { this.pk_idAdministrador = pk_idAdministrador; }

    public String getAdmCorreo() { return admCorreo; }
    public void setAdmCorreo(String admCorreo) { this.admCorreo = admCorreo; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
