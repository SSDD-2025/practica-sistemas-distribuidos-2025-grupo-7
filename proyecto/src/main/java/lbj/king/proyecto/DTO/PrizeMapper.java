package lbj.king.proyecto.DTO;
import org.mapstruct.Mapper;
import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Prize;


import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PrizeMapper {
    PrizeDTO toDTO (Prize prize);
    List<PrizeDTO> toDTOs(Collection <Prize> prizes);
    Prize toDomain (PrizeDTO prizeDTO);
}