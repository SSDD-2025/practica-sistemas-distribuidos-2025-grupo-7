package lbj.king.proyecto.repositories;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import lbj.king.proyecto.model.Play;

public interface PlayRepository extends JpaRepository<Play,Long> {
    @Transactional
    void deleteByUserId(@Param("userId") Long userId);
    Collection<Play> findByUserId(@Param("userId") Long userId);
    Page<Play> findPageByUserId(@Param("userId") Long userId,Pageable pageable);
}