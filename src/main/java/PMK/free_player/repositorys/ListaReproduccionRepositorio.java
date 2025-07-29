package PMK.free_player.repositorys;

import PMK.free_player.models.ListaReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaReproduccionRepositorio extends JpaRepository<ListaReproduccion, Integer> {
}
