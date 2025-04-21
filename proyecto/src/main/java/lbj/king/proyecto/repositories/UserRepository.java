package lbj.king.proyecto.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lbj.king.proyecto.DTO.UserrBasicDTO;
import lbj.king.proyecto.model.Userr;

public interface UserRepository extends JpaRepository<Userr,Long> {
    Optional<Userr> findByName(String name);

    // @Query("SELECT u FROM Userr u WHERE u.name = :name")
    // Optional<Userr> findByNameBasic(String name);
}
