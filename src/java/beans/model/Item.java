package beans.model;

import java.io.Serializable;

public class Item implements Serializable {

    private Integer id;
    private String brand;


    public Item(Integer id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }   
}