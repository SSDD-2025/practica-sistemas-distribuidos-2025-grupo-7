package lbj.king.proyecto.DTO;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import lbj.king.proyecto.model.Userr;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-16T20:12:14+0200",
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
        Blob image = null;

        id = userr.getId();
        name = userr.getName();
        currency = userr.getCurrency();
        image = userr.getImage();

        UserrDTO userrDTO = new UserrDTO( id, name, currency, image );

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

        name = userrDTO.name();

        String psw = null;
        String[] roles = null;

        Userr userr = new Userr( name, psw, roles );

        userr.setCurrency( userrDTO.currency() );
        userr.setImage( userrDTO.image() );

        return userr;
    }
}
