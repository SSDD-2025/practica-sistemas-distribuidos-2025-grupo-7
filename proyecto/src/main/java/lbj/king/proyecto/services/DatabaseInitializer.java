package lbj.king.proyecto.services;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Play;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.repositories.GameRepository;
import lbj.king.proyecto.repositories.PlayRepository;
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
    @Autowired
    PlayRepository playRep;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() throws IOException{
        ClassPathResource resource = new ClassPathResource("templates/roulette.html");
        InputStream inputStream = resource.getInputStream();
        byte[] fileBytes = inputStream.readAllBytes();
        Game g1=new Game("Roulette",36,0,36);
        g1.setFich(fileBytes);
        gameRep.save(g1);

        
        resource = new ClassPathResource("templates/dice.html");
        inputStream = resource.getInputStream();
        fileBytes = inputStream.readAllBytes();
        Game g2=new Game("Dice",4,1,6);
        g2.setFich(fileBytes);
        gameRep.save(g2);

        Prize p1 = new Prize("Rey de la Rule",1000);
        Prize p2 = new Prize("Rey de la Tragaperras",2000);
        Prize p3 = new Prize("Rey de los Dados",3000);
        prizeRep.save(p1); 
        prizeRep.save(p2);
        prizeRep.save(p3);

        Userr u1 = new Userr("a", passwordEncoder.encode("a"),"ADMIN");
        u1.setCurrency(10000000);
        userRep.save(u1);
        Userr u2 = new Userr("b", passwordEncoder.encode("b"),"USER");
        userRep.save(u2);
        Userr u3 = new Userr("c", passwordEncoder.encode("c"),"USER");
        userRep.save(u3);

        
        Play play1 = new Play(10f, u1, g1); // Dados
        playRep.save(play1);

        Play play2 = new Play(20f, u1, g2); // Ruleta
        playRep.save(play2);


        Play play4 = new Play(5f, u1, g1);
        playRep.save(play4);

        Play play5 = new Play(50f, u1, g2);
        playRep.save(play5);

        Play play6 = new Play(25f, u1, g1);
        playRep.save(play6);

        Play play7 = new Play(30f, u1, g2);
        playRep.save(play7);


        Play play9 = new Play(8f, u1, g1);
        playRep.save(play9);

        Play play10 = new Play(40f, u1, g2);
        playRep.save(play10);

        
        p1.setOwned(true);
        p1.setOwner(u1);
        prizeRep.save(p1);
    }
    
}
