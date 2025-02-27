package lbj.king.proyecto.controllers;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            model.addAttribute("hasImage", u.getImage() != null);
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
            return "redirect:/";
        }else{
            model.addAttribute("dineroNegativo", "true");
            return "redirect:/";
        }
    }
    

    @PostMapping("/profile/saveImage")
    public String saveImage(Model model, @RequestParam("image") MultipartFile image, HttpSession session) throws Exception {

        Usuario u = (Usuario) session.getAttribute("user");
        if (u == null) {
            return "redirect:/login";
        }
        if (image.isEmpty()) {
            return "redirect:/profile";
        }
        Blob imag = new SerialBlob(image.getBytes());
        u.setImage(imag);
        uSer.save(u);
        rep.save(u);
        model.addAttribute("userLogged", u);
        model.addAttribute("hasImage", u.getImage());
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        Usuario u = (Usuario)session.getAttribute("user");
        if(u == null){
            return "login";
        }
        if(u != null){
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.getImage());
            model.addAttribute("miLista", u.getLista());
        }
        return "profile";
    }

	@GetMapping("/profile/image")
	public ResponseEntity<Object> downloadImage(HttpSession session, Model model) throws SQLException {

        Usuario u = (Usuario)session.getAttribute("user");

        if (u.getImage() == null) {
            System.out.println(" El usuario no tiene imagen.");
            return ResponseEntity.notFound().build();
        }

        if(u != null && u.getImage() != null){
            Blob imag = u.getImage();
            /*model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.getImage());*/
            InputStreamResource file = new InputStreamResource(imag.getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(imag.length()).body(file);
        } else {
			return ResponseEntity.notFound().build();
		}
	}
    
}
