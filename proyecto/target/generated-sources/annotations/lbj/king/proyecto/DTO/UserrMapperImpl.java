package lbj.king.proyecto.DTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.model.Userr;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-19T19:11:23+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class UserrMapperImpl implements UserrMapper {

    @Override
    public UserrDTO toDTO(Userr userr) {
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

    @Override
    public List<UserrDTO> toDTOs(Collection<Userr> userrs) {
        if ( userrs == null ) {
            return null;
        }

        List<UserrDTO> list = new ArrayList<UserrDTO>( userrs.size() );
        for ( Userr userr : userrs ) {
            list.add( toDTO( userr ) );
        }

        return list;
    }

    @Override
    public Userr toDomain(UserrDTO userrDTO) {
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
}
