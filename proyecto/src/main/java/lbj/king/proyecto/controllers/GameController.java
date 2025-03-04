package lbj.king.proyecto.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {
    
    @Autowired
    private GameService gameSer;   

    @GetMapping("/game")
    public String getMain(@RequestParam String param) {
        return "main";
    }
    @GetMapping("/slot")
    public String getSlot(Model model, HttpSession session) {
        Userr u=(Userr)session.getAttribute("user");
        if(u!=null){
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.getImage() != null);
        }
        return "slot";
    }
    @GetMapping("/dice")
    public String getDice(Model model, HttpSession session) {
        Userr u=(Userr)session.getAttribute("user");
        if(u!=null){
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.getImage() != null);
        }
        return "dice";
    }
    @GetMapping("/roulette")
    public String getRoulette(Model model, HttpSession session) {
        Userr u=(Userr)session.getAttribute("user");
        if(u!=null){
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.getImage() != null);
        }
        return "roulette";
    }

    @GetMapping("/rouletteRecord")
    public String getHR(Model model, HttpSession session) {
        Userr u = (Userr)session.getAttribute("user");
        Game g= gameSer.findByName("Ruleta");
        model.addAttribute("userLogged", u);
        model.addAttribute("hasImage", u.getImage());
        model.addAttribute("listGames", g.getList());
        return "record";
    }
    @GetMapping("/diceRecord")
    public String getHD(Model model, HttpSession session) {
        Userr u = (Userr)session.getAttribute("user");
        Game g= gameSer.findByName("Dados");
        model.addAttribute("userLogged", u);
        model.addAttribute("hasImage", u.getImage());
        model.addAttribute("listGames", g.getList());
        return "record";
    }
    
    

}
