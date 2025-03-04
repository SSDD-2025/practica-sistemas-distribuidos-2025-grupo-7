package lbj.king.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import lbj.king.proyecto.model.Juegos;
import lbj.king.proyecto.model.Usuario;
import lbj.king.proyecto.services.GameService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GameController {
    
    @Autowired
    private GameService gameSer;   

    @GetMapping("/game")
    public String getMethodName(@RequestParam String param) {
        return "inicio";
    }
    @GetMapping("/slot")
    public String getSlot(Model model, HttpSession session) {
        Usuario u=(Usuario)session.getAttribute("user");
        if(u!=null){
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.getImage() != null);
        }
        return "slot";
    }
    @GetMapping("/dados")
    public String getDado(Model model, HttpSession session) {
        Usuario u=(Usuario)session.getAttribute("user");
        if(u!=null){
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.getImage() != null);
        }
        return "dados";
    }
    @GetMapping("/rule")
    public String getRule(Model model, HttpSession session) {
        Usuario u=(Usuario)session.getAttribute("user");
        if(u!=null){
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.getImage() != null);
        }
        return "rule";
    }

    @GetMapping("/historialR")
    public String getHR(Model model, HttpSession session) {
        Usuario u = (Usuario)session.getAttribute("user");
        Juegos j= gameSer.findByName("Ruleta");
        model.addAttribute("userLogged", u);
        model.addAttribute("hasImage", u.getImage());
        model.addAttribute("listGames", j.getList());
        return "historial";
    }
    @GetMapping("/historialD")
    public String getHD(Model model, HttpSession session) {
        Usuario u = (Usuario)session.getAttribute("user");
        Juegos j= gameSer.findByName("Dados");
        model.addAttribute("userLogged", u);
        model.addAttribute("hasImage", u.getImage());
        model.addAttribute("listGames", j.getList());
        return "historial";
    }
    
    

}
