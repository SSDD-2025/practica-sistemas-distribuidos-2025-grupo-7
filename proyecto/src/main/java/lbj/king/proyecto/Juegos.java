package lbj.king.proyecto;
import java.util.*;

import jakarta.annotation.Generated;
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
    private float winrate=0;

    @OneToMany
    private List<Partida> bets;
    
    public Juegos(){

    }
    public Juegos(String n){
        this.name=n;
        this.bets=new ArrayList<Partida>();
    }

    public long getId(){
        return id;
    }

    public void addPlay(Partida p){
        this.bets.add(p);
    }
}
