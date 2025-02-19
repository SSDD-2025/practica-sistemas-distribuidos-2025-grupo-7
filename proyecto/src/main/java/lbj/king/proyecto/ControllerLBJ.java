package lbj.king.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class ControllerLBJ {

    @Autowired
    private Usuario usuario;
    
    @GetMapping("/")
    public String greeting(Model model) {
        if(usuario.getName()==null){
            model.addAttribute("userName", "invitado");
        }else{
            model.addAttribute("userName",usuario.getName());
        }
        return "inicio";
    }

    
    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }
    
    @PostMapping("/procesarRegistro")
    public String procesarRegistro(@RequestParam String name,@RequestParam String psw,Model model) {       
        usuario.setName(name);
        usuario.setPassword(psw);
        model.addAttribute("userName", usuario.getName());

        return "inicio";
    }
    
    
}
