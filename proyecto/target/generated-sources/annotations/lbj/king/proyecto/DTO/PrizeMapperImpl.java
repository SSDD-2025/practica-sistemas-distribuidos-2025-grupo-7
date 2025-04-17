package lbj.king.proyecto.DTO;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.model.Userr;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-17T18:39:12+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class PrizeMapperImpl implements PrizeMapper {

    @Override
    public PrizeDTO toDTO(Prize prize) {
        if ( prize == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        int price = 0;
        Boolean owned = null;
        UserrDTO user = null;

        id = prize.getId();
        name = prize.getName();
        if ( prize.getPrice() != null ) {
            price = prize.getPrice();
        }
        owned = prize.getOwned();
        user = userrToUserrDTO( prize.getUser() );

        PrizeDTO prizeDTO = new PrizeDTO( id, name, price, owned, user );

        return prizeDTO;
    }

    @Override
    public List<PrizeDTO> toDTOs(Collection<Prize> prizes) {
        if ( prizes == null ) {
            return null;
        }

        List<PrizeDTO> list = new ArrayList<PrizeDTO>( prizes.size() );
        for ( Prize prize : prizes ) {
            list.add( toDTO( prize ) );
        }

        return list;
    }

    @Override
    public Prize toDomain(PrizeDTO prizeDTO) {
        if ( prizeDTO == null ) {
            return null;
        }

        String name = null;
        Integer price = null;

        name = prizeDTO.name();
        price = prizeDTO.price();

        Prize prize = new Prize( name, price );

        prize.setOwned( prizeDTO.owned() );
        prize.setUser( userrDTOToUserr( prizeDTO.user() ) );

        return prize;
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
