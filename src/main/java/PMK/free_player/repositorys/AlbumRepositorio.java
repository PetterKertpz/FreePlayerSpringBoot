package PMK.free_player.repositorys;

import PMK.free_player.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepositorio extends JpaRepository<Album, Integer> {

    // CAMBIADO: Se busca por la propiedad 'id' dentro del objeto 'idArtista'
    Optional<Album> findByidArtista_Id(Integer idArtista); // Antes: findByIdArtista

    // CAMBIADO: Se busca por la propiedad 'id' dentro del objeto 'idArtista'
    List<Album> findAllByidArtista_Id(Integer idArtista); // Antes: findAllByIdArtista
}