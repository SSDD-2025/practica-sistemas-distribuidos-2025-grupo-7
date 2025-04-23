package lbj.king.proyecto.api_rest;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import lbj.king.proyecto.DTO.PlayDTO;
import lbj.king.proyecto.DTO.PlayRequestDTO;
import lbj.king.proyecto.DTO.UserrBasicDTO;
import lbj.king.proyecto.DTO.UserrDTO;
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

    @GetMapping("/game/{id}")
    public List<PlayDTO> getPlaysByGame(@PathVariable long id) {
        GameDTO game = gameService.findById(id).orElseThrow();
        return gameService.getPlaysByGame(game.id());
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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserrDTO user = userService.findByName(username).orElseThrow();

        GameDTO game = gameService.findById(req.gameId()).orElseThrow();

        if (req.bet() == null || req.bet() <= 0 || req.selectedNumber() == null) {
            return ResponseEntity.badRequest().body("Apuesta o número no válidos.");
        }

        if (req.selectedNumber() < game.minPossibleNumber() || req.selectedNumber() > game.maxPossibleNumber()) {
            return ResponseEntity.badRequest().body("Número fuera de rango.");
        }

        if (user.currency() < req.bet()) {
            return ResponseEntity.badRequest().body("Saldo insuficiente.");
        }

        UserrBasicDTO userBasic = userService.findByNameBasic(username).orElseThrow();
        GameBasicDTO gameBasic = gameService.findByNameBasic(game.name()).orElseThrow();
        PlayDTO play = new PlayDTO(null, req.bet(), 0, false, userBasic, gameBasic);
        PlayDTO savedPlay = playService.save(play);

        playService.setWinDTO(savedPlay.id(), game.winMultp(), req.bet());

        userService.updateLessCurrencyUser(user.id(), req.bet());

        int randomNumber = (int) (Math.random() * (game.maxPossibleNumber() - game.minPossibleNumber() + 1)) + game.minPossibleNumber();

        if (req.selectedNumber() == randomNumber) {

            playService.setWonDTO(savedPlay.id());
            float premio = play.bet() * game.winMultp();
            userService.updateCurrencyUser(user.id(), premio);
        }


        PlayDTO updatedPlay = playService.findById(savedPlay.id()).orElseThrow();


    return ResponseEntity.ok(updatedPlay);
    }

    @PutMapping("/{id}")
	public PlayDTO replacePlay(@PathVariable long id, @RequestBody PlayDTO updatedplayDTO) throws SQLException {

		return playService.replacePlay(id, updatedplayDTO);
	}

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
