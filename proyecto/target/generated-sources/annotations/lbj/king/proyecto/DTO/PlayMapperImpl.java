package lbj.king.proyecto.DTO;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Play;
import lbj.king.proyecto.model.Userr;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-17T18:47:46+0200",
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
        UserrDTO user = null;
        GameDTO game = null;

        id = play.getId();
        bet = play.getBet();
        win = play.getWin();
        user = userrToUserrDTO( play.getUser() );
        game = gameToGameDTO( play.getGame() );

        boolean won = false;

        PlayDTO playDTO = new PlayDTO( id, bet, win, won, user, game );

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

        play.setBet( playDTO.bet() );
        play.setGame( gameDTOToGame( playDTO.game() ) );
        if ( playDTO.id() != null ) {
            play.setId( playDTO.id() );
        }
        play.setUser( userrDTOToUserr( playDTO.user() ) );
        play.setWin( playDTO.win() );
        play.setwon( playDTO.won() );

        return play;
    }

    protected UserrDTO userrToUserrDTO(Userr userr) {
        if ( userr == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        List<String> roles = null;
        float currency = 0.0f;
        Blob image = null;

        id = userr.getId();
        name = userr.getName();
        List<String> list = userr.getRoles();
        if ( list != null ) {
            roles = new ArrayList<String>( list );
        }
        currency = userr.getCurrency();
        image = userr.getImage();

        UserrDTO userrDTO = new UserrDTO( id, name, roles, currency, image );

        return userrDTO;
    }

    protected GameDTO gameToGameDTO(Game game) {
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

    protected Game gameDTOToGame(GameDTO gameDTO) {
        if ( gameDTO == null ) {
            return null;
        }

        Game game = new Game();

        if ( gameDTO.id() != null ) {
            game.setId( gameDTO.id() );
        }
        game.setMaxPossibleNumber( gameDTO.maxPossibleNumber() );
        game.setMinPossibleNumber( gameDTO.minPossibleNumber() );
        game.setName( gameDTO.name() );
        game.setWinMultp( gameDTO.winMultp() );

        return game;
    }

    protected String[] stringListToStringArray(List<String> list) {
        if ( list == null ) {
            return null;
        }

        String[] stringTmp = new String[list.size()];
        int i = 0;
        for ( String string : list ) {
            stringTmp[i] = string;
            i++;
        }

        return stringTmp;
    }

    protected Userr userrDTOToUserr(UserrDTO userrDTO) {
        if ( userrDTO == null ) {
            return null;
        }

        String name = null;
        String[] roles = null;

        name = userrDTO.name();
        roles = stringListToStringArray( userrDTO.roles() );

        String psw = null;

        Userr userr = new Userr( name, psw, roles );

        userr.setCurrency( userrDTO.currency() );
        if ( userrDTO.id() != null ) {
            userr.setId( userrDTO.id() );
        }
        userr.setImage( userrDTO.image() );

        return userr;
    }
}
