package business;

import business.entities.User;
import business.passwordhash.EncryptPasswordWithPBKDF2WithHmacSHA1;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RegisterSessionBean implements RegisterSessionBeanLocal {

    @PersistenceContext(unitName = "ZavrsniRadJavaPU")
    private EntityManager entityManager;

    @Override
    public boolean register(String name, String surname, String username, String password) {
        Query query = entityManager.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        List<User> userList = query.getResultList();
        if (userList.isEmpty()) {
            User user = new User();
            user.setFirstName(name);
            user.setLastName(surname);
            user.setUsername(username);
            try {
                user.setPassword(EncryptPasswordWithPBKDF2WithHmacSHA1.createHash(password));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(RegisterSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                ex.printStackTrace();
            }
            entityManager.persist(user);
            return true;
        } else {
            return false;
        }
    }
}