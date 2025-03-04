package lbj.king.proyecto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import lbj.king.proyecto.model.Play;

import java.util.List;

public interface PlayRepository extends JpaRepository<Play,Long> {
    @Transactional
    void deleteByUserId(@Param("userId") Long userId);
}