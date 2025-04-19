package lbj.king.proyecto.DTO;
import org.mapstruct.Mapper;

import lbj.king.proyecto.model.Userr;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserrBasicMapper {
    UserrBasicDTO toDTO (Userr userr);
    List<UserrBasicDTO> toDTOs(Collection <Userr> userrs);
    Userr toDomain (UserrBasicDTO userrBasicDTO);
}
