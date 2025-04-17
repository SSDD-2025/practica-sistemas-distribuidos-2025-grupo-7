package lbj.king.proyecto.api_rest;

import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lbj.king.proyecto.DTO.GameDTO;
import lbj.king.proyecto.services.GameService;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;



@RestController
@RequestMapping("/api/games")
public class GameRestController {
    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public Collection<GameDTO> getGames() {
        return gameService.getGamesR();
    }

    @GetMapping("/{id}")
    public GameDTO getGame(@PathVariable long id) {
        return gameService.getGame(id);
    }

    @PostMapping("/")
    public ResponseEntity<GameDTO> createGame(@RequestBody GameDTO gameDTO) {
		gameDTO = gameService.createGame(gameDTO);
		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(gameDTO.id()).toUri();
		return ResponseEntity.created(location).body(gameDTO);
	}

    @PutMapping("/{id}")
	public GameDTO replaceGame(@PathVariable long id, @PathVariable GameDTO updatedgameDTO) throws SQLException {

		return gameService.replaceGame(id, updatedgameDTO);
	}

    @DeleteMapping("/{id}")
    public GameDTO deleteGame(@PathVariable long id) {
        return gameService.deleteGame(id);
    }
}