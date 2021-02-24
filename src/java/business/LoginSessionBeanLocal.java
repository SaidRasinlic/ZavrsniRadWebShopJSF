package business;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Local
public interface LoginSessionBeanLocal {

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean login(String username, String password);
}
