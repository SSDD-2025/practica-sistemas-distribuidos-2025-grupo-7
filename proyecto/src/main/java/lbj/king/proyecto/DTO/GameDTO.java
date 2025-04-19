package lbj.king.proyecto.DTO;



public record GameDTO(
    Long id,
    String name,
    float winMultp,
    int minPossibleNumber,
    int maxPossibleNumber,
    boolean hasFich
    ) {
    
}
