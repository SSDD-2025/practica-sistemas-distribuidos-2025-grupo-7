package lbj.king.proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GameRepository extends JpaRepository<Partida,Long> {
    List<Partida> findById(long id);
}