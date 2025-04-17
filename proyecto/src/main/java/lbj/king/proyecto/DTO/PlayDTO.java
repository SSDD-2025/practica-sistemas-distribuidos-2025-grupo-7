package lbj.king.proyecto.DTO;

import lbj.king.proyecto.model.Game;
import lbj.king.proyecto.model.Userr;

public record PlayDTO(
    Long id,
    float bet,
    float win,
    boolean won,
    UserrDTO user,
    GameDTO game
) {
 
    
}
