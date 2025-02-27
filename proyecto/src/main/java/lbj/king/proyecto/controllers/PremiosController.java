package lbj.king.proyecto.controllers;

import java.net.http.HttpClient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lbj.king.proyecto.model.Premio;
import lbj.king.proyecto.model.Usuario;
import lbj.king.proyecto.repositories.PremiosRepository;
import lbj.king.proyecto.services.PremiosService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "premios";
    }

    @GetMapping("/premios/{id}")
    public String comprarPremio(Model model,@PathVariable long id, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("user");
        if (user == null) {
            return "login";
        }else{
            Premio premio = premioSer.findById(id);
            premio.setOwner(user);
            premioSer.save(premio);
        }
        model.addAttribute("userLogged", user);
        return "redirect:/premios";
    }
    
}
