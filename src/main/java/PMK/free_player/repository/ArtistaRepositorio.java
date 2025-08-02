package PMK.free_player.repository;

import PMK.free_player.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepositorio extends JpaRepository<Artista, Integer> {
}
