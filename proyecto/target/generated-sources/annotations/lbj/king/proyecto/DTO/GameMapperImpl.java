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
    date = "2025-05-07T14:17:38+0200",
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
        List<PlayDTO> playList = null;

        id = game.getId();
        name = game.getName();
        winMultp = game.getWinMultp();
        minPossibleNumber = game.getMinPossibleNumber();
        maxPossibleNumber = game.getMaxPossibleNumber();
        playList = playListToPlayDTOList( game.getPlayList() );

        boolean hasFich = false;

        GameDTO gameDTO = new GameDTO( id, name, winMultp, minPossibleNumber, maxPossibleNumber, hasFich, playList );

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
        game.setHasFich( gameDTO.hasFich() );
        game.setName( gameDTO.name() );
        game.setWinMultp( gameDTO.winMultp() );
        game.setMinPossibleNumber( gameDTO.minPossibleNumber() );
        game.setMaxPossibleNumber( gameDTO.maxPossibleNumber() );
        if ( game.getPlayList() != null ) {
            List<Play> list = playDTOListToPlayList( gameDTO.playList() );
            if ( list != null ) {
                game.getPlayList().addAll( list );
            }
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
        play.setBet( playDTO.bet() );
        play.setwon( playDTO.won() );
        play.setUser( userrBasicDTOToUserr( playDTO.user() ) );

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

    protected PlayDTO playToPlayDTO(Play play) {
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

    protected List<PlayDTO> playListToPlayDTOList(List<Play> list) {
        if ( list == null ) {
            return null;
        }

        List<PlayDTO> list1 = new ArrayList<PlayDTO>( list.size() );
        for ( Play play : list ) {
            list1.add( playToPlayDTO( play ) );
        }

        return list1;
    }

    protected List<Play> playDTOListToPlayList(List<PlayDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Play> list1 = new ArrayList<Play>( list.size() );
        for ( PlayDTO playDTO : list ) {
            list1.add( toDomain( playDTO ) );
        }

        return list1;
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
}
