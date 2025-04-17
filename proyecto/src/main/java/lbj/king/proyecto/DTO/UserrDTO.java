package lbj.king.proyecto.DTO;

import java.util.List;

import lbj.king.proyecto.model.Prize;

public record UserrDTO(
        Long id,
        String name,
        float currency,
        boolean imageBool,
        List<Prize> prizeList,
        List<String> roles
        ) {
}
