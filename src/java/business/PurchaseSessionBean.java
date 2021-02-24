package business;

import business.entities.Purchase;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PurchaseSessionBean implements PurchaseSessionBeanLocal {

    @PersistenceContext(unitName = "ZavrsniRadJavaPU")
    private EntityManager entityManager;

    @Override
    public boolean purchase(String cardNumber, String name, String surname) {
        Purchase purchase = new Purchase(null, cardNumber, name, surname);
        entityManager.persist(purchase);
        return true;
    }

}
