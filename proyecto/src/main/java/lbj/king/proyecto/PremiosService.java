package lbj.king.proyecto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.annotation.PostConstruct;

@Service
public class PremiosService {
     @Autowired
    private PremiosRepository premioRep;
    @Autowired
    private UserRepository userRep;


     @PostConstruct
    public void init() {
        Premio p1 = new Premio("Rey de la Rule");
        p1.setOwner(userRep.findByName("a"));
        Premio p2 = new Premio("Rey de los Dados");
        p2.setOwner(userRep.findByName("EjemploDos"));
        Premio p3 = new Premio("Rey de la tragaperras");
        p3.setOwner(userRep.findByName("a"));

        premioRep.save(p1);
        premioRep.save(p2);
        premioRep.save(p3); 
    }

    public List<Premio> getPremios() {

        List<Premio> premios = this.premioRep.findAll();
        return premios;
        
    }
}
