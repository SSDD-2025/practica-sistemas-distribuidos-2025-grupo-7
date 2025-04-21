package lbj.king.proyecto.controllers;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import lbj.king.proyecto.DTO.GameDTO;
import lbj.king.proyecto.DTO.UserrDTO;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.services.GameService;
import lbj.king.proyecto.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GameController {
    
    @Autowired
    private GameService gameSer;  
    @Autowired
    private UserService uSer; 

    @GetMapping("/game")
    public String getMain(@RequestParam String param) {
        return "main";
    }
    @GetMapping("/slot")
    public String getSlot(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            UserrDTO u = uSer.findByName(principal.getName()).get();
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.image());
        }
        return "slot";
    }
    @GetMapping("/dice")
    public String getDice(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            UserrDTO u = uSer.findByName(principal.getName()).get();
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.image());
        }
        return "dice";
    }
    @GetMapping("/roulette")
    public String getRoulette(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            UserrDTO u = uSer.findByName(principal.getName()).get();
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.image());
        }
        return "roulette";
    }

    @GetMapping("/rouletteRecord")
    public String getHR(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            UserrDTO u = uSer.findByName(principal.getName()).get();
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.image());
        }
        GameDTO g= gameSer.findByName("Roulette").orElseThrow();
        model.addAttribute("listGames", g.playList());
        return "record";
    }
    @GetMapping("/diceRecord")
    public String getHD(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            UserrDTO u = uSer.findByName(principal.getName()).get();
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.image());
        }
        GameDTO g= gameSer.findByName("Dice").orElseThrow();
        model.addAttribute("listGames", g.playList());//es lista de partidas no de games :(
        return "record";
    }

    @GetMapping("/game/form")
    public String showForm(Model model) {
        return "form";
    }
    
    @PostMapping("/game/save")
    public String postMethodName(@RequestParam("file") MultipartFile file,@RequestParam String name,@RequestParam int mult,@RequestParam int minPossibleNumber,@RequestParam int maxPossibleNumber,Model model,HttpServletRequest request) {

            try {
                //Select path
                String templatesDir = new File("proyecto\\src\\main\\resources\\templates").getAbsolutePath();
                System.out.println("Path: "+ templatesDir);
                //Create dir
                File directory = new File(templatesDir);

                // if there isnt we create it
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                
                // html name 
                String filePath = templatesDir + "/"+name+".html";
                // save it in games
                GameDTO g = new GameDTO(null, name, mult,minPossibleNumber,maxPossibleNumber, true, List.of());
                
                // g.setFich(file.getBytes());
                // g.setHasFich(true);
                // gameSer.save(g);
                gameSer.updateGameFile(g.id(), file);
                // Pass the html code introduced to the new file
                File f = new File(filePath);
                file.transferTo(f);
                System.out.println("COrrect file gen: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("ERRRRRRRRORRRRRRRRRR");
                return "error";
            }
            Principal principal = request.getUserPrincipal();
            if (principal != null) {
                UserrDTO u = uSer.findByName(principal.getName()).get();
                model.addAttribute("userLogged", u);
                model.addAttribute("hasImage", u.image());
            }
        return "redirect:/";
    }

    @GetMapping("/game/watch/{id}")
    public String watchGame(@PathVariable long id,Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        GameDTO g=gameSer.findById(id).get();
        if (principal != null) {
            UserrDTO u = uSer.findByName(principal.getName()).get();
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.image());
            model.addAttribute("game", g);
            System.out.println(u.name());
            return g.name();
        } else {
            model.addAttribute("game", g);
            return g.name();
        }
    }
}
