package lbj.king.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lbj.king.proyecto.model.Premio;
import lbj.king.proyecto.model.Usuario;

public interface PremiosRepository extends JpaRepository<Premio,Long> {
    public Premio findPremioByOwner(Usuario owner);
}
