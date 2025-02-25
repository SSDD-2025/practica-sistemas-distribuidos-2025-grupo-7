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
public class ControllerLBJ {

    @Autowired
    private UserRepository rep;


    @PostConstruct
    public void init(){
        Usuario u1 = new Usuario("a", "a");
        rep.save(u1);
        Usuario u2 = new Usuario("EjemploDos", "espa1bila");
        rep.save(u2);
    }
    
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
    
    @GetMapping("/rule")
    public String getRule(Model model, HttpSession session) {
        Usuario u=(Usuario)session.getAttribute("user");
        if(u!=null){
            model.addAttribute("userLogged", u);
        }
        return "rule";
    }

    @GetMapping("/dados")
    public String getDado(Model model, HttpSession session) {
        Usuario u=(Usuario)session.getAttribute("user");
        if(u!=null){
            model.addAttribute("userLogged", u);
        }
        return "dados";
    }
    
    @GetMapping("/slot")
    public String getSlot(Model model, HttpSession session) {
        Usuario u=(Usuario)session.getAttribute("user");
        if(u!=null){
            model.addAttribute("userLogged", u);
        }
        return "slot";
    }

    
    @GetMapping("/logout")
    public String getLogout(Model model) {
        return "logout";
    }
    

    @PostMapping("/procesarRegistro")
    public String procesarRegistro(@RequestParam String name,@RequestParam String psw,Model model) {      
        
        List<Usuario> lu= rep.findAll();

        for(Usuario u : lu){
            if(u.getName().equals(name)){
                model.addAttribute("registered", "true");
                return "register";
            }
        }

        Usuario newUser = new Usuario( name, psw);
        rep.save(newUser);

        return "inicio";
    }

    @GetMapping("/procesarLogout")
    public String getMethodName(HttpSession sesion) {
        sesion.invalidate();
        return "inicio";
    }
    

    @PostMapping("/procesarLogin")
    public String postMethodName(@RequestParam String name,@RequestParam String psw,Model model, HttpSession session) {
        
        List<Usuario> userList = rep.findAll();
        for(Usuario u:userList){
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
