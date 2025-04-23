package lbj.king.proyecto.controllers;

import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lbj.king.proyecto.DTO.GameDTO;
import lbj.king.proyecto.DTO.PlayDTO;
import lbj.king.proyecto.DTO.PrizeDTO;
import lbj.king.proyecto.DTO.UserrCompleteDTO;
import lbj.king.proyecto.DTO.UserrDTO;
import lbj.king.proyecto.services.GameService;
import lbj.king.proyecto.services.PlayService;
import lbj.king.proyecto.services.PrizeService;
import lbj.king.proyecto.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService uSer;

    @Autowired
    private PlayService pSer;
    @Autowired
    private PrizeService prizeSer;
    @Autowired
    private GameService gameSer;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String getIndex(Model model, HttpServletRequest request) {

        Collection<GameDTO> gameList = gameSer.getGames();
        if (gameList.size() > 0) {
            model.addAttribute("Juegos", gameList);
        }
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            UserrDTO u = uSer.findByName(principal.getName()).orElseThrow();
            UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();
            model.addAttribute("userLogged", u);
            model.addAttribute("hasImage", uAux.image());
            return "main";
        } else {
            return "main";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken()); 
        return "login";
    }

    @GetMapping("/loginError")
	public String loginError() {
		return "loginError";
	}

    @GetMapping("/logout")
    public String getLogout(Model model) {
        return "logout";
    }

    @PostMapping("/registerProcess")
    public String procesarRegistro(@RequestParam String name, @RequestParam String psw, Model model) {

        for (UserrDTO u : uSer.getUsers()) {
            if (u.name().equals(name)) {
                model.addAttribute("registered", true);
                return "register";
            }
        }
        UserrCompleteDTO newUser = new UserrCompleteDTO(null, name, passwordEncoder.encode(psw), 0, false, List.of("USER"), List.of(), List.of(), null);
        uSer.saveComplete(newUser);
        Collection<GameDTO> gameList = gameSer.getGames();
        if (gameList.size() > 0) {
            model.addAttribute("Juegos", gameList);
        }
        return "main";
    }

    @GetMapping("/procesarLogout")
    public String getMethodName(HttpSession sesion) {
        sesion.invalidate();
        return "redirect:/";
    }

    @PostMapping("/balanceProcess")
    public String postSaldo(@RequestParam float money, HttpServletRequest request, Model model) {

        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            UserrDTO u = uSer.findByName(principal.getName()).get();
            model.addAttribute("userLogged", u);
            UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();

            model.addAttribute("hasImage", uAux.image());
            System.out.println(u.name());
            if (money > 0) {
                uSer.updateCurrencyUser(u.id(), money);
                return "redirect:/";
            } else {
                model.addAttribute("dineroNegativo", "true");
                return "main";
            }
        } else {
            throw new NoSuchElementException();
        }
        
    }

    @PostMapping("/profile/saveImage")
    public String saveImage(@RequestParam("image") MultipartFile image, 
                        HttpSession session,
                        HttpServletRequest request) throws Exception {

        Principal principal = request.getUserPrincipal();
        UserrDTO u = uSer.findByName(principal.getName()).orElseThrow();
        
        if (u == null) {
            return "redirect:/login";
        }

        if (!image.isEmpty()) {
            Blob imag = new SerialBlob(image.getBytes());
            uSer.setImageUser(u.id(), imag);
            
            session.setAttribute("user", u);
        }
        
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            UserrDTO u = uSer.findByName(principal.getName()).get();
            UserrDTO aux = uSer.findById(u.id()).get();
            model.addAttribute("userLogged", aux);
            UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();

            model.addAttribute("hasImage", uAux.image());
            model.addAttribute("listGames", aux.playList());
            model.addAttribute("userId", aux.id());

            Collection<GameDTO> gameList = gameSer.getGames();
            if (gameList.size() > 0) {
                model.addAttribute("Juegos", gameList);
            }
            return "profile";
        } else {
            throw new NoSuchElementException();
        }
    }

    @GetMapping("/profile/image")
public ResponseEntity<Object> downloadImage(HttpServletRequest request) throws SQLException {
    Principal principal = request.getUserPrincipal();
    UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();


    if (uAux.image() == null) {
        System.out.println("El usuario no tiene imagen.");
        return ResponseEntity.notFound().build();
    }

    Blob imag = uAux.image();
    InputStreamResource file = new InputStreamResource(imag.getBinaryStream());
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
            .contentLength(imag.length())
            .body(file);
}

@PostMapping("/deleteUser")
public String deleteUser(HttpServletRequest request) {
    Principal principal = request.getUserPrincipal();
    UserrDTO u = uSer.findByName(principal.getName()).get();

    Optional<UserrDTO> us = uSer.findById(u.id());
    if (!us.isPresent()) {
        return "diceError";
    }

    for (PrizeDTO p : u.prizeList()) {
        prizeSer.changePrize(p); 
    }

    uSer.deleteUserById(u.id());
    request.getSession().invalidate(); 

    return "redirect:/";
}

@PostMapping("/deleteGames")
public String deleteGames(Model model, HttpServletRequest request) {
    Principal principal = request.getUserPrincipal();
    UserrDTO u = uSer.findByName(principal.getName()).get();

    pSer.deletePartidasByUsuarioId(u.id());

    model.addAttribute("userLogged", u);
    UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();

    model.addAttribute("hasImage", uAux.image());
    model.addAttribute("listGames", u.playList());

    return "redirect:/profile";
}

@PostMapping("/game/{partida_id}/delete")
public String deleteGame(Model model, @PathVariable long partida_id, HttpServletRequest request) {
    Principal principal = request.getUserPrincipal();
    UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();

    PlayDTO partida = pSer.findById(partida_id).get();

    uAux.playList().remove(partida);
    pSer.deletePartidaById(partida_id);
    uSer.saveComplete(uAux);

    model.addAttribute("userLogged", uAux);

    model.addAttribute("hasImage", uAux.image());
    model.addAttribute("listGames", uAux.playList());

    return "profile";
}

}
