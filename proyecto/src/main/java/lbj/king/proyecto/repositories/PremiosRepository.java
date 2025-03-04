package lbj.king.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lbj.king.proyecto.model.Prize;
import lbj.king.proyecto.model.Userr;

public interface PremiosRepository extends JpaRepository<Prize,Long> {
    public Prize findPremioByOwner(Userr owner);
    public Prize findPremioById(Long id);
}
