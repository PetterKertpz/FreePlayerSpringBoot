package PMK.free_player.repository;

import PMK.free_player.models.ConfiguracionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionUsuarioRepositorio extends JpaRepository<ConfiguracionUsuario, Integer> {
}
