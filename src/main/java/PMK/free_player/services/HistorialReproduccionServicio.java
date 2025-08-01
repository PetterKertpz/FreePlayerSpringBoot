package PMK.free_player.services;


import PMK.free_player.models.HistorialReproduccion;
import PMK.free_player.repositorys.HistorialReproduccionRepositorio;
import PMK.free_player.services.interfaces.IHistorialReproduccion;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HistorialReproduccionServicio implements IHistorialReproduccion {

    private static final Logger log = LoggerFactory.getLogger(HistorialReproduccionServicio.class);
    private final HistorialReproduccionRepositorio historialReproduccionRepositorio;

    @Override
    public List<HistorialReproduccion> listarHistorialReproduccion() {
        log.debug("Iniciando lista de historialReproduccion");
        List<HistorialReproduccion> historialReproduccionList = historialReproduccionRepositorio.findAll();
        if (historialReproduccionList.isEmpty()) {
            log.debug("No se encontraron registros de historialReproduccion");
            return List.of();
        }
        log.debug("HistorialReproduccion encontrado: {}", historialReproduccionList.size());
        return historialReproduccionList;
    }

    @Override
    public Optional<HistorialReproduccion> findHistorialReproduccionById(Integer idHistorial) {
        log.debug("Iniciando historialReproduccion");
        Optional<HistorialReproduccion> historialReproduccion = historialReproduccionRepositorio.findById(idHistorial);
        if (historialReproduccion.isEmpty()) {
            log.debug("No se encontró el historial de reproduccion con id: {}", idHistorial);
            return Optional.empty();
        } else {
            log.debug("HistorialReproduccion encontrado: {}", historialReproduccion.get());
            return historialReproduccion;
        }
    }

    @Override
    public Optional<HistorialReproduccion> findHistorialReproduccionByCancion(Integer idCancion) {
        log.debug("Iniciando búsqueda de historialReproduccion por canción con id: {}", idCancion);
        // Usar el método correcto. Si esperas solo uno, puedes tomar el primero de la lista.
        // Si esperas varios, cambia el tipo de retorno del servicio a List<HistorialReproduccion>.
        List<HistorialReproduccion> historialReproduccionList = historialReproduccionRepositorio.findByidCancion_Id(idCancion);
        if (historialReproduccionList.isEmpty()) {
            log.debug("No se encontró el historial de reproduccion para la cancion con id: {}", idCancion);
            return Optional.empty();
        } else {
            log.debug("HistorialReproduccion encontrado: {}", historialReproduccionList.getFirst()); // Podrías devolver el primero o manejar la lista.
            return Optional.of(historialReproduccionList.getFirst()); // Devuelve el primero
        }
    }

    @Override
    public HistorialReproduccion save(HistorialReproduccion historialReproduccion) {
        log.debug("Iniciando busqueda de historialReproduccion");
        try {
            HistorialReproduccion savedHistorial = historialReproduccionRepositorio.save(historialReproduccion);
            log.debug("HistorialReproduccion guardado exitosamente: {}", savedHistorial);
            return savedHistorial;
        } catch (Exception e) {
            log.error("Error al guardar el historial de reproduccion: {}", e.getMessage());
            throw e; // Re-throw the exception to handle it upstream if necessary
        }
    }

    @Override
    public void deleteHistorialReproduccionById(Integer idHistorial) {
        log.debug("Iniciando busqueda de historialReproduccion");
        if (!historialReproduccionRepositorio.existsById(idHistorial)) {
            log.debug("No se encontró el historial de reproduccion con id: {}", idHistorial);
            throw new RuntimeException("No se encontró el historial de reproduccion con id: " + idHistorial);
        }
        historialReproduccionRepositorio.deleteById(idHistorial);
        log.debug("Historial de reproduccion eliminado exitosamente con id: {}", idHistorial);
    }
}
