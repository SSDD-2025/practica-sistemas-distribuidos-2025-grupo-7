package lbj.king.proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lbj.king.proyecto.model.Juegos;
import lbj.king.proyecto.model.Partida;
import lbj.king.proyecto.repositories.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRep;
    @PostConstruct
    public void init(){
        Juegos g1=new Juegos("Ruleta",36);
        gameRep.save(g1);
        Juegos g2=new Juegos("Dados",4);
        gameRep.save(g2);
        Juegos g3=new Juegos("Slots",1);
        gameRep.save(g3);
    }

    public Juegos findByName(String n){
        return gameRep.findByName(n);
    }
    public void save(Juegos j){
        gameRep.save(j);
    }
}
