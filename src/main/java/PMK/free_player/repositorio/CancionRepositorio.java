package PMK.free_player.repositorio;

import PMK.free_player.modelo.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionRepositorio extends JpaRepository<Cancion,Integer> {

}
