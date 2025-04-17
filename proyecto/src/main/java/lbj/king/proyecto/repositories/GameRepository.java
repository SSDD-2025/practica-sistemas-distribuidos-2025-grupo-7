package lbj.king.proyecto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import lbj.king.proyecto.model.Game;

public interface GameRepository extends JpaRepository<Game,Long> {
    Game findByName(String name);
}
