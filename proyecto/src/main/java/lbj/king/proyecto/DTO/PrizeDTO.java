package lbj.king.proyecto.DTO;

import lbj.king.proyecto.model.Userr;

public record PrizeDTO(
    long id,
    String name,
    int price,
    Boolean owned,
    Userr ownner
) {
    
}
