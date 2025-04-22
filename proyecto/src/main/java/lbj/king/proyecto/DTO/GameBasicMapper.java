package lbj.king.proyecto.DTO;
import org.mapstruct.Mapper;

import lbj.king.proyecto.model.Game;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GameBasicMapper {
    GameBasicDTO toDTO (Game game);
    List<GameBasicDTO> toDTOs(Collection <Game> games);
    Game toDomain (GameBasicDTO gameBasicDTO);
}
