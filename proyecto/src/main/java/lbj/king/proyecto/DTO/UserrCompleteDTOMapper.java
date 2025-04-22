package lbj.king.proyecto.DTO;
import org.mapstruct.Mapper;

import lbj.king.proyecto.model.Userr;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserrCompleteDTOMapper {
    UserrCompleteDTO toDTO (Userr userr);
    List<UserrCompleteDTO> toDTOs(Collection <Userr> userrs);
    Userr toDomain (UserrCompleteDTO userrDTO);
}
