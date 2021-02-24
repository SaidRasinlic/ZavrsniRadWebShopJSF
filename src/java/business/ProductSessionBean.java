package business;

import business.entities.Products;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProductSessionBean implements ProductSessionBeanLocal {

    @PersistenceContext(unitName = "ZavrsniRadJavaPU")
    private EntityManager entityManager;

    @Override
    public List<Products> getAllProducts() {
        try {
            Query query = entityManager.createNamedQuery("Products.findAll");
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}