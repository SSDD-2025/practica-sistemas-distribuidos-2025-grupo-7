package lbj.king.proyecto.controllers;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @Autowired
    private PlayService playSer;
    @Autowired
    private UserService uSer;
    @Autowired
    private GameService gameSer;

    @PostMapping("/dicePlayProcess")
    public String postMethodName(@RequestParam(required = false) Float bet,@RequestParam(required = false) Integer diceNumber ,Model model, HttpSession session) {
        //Checks if there is any empty input
        if(bet==null|| diceNumber==null){
            return "diceError";
        }
        //Tries to get user
        Userr u= (Userr) session.getAttribute("user");
        Optional<Userr> us=uSer.findById(u.getId());
        if(!us.isPresent()){
            return "diceError";
        }else{
            u=us.get();
        }
        Game g = gameSer.findByName("Dados");
        //validate input
        if(diceNumber<=0 || diceNumber >6 || bet<=0){
            return "diceError";
        }

        if(u.getCurrency()>=bet && g!=null){
            
            Play p1= new Play(bet,u,g);
            p1.setWin(p1.getBet()*gameSer.findByName("Dados").getWinMultp());
            playSer.save(p1);
            u.addPlay(p1);
            u.setCurrency(u.getCurrency()-bet);
            uSer.save(u);

            model.addAttribute("partidaCreada",p1);
            model.addAttribute("userLogged", u);
            model.addAttribute("nDado", diceNumber);
            model.addAttribute("hasImage", u.getImage());

            //Used in next step of play
            session.setAttribute("diceNumber", diceNumber);
            session.setAttribute("actualPlay", p1);
            session.setAttribute("user", u);

            return "dice";
        }else{
            model.addAttribute("saldoInsuficiente", "true");
            model.addAttribute("userLogged",u);
            model.addAttribute("hasImage", u.getImage());

            return "dice";
        }        
    }

    @GetMapping("/diceBetProcess")
    public String getPartida(HttpSession session, Model model) {
        //tries get user
        boolean matchResult=false;
        Userr u = (Userr)session.getAttribute("user");
        Optional<Userr> us=uSer.findById(u.getId());
        if(!us.isPresent()){
            return "diceError";
        }else{
            u=us.get();
        }

        model.addAttribute("userLogged", u);
        model.addAttribute("playingGame", "true");
        //gets the number user selected
        int nDado=(int)session.getAttribute("diceNumber");
        //Generation of random numbers
        int nr1 = (int) (Math.random() * 4) + 1;
        int nr2 = (int) (Math.random() * 4) + 1;

        //In order to know which number will show up
        switch (nr1) {
            case 1:
                matchResult=nDado==4;
                break;
            case 2:
                switch (nr2) {
                    case 1:
                        matchResult=nDado==2;
                        break;
                    case 2:
                        matchResult=nDado==1;
                        break;
                    case 3:
                        matchResult=nDado==5;
                        break;
                    case 4:
                        matchResult=nDado==6;
                        break;
                }
                break;
            case 3:
                matchResult=nDado==3;
                break;
            case 4:
                switch (nr2) {
                    case 1:
                        matchResult=nDado==5;
                        break;
                    case 2:
                        matchResult=nDado==6;
                        break;
                    case 3:
                        matchResult=nDado==2;
                        break;
                    case 4:
                        matchResult=nDado==1;
                        break;
                        
                }
                break;
        }

        //If win
        Play p=(Play) session.getAttribute("actualPlay");
        if(matchResult){
            model.addAttribute("victory", "true");
            //user and play update
            u.setCurrency(u.getCurrency()+p.getBet()*gameSer.findByName("Dados").getWinMultp());
            p.won();
            playSer.save(p);
            uSer.save(u);
            //session update
            session.setAttribute("user", u);
        }

        gameSer.findByName("Dados").addPlay(p);
        //The 2 random numbers will be used in a javascript function
        model.addAttribute("n1", nr1);
        model.addAttribute("n2", nr2);
        model.addAttribute("hasImage", u.getImage());

        

        return "dice";
    }

    @GetMapping("/diceBetAgain")
    public String getLink(Model model, HttpSession session) {
        Userr u = (Userr)session.getAttribute("user");
        model.addAttribute("userLogged", u);
        model.addAttribute("hasImage", u.getImage());
        return "dice";
    }
    
    @PostMapping("/roulettePlayProcess")
    public String procesarPartidaRule(HttpSession session, Model model, @RequestParam(required=false) Integer selectedNumber, @RequestParam(required=false) Float bet) {
        //checks if request params were introduced
        if(bet==null || selectedNumber==null){
            model.addAttribute("userLogged", session.getAttribute("user"));
            return "main";
        }
        
        //get user and game
        Userr u = (Userr)session.getAttribute("user");
        Optional<Userr> us=uSer.findById(u.getId());
        if(!us.isPresent()){
            return "errorRule";
        }else{
            u=us.get();
        }
        Game j= gameSer.findByName("Ruleta");
        //generates play
        if(u.getCurrency() >= bet){

            Play newPlay = new Play(bet,u,j);
            newPlay.setWin(newPlay.getBet()*gameSer.findByName("Ruleta").getWinMultp());

            playSer.save(newPlay);
            u.addPlay(newPlay);
            u.setCurrency(u.getCurrency()-bet);
            uSer.save(u);
            session.setAttribute("actualPlay", newPlay);
            session.setAttribute("user", u);
            session.setAttribute("rNumber", selectedNumber);
            model.addAttribute("userLogged", u);
            model.addAttribute("playingGame", "true");
            model.addAttribute("hasImage", u.getImage());

            return "roulette";
        } else {
            model.addAttribute("insufficientBalance", "true");
            model.addAttribute("userLogged",u);
            model.addAttribute("hasImage", u.getImage());
            return "roulette";
        }

        

    }
    @GetMapping("/rouletteBetProcess")
    public String getMethodName(Model model, HttpSession session) {
        //gets user
        Userr u = (Userr)session.getAttribute("user");
        Optional<Userr> us=uSer.findById(u.getId());
        if(!us.isPresent()){
            return "rouletteError";
        }else{
            u=us.get();
        }

        int x=(int)session.getAttribute("rNumber");
        //random number
        int nr = (int) (Math.random() * 35);
        Play p=(Play) session.getAttribute("actualPlay");
        //if win
        if(x==nr){
            model.addAttribute("victory", "true");
            u.setCurrency(u.getCurrency()+p.getBet()*gameSer.findByName("Ruleta").getWinMultp());
            p.won();
            playSer.save(p);
            uSer.save(u);
        }
        //model and session
        session.setAttribute("user", u);
        gameSer.findByName("Ruleta").addPlay(p);
        model.addAttribute("userLogged", session.getAttribute("user"));
        model.addAttribute("postRule", "true");
        model.addAttribute("n",nr);
        model.addAttribute("hasImage", u.getImage());

        return "roulette";
    }
    

    @GetMapping("/rouletteBetAgain")

    public String getA(Model model, HttpSession session) {

        model.addAttribute("userLogged", session.getAttribute("user"));
        Userr u = (Userr)session.getAttribute("user");
        model.addAttribute("hasImage", u.getImage());

        return "roulette";

    }
}

