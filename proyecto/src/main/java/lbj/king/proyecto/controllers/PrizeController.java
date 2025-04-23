package lbj.king.proyecto.controllers;
import java.security.Principal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lbj.king.proyecto.DTO.PrizeDTO;
import lbj.king.proyecto.DTO.UserrCompleteDTO;
import lbj.king.proyecto.DTO.UserrDTO;
import lbj.king.proyecto.services.PrizeService;
import lbj.king.proyecto.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PrizeController {
    @Autowired
    private PrizeService prizeSer;

    @Autowired
    private UserService uSer;

    @GetMapping("/prizes")
    public String showPremios(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            UserrDTO u = uSer.findByName(principal.getName()).get();
            model.addAttribute("userLogged", u);
            UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();

            model.addAttribute("hasImage", uAux.image());
        }
        Collection<PrizeDTO> prizes = prizeSer.getPrizes();
        model.addAttribute("premios", prizes);
        return "prizes";
    }

    @GetMapping("/prizes/{id}")
    public String comprarPremio(Model model, @PathVariable long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        UserrDTO user = uSer.findByName(principal.getName()).get();
        PrizeDTO prize = prizeSer.findById(id).orElseThrow();

        if (user.currency() >= prize.price() && !prize.owned()) {
            prizeSer.setOwnerPrize(prize.id(), user.id());

            uSer.updateLessCurrencyUser(user.id(), prize.price());
        } else {
            model.addAttribute("userLogged", user);
            UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();

            model.addAttribute("hasImage", uAux.image());
            return "prizeError";
        }

        model.addAttribute("userLogged", user);
        UserrCompleteDTO userAux = uSer.findByNameComplete(principal.getName()).orElseThrow();

        model.addAttribute("hasImage", userAux.image());
        return "redirect:/prizes";
    }

    @PostMapping("/prizes/{id}/delete")
    public String deletePrize(Model model, @PathVariable long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        PrizeDTO prize = prizeSer.findById(id).orElseThrow();
        UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();

        uAux.prizeList().remove(prize);
        uSer.saveComplete(uAux);
        prizeSer.deletePrizeById(id);

        model.addAttribute("userLogged", uAux);

        model.addAttribute("hasImage", uAux.image());

        return "redirect:/prizes";
    }

    @PostMapping("/prizes/new")
    public String newPrize(@RequestParam String prizeName, @RequestParam int prizeValue) {
        PrizeDTO p = new PrizeDTO(null, prizeName, prizeValue, false, null);
        prizeSer.save(p);
        return "redirect:/prizes";
    }
}
