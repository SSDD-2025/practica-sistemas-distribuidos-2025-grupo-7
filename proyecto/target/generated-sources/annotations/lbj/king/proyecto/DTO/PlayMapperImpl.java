package lbj.king.proyecto.DTO;

import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import lbj.king.proyecto.model.Play;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-17T14:38:02+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class PlayMapperImpl implements PlayMapper {

    @Override
    public PlayDTO toDTO(Play play) {
        if ( play == null ) {
            return null;
        }

        Long id = null;
        float bet = 0.0f;
        float win = 0.0f;
        String userName = null;
        String gameName = null;

        id = play.getId();
        bet = play.getBet();
        win = play.getWin();
        userName = play.getUserName();
        gameName = play.getGameName();

        boolean won = false;

        PlayDTO playDTO = new PlayDTO( id, bet, win, won, userName, gameName );

        return playDTO;
    }

    @Override
    public Collection<PlayDTO> toDTOs(Collection<Play> plays) {
        if ( plays == null ) {
            return null;
        }

        Collection<PlayDTO> collection = new ArrayList<PlayDTO>( plays.size() );
        for ( Play play : plays ) {
            collection.add( toDTO( play ) );
        }

        return collection;
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
