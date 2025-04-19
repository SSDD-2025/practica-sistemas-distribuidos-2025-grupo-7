package lbj.king.proyecto.services;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lbj.king.proyecto.DTO.PrizeDTO;
import lbj.king.proyecto.DTO.PrizeMapper;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.repositories.PrizeRepository;

@Service
public class PrizeService {
     @Autowired
    private PrizeRepository pRep;
    @Autowired
    private PrizeMapper mapper;

    public List<Prize> getPremios() {

        List<Prize> premios = this.pRep.findAll();
        return premios;
        
    }

    public void save(Prize p) {
        pRep.save(p);
    }

    public Optional<Prize> findById(long id) {
        return pRep.findById(id);
    }

    public void changePrize(Prize p){
        p.setOwner(null);
        p.setOwned(false);
    }

    @Transactional
    public void deletePrizeById(Long id){
        pRep.deleteById(id);
    }

    
    //para api rest
    private PrizeDTO toDTO(Prize prize) {
        return mapper.toDTO(prize);
    }


    private Prize toDomain(PrizeDTO prizeDTO) {
		return mapper.toDomain(prizeDTO);
	}


    private Collection<PrizeDTO> toDTOs(Collection<Prize> prizes) {
        return mapper.toDTOs(prizes);
    }

    
    public Collection<PrizeDTO> getPrizes() {
        return toDTOs(pRep.findAll());
    }


    public PrizeDTO getPrize(long id) {
        return toDTO(pRep.findById(id).orElseThrow()); 
    }


    public PrizeDTO createPrize(PrizeDTO prizeDTO) {
        if(prizeDTO.id() != null){
            throw new IllegalArgumentException();
        } 
        Prize prize = toDomain(prizeDTO); 
        pRep.save(prize);
        return toDTO(prize);
    }

    public PrizeDTO replacePrize(long id, PrizeDTO updatedPrizeDTO) throws SQLException {
        Prize existing = pRep.findById(id).orElseThrow();
    
        existing.setName(updatedPrizeDTO.name());
        existing.setPrice(updatedPrizeDTO.price());
        existing.setOwned(updatedPrizeDTO.owned());
    
        pRep.save(existing); 
    
        return toDTO(existing);
    }
    

    public PrizeDTO deletePrize(long id) {
        Prize prize = pRep.findById(id).orElseThrow();

		PrizeDTO prizeDTO = toDTO(prize);

		pRep.deleteById(id);

		return prizeDTO;
    }

}
