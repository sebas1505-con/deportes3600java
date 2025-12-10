package beans;

import dao.LoginDAO;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String correo;
    private String clave;
    private Usuario usuarioLogueado;

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public Usuario getUsuarioLogueado() { return usuarioLogueado; }

    public String iniciarSesion() {
        try {
            LoginDAO loginDao = new LoginDAO();
            usuarioLogueado = loginDao.login(correo, clave);

            if (usuarioLogueado != null) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(true);
                session.setAttribute("usuario", usuarioLogueado);

                if ("admin".equalsIgnoreCase(usuarioLogueado.getRol())) {
                    return "/admin?faces-redirect=true";
                } else {
                    return "/usuario?faces-redirect=true";
                }
            }

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos incorrectos"));
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Problema al iniciar sesión"));
            return null;
        }
    }

    public String cerrarSesion() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);

        if (session != null) session.invalidate();

        return "/login.xhtml?faces-redirect=true";
    }

    public void verifSesionUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");

        if (usuario == null || "admin".equalsIgnoreCase(usuario.getRol())) {
            try {
                context.getExternalContext().redirect(
                    context.getExternalContext().getRequestContextPath() + "/sinacceso.xhtml"
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void verifSesionAdmin() {
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");

        if (usuario == null || !"admin".equalsIgnoreCase(usuario.getRol())) {
            try {
                context.getExternalContext().redirect(
                    context.getExternalContext().getRequestContextPath() + "/sinacceso.xhtml"
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
