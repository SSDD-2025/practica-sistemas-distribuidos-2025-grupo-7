package lbj.king.proyecto.services;
import java.util.List;
import java.util.Optional;

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
    public Optional<Game> findById(long id){
        return gameRep.findById(id);
    }
    public void save(Game j){
        gameRep.save(j);
    }
    public List<Game> getGames(){
        return gameRep.findAll();
    }
}
