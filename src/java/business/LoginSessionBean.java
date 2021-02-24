package business;

import business.entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LoginSessionBean implements LoginSessionBeanLocal {

    @PersistenceContext(unitName = "ZavrsniRadJavaPU")
    private EntityManager entityManager;

    @Override
    public boolean login(String username, String password) {
        try {
            Query query = entityManager.createNamedQuery("User.findLoggedUser");
            query.setParameter("username", username);
            query.setParameter("password", password);
            User loggedUsers = (User) query.getSingleResult();
            if (loggedUsers != null && username.equals(loggedUsers.getUsername()) && password.equals(loggedUsers.getPassword())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
