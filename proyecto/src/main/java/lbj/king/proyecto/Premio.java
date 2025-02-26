package lbj.king.proyecto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Premio {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne
    private Usuario owner;

    public Premio(){}

    public Premio(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Usuario getOwner() {
        return owner;
    }
    public void setOwner(Usuario owner) {
        this.owner = owner;
    }
    

}
