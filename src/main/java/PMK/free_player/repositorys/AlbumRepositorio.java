package PMK.free_player.repositorys;

import PMK.free_player.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepositorio extends JpaRepository<Album, Integer> {

    Optional<Album> findByIdArtista(Integer idArtista);

    List<Album> findAllByIdArtista(Integer idArtista);
}
