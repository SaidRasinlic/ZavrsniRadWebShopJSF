package business;

import business.entities.User;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Local
public interface PurchaseSessionBeanLocal {

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean purchase(String cardNumber, String name, String surname);
}
