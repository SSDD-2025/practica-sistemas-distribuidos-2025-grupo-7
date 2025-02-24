package lbj.king.proyecto;

import java.util.*;

import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Controller
public class GameController {
    
    @Autowired
    private GameRepository gameRep;
    @Autowired
    private UserRepository rep;


    @PostMapping("/procesarPartida")
    public String postMethodName(@RequestParam float apuesta,@RequestParam int nDado ,Model model, HttpSession session) {
        
        Usuario u= (Usuario) session.getAttribute("user");
        if(u.getCurrency()>apuesta){
            Partida p1= new Partida(apuesta,u);
            gameRep.save(p1);
            
            //Da error añadir la partida al usuario
            //u.addGame(p1);
            u.setCurrency(u.getCurrency()-apuesta);
            rep.save(u);
            model.addAttribute("partidaCreada",p1);
            model.addAttribute("userLogged", u);
            model.addAttribute("nDado", nDado);

            //para procesarPartidaDado
            session.setAttribute("numeroDado", nDado);
            session.setAttribute("pActual", p1);
            return "dados";
        }else{
            model.addAttribute("saldoInsuficiente", "true");
            model.addAttribute("userLogged",u);
            return "dados";
        }        
    }

    @GetMapping("/procesarPartidaDado")
    public String getPartida(HttpSession session, Model model) {

        boolean matchResult=false;
        Usuario u = (Usuario)session.getAttribute("user");

        model.addAttribute("userLogged", u);
        int nDado=(int)session.getAttribute("numeroDado");
        model.addAttribute("playingGame", "true");

        //number selected by the user

        //int nr1 = (int) (Math.random() * 4) + 1;
        //int nr2 = (int) (Math.random() * 4) + 1;
        int nr1=1;
        int nr2=1;

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
        if(matchResult){
            model.addAttribute("victory", "true");
            
            u.setCurrency(u.getCurrency()+1);
            rep.save(u);
        }
        model.addAttribute("n1", nr1);
        model.addAttribute("n2", nr2);
        
        //AQUI PROCESAR LOS NUMEROS ALEATORIOS PARA SABER QUE CARA DEL DADO SALDRÁ

        return "dados";
    }

    @GetMapping("/redirigir_volverApostarDado")
    public String getLink(Model model, HttpSession session) {
        model.addAttribute("userLogged", session.getAttribute("user"));
        return "dados";
    }
    
    
    
    
}

