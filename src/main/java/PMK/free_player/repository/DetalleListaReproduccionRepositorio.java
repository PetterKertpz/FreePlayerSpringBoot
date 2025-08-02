// PMK.free_player.repository.DetalleListaReproduccionRepositorio.java
package PMK.free_player.repository;

import PMK.free_player.models.DetalleListaReproduccion;
import PMK.free_player.models.DetalleListaReproduccionPk; // Importa la clave compuesta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // Una lista puede tener muchas canciones, una canción puede estar en varias listas.


@Repository
// La clave primaria es DetalleListaReproduccionPk, no Integer.
public interface DetalleListaReproduccionRepositorio extends JpaRepository<DetalleListaReproduccion, DetalleListaReproduccionPk> {

    // Para buscar todos los detalles por un ID de lista
    List<DetalleListaReproduccion> findById_IdLista(Integer idLista);

    // Para buscar todos los detalles por un ID de canción
    List<DetalleListaReproduccion> findById_IdCancion(Integer idCancion);

    // deleteById(DetalleListaReproduccionPk id); <-- Ya está en la interfaz, lo cual es correcto.
    // Pero el servicio lo está llamando con un Integer.
}