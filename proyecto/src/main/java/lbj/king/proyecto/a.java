package lbj.king.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class a {

    @Autowired
    private Usuario usuario;

    
    @GetMapping("/main_ini")
    public String greeting(Model model) {
        model.addAttribute("nombre", "Cristiano Ronaldo");
        return "main_ini";
    }

    
    
    @PostMapping("/procesarRegistro")
    public String procesarRegistro(@RequestParam String dni,@RequestParam String n,@RequestParam String c) {
        
        usuario.setdni(dni);
        usuario.setnombre(n);
        usuario.setcontraseña(c);
        return "usuarioRegistrado";
    }
    
    
}
