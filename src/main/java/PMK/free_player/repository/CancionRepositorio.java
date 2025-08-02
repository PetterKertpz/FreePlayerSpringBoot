package PMK.free_player.repository;

import PMK.free_player.models.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancionRepositorio extends JpaRepository<Cancion, Integer> {
    List<Cancion> findByIdAlbum_Id(Integer idAlbum); // Para buscar por el ID del álbum
    List<Cancion> findByIdGenero_Id(Integer idGenero); // Para buscar por el ID del género
}
