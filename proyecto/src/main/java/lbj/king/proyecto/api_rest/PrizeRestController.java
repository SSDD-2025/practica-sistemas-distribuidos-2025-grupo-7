package lbj.king.proyecto.api_rest;

import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import lbj.king.proyecto.DTO.PrizeDTO;
import lbj.king.proyecto.DTO.PrizeMapper;
import lbj.king.proyecto.DTO.UserrDTO;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.services.PrizeService;
import lbj.king.proyecto.services.UserService;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/prizes")
public class PrizeRestController {
    
    @Autowired
    private PrizeService prizeService;
    @Autowired
    private UserService userService;
    @Autowired
    private PrizeMapper prizeMapper;

    @GetMapping("/")
    public Page<PrizeDTO> getPrizes(Pageable pageable) {
        return prizeService.getPrizesPageable(pageable);
    }

    @GetMapping("/{id}")
    public PrizeDTO getPrizeById(@PathVariable long id) {
        return prizeService.getPrize(id);
    }

    @PostMapping("/")
    public ResponseEntity<PrizeDTO> createPrize(@RequestBody PrizeDTO prizeDTO) {

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

    @PostMapping("/{id}/buy")
    public PrizeDTO buyPrize(@PathVariable long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserrDTO user = userService.findByName(username).orElseThrow();
        PrizeDTO prize = prizeService.findById(id).orElseThrow();
        if (user.currency() >= prize.price() && !prize.owned()) {

            // prize.setOwner(user);
            // prize.setOwned(true);
            // prizeService.save(prize);
            prizeService.setOwnerPrize(prize.id(), user.id());

            // user.setCurrency(user.getCurrency() - prize.getPrice());
            userService.updateLessCurrencyUser(user.id(), prize.price());

        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient currency or prize already owned");
        } 

        // return prizeMapper.toDTO(prize);
        PrizeDTO updatedPrize = prizeService.findById(prize.id()).orElseThrow();
        return updatedPrize;
    }
    
}
