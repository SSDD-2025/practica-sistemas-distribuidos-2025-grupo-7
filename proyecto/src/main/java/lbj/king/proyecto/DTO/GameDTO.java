package lbj.king.proyecto.DTO;

import java.util.List;

public record GameDTO(
    Long id,
    String name,
    float winMultp,
    int minPossibleNumber,
    int maxPossibleNumber,
    boolean hasFich,
    List<PlayDTO> playList
    ) {
    
}
