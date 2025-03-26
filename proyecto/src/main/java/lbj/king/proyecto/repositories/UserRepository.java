package lbj.king.proyecto.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lbj.king.proyecto.model.Userr;

public interface UserRepository extends JpaRepository<Userr,Long> {
    Optional<Userr> findByName(String name);
}
