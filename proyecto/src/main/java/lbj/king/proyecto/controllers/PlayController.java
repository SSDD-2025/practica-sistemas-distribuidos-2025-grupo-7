package lbj.king.proyecto.controllers;

import java.util.*;

import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lbj.king.proyecto.model.Juegos;
import lbj.king.proyecto.model.Partida;
import lbj.king.proyecto.model.Usuario;
import lbj.king.proyecto.repositories.GameRepository;
import lbj.king.proyecto.repositories.PlayRepository;
import lbj.king.proyecto.repositories.UserRepository;
import lbj.king.proyecto.services.GameService;
import lbj.king.proyecto.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class PlayController {
    
    @Autowired
    private PlayRepository playRep;
    @Autowired
    private UserService uSer;
    @Autowired
    private GameService gameSer;

    @PostMapping("/procesarPartidaDado")
    public String postMethodName(@RequestParam(required = false) Float apuesta,@RequestParam(required = false) Integer nDado ,Model model, HttpSession session) {
        
        if(apuesta==null|| nDado==null){
            return "errorDados";
        }

        Usuario u= (Usuario) session.getAttribute("user");
        Optional<Usuario> us=uSer.findById(u.getId());
        if(!us.isPresent()){
            return "errorDados";
        }else{
            u=us.get();
        }
        u.getLista().size();
        Juegos g = gameSer.findByName("Dados");

        if(nDado<=0 || nDado >6 || apuesta<=0){
            return "errorDados";
        }

        if(u.getCurrency()>=apuesta && g!=null){
            Partida p1= new Partida(apuesta,u,g);
            p1.setWin(p1.getBet()*gameSer.findByName("Dados").getWinMultp());
            playRep.save(p1);
            g.addPlay(p1);
            u.addGame(p1);
            u.setCurrency(u.getCurrency()-apuesta);
            uSer.save(u);
            gameSer.save(g);
            session.setAttribute("user", u);
            model.addAttribute("partidaCreada",p1);
            model.addAttribute("userLogged", u);
            model.addAttribute("nDado", nDado);

            //para procesarPartidaDado
            session.setAttribute("numeroDado", nDado);
            session.setAttribute("pActual", p1);

            model.addAttribute("hasImage", u.getImage());

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

        boolean matchResult=false;
        Usuario u = (Usuario)session.getAttribute("user");
        Optional<Usuario> us=uSer.findById(u.getId());
        if(!us.isPresent()){
            return "errorDados";
        }else{
            u=us.get();
        }

        model.addAttribute("userLogged", u);
        int nDado=(int)session.getAttribute("numeroDado");
        model.addAttribute("playingGame", "true");

        //number selected by the user

        int nr1 = (int) (Math.random() * 4) + 1;
        int nr2 = (int) (Math.random() * 4) + 1;

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
        //3 atributos para el model, uno para que se gire el dado, 2 para los numeros aleatorios
        Partida p=(Partida) session.getAttribute("pActual");
        if(matchResult){
            model.addAttribute("victory", "true");
            
            u.setCurrency(u.getCurrency()+p.getBet()*gameSer.findByName("Dados").getWinMultp());
            p.won();

            playRep.save(p);
            uSer.save(u);
        }

        gameSer.findByName("Dados").addPlay(p);
        System.out.println(nr1);
        System.out.println(nr2);
        model.addAttribute("n1", nr1);
        model.addAttribute("n2", nr2);
        
        model.addAttribute("hasImage", u.getImage());

        //AQUI PROCESAR LOS NUMEROS ALEATORIOS PARA SABER QUE CARA DEL DADO SALDRÁ

        return "dados";
    }

    @GetMapping("/redirigir_volverApostarDado")
    public String getLink(Model model, HttpSession session) {
        model.addAttribute("userLogged", session.getAttribute("user"));

        Usuario u = (Usuario)session.getAttribute("user");
        model.addAttribute("hasImage", u.getImage());

        return "dados";
    }
    
    
    
    //pa la rule

    @PostMapping("/procesarPartidaRule")
    public String procesarPartidaRule(HttpSession session, Model model, @RequestParam(required=false) Integer numElegido, @RequestParam(required=false) Float apuesta) {

        if(apuesta==null || numElegido==null){
            model.addAttribute("userLogged", session.getAttribute("user"));
            return "inicio";
        }
        
        Usuario u = (Usuario)session.getAttribute("user");
        Optional<Usuario> us=uSer.findById(u.getId());
        if(!us.isPresent()){
            return "errorRule";
        }else{
            u=us.get();
        }
        Juegos j= gameSer.findByName("Ruleta");

        if(u.getCurrency() >= apuesta){

            Partida miPartida = new Partida(apuesta,u,j);
            miPartida.setWin(miPartida.getBet()*gameSer.findByName("Dados").getWinMultp());
            playRep.save(miPartida);
            session.setAttribute("pActual", miPartida);
            u.addGame(miPartida);
            u.setCurrency(u.getCurrency()-apuesta);
            uSer.save(u);
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

        Usuario u = (Usuario)session.getAttribute("user");
        Optional<Usuario> us=uSer.findById(u.getId());
        if(!us.isPresent()){
            return "errorRule";
        }else{
            u=us.get();
        }

        int x=(int)session.getAttribute("nRule");
        int nr = (int) (Math.random() * 35);
        System.out.println(x);
        Partida p=(Partida) session.getAttribute("pActual");

        if(x==nr){
            model.addAttribute("victory", "true");
            
            u.setCurrency(u.getCurrency()+p.getBet()*gameSer.findByName("Ruleta").getWinMultp());
            p.won();

            playRep.save(p);
            uSer.save(u);
            
        }

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

