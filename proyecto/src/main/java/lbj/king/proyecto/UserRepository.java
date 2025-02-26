package lbj.king.proyecto;



import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario,Long> {
    Usuario findByName(String name);
}
