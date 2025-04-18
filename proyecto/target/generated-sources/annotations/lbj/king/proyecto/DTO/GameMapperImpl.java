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
    date = "2025-04-18T12:12:37+0200",
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
        game.setName( gameDTO.name() );
        game.setWinMultp( gameDTO.winMultp() );
        game.setMinPossibleNumber( gameDTO.minPossibleNumber() );
        game.setMaxPossibleNumber( gameDTO.maxPossibleNumber() );

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
        play.setUser( userrDTOToUserr( playDTO.user() ) );

        return play;
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
}
