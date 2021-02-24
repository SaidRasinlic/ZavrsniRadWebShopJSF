package beans;

import business.PurchaseSessionBeanLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "purchaseManagedBean", eager = true)
@SessionScoped
public class PurchaseManagedBean implements Serializable {

    public PurchaseManagedBean() {
    }

    @EJB
    private PurchaseSessionBeanLocal purchaseSessionBeanLocal;
    private String firstName;
    private String lastName;
    private String cardNumber;

    public String purchase() {

        boolean purchased = purchaseSessionBeanLocal.purchase(firstName, lastName, cardNumber);
        if (purchased) {
            return "index";
        }
        return "register";
    }
}
