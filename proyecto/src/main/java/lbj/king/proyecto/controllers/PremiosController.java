package lbj.king.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lbj.king.proyecto.model.Premio;
import lbj.king.proyecto.services.PremiosService;

@Controller
public class PremiosController {
    @Autowired
    private PremiosService premioSer;

    @GetMapping("/premios")
    public String showPremios(Model model) {
        List<Premio> premios = premioSer.getPremios();
        model.addAttribute("premios", premios);
        for (Premio p : premios) {
            model.addAttribute("jrexdutyuv", p.getOwner().getName());
        }

        return "premios";
    }
    
}
