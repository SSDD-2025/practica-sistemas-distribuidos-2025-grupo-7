package lbj.king.proyecto.DTO;

import java.util.List;

public record UserrDTO(
        Long id,
        String name,
        float currency,
        boolean imageBool,
        List<String> roles,
        List<PrizeDTO> prizeList,
        List<PlayDTO> playList

        ) {}
