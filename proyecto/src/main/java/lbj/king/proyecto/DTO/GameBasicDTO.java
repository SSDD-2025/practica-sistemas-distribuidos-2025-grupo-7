package lbj.king.proyecto.DTO;

import java.util.List;

public record GameBasicDTO(
    Long id,
    String name,
    float winMultp,
    int minPossibleNumber,
    int maxPossibleNumber,
    boolean hasFich
    ) {
    
}
