package lbj.king.proyecto.controllers;

import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lbj.king.proyecto.DTO.PrizeDTO;
import lbj.king.proyecto.services.PrizeService;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/prizes")
public class PrizeRestController {
    
    @Autowired
    private PrizeService prizeService;

    @GetMapping("/")
    public Collection<PrizeDTO> getPrizes() {
        return prizeService.getPrizes();
    }

    @GetMapping("/{id}")
    public PrizeDTO getPrizeById(Long id) {
        return prizeService.getPrize(id);
    }

    @PostMapping("/")
    public ResponseEntity<PrizeDTO> createBook(@RequestBody PrizeDTO prizeDTO) {

		prizeDTO = prizeService.createPrize(prizeDTO);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(prizeDTO.id()).toUri();

		return ResponseEntity.created(location).body(prizeDTO);
	}

    @PutMapping("/{id}")
	public PrizeDTO replacePrize(@PathVariable long id, @RequestBody PrizeDTO updatedPrizeDTO) throws SQLException {

		return prizeService.replacePrize(id, updatedPrizeDTO);
	}

    @DeleteMapping("/{id}")
    public PrizeDTO deletePrize(@PathVariable long id) {
        return prizeService.deletePrize(id);
    }
}
