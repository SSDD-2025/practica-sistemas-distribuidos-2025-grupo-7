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
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private float winMultp;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Play> bets;
    
    public Game(){

    }
    public Game(String n,float wm){
        this.name=n;
        this.bets=new ArrayList<Play>();
        this.winMultp=wm;
    }

    public long getId(){
        return id;
    }

    public void addPlay(Play p){
        this.bets.add(p);
    }
    public float getWinMultp(){
        return this.winMultp;
    }
    public List<Play> getList(){
        return this.bets;
    }
}
