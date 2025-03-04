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
    private PremiosRepository pRep;


    @PostConstruct
    public void init() {
        Prize p1 = new Prize("Rey de la Rule",1000);
        Prize p2 = new Prize("Rey de la Tragaperras",2000);
        Prize p3 = new Prize("Rey de los Dados",3000);
        pRep.save(p1); 
        pRep.save(p2);
        pRep.save(p3);
    }

    public List<Prize> getPremios() {

        List<Prize> premios = this.pRep.findAll();
        return premios;
        
    }

    public void save(Prize p) {
        pRep.save(p);
    }

    public Prize findById(long id) {
        return pRep.findPremioById(id);
    }
}
