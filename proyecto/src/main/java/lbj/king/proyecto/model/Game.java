package lbj.king.proyecto.model;
import java.sql.Blob;
import java.util.*;

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
    private Long id;

    private String name;
    private float winMultp;
    private int minPossibleNumber;
    private int maxPossibleNumber;


    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Play> bets;
    @Lob
    private byte[] fich;
    private boolean hasFich=false;

    
    public Game(){

    }
    public Game(String n,float wm,int minPosibleNum, int maxPosibleNum){
        this.name=n;
        this.bets=new ArrayList<Play>();
        this.winMultp=wm;
        this.minPossibleNumber=minPosibleNum;
        this.maxPossibleNumber=maxPosibleNum;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean hasFich(){
        return this.hasFich;
    }
    public void setHasFich(boolean hasFich){
        this.hasFich=hasFich;
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
    public void setName(String name){
        this.name = name;
    }
    public void setWinMultp(float winMultp){
        this.winMultp = winMultp;
    }
    public void setMinPossibleNumber(int minPosibleNumber){
        this.minPossibleNumber = minPosibleNumber;
    }
    public void setMaxPossibleNumber(int maxPossibleNumber){
        this.maxPossibleNumber = maxPossibleNumber;
    }

}
