package lbj.king.proyecto.DTO;

import java.sql.Blob;

public record UserrDTO(
        Long id,
        String name,
        float currency,
        Blob image) {
}
