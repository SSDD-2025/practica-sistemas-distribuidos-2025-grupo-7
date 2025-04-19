package lbj.king.proyecto.api_rest;

import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lbj.king.proyecto.DTO.PlayDTO;
import lbj.king.proyecto.model.Play;
import lbj.king.proyecto.services.PlayService;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;



@RestController
@RequestMapping("/api/plays")
public class PlayRestController {
    @Autowired
    private PlayService playService;

    @GetMapping("/")
    public Collection<PlayDTO> getPlays() {
        return playService.getPlays();
    }

    @GetMapping("/{id}")
    public PlayDTO getPlayById(@PathVariable long id) {
        return playService.getPlay(id);
    }

    @PostMapping("/")
    public ResponseEntity<PlayDTO> createPlay(@RequestBody PlayDTO playDTO) {

		playDTO = playService.createPlay(playDTO);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(playDTO.id()).toUri();

		return ResponseEntity.created(location).body(playDTO);
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
