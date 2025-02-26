package lbj.king.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GameController {
    
    @Autowired
    private GameRepository gameRep;

    public GameController(){

    }

    @PostConstruct
    public void init(){
        Juegos g1=new Juegos("Ruleta");
        gameRep.save(g1);
        Juegos g2=new Juegos("Dados");
        gameRep.save(g2);
        Juegos g3=new Juegos("Slots");
        gameRep.save(g3);
    }

    @GetMapping("/game")
    public String getMethodName(@RequestParam String param) {
        return "inicio";
    }
    

}
