package lbj.king.proyecto;

import java.util.Optional;

import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.PostConstruct;

import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class ControllerLBJ {

    @Autowired
    private UserRepository rep;

    private Usuario usuario;

    @PostConstruct
    public void init(){
        Usuario u1 = new Usuario("00000","Ejemplo", "espabila");
        rep.save(u1);
        Usuario u2 = new Usuario("002000","EjemploDos", "espa1bila");
        rep.save(u2);
    }
    
    @GetMapping("/")
    public String greeting(Model model) {
        return "inicio";
    }

    
    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }  

    @GetMapping("/rule")
    public String getRule(Model model) {
        return "rule";
    }
    
    @PostMapping("/procesarRegistro")
    public String procesarRegistro(@RequestParam String name,@RequestParam String psw,Model model) {       
        usuario.setName(name);
        usuario.setPassword(psw);

        return "inicio";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("usuarios", rep.findAll());
        return "usuarios";
    }

    @GetMapping("/users/{id}")
    public String getUser(@RequestParam Long id, Model model) {

        Optional<Usuario> usuarioOp = rep.findById(id);
        if(usuarioOp.isPresent()){
            model.addAttribute("usuario", usuarioOp.get());
            return "inicio";
        }else{
            return "No esta";
        }
    }
    
    
    
    
}
