package PMK.free_player.service;

import PMK.free_player.dto.CancionListaDetalleDto;
import PMK.free_player.exception.NoDataFoundException;
import PMK.free_player.models.Cancion;
import PMK.free_player.models.DetalleListaReproduccion;
import PMK.free_player.models.ListaReproduccion;
import PMK.free_player.repository.DetalleListaReproduccionRepositorio;
import PMK.free_player.repository.ListaReproduccionRepositorio;
import PMK.free_player.service.interfaces.IDetalleListaReproduccion;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DetalleListaReproduccionServicio implements IDetalleListaReproduccion {

    private static final Logger log = LoggerFactory.getLogger(DetalleListaReproduccionServicio.class);
    private final DetalleListaReproduccionRepositorio detalleListaReproduccionRepositorio;
    private final ListaReproduccionRepositorio listaReproduccionRepositorio;

    @Override
    public List<DetalleListaReproduccion> findDetalleListaReproduccionByIdLista(Integer idLista) {
        // Ahora busca una lista por el ID de la lista.
        // Si la interfaz ITDetalleListaReproduccion espera un Optional<DetalleListaReproduccion>
        // y no una lista, esto es un mismatch. Deberías ajustar la interfaz o la lógica aquí
        // para devolver un solo resultado si es lo que esperas, o cambiar el retorno a List.
        log.debug("Buscando detalle de lista de reproducción por ID de lista: {}", idLista);
        List<DetalleListaReproduccion> detalles = detalleListaReproduccionRepositorio.findById_IdLista(idLista);
        if (detalles.isEmpty()) {
            log.warn("No se encontraron detalles de lista de reproducción para el ID de lista: {}", idLista);
        }
        log.info("Se encontraron {} detalles de lista de reproducción para el ID de lista: {}", detalles.size(), idLista);
        return  detalles;
    }

    @Override
    public void saveCancionALista(DetalleListaReproduccion detalleListaReproduccion) {
        // Este metodo parece correcto ya que recibe el objeto completo.
        log.debug("Guardando canción en lista de reproducción: {}", detalleListaReproduccion);
        detalleListaReproduccionRepositorio.save(detalleListaReproduccion);
        log.info("Canción guardada en lista con ID de lista: {} y ID de canción: {}",
                detalleListaReproduccion.getId().getIdLista(), detalleListaReproduccion.getId().getIdCancion());
    }

    @Override
    public void deleteCancionDeLista(Integer idCancion) {

        log.debug("Intentando eliminar canción de lista(s) por ID de canción: {}", idCancion);
        List<DetalleListaReproduccion> detallesToDelete = detalleListaReproduccionRepositorio.findById_IdCancion(idCancion);

        if (detallesToDelete.isEmpty()) {
            log.warn("No se encontraron detalles de lista de reproducción para la canción con ID: {}", idCancion);
            throw new NoDataFoundException("No se encontraron detalles de lista de reproducción para la canción con ID: " + idCancion);
        }

        detalleListaReproduccionRepositorio.deleteAll(detallesToDelete);
        log.info("Se eliminaron {} entradas de canciones con ID {} de todas las listas de reproducción.", detallesToDelete.size(), idCancion);
    }

    @Transactional(readOnly = true)
    public List<CancionListaDetalleDto> obtenerDetallesCancionesDeLista(Integer idLista) {
        log.info("Obteniendo DTOs detallados de canciones para la lista ID: {}", idLista);
        if (idLista == null) {
            log.warn("El ID de la lista es nulo.");
            return Collections.emptyList();
        }

        // 1. Obtenemos la entidad de la Lista de Reproducción
        Optional<ListaReproduccion> listaOpt = listaReproduccionRepositorio.findById(idLista);
        if (listaOpt.isEmpty()) {
            log.warn("No se encontró una lista con el ID: {}", idLista);
            return Collections.emptyList();
        }
        ListaReproduccion lista = listaOpt.get();

        // 2. Obtenemos los detalles (las canciones) de esa lista
        List<DetalleListaReproduccion> detalles = detalleListaReproduccionRepositorio.findById_IdLista(idLista);

        // 3. Mapeamos cada canción, pasándole la información de la lista a la que pertenece
        return detalles.stream()
                .map(detalle -> mapearEntidadADetalleDto(detalle.getIdCancion(), lista))
                .collect(Collectors.toList());
    }

    private CancionListaDetalleDto mapearEntidadADetalleDto(Cancion cancion, ListaReproduccion lista) {
        CancionListaDetalleDto dto = new CancionListaDetalleDto();

        // Mapeo de los datos de la Lista
        dto.setNombreLista(lista.getNombre());
        dto.setDescripcionLista(lista.getDescripcion());
        dto.setUltimaModificacionLista(lista.getUltimaModificacion());

        // Mapeo de los datos de la Canción
        dto.setTituloCancion(cancion.getTitulo());
        dto.setLetraCancion(cancion.getLetra()); // Asumiendo que la entidad Cancion tiene un campo 'letra'

        // Comprobaciones de nulidad para evitar errores
        if (cancion.getArtista() != null) {
            dto.setNombreArtista(cancion.getArtista().getNombre());
        }
        if (cancion.getAlbum() != null) {
            dto.setTituloAlbum(cancion.getAlbum().getTitulo());
        }
        if (cancion.getGenero() != null) {
            dto.setNombreGenero(cancion.getGenero().getNombre());
        }

        return dto;
    }



}
