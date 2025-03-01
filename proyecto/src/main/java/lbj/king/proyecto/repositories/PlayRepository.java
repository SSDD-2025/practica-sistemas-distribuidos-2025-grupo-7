package lbj.king.proyecto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import lbj.king.proyecto.model.Partida;

import java.util.List;

public interface PlayRepository extends JpaRepository<Partida,Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Partida p WHERE p.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}