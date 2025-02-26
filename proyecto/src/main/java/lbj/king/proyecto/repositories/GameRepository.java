package lbj.king.proyecto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import lbj.king.proyecto.model.Juegos;

import java.util.List;

public interface GameRepository extends JpaRepository<Juegos,Long> {
    Juegos findByName(String name);
}
