package lbj.king.proyecto.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lbj.king.proyecto.model.Play;
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

    public void changePrize(Prize p){
        p.setOwner(null);
        p.setOwned(false);
    }

    @Transactional
    public void deletePrizeById(Long id){
        pRep.deleteById(id);
    }

}
