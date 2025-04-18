package lbj.king.proyecto.DTO;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Play;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.model.Userr;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-18T12:35:40+0200",
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

        if ( playDTO.id() != null ) {
            play.setId( playDTO.id() );
        }
        play.setWin( playDTO.win() );
        play.setBet( playDTO.bet() );
        play.setwon( playDTO.won() );
        play.setUser( userrDTOToUserr( playDTO.user() ) );
        play.setGame( gameDTOToGame( playDTO.game() ) );

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

    protected PrizeDTO prizeToPrizeDTO(Prize prize) {
        if ( prize == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        int price = 0;
        Boolean owned = null;
        UserrBasicDTO user = null;

        id = prize.getId();
        name = prize.getName();
        if ( prize.getPrice() != null ) {
            price = prize.getPrice();
        }
        owned = prize.getOwned();
        user = userrToUserrBasicDTO( prize.getUser() );

        PrizeDTO prizeDTO = new PrizeDTO( id, name, price, owned, user );

        return prizeDTO;
    }

    protected List<PrizeDTO> prizeListToPrizeDTOList(List<Prize> list) {
        if ( list == null ) {
            return null;
        }

        List<PrizeDTO> list1 = new ArrayList<PrizeDTO>( list.size() );
        for ( Prize prize : list ) {
            list1.add( prizeToPrizeDTO( prize ) );
        }

        return list1;
    }

    protected UserrDTO userrToUserrDTO(Userr userr) {
        if ( userr == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        float currency = 0.0f;
        boolean imageBool = false;
        List<String> roles = null;
        List<PrizeDTO> prizeList = null;

        id = userr.getId();
        name = userr.getName();
        currency = userr.getCurrency();
        imageBool = userr.getImageBool();
        List<String> list = userr.getRoles();
        if ( list != null ) {
            roles = new ArrayList<String>( list );
        }
        prizeList = prizeListToPrizeDTOList( userr.getPrizeList() );

        UserrDTO userrDTO = new UserrDTO( id, name, currency, imageBool, roles, prizeList );

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

    protected Prize prizeDTOToPrize(PrizeDTO prizeDTO) {
        if ( prizeDTO == null ) {
            return null;
        }

        String name = null;
        Integer price = null;

        name = prizeDTO.name();
        price = prizeDTO.price();

        Prize prize = new Prize( name, price );

        prize.setUser( userrBasicDTOToUserr( prizeDTO.user() ) );
        prize.setOwned( prizeDTO.owned() );

        return prize;
    }

    protected List<Prize> prizeDTOListToPrizeList(List<PrizeDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Prize> list1 = new ArrayList<Prize>( list.size() );
        for ( PrizeDTO prizeDTO : list ) {
            list1.add( prizeDTOToPrize( prizeDTO ) );
        }

        return list1;
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
        userr.setImageBool( userrDTO.imageBool() );
        if ( userr.getPrizeList() != null ) {
            List<Prize> list = prizeDTOListToPrizeList( userrDTO.prizeList() );
            if ( list != null ) {
                userr.getPrizeList().addAll( list );
            }
        }

        return userr;
    }

    protected Game gameDTOToGame(GameDTO gameDTO) {
        if ( gameDTO == null ) {
            return null;
        }

        Game game = new Game();

        if ( gameDTO.id() != null ) {
            game.setId( gameDTO.id() );
        }
        game.setName( gameDTO.name() );
        game.setWinMultp( gameDTO.winMultp() );
        game.setMinPossibleNumber( gameDTO.minPossibleNumber() );
        game.setMaxPossibleNumber( gameDTO.maxPossibleNumber() );

        return game;
    }
}
