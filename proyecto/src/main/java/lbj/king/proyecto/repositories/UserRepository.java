package lbj.king.proyecto.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import lbj.king.proyecto.model.Userr;

public interface UserRepository extends JpaRepository<Userr,Long> {
    Userr findByName(String name);
}
