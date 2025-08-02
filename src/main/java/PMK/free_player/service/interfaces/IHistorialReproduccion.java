package PMK.free_player.service.interfaces;

import PMK.free_player.models.HistorialReproduccion;

import java.util.List;
import java.util.Optional;

public interface IHistorialReproduccion {

    List<HistorialReproduccion> listarHistorialReproduccion();

    Optional<HistorialReproduccion> findHistorialReproduccionById(Integer idHistorial);

    List<HistorialReproduccion> findHistorialReproduccionByCancion(Integer idCancion);

    HistorialReproduccion save(HistorialReproduccion historialReproduccion);

    void deleteHistorialReproduccionById(Integer idHistorial);


}
