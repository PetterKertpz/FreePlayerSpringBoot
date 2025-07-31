package PMK.free_player.repositorys;

import PMK.free_player.models.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionRepositorio extends JpaRepository<Cancion, Integer> {
}
