package lbj.king.proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.annotation.PostConstruct;
import lbj.king.proyecto.model.Premio;
import lbj.king.proyecto.repositories.PremiosRepository;
import lbj.king.proyecto.repositories.UserRepository;

@Service
public class PremiosService {
     @Autowired
    private PremiosRepository premioRep;
    @Autowired
    private UserRepository userRep;


    @PostConstruct
    public void init() {
        Premio p1 = new Premio("Rey de la Rule");
        premioRep.save(p1); 
    }

    public List<Premio> getPremios() {

        List<Premio> premios = this.premioRep.findAll();
        return premios;
        
    }
}
