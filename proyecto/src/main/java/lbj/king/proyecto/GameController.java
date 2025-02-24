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
            return "dados";
        }else{
            model.addAttribute("saldoInsuficiente", "true");
            model.addAttribute("userLogged",u);
            return "dados";
        }        
    }

    @GetMapping("/procesarPartidaDado")
    public String getPartida(HttpSession session, Model model) {

        //number selected by the user
        int nDado = (int) session.getAttribute("numeroDado");

        int nr1 = (int) (Math.random() * 6) + 1;
        int nr2 = (int) (Math.random() * 6) + 1;

        //3 atributos para el model, uno para que se gire el dado, 2 para los numeros aleatorios

        //AQUI PROCESAR LOS NUMEROS ALEATORIOS PARA SABER QUE CARA DEL DADO SALDRÁ

        return "inicio";
    }
    
    
    
}

