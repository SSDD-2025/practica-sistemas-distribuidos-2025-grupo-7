package lbj.king.proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlayRepository extends JpaRepository<Partida,Long> {
}