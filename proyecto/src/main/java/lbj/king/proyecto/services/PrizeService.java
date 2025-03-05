package lbj.king.proyecto.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.repositories.PrizeRepository;

@Service
public class PrizeService {
     @Autowired
    private PrizeRepository pRep;

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
