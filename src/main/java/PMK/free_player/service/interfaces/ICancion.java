package PMK.free_player.service.interfaces;

import PMK.free_player.exception.NoDataFoundException;
import PMK.free_player.models.Cancion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface ICancion {

    Logger log = LoggerFactory.getLogger(ICancion.class);

    List<Cancion> listarCanciones();

    List<Cancion> listarCancionesPorAlbum(Integer idAlbum);

    List<Cancion> listarCancionesPorGenero(Integer idGenero);

    Optional<Cancion> findCancionById(Integer idCancion);

    Cancion saveCancion(Cancion cancion);

    void deleteCancionById(Integer idCancion);

    default List<Cancion> listarCancionesPorId(Integer id, String tipo, FindByIdOperation findOperation){
        if (id == null) {
            log.error("El ID de la {} no puede ser nulo", tipo);
            throw new IllegalArgumentException("El ID de la " + tipo + " no puede ser nulo");
        }
        log.info("Buscando canciones por ID de {}: {}", tipo, id);

        List<Cancion> listaCanciones = findOperation.findAllById(Collections.singleton(id));
        if (listaCanciones.isEmpty()) {
            log.warn("No se encontraron canciones en la base de datos para el ID de {}", tipo);
            throw new NoDataFoundException("No se encontraron canciones en la base de datos para el ID de " + tipo);
        }
        log.info("Se encontraron {} canciones para el ID de {}", listaCanciones.size(), tipo);
        return listaCanciones;
    }

    @FunctionalInterface
    interface FindByIdOperation {
        List<Cancion> findAllById(Iterable<Integer> ignoredIds);
    }

}
