package PMK.free_player.repositorys;

import PMK.free_player.models.TemaInterfaz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaInterfazRepositorio extends JpaRepository<TemaInterfaz, Integer> {
}
