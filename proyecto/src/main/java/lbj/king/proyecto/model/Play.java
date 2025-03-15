package lbj.king.proyecto.model;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Play {
    private float bet;
    private float win;
    private boolean won=false;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    private Userr user;
    @ManyToOne
    private Game game;

    public Play(){

    }

    public Play(float bet, Userr u,Game j){
        this.bet=bet;
        this.user=u;
        this.game=j;
    }

    public float getWin(){
        return this.win;
    }

    public void won(){
        this.won=true;
    }
    public float getBet(){
        return bet;
    }
    public void setWin(float w){
        this.win=w;
    }
    public Game getGame(){
        return this.game;
    }
}
