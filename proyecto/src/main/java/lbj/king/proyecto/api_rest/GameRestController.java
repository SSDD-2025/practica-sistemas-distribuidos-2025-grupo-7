package lbj.king.proyecto.api_rest;

import java.io.File;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import lbj.king.proyecto.DTO.GameDTO;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.services.GameService;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;



@RestController
@RequestMapping("/api/games")
public class GameRestController {
    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public Page<GameDTO> getGames(Pageable pageable) {
        return gameService.getGamesPageable(pageable);
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

    @PostMapping("/upload")
    public ResponseEntity<?> uploadGame(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("mult") int mult,
            @RequestParam("min") int min,
            @RequestParam("max") int max,
            HttpServletRequest request) throws IllegalStateException, java.io.IOException {

        try {
            String templatesDir = new File("src/main/resources/templates").getAbsolutePath();

            File directory = new File(templatesDir);
            if (!directory.exists()) directory.mkdirs();

            String filePath = templatesDir + "/" + name + ".html";

            Game g = new Game(name, mult, min, max);
            g.setFich(file.getBytes());
            g.setHasFich(true);
            gameService.save(g);

            File f = new File(filePath);
            file.transferTo(f);

            return ResponseEntity.ok("Juego subido correctamente: " + name);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al guardar el juego.");
        }
    }

    @PutMapping("/{id}")
	public GameDTO replaceGame(@PathVariable long id, @RequestBody GameDTO updatedgameDTO) throws SQLException {

		return gameService.replaceGame(id, updatedgameDTO);
	}

    @DeleteMapping("/{id}")
    public GameDTO deleteGame(@PathVariable long id) {
        return gameService.deleteGame(id);
    }
}