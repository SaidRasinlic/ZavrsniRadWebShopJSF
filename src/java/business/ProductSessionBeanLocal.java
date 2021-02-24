package business;

import business.entities.Products;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProductSessionBeanLocal {

    public List<Products> getAllProducts();
}