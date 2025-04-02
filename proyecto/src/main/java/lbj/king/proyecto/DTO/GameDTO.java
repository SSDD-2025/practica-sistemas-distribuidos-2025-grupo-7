package lbj.king.proyecto.DTO;

import java.sql.Blob;


public record GameDTO(
    Long id,
    String name,
    float winMultp,
    int minPossibleNumber,
    int maxPossibleNumber,
    byte[] fich,
    Blob image
    ) {
    
}
