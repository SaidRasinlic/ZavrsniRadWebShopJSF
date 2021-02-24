package business.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "purchase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchase.findAll", query = "SELECT p FROM Purchase p")
    , @NamedQuery(name = "Purchase.findByPurchaseId", query = "SELECT p FROM Purchase p WHERE p.purchaseId = :purchaseId")
    , @NamedQuery(name = "Purchase.findByCardNumber", query = "SELECT p FROM Purchase p WHERE p.cardNumber = :cardNumber")
    , @NamedQuery(name = "Purchase.findByName", query = "SELECT p FROM Purchase p WHERE p.name = :name")
    , @NamedQuery(name = "Purchase.findBySurname", query = "SELECT p FROM Purchase p WHERE p.surname = :surname")
})
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "purchase_id")
    private Integer purchaseId;
    @Basic(optional = false)

    @Column(name = "card_number")
    private String cardNumber;
    @Basic(optional = false)

    @Column(name = "name")
    private String name;
    @Basic(optional = false)

    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)

    @OneToMany(mappedBy = "purchaseId")
    private List<PurchaseProduct> purchaseProductList;
    @JoinColumn(name = "user_id", referencedColumnName   = "user_id")
    @ManyToOne(optional = false)
    private User userId;

    public Purchase() {
    }

    public Purchase(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Purchase(Integer purchaseId, String cardNumber, String name, String surname) {
        this.purchaseId = purchaseId;
        this.cardNumber = cardNumber;
        this.name = name;
        this.surname = surname;

    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @XmlTransient
    public List<PurchaseProduct> getPurchaseProductList() {
        return purchaseProductList;
    }

    public void setPurchaseProductList(List<PurchaseProduct> purchaseProductList) {
        this.purchaseProductList = purchaseProductList;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseId != null ? purchaseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Purchase)) {
            return false;
        }
        Purchase other = (Purchase) object;
        if ((this.purchaseId == null && other.purchaseId != null) || (this.purchaseId != null && !this.purchaseId.equals(other.purchaseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.model.Purchase[ purchaseId=" + purchaseId + " ]";
    }

}
