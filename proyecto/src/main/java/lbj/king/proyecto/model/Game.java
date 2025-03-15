package lbj.king.proyecto.model;
import java.sql.Blob;
import java.util.*;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private float winMultp;
    private int minPossibleNumber;
    private int maxPossibleNumber;


    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Play> bets;
    @Lob
    private byte[] fich;
    @Lob
    private Blob image=null;
    
    public Game(){

    }
    public Game(String n,float wm,int minPosibleNum, int maxPosibleNum){
        this.name=n;
        this.bets=new ArrayList<Play>();
        this.winMultp=wm;
        this.minPossibleNumber=minPosibleNum;
        this.maxPossibleNumber=maxPosibleNum;
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
    public void setFich(byte[] contenidoA){
        this.fich=contenidoA;
    }
    public byte[] getFich(){
        return this.fich;
    }
    public int getMinPossibleNumber(){
        return this.minPossibleNumber;
    }
    public int getMaxPossibleNumber(){
        return this.maxPossibleNumber;
    }
    public String getName(){
        return this.name;
    }
}
