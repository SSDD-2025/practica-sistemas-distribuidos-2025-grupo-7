package lbj.king.proyecto.api_rest;

<<<<<<< HEAD
import java.net.URI;
import java.security.Principal;
=======
>>>>>>> e7fec7885a538422492c8a937a0e896e40771191
import java.sql.SQLException;
import java.util.Collection;

<<<<<<< HEAD
import java.util.Optional;
import org.h2.engine.User;
=======
>>>>>>> e7fec7885a538422492c8a937a0e896e40771191
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
import lbj.king.proyecto.DTO.GameMapper;
import lbj.king.proyecto.DTO.PlayDTO;
import lbj.king.proyecto.DTO.PlayMapper;
import lbj.king.proyecto.DTO.PlayRequestDTO;
import lbj.king.proyecto.DTO.UserrBasicMapper;
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

        Userr u = userService.findById(userId).orElseThrow();

        Pageable pageable = PageRequest.of(page, size);
        return playService.getPlaysByUser(u.getId(), pageable);
    }

    @GetMapping("/")
    public Page<PlayDTO> getPlays() {
        Pageable pageable = PageRequest.of(0, 10);
        return playService.getPlaysPageable(pageable);
    }

    @GetMapping("/{id}")
    public PlayDTO getPlayById(@PathVariable long id) {
        return playService.getPlay(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> createPlay(@RequestBody PlayRequestDTO req) {
        // Obtener usuario autenticado
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Userr user = userService.findByName(username).orElseThrow();

        // Validar juego
        Game game = gameService.findById(req.gameId()).orElseThrow();

        // Validaciones
        if (req.bet() == null || req.bet() <= 0 || req.selectedNumber() == null) {
            return ResponseEntity.badRequest().body("Apuesta o número no válidos.");
        }

        if (req.selectedNumber() < game.getMinPossibleNumber() || req.selectedNumber() > game.getMaxPossibleNumber()) {
            return ResponseEntity.badRequest().body("Número fuera de rango.");
        }

        if (user.getCurrency() < req.bet()) {
            return ResponseEntity.badRequest().body("Saldo insuficiente.");
        }

        // Crear la partida
        Play play = new Play(req.bet(), user, game);
        play.setWin(req.bet() * game.getWinMultp());

        // Restar apuesta
        user.setCurrency(user.getCurrency() - req.bet());

        // Generar número aleatorio
        int randomNumber = (int) (Math.random() * (game.getMaxPossibleNumber() - game.getMinPossibleNumber() + 1)) + game.getMinPossibleNumber();

        boolean victory = false;
        if (req.selectedNumber() == randomNumber) {
            victory = true;
            play.won();
            float premio = play.getBet() * game.getWinMultp();
            user.setCurrency(user.getCurrency() + premio);
        }

        // Guardar
        playService.save(play);
        userService.save(user);

        // Respuesta
        PlayDTO result = new PlayDTO(
            play.getId(),
            play.getBet(),
            play.getWin(),
            play.getWon(),
            userrMapper.toDTO(user),
            gameMapper.toDTO(game)
        );

    return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
	public PlayDTO replacePlay(@PathVariable long id, @RequestBody PlayDTO updatedplayDTO) throws SQLException {

		return playService.replacePlay(id, updatedplayDTO);
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlay(@PathVariable Long id) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    Play play = playService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    if (!play.getUser().getName().equals(username)) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    playService.deletePlay(id);
    return ResponseEntity.noContent().build();
}
}
