package lbj.king.proyecto.controllers;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lbj.king.proyecto.DTO.PrizeDTO;
import lbj.king.proyecto.DTO.UserrDTO;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.model.Userr;
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
            model.addAttribute("hasImage", u.image());
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
            // premio.setOwner(user);
            // premio.setOwned(true);
            // prizeSer.save(premio);
            prizeSer.setOwnerPrize(prize.id(), user.id());

            // user.setCurrency(user.getCurrency() - premio.getPrice());
            // uSer.save(user);
            uSer.updateLessCurrencyUser(user.id(), prize.price());
        } else {
            model.addAttribute("userLogged", user);
            model.addAttribute("hasImage", user.image());
            return "prizeError";
        }

        model.addAttribute("userLogged", user);
        model.addAttribute("hasImage", user.image());
        return "redirect:/prizes";
    }

    @PostMapping("/prizes/{id}/delete")
    public String deletePrize(Model model, @PathVariable long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        UserrDTO user = uSer.findByName(principal.getName()).get();
        PrizeDTO prize = prizeSer.findById(id).orElseThrow();

        user.prizeList().remove(prize);
        uSer.save(user);
        prizeSer.deletePrizeById(id);

        model.addAttribute("userLogged", user);
        model.addAttribute("hasImage", user.image());

        return "redirect:/prizes";
    }

    @PostMapping("/prizes/new")
    public String newPrize(@RequestParam String prizeName, @RequestParam int prizeValue) {
        PrizeDTO p = new PrizeDTO(null, prizeName, prizeValue, false, null);
        prizeSer.save(p);
        return "redirect:/prizes";
    }
}
