package lbj.king.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.model.Userr;

public interface PrizeRepository extends JpaRepository<Prize,Long> {
    public Prize findPremioByOwner(Userr owner);
    public Prize findPremioById(Long id);
}
