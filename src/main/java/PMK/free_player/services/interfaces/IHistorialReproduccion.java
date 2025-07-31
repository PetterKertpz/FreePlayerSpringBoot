package PMK.free_player.services.interfaces;

import PMK.free_player.models.HistorialReproduccion;

import java.util.List;
import java.util.Optional;

public interface IHistorialReproduccion {

    List<HistorialReproduccion> listarHistorialReproduccion();

    Optional<HistorialReproduccion> findHistorialReproduccionById(Integer idHistorial);

    Optional<HistorialReproduccion> findHistorialReproduccionByCancion(Integer idCancion);

    HistorialReproduccion save(HistorialReproduccion historialReproduccion);

    void deleteHistorialReproduccionById(Integer idHistorial);


}
