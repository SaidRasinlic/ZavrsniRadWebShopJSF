package beans;

import beans.model.ShoppingCartItem;
import business.ProductSessionBeanLocal;
import business.entities.Products;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "productManagedBean", eager = true)
@SessionScoped
public class ProductManagedBean implements Serializable {

    @EJB
    private ProductSessionBeanLocal productSessionBeanLocal;
    private static Map<ShoppingCartItem, Integer> availableItems = new HashMap<>();
    private int quantity;
    private final List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

    public ProductManagedBean() {
    }

    public void submit() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/faces/creditcard.xhtml");
    }

    public void nastaviKupovinu() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "#Proizvodi");
    }

    public void nazadNaKorpu() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/faces/korpa.xhtml");
    }

    @SuppressWarnings("unchecked")
    public void invalidateSession() {
        if (FacesContext.getCurrentInstance() != null) {
            Map m = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            for (Object o : m.keySet()) {
                m.put(o, null);
            }
        }
    }

    /*    public String logbroout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();

        final HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        request.getSession(false).invalidate();
        return "/faces/nosession.xhtml";
    }

    public void logout() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath());
    }

    public synchronized void clear() { // metoda da ocisti kompletnu korpu
        shoppingCartItems.clear();
        quantity = 0;
         totalItems = 0;
    } 
    
     */
    public BigDecimal total() { // metoda koja prikazuje totalni iznos artikala
        double s = 0;
        for (ShoppingCartItem item : this.shoppingCartItems) {
            s += item.getProduct().getProductPrice().doubleValue() * item.getQuantity();
        }
        return BigDecimal.valueOf(s).setScale(2, RoundingMode.UP);
    }

    public void addProductToCart(Products product) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        if (session.getAttribute("user") == null) {
            facesContext.getExternalContext().redirect("faces/loginregister.xhtml");
        } else {

            for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
                if (product.getProductId() == shoppingCartItem.getProduct().getProductId()) {
                    shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + quantity);
                    return;
                }
            }
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, quantity);

            shoppingCartItems.add(shoppingCartItem);
        }
    }

    public void removeProduct(Products product) {
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            if (product.getProductId() == shoppingCartItem.getProduct().getProductId()) {
                shoppingCartItems.remove(shoppingCartItem);
            }
        }
    }

    public String delete(ShoppingCartItem shoppingCartItem) {

        this.shoppingCartItems.remove(shoppingCartItem);
        return "";
    }

    public int getTotalItems() {
        int count = 0;
        for (int i = 0; i < shoppingCartItems.size(); i++) {
            count += shoppingCartItems.get(i).getQuantity();
        }
        return count;
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Products> getAllProducts() {
        return productSessionBeanLocal.getAllProducts();
    }

    public void setAvailableItems(Map<ShoppingCartItem, Integer> availableItems) {
        ProductManagedBean.availableItems = availableItems;
    }

    public Map<ShoppingCartItem, Integer> getAvailableItems() {
        return availableItems;
    }
}

//https://www.ntu.edu.sg/home/ehchua/programming/java/JavaServletCaseStudyPart2.html up
//https://stackoverflow.com/questions/19890277/pselectonemenu-get-just-first-value-from-list-primefaces-jsf
//https://stackoverflow.com/questions/28104445/input-field-in-hdatatable-always-receives-value-of-last-item-in-list
/*
        public BigDecimal total() {
        BigDecimal s = null;
        for (ShoppingCartItem item : this.shoppingCartItems) {
            s = item.getProduct().getProductPrice().multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.UP);
            s = s.add(s);
        }
        return s;
    }
    
        double s = 0;
        for (ShoppingCartItem item : this.shoppingCartItems) {
            s += item.getProduct().getProductPrice().doubleValue() * item.getQuantity();
        }
        return s;
 */
