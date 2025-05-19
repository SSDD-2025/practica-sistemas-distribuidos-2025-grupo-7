package lbj.king.proyecto.DTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import lbj.king.proyecto.model.Game;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-19T13:39:47+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class GameBasicMapperImpl implements GameBasicMapper {

    @Override
    public GameBasicDTO toDTO(Game game) {
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

        boolean hasFich = false;

        GameBasicDTO gameBasicDTO = new GameBasicDTO( id, name, winMultp, minPossibleNumber, maxPossibleNumber, hasFich );

        return gameBasicDTO;
    }

    @Override
    public List<GameBasicDTO> toDTOs(Collection<Game> games) {
        if ( games == null ) {
            return null;
        }

        List<GameBasicDTO> list = new ArrayList<GameBasicDTO>( games.size() );
        for ( Game game : games ) {
            list.add( toDTO( game ) );
        }

        return list;
    }

    @Override
    public Game toDomain(GameBasicDTO gameBasicDTO) {
        if ( gameBasicDTO == null ) {
            return null;
        }

        Game game = new Game();

        if ( gameBasicDTO.id() != null ) {
            game.setId( gameBasicDTO.id() );
        }
        game.setHasFich( gameBasicDTO.hasFich() );
        game.setName( gameBasicDTO.name() );
        game.setWinMultp( gameBasicDTO.winMultp() );
        game.setMinPossibleNumber( gameBasicDTO.minPossibleNumber() );
        game.setMaxPossibleNumber( gameBasicDTO.maxPossibleNumber() );

        return game;
    }
}
