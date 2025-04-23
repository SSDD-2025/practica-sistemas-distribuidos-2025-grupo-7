package lbj.king.proyecto.services;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lbj.king.proyecto.DTO.GameBasicDTO;
import lbj.king.proyecto.DTO.GameBasicMapper;
import lbj.king.proyecto.DTO.GameDTO;
import lbj.king.proyecto.DTO.GameMapper;
import lbj.king.proyecto.DTO.PlayDTO;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Play;
import lbj.king.proyecto.repositories.GameRepository;
import lbj.king.proyecto.repositories.PlayRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRep;
    @Autowired
    private PlayRepository playRep;
    @Autowired
    private GameMapper mapper;
    @Autowired
    private GameBasicMapper basicMapper;

    public Optional<GameDTO> findByName(String name) {
        return gameRep.findByName(name)
                .map(this::toDTO);
    }
    public Optional<GameBasicDTO> findByNameBasic(String name) {
        return gameRep.findByName(name).map(this::toBasicDTO);
    }

    public Optional<GameDTO> findById(long id) {
        return gameRep.findById(id)
                .map(this::toDTO);
    }

    public GameDTO save(GameDTO gameDTO) {
        Game game = toDomain(gameDTO);
        gameRep.save(game);
        return toDTO(game);
    }

    public Collection<GameDTO> getGames(){
        return toDTOs(gameRep.findAll());
    }

    @Transactional
    public void updateGameFile(Long id, MultipartFile file) throws IOException {
        Game game = gameRep.findById(id).orElseThrow();
        
        if (file != null && !file.isEmpty()) {
            game.setFich(file.getBytes());
            game.setHasFich(true);
            gameRep.save(game);
        }
    }

    public void addPlayDTO(Long gameId, Long playId){
        Game game = gameRep.findById(gameId).orElseThrow();
        Play play = playRep.findById(playId).orElseThrow();
        game.addPlay(play);
        gameRep.save(game);
    }

        //para api rest
    private GameDTO toDTO(Game game) {
        return mapper.toDTO(game);
    }

    private GameBasicDTO toBasicDTO(Game game) {
		return basicMapper.toDTO(game);
	}


    private Game toDomain(GameDTO gameDTO) {
		return mapper.toDomain(gameDTO);
	}


    private Collection<GameDTO> toDTOs(Collection<Game> games) {
        return mapper.toDTOs(games);
    }

    public Page<GameDTO> getGamesPageable(Pageable pageable) {
		return gameRep.findAll(pageable).map(this::toDTO);
	}
    public List<PlayDTO> getPlaysByGame(long id) {
        GameDTO game = toDTO(gameRep.findById(id).orElseThrow());
        List<PlayDTO> list = game.playList();
        return list;
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

    public void editGame(long id, float n) throws SQLException{
        Game game = gameRep.findById(id).orElseThrow();
		game.setWinMultp(n);

		gameRep.save(game);
    }

}
