// PMK.free_player.repositorys.FavoritoRepositorio.java
package PMK.free_player.repositorys;

import PMK.free_player.models.Favorito;
import PMK.free_player.models.FavoritoPk; // Importa la clave compuesta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // Una canción puede ser favorita para muchos usuarios.
import java.util.Optional;

@Repository
// La clave primaria de Favorito es FavoritoPk, no Integer.
public interface FavoritoRepositorio extends JpaRepository<Favorito, FavoritoPk> {

    // Busca favoritos por el ID de la canción (parte de la clave compuesta)
    // Esto asume que el campo en FavoritoPk es 'idCancion'.
    List<Favorito> findById_IdCancion(Integer idCancion);

}