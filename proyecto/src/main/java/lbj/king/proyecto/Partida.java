package lbj.king.proyecto;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Partida {
    private float bet;
    private float win;
    private boolean won=false;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    private Usuario user;

    public Partida(){

    }

    public Partida(float bet, Usuario u){
        this.bet=bet;
        this.win=bet*6;
        this.user=u;
    }

    public float getWin(){
        return this.win;
    }

    public void won(){
        this.won=true;
    }
}
