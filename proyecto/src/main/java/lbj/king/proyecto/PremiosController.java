package lbj.king.proyecto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PremiosController {
    @Autowired
    private PremiosService premioSer;

    @GetMapping("/premios")
    public String showPremios(Model model) {
        List<Premio> premios = premioSer.getPremios();
        model.addAttribute("premios", premios);
        for (Premio p : premios) {
            model.addAttribute("name", p);
        }

        return "premios";
    }
    
}
