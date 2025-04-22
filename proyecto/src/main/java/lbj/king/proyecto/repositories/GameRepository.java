package lbj.king.proyecto.repositories;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import lbj.king.proyecto.model.Game;

public interface GameRepository extends JpaRepository<Game,Long> {
    Optional<Game> findByName(String name);
    // Page<Game> findPageByGame(@Param("id") Long id,Pageable pageable);

}
