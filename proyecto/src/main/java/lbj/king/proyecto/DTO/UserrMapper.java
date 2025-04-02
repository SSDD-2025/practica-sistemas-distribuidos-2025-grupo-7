package lbj.king.proyecto.DTO;
import org.mapstruct.Mapper;

import lbj.king.proyecto.model.Userr;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserrMapper {
    UserrDTO toDTO (Userr userr);
    List<UserrDTO> toDTOs(Collection <Userr> userrs);
    Userr toDomain (UserrDTO userrDTO);
}
