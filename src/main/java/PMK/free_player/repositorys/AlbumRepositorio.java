package PMK.free_player.repositorys;

import PMK.free_player.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepositorio extends JpaRepository<Album, Integer> {
}
