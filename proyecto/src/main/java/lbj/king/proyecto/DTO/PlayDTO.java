package lbj.king.proyecto.DTO;

public record PlayDTO(
    Long id,
    float bet,
    float win,
    boolean won,
    UserrBasicDTO user,
    GameDTO game
) {
 
    
}
