package lbj.king.proyecto.controllers;
import java.security.Principal;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lbj.king.proyecto.DTO.GameBasicDTO;
import lbj.king.proyecto.DTO.GameDTO;
import lbj.king.proyecto.DTO.PlayDTO;
import lbj.king.proyecto.DTO.UserrBasicDTO;
import lbj.king.proyecto.DTO.UserrCompleteDTO;
import lbj.king.proyecto.DTO.UserrDTO;
import lbj.king.proyecto.services.GameService;
import lbj.king.proyecto.services.PlayService;
import lbj.king.proyecto.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PlayController {
    
    @Autowired
    private PlayService playSer;
    @Autowired
    private UserService uSer;
    @Autowired
    private GameService gameSer;


    @PostMapping("/game/watch/{id}/process")
    public String sameGames(@PathVariable long id,@RequestParam(required = false) Float bet,@RequestParam(required = false) Integer selectedNumber ,Model model, HttpSession session, HttpServletRequest request) {
        
        UserrDTO u;
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            u = uSer.findByName(principal.getName()).get();
            model.addAttribute("userLogged", u);
            UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();

            model.addAttribute("hasImage", uAux.image());
        }else{
            return "error";
        }

        GameDTO actualGame;
        Optional<GameDTO> og = gameSer.findById(id);
        if(!og.isPresent()){
            System.out.println("Not present");
            return "error";
        }else{
            actualGame = og.get();
        }
        if(bet==null|| selectedNumber==null){
            System.out.println("empty inputs");
            return "error";
        }
        
        System.out.println(selectedNumber);
        System.out.println(actualGame.minPossibleNumber());
        System.out.println(actualGame.maxPossibleNumber());
        System.out.println(bet);
        if(selectedNumber<actualGame.minPossibleNumber() || selectedNumber >actualGame.maxPossibleNumber() || bet<=0){
            System.out.println("wrong number input");
            return "error";
        }

        if(u.currency()>=bet && actualGame!=null){
            UserrBasicDTO userBasic = uSer.findByNameBasic(principal.getName()).get();
            GameBasicDTO gameBasic = gameSer.findByNameBasic(actualGame.name()).orElseThrow();


            
            PlayDTO play = new PlayDTO(null, bet, 0, false, userBasic, gameBasic);
            PlayDTO savedPlay = playSer.save(play);
            PlayDTO updatedPlay = playSer.findById(savedPlay.id()).orElseThrow();

            playSer.setWinDTO(updatedPlay.id(), actualGame.winMultp(), updatedPlay.bet());

            uSer.addPlayToId(updatedPlay.id(),u.id());

            uSer.updateLessCurrencyUser(u.id(), bet);

            boolean miBool = true;
            model.addAttribute("userLogged", u);
            UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();

            model.addAttribute("hasImage", uAux.image());
            model.addAttribute("playingGame", miBool);
            model.addAttribute("selectedNumber",selectedNumber);
            model.addAttribute("game", actualGame);

            session.setAttribute("selectedNumber", selectedNumber);
            session.setAttribute("actualPlay", updatedPlay);
            session.setAttribute("user", uAux);

            

            return actualGame.name();
        }else{
            System.out.println("ERRORRRRRRRRRR");
            model.addAttribute("insufficientBalance", "true");
            model.addAttribute("userLogged",u);
            UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();

            model.addAttribute("hasImage", uAux.image());
            model.addAttribute("game", actualGame);

            return actualGame.name();
        }        
    }


    @GetMapping("/sameGameBetProcess/{id}")
    public String sameGameProcess(@PathVariable long id,Model model, HttpSession session, HttpServletRequest request) {
        GameDTO actualGame;
        UserrDTO u;
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            u = uSer.findByName(principal.getName()).get();
            model.addAttribute("userLogged", u);
            UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();

            model.addAttribute("hasImage", uAux.image());
        }else{
            return "error";
        }
        Optional<GameDTO> og = gameSer.findById(id);
        if(!og.isPresent()){
            return "error";
        }else{
            actualGame = og.get();
        }
    
        int selectedNumber=(int)session.getAttribute("selectedNumber");
        int randomNumber = (int) (Math.random() * actualGame.maxPossibleNumber()+ actualGame.minPossibleNumber()); //AÑADIR ESTOS ATRIBUTOS  A JUEGO E IMAGEN 
        System.out.println(randomNumber);
        System.out.println(selectedNumber);
        PlayDTO p=(PlayDTO) session.getAttribute("actualPlay");
        //if win
        if(selectedNumber==randomNumber){
            model.addAttribute("randomNumber",randomNumber);
            boolean miBool = true;
            model.addAttribute("victory", miBool);


            GameDTO game = gameSer.findById(id).get();
            uSer.userWinPlay(u.id(), p.id(), game.id());
            playSer.setWonDTO(p.id());

        }
        session.setAttribute("user", u);

        gameSer.addPlayDTO(actualGame.id(), p.id());

        model.addAttribute("userLogged", session.getAttribute("user"));
        boolean miBool = true;
        model.addAttribute("afterGame", miBool);
        model.addAttribute("randomNumber",randomNumber);
        UserrCompleteDTO uAux = uSer.findByNameComplete(principal.getName()).orElseThrow();

        model.addAttribute("hasImage", uAux.image());
        model.addAttribute("game", actualGame);
        
        session.setAttribute("user", u);


        return actualGame.name();
    }

    @GetMapping("/game/redirect/{id}")
    public String getMethodName(@PathVariable long id, Model model, HttpSession session) {
        return gameSer.findById(id).get().name();
    }
    
}

