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
    date = "2025-04-17T18:29:39+0200",
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
        userr.setImage( userrDTO.image() );

        return userr;
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
}
