package lbj.king.proyecto.DTO;

import java.sql.Blob;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lbj.king.proyecto.model.Play;
import lbj.king.proyecto.model.Prize;

public record UserrDTO(
        Long id,
        String name,
        List<String> roles,
        float currency,
        Blob image

        ) {
}
