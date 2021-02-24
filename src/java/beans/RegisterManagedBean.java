package beans;

import business.RegisterSessionBeanLocal;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "registerManagedBean", eager = true)
@SessionScoped
public class RegisterManagedBean implements Serializable {

    @EJB
    private RegisterSessionBeanLocal registerSessionBeanLocal;
    private String message = "";
    private String name;
    private String surname;
    private String username;
    private String password;

    public RegisterManagedBean() {
    }

    public String register() throws NoSuchAlgorithmException, InvalidKeySpecException {

        boolean userCreated = registerSessionBeanLocal.register(name, surname, username, password);
        if (userCreated) {
            return "index";
        }
        message = "Unijeto korisničko ime je zauzeto";
        return "register";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}