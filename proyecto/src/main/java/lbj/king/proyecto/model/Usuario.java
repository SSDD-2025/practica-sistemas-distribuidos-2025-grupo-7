package lbj.king.proyecto.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
@Entity
public class Usuario {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    
    private String name;
    private String password;
    private float currency;

    @OneToMany(mappedBy = "owner")
    private List<Premio> premios;

    @OneToMany
    private List<Partida> lista;

    protected Usuario() {}

    public Usuario(String name,String psw){
        this.name=name;
        this.password=psw;
        this.currency=0;
        this.lista=new ArrayList<Partida>();
        this.premios=new ArrayList<Premio>();
    }

    public void setName(String name){
        this.name=name;
    }
    public void setPassword(String psw){
        this.password=psw;
    }
    public void setCurrency(float cv){
        this.currency=cv;
    }

    public String getName(){
        return this.name;
    }
    public String getPassword(){
        return this.password;
    }
    public float getCurrency(){
        return this.currency;
    }
    public List<Partida> getLista(){
        return this.lista;
    }
    public List<Premio> getPremios(){
        return this.premios;
    }
    public long getId(){
        return this.id;
    }


    @Transactional
    public void addGame(Partida p){
        this.lista.add(p);
    }
    public void addPremio(Premio p){
        this.premios.add(p);
    }

}
