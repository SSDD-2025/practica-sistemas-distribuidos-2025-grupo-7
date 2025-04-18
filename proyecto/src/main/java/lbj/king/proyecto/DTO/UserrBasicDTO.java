package lbj.king.proyecto.DTO;

import java.util.List;

public record UserrBasicDTO(
        Long id,
        String name,   
        float currency,
        boolean imageBool,
        List<String> roles

        ) {
}
