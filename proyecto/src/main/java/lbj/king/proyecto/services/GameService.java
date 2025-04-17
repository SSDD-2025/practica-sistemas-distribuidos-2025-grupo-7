package lbj.king.proyecto.services;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lbj.king.proyecto.DTO.GameDTO;
import lbj.king.proyecto.DTO.GameMapper;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.repositories.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRep;
    @Autowired
    private GameMapper mapper;

    public Game findByName(String n){
        return gameRep.findByName(n);
    }
    public Optional<Game> findById(long id){
        return gameRep.findById(id);
    }
    public void save(Game j){
        gameRep.save(j);
    }
    public List<Game> getGames(){
        return gameRep.findAll();
    }

        //para api rest
    private GameDTO toDTO(Game game) {
        return mapper.toDTO(game);
    }


    private Game toDomain(GameDTO gameDTO) {
		return mapper.toDomain(gameDTO);
	}


    private Collection<GameDTO> toDTOs(Collection<Game> games) {
        return mapper.toDTOs(games);
    }

    
    public Collection<GameDTO> getGamesR() {
        return toDTOs(gameRep.findAll());
    }


    public GameDTO getGame(long id) {
        return toDTO(gameRep.findById(id).orElseThrow()); 
    }


    public GameDTO createGame(GameDTO gameDTO) {
        if(gameDTO.id() != null){
            throw new IllegalArgumentException();
        } 
        Game game = toDomain(gameDTO); 
        gameRep.save(game);
        return toDTO(game);
    }

    public GameDTO replaceGame(long id, GameDTO updatedGameDTO) throws SQLException{
		Game updatedGame = toDomain(updatedGameDTO);
		updatedGame.setId(id);

		gameRep.save(updatedGame);

		return toDTO(updatedGame);
    }

    public GameDTO deleteGame(long id) {
        Game game = gameRep.findById(id).orElseThrow();

		GameDTO gameDTO = toDTO(game);

		gameRep.deleteById(id);

		return gameDTO;
    }
}
