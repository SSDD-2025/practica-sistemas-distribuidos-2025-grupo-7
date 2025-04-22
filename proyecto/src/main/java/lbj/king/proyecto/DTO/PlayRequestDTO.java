package lbj.king.proyecto.DTO;

public record PlayRequestDTO(
    Long id,
    Long gameId,
    Float bet,
    Integer selectedNumber
) {} 
