package PMK.free_player.repositorys;

import PMK.free_player.models.HistorialReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialReproduccionRepositorio extends JpaRepository<HistorialReproduccion, Integer> {
    List<HistorialReproduccion> findByidCancion_Id(Integer idCancion);
}
