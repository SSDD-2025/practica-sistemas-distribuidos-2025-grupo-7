package lbj.king.proyecto.DTO;

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
    date = "2025-04-22T18:47:24+0200",
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
        boolean won = false;
        UserrBasicDTO user = null;
        GameBasicDTO game = null;

        id = play.getId();
        bet = play.getBet();
        win = play.getWin();
        won = play.getWon();
        user = userrToUserrBasicDTO( play.getUser() );
        game = gameToGameBasicDTO( play.getGame() );

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

        if ( playDTO.id() != null ) {
            play.setId( playDTO.id() );
        }
        play.setWin( playDTO.win() );
        play.setBet( playDTO.bet() );
        play.setwon( playDTO.won() );
        play.setUser( userrBasicDTOToUserr( playDTO.user() ) );
        play.setGame( gameBasicDTOToGame( playDTO.game() ) );

        return play;
    }

    protected UserrBasicDTO userrToUserrBasicDTO(Userr userr) {
        if ( userr == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        float currency = 0.0f;
        boolean imageBool = false;
        List<String> roles = null;

        id = userr.getId();
        name = userr.getName();
        currency = userr.getCurrency();
        imageBool = userr.getImageBool();
        List<String> list = userr.getRoles();
        if ( list != null ) {
            roles = new ArrayList<String>( list );
        }

        UserrBasicDTO userrBasicDTO = new UserrBasicDTO( id, name, currency, imageBool, roles );

        return userrBasicDTO;
    }

    protected GameBasicDTO gameToGameBasicDTO(Game game) {
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

    protected Userr userrBasicDTOToUserr(UserrBasicDTO userrBasicDTO) {
        if ( userrBasicDTO == null ) {
            return null;
        }

        String name = null;
        String[] roles = null;

        name = userrBasicDTO.name();
        roles = stringListToStringArray( userrBasicDTO.roles() );

        String psw = null;

        Userr userr = new Userr( name, psw, roles );

        userr.setCurrency( userrBasicDTO.currency() );
        if ( userrBasicDTO.id() != null ) {
            userr.setId( userrBasicDTO.id() );
        }
        userr.setImageBool( userrBasicDTO.imageBool() );

        return userr;
    }

    protected Game gameBasicDTOToGame(GameBasicDTO gameBasicDTO) {
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
