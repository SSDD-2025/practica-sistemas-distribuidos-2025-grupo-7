package lbj.king.proyecto.controllers;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import lbj.king.proyecto.model.Juegos;
import lbj.king.proyecto.model.Partida;
import lbj.king.proyecto.model.Usuario;
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

    @PostMapping("/procesarPartidaDado")
    public String postMethodName(@RequestParam(required = false) Float apuesta,@RequestParam(required = false) Integer nDado ,Model model, HttpSession session) {
        //Checks if there`s any empty input
        if(apuesta==null|| nDado==null){
            return "errorDados";
        }
        //Tries to get user
        Usuario u= (Usuario) session.getAttribute("user");
        Optional<Usuario> us=uSer.findById(u.getId());
        if(!us.isPresent()){
            return "errorDados";
        }else{
            u=us.get();
        }
        u.getLista().size();
        Juegos g = gameSer.findByName("Dados");
        //validate input
        if(nDado<=0 || nDado >6 || apuesta<=0){
            return "errorDados";
        }

        if(u.getCurrency()>=apuesta && g!=null){
            
            Partida p1= new Partida(apuesta,u,g);
            p1.setWin(p1.getBet()*gameSer.findByName("Dados").getWinMultp());
            playSer.save(p1);
            u.addGame(p1);
            u.setCurrency(u.getCurrency()-apuesta);
            uSer.save(u);

            model.addAttribute("partidaCreada",p1);
            model.addAttribute("userLogged", u);
            model.addAttribute("nDado", nDado);
            model.addAttribute("hasImage", u.getImage());

            //Used in next step of play
            session.setAttribute("numeroDado", nDado);
            session.setAttribute("pActual", p1);
            session.setAttribute("user", u);

            return "dados";
        }else{
            model.addAttribute("saldoInsuficiente", "true");
            model.addAttribute("userLogged",u);
            model.addAttribute("hasImage", u.getImage());

            return "dados";
        }        
    }

    @GetMapping("/procesarApuestaDado")
    public String getPartida(HttpSession session, Model model) {
        //tries get user
        boolean matchResult=false;
        Usuario u = (Usuario)session.getAttribute("user");
        Optional<Usuario> us=uSer.findById(u.getId());
        if(!us.isPresent()){
            return "errorDados";
        }else{
            u=us.get();
        }

        model.addAttribute("userLogged", u);
        model.addAttribute("playingGame", "true");
        //gets the number user selected
        int nDado=(int)session.getAttribute("numeroDado");
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
        Partida p=(Partida) session.getAttribute("pActual");
        if(matchResult){
            model.addAttribute("victory", "true");
            u.setCurrency(u.getCurrency()+p.getBet()*gameSer.findByName("Dados").getWinMultp());
            p.won();
            playSer.save(p);
            uSer.save(u);
            session.setAttribute("user", u);
        }

        gameSer.findByName("Dados").addPlay(p);
        //The 2 random numbers will be used in a javascript function
        model.addAttribute("n1", nr1);
        model.addAttribute("n2", nr2);
        model.addAttribute("hasImage", u.getImage());

        

        return "dados";
    }

    @GetMapping("/redirigir_volverApostarDado")
    public String getLink(Model model, HttpSession session) {
        Usuario u = (Usuario)session.getAttribute("user");
        model.addAttribute("userLogged", u);
        model.addAttribute("hasImage", u.getImage());
        return "dados";
    }
    
    @PostMapping("/procesarPartidaRule")
    public String procesarPartidaRule(HttpSession session, Model model, @RequestParam(required=false) Integer numElegido, @RequestParam(required=false) Float apuesta) {
        //checks if request params were introduced
        if(apuesta==null || numElegido==null){
            model.addAttribute("userLogged", session.getAttribute("user"));
            return "inicio";
        }
        
        //get user and game
        Usuario u = (Usuario)session.getAttribute("user");
        Optional<Usuario> us=uSer.findById(u.getId());
        if(!us.isPresent()){
            return "errorRule";
        }else{
            u=us.get();
        }
        Juegos j= gameSer.findByName("Ruleta");
        //generates play
        if(u.getCurrency() >= apuesta){

            Partida miPartida = new Partida(apuesta,u,j);
            miPartida.setWin(miPartida.getBet()*gameSer.findByName("Dados").getWinMultp());
            playSer.save(miPartida);
            u.addGame(miPartida);
            u.setCurrency(u.getCurrency()-apuesta);
            uSer.save(u);
            session.setAttribute("pActual", miPartida);
            session.setAttribute("user", u);
            session.setAttribute("nRule", numElegido);
            model.addAttribute("userLogged", u);
            model.addAttribute("playingGame", "true");
            model.addAttribute("hasImage", u.getImage());

            return "rule";
        } else {
            model.addAttribute("saldoInsuficiente", "true");
            model.addAttribute("userLogged",u);
            model.addAttribute("hasImage", u.getImage());
            return "rule";
        }

        

    }
    @GetMapping("/procesarApuestaRule")
    public String getMethodName(Model model, HttpSession session) {
        //gets user
        Usuario u = (Usuario)session.getAttribute("user");
        Optional<Usuario> us=uSer.findById(u.getId());
        if(!us.isPresent()){
            return "errorRule";
        }else{
            u=us.get();
        }

        int x=(int)session.getAttribute("nRule");
        //random number
        int nr = (int) (Math.random() * 35);
        Partida p=(Partida) session.getAttribute("pActual");
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

        return "rule";
    }
    

    @GetMapping("/redirigir_volverApostarRule")

    public String getA(Model model, HttpSession session) {

        model.addAttribute("userLogged", session.getAttribute("user"));
        Usuario u = (Usuario)session.getAttribute("user");
        model.addAttribute("hasImage", u.getImage());

        return "rule";

    }
}

