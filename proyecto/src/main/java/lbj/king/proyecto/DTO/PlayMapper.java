package lbj.king.proyecto.DTO;

import java.util.Collection;

import org.mapstruct.Mapper;

import lbj.king.proyecto.model.Play;

@Mapper(componentModel = "spring")
public interface PlayMapper {

    PlayDTO toDTO (Play play);
    Collection<PlayDTO> toDTOs (Collection<Play> plays);
    Play toDomain (PlayDTO playDTO);

    
}  