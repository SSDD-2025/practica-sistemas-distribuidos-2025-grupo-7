package lbj.king.proyecto.DTO;

public record PrizeDTO(
    Long id,
    String name,
    int price,
    Boolean owned,
    UserrBasicDTO owner
) {
    // public int getPrice() { return price; } // Para {{Price}}
    
    // // public UserrBasicDTO getOwner() { return user; } // Para {{Owner.name}}
    
    // public boolean isOwned() { return owned != null && owned; }
}
