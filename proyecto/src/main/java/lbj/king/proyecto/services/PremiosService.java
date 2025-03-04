package lbj.king.proyecto.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.repositories.PremiosRepository;

@Service
public class PremiosService {
     @Autowired
    private PremiosRepository premioRep;


    @PostConstruct
    public void init() {
        Prize p1 = new Prize("Rey de la Rule",1000);
        Prize p2 = new Prize("Rey de la Tragaperras",2000);
        premioRep.save(p1); 
        premioRep.save(p2);
    }

    public List<Prize> getPremios() {

        List<Prize> premios = this.premioRep.findAll();
        return premios;
        
    }

    public void save(Prize p) {
        premioRep.save(p);
    }

    public Prize findById(long id) {
        return premioRep.findPremioById(id);
    }
}
