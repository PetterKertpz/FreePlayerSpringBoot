package PMK.free_player.repository;

import PMK.free_player.models.ConfiguracionReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionReproduccionRepositorio extends JpaRepository<ConfiguracionReproduccion, Integer> {
}
