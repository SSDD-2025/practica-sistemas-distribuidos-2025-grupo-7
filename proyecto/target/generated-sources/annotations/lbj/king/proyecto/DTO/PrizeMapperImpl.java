package lbj.king.proyecto.DTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import lbj.king.proyecto.model.Prize;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-17T20:01:49+0200",
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
        String userName = null;

        id = prize.getId();
        name = prize.getName();
        if ( prize.getPrice() != null ) {
            price = prize.getPrice();
        }
        owned = prize.getOwned();
        userName = prize.getUserName();

        PrizeDTO prizeDTO = new PrizeDTO( id, name, price, owned, userName );

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

        return prize;
    }
}
