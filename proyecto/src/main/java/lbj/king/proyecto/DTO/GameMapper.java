package lbj.king.proyecto.DTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Play;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameDTO toDTO (Game game);
    List<GameDTO> toDTOs(Collection <Game> games);
    Game toDomain (GameDTO gameDTO);

    @Mapping(target = "game", ignore = true)
    Play toDomain(PlayDTO playDTO);
}
