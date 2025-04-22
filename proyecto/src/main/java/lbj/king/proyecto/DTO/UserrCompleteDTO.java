package lbj.king.proyecto.DTO;

import java.sql.Blob;
import java.util.List;

public record UserrCompleteDTO(
        Long id,
        String name,     
        String password, 
        float currency,
        boolean imageBool,
        List<String> roles,
        List<PrizeDTO> prizeList,
        List<PlayDTO> playList,
        Blob image

        ) {}
