package lbj.king.proyecto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import lbj.king.proyecto.model.Play;

public interface PlayRepository extends JpaRepository<Play,Long> {
    @Transactional
    void deleteByUserId(@Param("userId") Long userId);
}