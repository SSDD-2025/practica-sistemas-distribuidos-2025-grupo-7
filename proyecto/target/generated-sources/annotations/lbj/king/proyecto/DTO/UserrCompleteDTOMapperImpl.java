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
    date = "2025-05-19T13:39:47+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class UserrCompleteDTOMapperImpl implements UserrCompleteDTOMapper {

    @Override
    public UserrCompleteDTO toDTO(Userr userr) {
        if ( userr == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String password = null;
        float currency = 0.0f;
        boolean imageBool = false;
        List<String> roles = null;
        List<PrizeDTO> prizeList = null;
        List<PlayDTO> playList = null;
        Blob image = null;

        id = userr.getId();
        name = userr.getName();
        password = userr.getPassword();
        currency = userr.getCurrency();
        imageBool = userr.getImageBool();
        List<String> list = userr.getRoles();
        if ( list != null ) {
            roles = new ArrayList<String>( list );
        }
        prizeList = prizeListToPrizeDTOList( userr.getPrizeList() );
        playList = playListToPlayDTOList( userr.getPlayList() );
        image = userr.getImage();

        UserrCompleteDTO userrCompleteDTO = new UserrCompleteDTO( id, name, password, currency, imageBool, roles, prizeList, playList, image );

        return userrCompleteDTO;
    }

    @Override
    public List<UserrCompleteDTO> toDTOs(Collection<Userr> userrs) {
        if ( userrs == null ) {
            return null;
        }

        List<UserrCompleteDTO> list = new ArrayList<UserrCompleteDTO>( userrs.size() );
        for ( Userr userr : userrs ) {
            list.add( toDTO( userr ) );
        }

        return list;
    }

    @Override
    public Userr toDomain(UserrCompleteDTO userrDTO) {
        if ( userrDTO == null ) {
            return null;
        }

        String name = null;
        String[] roles = null;

        name = userrDTO.name();
        roles = stringListToStringArray( userrDTO.roles() );

        String psw = null;

        Userr userr = new Userr( name, psw, roles );

        userr.setPassword( userrDTO.password() );
        userr.setCurrency( userrDTO.currency() );
        if ( userrDTO.id() != null ) {
            userr.setId( userrDTO.id() );
        }
        userr.setImageBool( userrDTO.imageBool() );
        userr.setImage( userrDTO.image() );
        if ( userr.getPlayList() != null ) {
            List<Play> list = playDTOListToPlayList( userrDTO.playList() );
            if ( list != null ) {
                userr.getPlayList().addAll( list );
            }
        }
        if ( userr.getPrizeList() != null ) {
            List<Prize> list1 = prizeDTOListToPrizeList( userrDTO.prizeList() );
            if ( list1 != null ) {
                userr.getPrizeList().addAll( list1 );
            }
        }

        return userr;
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
        UserrBasicDTO owner = null;

        id = prize.getId();
        name = prize.getName();
        if ( prize.getPrice() != null ) {
            price = prize.getPrice();
        }
        owned = prize.getOwned();
        owner = userrToUserrBasicDTO( prize.getOwner() );

        PrizeDTO prizeDTO = new PrizeDTO( id, name, price, owned, owner );

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

    protected Play playDTOToPlay(PlayDTO playDTO) {
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

    protected List<Play> playDTOListToPlayList(List<PlayDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Play> list1 = new ArrayList<Play>( list.size() );
        for ( PlayDTO playDTO : list ) {
            list1.add( playDTOToPlay( playDTO ) );
        }

        return list1;
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

        prize.setOwned( prizeDTO.owned() );
        prize.setOwner( userrBasicDTOToUserr( prizeDTO.owner() ) );

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
}
