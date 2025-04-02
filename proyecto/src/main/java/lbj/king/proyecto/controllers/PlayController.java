package lbj.king.proyecto.controllers;
import java.security.Principal;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Play;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.services.GameService;
import lbj.king.proyecto.services.PlayService;
import lbj.king.proyecto.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PlayController {

    private final PrizeController prizeController;
    
    @Autowired
    private PlayService playSer;
    @Autowired
    private UserService uSer;
    @Autowired
    private GameService gameSer;

    PlayController(PrizeController prizeController) {
        this.prizeController = prizeController;
    }

    @PostMapping("/game/watch/{id}/process")
    public String sameGames(@PathVariable long id,@RequestParam(required = false) Float bet,@RequestParam(required = false) Integer selectedNumber ,Model model, HttpSession session, HttpServletRequest request) {
        
        Userr u;
        //Tries to get user
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            u = uSer.findByName(principal.getName()).get();
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.getImage());
        }else{
            return "error";
        }

        Game actualGame;
        //gets game
        Optional<Game> og = gameSer.findById(id);
        if(!og.isPresent()){
            System.out.println("Not present");
            return "error";
        }else{
            actualGame = og.get();
        }
        //Checks if there is any empty input
        if(bet==null|| selectedNumber==null){
            System.out.println("empty inputs");
            return "error";
        }
        
        //validate input
        System.out.println(selectedNumber);
        System.out.println(actualGame.getMinPossibleNumber());
        System.out.println(actualGame.getMaxPossibleNumber());
        System.out.println(bet);
        if(selectedNumber<actualGame.getMinPossibleNumber() || selectedNumber >actualGame.getMaxPossibleNumber() || bet<=0){
            System.out.println("wrong number input");
            return "error";
        }

        if(u.getCurrency()>=bet && actualGame!=null){
            Play p1= new Play(bet,u,actualGame);
            p1.setWin(p1.getBet()*actualGame.getWinMultp());
            playSer.save(p1);
            u.addPlay(p1);
            u.setCurrency(u.getCurrency()-bet);
            uSer.save(u);

            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.getImage());
            model.addAttribute("playingGame", "true");
            model.addAttribute("selectedNumber",selectedNumber);
            model.addAttribute("game", actualGame);

            //Used in next step of play
            session.setAttribute("selectedNumber", selectedNumber);
            session.setAttribute("actualPlay", p1);
            session.setAttribute("user", u);

            

            return actualGame.getName();
        }else{
            System.out.println("ERRORRRRRRRRRR");
            model.addAttribute("insufficientBalance", "true");
            model.addAttribute("userLogged",u);
            model.addAttribute("hasImage", u.getImage());
            model.addAttribute("game", actualGame);

            return actualGame.getName();
        }        
    }


    @GetMapping("/sameGameBetProcess/{id}")
    public String sameGameProcess(@PathVariable long id,Model model, HttpSession session, HttpServletRequest request) {
        Game actualGame;
        //gets user
        Userr u;
        //Tries to get user
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            u = uSer.findByName(principal.getName()).get();
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.getImage());
        }else{
            return "error";
        }
        //gets game
        Optional<Game> og = gameSer.findById(id);
        if(!og.isPresent()){
            return "error";
        }else{
            actualGame = og.get();
        }
    
        int selectedNumber=(int)session.getAttribute("selectedNumber");
        //random number
        int randomNumber = (int) (Math.random() * actualGame.getMaxPossibleNumber()+ actualGame.getMinPossibleNumber()); //AÑADIR ESTOS ATRIBUTOS  A JUEGO E IMAGEN 
        System.out.println(randomNumber);
        System.out.println(selectedNumber);
        Play p=(Play) session.getAttribute("actualPlay");
        //if win
        if(selectedNumber==randomNumber){
            model.addAttribute("randomNumber",randomNumber);
            model.addAttribute("victory", "true");
            u.setCurrency(u.getCurrency()+p.getBet()*gameSer.findById(id).get().getWinMultp());
            p.won();
            playSer.save(p);
            uSer.save(u);
        }
        //model and session
        session.setAttribute("user", u);
        gameSer.findById(id).get().addPlay(p);  
        model.addAttribute("userLogged", session.getAttribute("user"));
        model.addAttribute("afterGame", "true");
        model.addAttribute("randomNumber",randomNumber);
        model.addAttribute("hasImage", u.getImage());
        model.addAttribute("game", actualGame);


        return actualGame.getName();
    }

    @GetMapping("/game/redirect/{id}")
    public String getMethodName(@PathVariable long id, Model model, HttpSession session) {
        return gameSer.findById(id).get().getName();
    }
    
}

