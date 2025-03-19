package lbj.king.proyecto.controllers;
import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/game/form")
    public String showForm(Model model) {
        return "form";
    }
    
    @PostMapping("/game/save")
    public String postMethodName(@RequestParam("file") MultipartFile file,@RequestParam String name,@RequestParam int mult,@RequestParam int minPossibleNumber,@RequestParam int maxPossibleNumber,Model model,HttpSession session) {

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
                Game g = new Game(name, mult,minPossibleNumber,maxPossibleNumber);
                g.setFich(file.getBytes());
                gameSer.save(g);
                // Pass the html code introduced to the new file
                File f = new File(filePath);
                file.transferTo(f);
                System.out.println("COrrect file gen: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("ERRRRRRRRORRRRRRRRRR");
                return "error";
            }
        
        model.addAttribute("userLogged", session.getAttribute("user"));
        return "redirect:/";
    }

    @GetMapping("/game/watch/{id}")
    public String watchGame(@PathVariable long id,Model model, HttpSession session) {
        model.addAttribute("userLogged", session.getAttribute("user"));
        Game g=gameSer.findById(id).get();
        System.out.println(id);
        model.addAttribute("game", g);
        return g.getName();
    }
}
