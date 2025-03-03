package lbj.king.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;

@Entity
public class Prize {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Integer price;
    private Boolean owned = false;

    @ManyToOne
    private Userr owner;

    protected Prize(){}
    public Prize(String name, Integer price){
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Boolean getOwned() {
        return owned;
    }
    public void setOwned(Boolean owned) {
        this.owned = owned;
    }
    public Userr getOwner() {
        return owner;
    }
    public void setOwner(Userr owner) {
        this.owner = owner;
    }
    

}
