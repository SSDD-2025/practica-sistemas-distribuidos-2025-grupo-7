package lbj.king.proyecto.model;
import java.util.*;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Juegos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private float winMultp;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partida> bets;
    
    public Juegos(){

    }
    public Juegos(String n,float wm){
        this.name=n;
        this.bets=new ArrayList<Partida>();
        this.winMultp=wm;
    }

    public long getId(){
        return id;
    }

    public void addPlay(Partida p){
        this.bets.add(p);
    }
    public float getWinMultp(){
        return this.winMultp;
    }
}
