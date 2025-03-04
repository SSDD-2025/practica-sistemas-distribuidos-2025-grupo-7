package lbj.king.proyecto.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.repositories.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRep;
    @PostConstruct
    public void init(){
        Game g1=new Game("Ruleta",36);
        gameRep.save(g1);
        Game g2=new Game("Dados",4);
        gameRep.save(g2);
        Game g3=new Game("Slots",1);
        gameRep.save(g3);
    }

    public Game findByName(String n){
        return gameRep.findByName(n);
    }
    public void save(Game j){
        gameRep.save(j);
    }
}
