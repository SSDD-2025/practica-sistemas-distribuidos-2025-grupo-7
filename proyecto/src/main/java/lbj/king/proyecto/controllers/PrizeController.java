package lbj.king.proyecto.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.services.PremiosService;
import jakarta.servlet.http.HttpSession;


@Controller
public class PrizeController {
    @Autowired
    private PremiosService premioSer;

    @GetMapping("/prizes")
    public String showPremios(Model model, HttpSession session) {
        Userr user = (Userr) session.getAttribute("user");
        List<Prize> premios = premioSer.getPremios();
        model.addAttribute("premios", premios);
        if(user != null){
            model.addAttribute("userLogged", user);
            model.addAttribute("hasImage", user.getImage());
        }
        return "prizes";
    }

    @GetMapping("/prizes/{id}")
    public String comprarPremio(Model model,@PathVariable long id, HttpSession session) {
        Userr user = (Userr) session.getAttribute("user");
        Prize premio = premioSer.findById(id);
        if (user == null) {
            return "login";
        }else{
            if (user.getCurrency()>=premio.getPrice() && !premio.getOwned()){
            premio.setOwner(user);
            premio.setOwned(true);
            premioSer.save(premio);
            user.setCurrency(user.getCurrency()-premio.getPrice());
            }else{
                //Redirigir a error(falta pasta)
            }
        }
        model.addAttribute("userLogged", user);
        model.addAttribute("hasImage", user.getImage());
        return "redirect:/prizes";
    }
    
}
