package lbj.king.proyecto.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lbj.king.proyecto.model.Game;

public interface GameRepository extends JpaRepository<Game,Long> {
    Optional<Game> findByName(String name);
}
