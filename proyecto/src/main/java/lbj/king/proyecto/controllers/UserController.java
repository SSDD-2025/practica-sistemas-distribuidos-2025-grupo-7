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
import lbj.king.proyecto.model.Usuario;
import lbj.king.proyecto.repositories.UserRepository;
import lbj.king.proyecto.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class UserController {

    @Autowired
    private UserService uSer;
    @Autowired
    private UserRepository rep;


    
    
    /* Index without logged user */
    @GetMapping("/")
    public String getIndex(Model model, HttpSession session) {    
        Usuario u=(Usuario)session.getAttribute("user");
        if(u!=null){
            model.addAttribute("userLogged", u);
        }
        return "inicio";
    }
    
    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }  

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @GetMapping("/logout")
    public String getLogout(Model model) {
        return "logout";
    }
    

    @PostMapping("/procesarRegistro")
    public String procesarRegistro(@RequestParam String name,@RequestParam String psw,Model model) {      
        

        for(Usuario u : uSer.getUsuarios()){
            if(u.getName().equals(name)){
                model.addAttribute("registered", "true");
                return "register";
            }
        }

        Usuario newUser = new Usuario( name, psw);
        uSer.save(newUser);

        return "inicio";
    }

    @GetMapping("/procesarLogout")
    public String getMethodName(HttpSession sesion) {
        sesion.invalidate();
        return "inicio";
    }
    

    @PostMapping("/procesarLogin")
    public String postMethodName(@RequestParam String name,@RequestParam String psw,Model model, HttpSession session) {
        
        for(Usuario u:uSer.getUsuarios()){
            if(u.getName().equals(name)){
                if(u.getPassword().equals(psw)){
                    session.setAttribute("user", u);
                    model.addAttribute("userLogged", u);
                    return "inicio";                    
                }                
            }          
        }
        model.addAttribute("error", "true");
        return "login";
    }
    

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("usuarios", rep.findAll());
        return "usuarios";
    }
    
    @PostMapping("/procesarSaldo")
    public String postSaldo(@RequestParam float money, HttpSession session,Model model) {
        //TODO: process POST request

        if(money>0){
            Usuario u = (Usuario)session.getAttribute("user");
            model.addAttribute("userLogged", u);
            u.setCurrency(u.getCurrency()+money);
            rep.save(u);
            return "inicio";
        }else{
            model.addAttribute("dineroNegativo", "true");
            return "inicio";
        }
    }
    
    
}
