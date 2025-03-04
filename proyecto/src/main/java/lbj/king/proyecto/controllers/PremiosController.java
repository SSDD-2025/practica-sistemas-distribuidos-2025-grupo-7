package lbj.king.proyecto.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lbj.king.proyecto.model.Premio;
import lbj.king.proyecto.model.Usuario;
import lbj.king.proyecto.services.PremiosService;
import jakarta.servlet.http.HttpSession;


@Controller
public class PremiosController {
    @Autowired
    private PremiosService premioSer;

    @GetMapping("/premios")
    public String showPremios(Model model, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("user");
        List<Premio> premios = premioSer.getPremios();
        model.addAttribute("premios", premios);
        model.addAttribute("userLogged", user);
        model.addAttribute("hasImage", user.getImage());
        return "premios";
    }

    @GetMapping("/premios/{id}")
    public String comprarPremio(Model model,@PathVariable long id, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("user");
        Premio premio = premioSer.findById(id);
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
        return "redirect:/premios";
    }
    
}
