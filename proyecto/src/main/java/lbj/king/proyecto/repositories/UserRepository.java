package lbj.king.proyecto.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import lbj.king.proyecto.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario,Long> {
    Usuario findByName(String name);
}
