package lbj.king.proyecto.DTO;

public record PrizeDTO(
    Long id,
    String name,
    int price,
    Boolean owned,
    String userName
) {
    
}
