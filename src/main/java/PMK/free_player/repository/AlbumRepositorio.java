// En PMK.free_player.repository.AlbumRepositorio.java
package PMK.free_player.repository;

import PMK.free_player.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepositorio extends JpaRepository<Album, Integer> {
    // Correcto: Buscar por el ID del campo 'idArtista' que es una relación a la entidad Artista
    List<Album> findByidArtista_Id(Integer idArtista); // ¡CORREGIDO!
}