package lbj.king.proyecto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PremiosRepository extends JpaRepository<Premio,Long> {
    public Premio findPremioByOwner(Usuario owner);
}
