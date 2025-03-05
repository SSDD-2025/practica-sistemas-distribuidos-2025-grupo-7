package lbj.king.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.repositories.GameRepository;
import lbj.king.proyecto.repositories.PrizeRepository;
import lbj.king.proyecto.repositories.UserRepository;

@Service
public class DatabaseInitializer {
    
    @Autowired
    UserRepository userRep;
    @Autowired
    GameRepository gameRep;
    @Autowired
    PrizeRepository prizeRep;

    @PostConstruct
    public void init(){
        Game g1=new Game("Ruleta",36);
        gameRep.save(g1);
        Game g2=new Game("Dados",4);
        gameRep.save(g2);
        Game g3=new Game("Slots",1);
        gameRep.save(g3);

        Prize p1 = new Prize("Rey de la Rule",1000);
        Prize p2 = new Prize("Rey de la Tragaperras",2000);
        Prize p3 = new Prize("Rey de los Dados",3000);
        prizeRep.save(p1); 
        prizeRep.save(p2);
        prizeRep.save(p3);

        Userr u1 = new Userr("a", "a");
        userRep.save(u1);
        Userr u2 = new Userr("EjemploDos", "espa1bila");
        userRep.save(u2);
    }
    
}
