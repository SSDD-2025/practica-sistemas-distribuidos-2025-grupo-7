package lbj.king.proyecto.DTO;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Play;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-17T14:38:02+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class GameMapperImpl implements GameMapper {

    @Override
    public GameDTO toDTO(Game game) {
        if ( game == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        float winMultp = 0.0f;
        int minPossibleNumber = 0;
        int maxPossibleNumber = 0;

        id = game.getId();
        name = game.getName();
        winMultp = game.getWinMultp();
        minPossibleNumber = game.getMinPossibleNumber();
        maxPossibleNumber = game.getMaxPossibleNumber();

        Blob image = null;

        GameDTO gameDTO = new GameDTO( id, name, winMultp, minPossibleNumber, maxPossibleNumber, image );

        return gameDTO;
    }

    @Override
    public List<GameDTO> toDTOs(Collection<Game> games) {
        if ( games == null ) {
            return null;
        }

        List<GameDTO> list = new ArrayList<GameDTO>( games.size() );
        for ( Game game : games ) {
            list.add( toDTO( game ) );
        }

        return list;
    }

    @Override
    public Game toDomain(GameDTO gameDTO) {
        if ( gameDTO == null ) {
            return null;
        }

        Game game = new Game();

        if ( gameDTO.id() != null ) {
            game.setId( gameDTO.id() );
        }

        return game;
    }

    @Override
    public Play toDomain(PlayDTO playDTO) {
        if ( playDTO == null ) {
            return null;
        }

        Play play = new Play();

        if ( playDTO.id() != null ) {
            play.setId( playDTO.id() );
        }
        play.setWin( playDTO.win() );

        return play;
    }
}
