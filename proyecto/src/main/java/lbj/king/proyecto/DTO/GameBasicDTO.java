package lbj.king.proyecto.DTO;


public record GameBasicDTO(
    Long id,
    String name,
    float winMultp,
    int minPossibleNumber,
    int maxPossibleNumber,
    boolean hasFich
    ) {
    
}
