package lbj.king.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.annotation.PostConstruct;
@Controller
public class PremiosController {
    @Autowired
    private PremiosRepository premioRep;
    @PostConstruct
    public void init() {
        Premio p1 = new Premio("Rey de la Rule");
        Premio p2 = new Premio("Rey de los Dados");
        Premio p3 = new Premio("Rey de la tragaperras");

        premioRep.save(p1);
        premioRep.save(p2);
        premioRep.save(p3); 
    }
    @GetMapping("/premios")
    public String getPremios(Model model) {
        model.addAttribute("premios", premioRep.findAll());
        return "premios";
    }
}
