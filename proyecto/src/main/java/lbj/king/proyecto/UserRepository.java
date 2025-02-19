package lbj.king.proyecto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findByName(String name);
}
