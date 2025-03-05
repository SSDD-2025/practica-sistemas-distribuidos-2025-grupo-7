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
    

    public Game findByName(String n){
        return gameRep.findByName(n);
    }
    public void save(Game j){
        gameRep.save(j);
    }
}
