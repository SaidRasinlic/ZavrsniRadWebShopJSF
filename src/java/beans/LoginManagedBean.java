package beans;

import business.LoginSessionBeanLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "loginManagedBean", eager = true) //isto sto i Component u Springu
@SessionScoped
public class LoginManagedBean implements Serializable {

    @EJB
    //@ManagedProperty("#loginSessionBean") ovo je isto sto i AutoWired
    private LoginSessionBeanLocal loginSessionBeanLocal;
    private String username;
    private String password;

    public LoginManagedBean() {
    }

    public String login() {
        boolean userLogged = loginSessionBeanLocal.login(username, password);
        FacesContext context = FacesContext.getCurrentInstance();

        if (userLogged) {
            context.getExternalContext().getSessionMap().put("user", username);
            return "index";
        }
        return "loginregister";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
/* public String login() {
        User user = userService.find(username, password);
        FacesContext context = FacesContext.getCurrentInstance();

        if (user == null) {
            context.addMessage(null, new FacesMessage("Unknown login, try again"));
            username = null;
            password = null;
            return null;
        } else {
            context.getExternalContext().getSessionMap().put("user", user);
            return "userhome?faces-redirect=true";
        }
    }
     
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }
 */
