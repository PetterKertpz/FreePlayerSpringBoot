package PMK.free_player.repository;

import PMK.free_player.models.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancionRepositorio extends JpaRepository<Cancion, Integer> {
    List<Cancion> findByAlbumId(Integer idAlbum); // Para buscar por el ID del álbum
    List<Cancion> findByGeneroId(Integer idGenero); // Para buscar por el ID del género
}
