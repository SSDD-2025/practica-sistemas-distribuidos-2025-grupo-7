package lbj.king.proyecto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import lbj.king.proyecto.model.Partida;

import java.util.List;

public interface PlayRepository extends JpaRepository<Partida,Long> {
    @Transactional
    void deleteByUserId(Long userId);
}