package business.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "purchase_product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseProduct.findAll", query = "SELECT p FROM PurchaseProduct p")
    , @NamedQuery(name = "PurchaseProduct.findById", query = "SELECT p FROM PurchaseProduct p WHERE p.id = :id")
    , @NamedQuery(name = "PurchaseProduct.findByQuantity", query = "SELECT p FROM PurchaseProduct p WHERE p.quantity = :quantity")})
public class PurchaseProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)

    @Column(name = "quantity")
    private int quantity;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne
    private Products productId;
    @JoinColumn(name = "purchase_id", referencedColumnName = "purchase_id")
    @ManyToOne
    private Purchase purchaseId;

    public PurchaseProduct() {
    }

    public PurchaseProduct(Integer id) {
        this.id = id;
    }

    public PurchaseProduct(Integer id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    public Purchase getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Purchase purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseProduct)) {
            return false;
        }
        PurchaseProduct other = (PurchaseProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.model.PurchaseProduct[ id=" + id + " ]";
    }

}
