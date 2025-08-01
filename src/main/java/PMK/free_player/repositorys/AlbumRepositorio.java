package PMK.free_player.repositorys;

import PMK.free_player.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlbumRepositorio extends JpaRepository<Album, Integer> {

    List<Album> findByidArtista(Integer idArtista);
}
