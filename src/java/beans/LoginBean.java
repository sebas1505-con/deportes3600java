package beans;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import dao.UsuarioDAO;

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

    // ------------------ INICIAR SESIÓN ------------------
    public String iniciarSesion() {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            usuarioLogueado = dao.login(correo, clave);

            if (usuarioLogueado != null) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(true);

                session.setAttribute("usuario", usuarioLogueado);

                // 🚀 Redirección según rol
                if ("admin".equalsIgnoreCase(usuarioLogueado.getRol())) {
                    return "/admin/admin.xhtml?faces-redirect=true";
                } else if ("cliente".equalsIgnoreCase(usuarioLogueado.getRol())) {
                    return "/usuario.xhtml?faces-redirect=true"; // ✅ ahora en la raíz
                } else {
                    return "/sinacceso.xhtml?faces-redirect=true";
                }

            } else {
                FacesMessage msg = new FacesMessage("Datos incorrectos");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ------------------ CERRAR SESIÓN ------------------
    public String cerrarSesion() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);

        if (session != null) session.invalidate();

        return "/login.xhtml?faces-redirect=true";
    }

    // ------------------ VERIFICAR SESIÓN ------------------
    public void verif_sesion(String rol) {
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");

        if (usuario == null || !usuario.getRol().equalsIgnoreCase(rol)) {
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
