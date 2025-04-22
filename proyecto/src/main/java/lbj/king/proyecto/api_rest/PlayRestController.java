package lbj.king.proyecto.api_rest;


import java.net.URI;
import java.security.Principal;

import java.sql.SQLException;
import java.util.Collection;

import java.util.Optional;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import lbj.king.proyecto.DTO.GameBasicDTO;
import lbj.king.proyecto.DTO.GameDTO;
import lbj.king.proyecto.DTO.GameMapper;
import lbj.king.proyecto.DTO.PlayDTO;
import lbj.king.proyecto.DTO.PlayMapper;
import lbj.king.proyecto.DTO.PlayRequestDTO;
import lbj.king.proyecto.DTO.UserrBasicDTO;
import lbj.king.proyecto.DTO.UserrBasicMapper;
import lbj.king.proyecto.DTO.UserrDTO;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Play;
import lbj.king.proyecto.model.Userr;
import lbj.king.proyecto.services.GameService;
import lbj.king.proyecto.services.PlayService;
import lbj.king.proyecto.services.UserService;




@RestController
@RequestMapping("/api/plays")
public class PlayRestController {
    @Autowired
    private PlayService playService;
    @Autowired
    private UserService userService;
     @Autowired
    private GameService gameService;
    @Autowired
    private PlayMapper playMapper;
    @Autowired
    private UserrBasicMapper userrMapper;
    @Autowired
    private GameMapper gameMapper;

    @GetMapping
    public Page<PlayDTO> getMyPlays(@RequestParam Long userId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        HttpServletRequest request) {

        UserrDTO u = userService.findById(userId).orElseThrow();

        Pageable pageable = PageRequest.of(page, size);
        return playService.getPlaysByUser(u.id(), pageable);
    }

    @GetMapping("/")
    public Page<PlayDTO> getPlays() {
        Pageable pageable = PageRequest.of(0, 10);
        return playService.getPlaysPageable(pageable);
    }
    
    @GetMapping("/me")
    public Page<PlayDTO> getMyPlays() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserrDTO user = userService.findByName(username).orElseThrow();
        
        return playService.getPlaysByUser(user.id(), PageRequest.of(0, 10));
    }

    @GetMapping("/{id}")
    public PlayDTO getPlayById(@PathVariable long id) {
        return playService.getPlay(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> createPlay(@RequestBody PlayRequestDTO req) {
        // Obtener usuario autenticado
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserrDTO user = userService.findByName(username).orElseThrow();

        // Validar juego
        GameDTO game = gameService.findById(req.gameId()).orElseThrow();

        // Validaciones
        if (req.bet() == null || req.bet() <= 0 || req.selectedNumber() == null) {
            return ResponseEntity.badRequest().body("Apuesta o número no válidos.");
        }

        if (req.selectedNumber() < game.minPossibleNumber() || req.selectedNumber() > game.maxPossibleNumber()) {
            return ResponseEntity.badRequest().body("Número fuera de rango.");
        }

        if (user.currency() < req.bet()) {
            return ResponseEntity.badRequest().body("Saldo insuficiente.");
        }

        // Crear la partida
        // Play play = new Play(req.bet(), user, game);
        UserrBasicDTO userBasic = userService.findByNameBasic(username).orElseThrow();
        // PlayDTO play = new PlayDTO(null, req.bet(), 0, false, userBasic, game);
        GameBasicDTO gameBasic = gameService.findByNameBasic(game.name()).orElseThrow();
        PlayDTO play = new PlayDTO(null, req.bet(), 0, false, userBasic, gameBasic);
        PlayDTO savedPlay = playService.save(play);

        // play.setWin(req.bet() * game.getWinMultp());
        playService.setWinDTO(savedPlay.id(), game.winMultp(), req.bet());

        // Restar apuesta
        // user.setCurrency(user.getCurrency() - req.bet());
        userService.updateLessCurrencyUser(user.id(), req.bet());

        // Generar número aleatorio
        int randomNumber = (int) (Math.random() * (game.maxPossibleNumber() - game.minPossibleNumber() + 1)) + game.minPossibleNumber();

        boolean victory = false;
        if (req.selectedNumber() == randomNumber) {
            victory = true;

            // play.won();
            playService.setWonDTO(savedPlay.id());
            float premio = play.bet() * game.winMultp();
            // user.setCurrency(user.getCurrency() + premio);
            userService.updateCurrencyUser(user.id(), premio);
        }

        // Guardar
        // playService.save(play);
        // userService.save(user);

        // Respuesta
        // PlayDTO result = new PlayDTO(
        //     play.id(),
        //     play.bet(),
        //     play.win(),
        //     play.won(),
        //     play.user(),
        //     play.game()
        // );
        PlayDTO updatedPlay = playService.findById(savedPlay.id()).orElseThrow();


    return ResponseEntity.ok(updatedPlay);
    }

    @PutMapping("/{id}")
	public PlayDTO replacePlay(@PathVariable long id, @RequestBody PlayDTO updatedplayDTO) throws SQLException {

		return playService.replacePlay(id, updatedplayDTO);
	}

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> deletePlay(@PathVariable Long id) {
    //     String username = SecurityContextHolder.getContext().getAuthentication().getName();

    //     PlayDTO play = playService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    //     if (!play.user().name().equals(username)) {
    //         throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    //     }

    //     playService.deletePlay(id);
    //     return ResponseEntity.noContent().build();
    // }
    @DeleteMapping("/{id}")
    public PlayDTO deletePlay(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        PlayDTO play = playService.findById(id).orElseThrow();

        if (!play.user().name().equals(username)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        playService.deletePlay(id);
        return play;
    }
}
