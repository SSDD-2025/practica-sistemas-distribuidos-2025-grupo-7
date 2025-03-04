package lbj.king.proyecto.controllers;
import java.sql.Blob;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
import lbj.king.proyecto.model.Play;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.services.PlayService;
import lbj.king.proyecto.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class UserController {

    @Autowired
    private UserService uSer;

    @Autowired
    private PlayService pSer;
    
    
    /* Index without logged user */
    @GetMapping("/")
    public String getIndex(Model model, HttpSession session) {    
        Userr u=(Userr)session.getAttribute("user");
        if(u!=null){
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", u.getImage() != null);
        }
        return "main";
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
    

    @PostMapping("/registerProcess")
    public String procesarRegistro(@RequestParam String name,@RequestParam String psw,Model model) {      

        for(Userr u : uSer.getUsuarios()){
            if(u.getName().equals(name)){
                model.addAttribute("registered", "true");
                return "register";
            }
        }
        Userr newUser = new Userr( name, psw);
        uSer.save(newUser);

        return "main";
    }

    @GetMapping("/procesarLogout")
    public String getMethodName(HttpSession sesion) {
        sesion.invalidate();
        return "main";
    }
    

    @PostMapping("/loginProcess")
    public String postMethodName(@RequestParam String name,@RequestParam String psw,Model model, HttpSession session) {
        
        for(Userr u:uSer.getUsuarios()){
            if(u.getName().equals(name)){
                if(u.getPassword().equals(psw)){
                    session.setAttribute("user", u);
                    model.addAttribute("userLogged", u);
                    return "main";                    
                }                
            }          
        }
        model.addAttribute("error", "true");
        return "login";
    }
    

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("usuarios", uSer.findAll());
        return "usuarios";
    }
    
    @PostMapping("/balanceProcess")
    public String postSaldo(@RequestParam float money, HttpSession session,Model model) {
        //TODO: process POST request

        if(money>0){
            Userr u = (Userr)session.getAttribute("user");
            model.addAttribute("userLogged", u);
            u.setCurrency(u.getCurrency()+money);
            uSer.save(u);

            model.addAttribute("hasImage", u.getImage());

            return "redirect:/";
        }else{
            model.addAttribute("dineroNegativo", "true");

            Userr u = (Userr)session.getAttribute("user");
            model.addAttribute("hasImage", u.getImage());

            return "redirect:/";
        }
    }
    

    @PostMapping("/profile/saveImage")
    public String saveImage(Model model, @RequestParam("image") MultipartFile image, HttpSession session) throws Exception {

        Userr u = (Userr) session.getAttribute("user");
        if (u == null) {
            return "redirect:/login";
        }
        if (image.isEmpty()) {
            return "redirect:/profile";
        }
        Blob imag = new SerialBlob(image.getBytes());
        u.setImage(imag);
        uSer.save(u);
        model.addAttribute("userLogged", u);
        model.addAttribute("hasImage", u.getImage());
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        Userr u = (Userr)session.getAttribute("user");
        
        if(u == null){
            return "login";
        }
        if(u != null){
            Userr aux = uSer.findById(u.getId()).get();
            model.addAttribute("userLogged", aux);
            model.addAttribute("hasImage", u.getImage());
            model.addAttribute("listGames", aux.getLista());
        }
        return "profile";
    }

	@GetMapping("/profile/image")
	public ResponseEntity<Object> downloadImage(HttpSession session, Model model) throws SQLException {

        Userr u = (Userr)session.getAttribute("user");

        if (u.getImage() == null) {
            System.out.println(" El usuario no tiene imagen.");
            return ResponseEntity.notFound().build();
        }

        if(u != null && u.getImage() != null){
            Blob imag = u.getImage();
            InputStreamResource file = new InputStreamResource(imag.getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(imag.length()).body(file);
        } else {
			return ResponseEntity.notFound().build();
		}
	}
    @PostMapping("/deleteUser")
    public String deleteUser(Model model, HttpSession session) {
        Userr u = (Userr) session.getAttribute("user");
        if (u != null) {
            uSer.deleteUserById(u.getId());
            session.invalidate();
        }
        return "redirect:/";
    }

    
    @PostMapping("/deleteGames")
    public String deleteGames(Model model, HttpSession session) {
        Userr u = (Userr) session.getAttribute("user");
        if (u != null) {
            pSer.deletePartidasByUsuarioId(u.getId());
        }
        model.addAttribute("userLogged", u);
        model.addAttribute("hasImage", u.getImage());
        model.addAttribute("listGames", u.getLista());
        return "redirect:/profile";
    }    

    @PostMapping("/game/{partida_id}/delete")
    public String deleteGame(Model model, @PathVariable long partida_id, HttpSession session) {
        Userr aux = (Userr) session.getAttribute("user");
        Userr u = uSer.findById(aux.getId()).get();
		Play partida = pSer.findByName(partida_id).get();

        u.getLista().remove(partida);
        pSer.deletePartidaById(partida_id); 
        uSer.save(u);
        
        model.addAttribute("userLogged", u);
        model.addAttribute("hasImage", u.getImage());
        model.addAttribute("listGames", u.getLista());
        return "profile";
    }


}
