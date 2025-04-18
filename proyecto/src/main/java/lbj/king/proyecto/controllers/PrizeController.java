package lbj.king.proyecto.controllers;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.services.PrizeService;
import lbj.king.proyecto.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PrizeController {
    @Autowired
    private PrizeService premioSer;

    @Autowired
    private UserService uSer;

    @GetMapping("/prizes")
    public String showPremios(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            Userr u = uSer.findByName(principal.getName()).get();
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.getImage());
        }
        List<Prize> premios = premioSer.getPremios();
        model.addAttribute("premios", premios);
        return "prizes";
    }

    @GetMapping("/prizes/{id}")
    public String comprarPremio(Model model, @PathVariable long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Userr user = uSer.findByName(principal.getName()).get();
        Prize premio = premioSer.findById(id);

        if (user.getCurrency() >= premio.getPrice() && !premio.getOwned()) {
            premio.setOwner(user);
            premio.setOwned(true);
            premioSer.save(premio);

            user.setCurrency(user.getCurrency() - premio.getPrice());
            uSer.save(user);
        } else {
            model.addAttribute("userLogged", user);
            model.addAttribute("hasImage", user.getImage());
            return "prizeError";
        }

        model.addAttribute("userLogged", user);
        model.addAttribute("hasImage", user.getImage());
        return "redirect:/prizes";
    }

    @PostMapping("/prizes/{id}/delete")
    public String deleteGame(Model model, @PathVariable long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Userr user = uSer.findByName(principal.getName()).get();
        Prize prize = premioSer.findById(id);

        user.getPrizeList().remove(prize);
        uSer.save(user);
        premioSer.deletePrizeById(id);

        model.addAttribute("userLogged", user);
        model.addAttribute("hasImage", user.getImage());

        return "redirect:/prizes";
    }

    @PostMapping("/prizes/new")
    public String newPrize(@RequestParam String prizeName, @RequestParam int prizeValue) {
        Prize p = new Prize(prizeName, prizeValue);
        premioSer.save(p);
        return "redirect:/prizes";
    }
}
