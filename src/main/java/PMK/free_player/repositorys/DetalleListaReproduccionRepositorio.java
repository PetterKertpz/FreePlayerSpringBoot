package PMK.free_player.repositorys;

import PMK.free_player.models.DetalleListaReproduccion;
import PMK.free_player.models.DetalleListaReproduccionPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleListaReproduccionRepositorio extends JpaRepository<DetalleListaReproduccion, Integer> {

    void deleteById(DetalleListaReproduccionPk id);

}
