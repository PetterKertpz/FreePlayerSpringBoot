package PMK.free_player.service;

import PMK.free_player.dto.CancionDto;
import PMK.free_player.exception.NoDataFoundException;
import PMK.free_player.models.Cancion;
import PMK.free_player.repository.CancionRepositorio;
import PMK.free_player.service.interfaces.ICancion;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CancionServicio implements ICancion {

    private static final Logger log = LoggerFactory.getLogger(CancionServicio.class);
    private final CancionRepositorio cancionRepositorio;

    @Override
    public List<Cancion> listarCanciones() {
        log.info("Buscando todas las canciones en la base de datos");
        List<Cancion> listaCanciones = cancionRepositorio.findAll();
        if (listaCanciones.isEmpty()) {
            log.warn("No se encontraron canciones en la base de datos");
            throw new NoDataFoundException("No se encontraron canciones en la base de datos");
        }
        log.info("Se encontraron {} canciones", listaCanciones.size());
        return listaCanciones;
    }

    @Override
    public List<Cancion> listarCancionesPorAlbum(Integer idAlbum) {
        log.info("Buscando canciones por ID de album: {}", idAlbum);
        List<Cancion> canciones = cancionRepositorio.findByAlbumId(idAlbum); // ¡Corregido!
        if (canciones.isEmpty()) {
            log.warn("No se encontraron canciones para el álbum con ID: {}", idAlbum);
            throw new NoDataFoundException("No se encontraron canciones para el álbum con ID: " + idAlbum);
        }
        return canciones;
    }

    @Override
    public List<Cancion> listarCancionesPorGenero(Integer idGenero) {
        log.info("Buscando canciones por ID de género: {}", idGenero);
        List<Cancion> canciones = cancionRepositorio.findByGeneroId(idGenero); // ¡Corregido!
        if (canciones.isEmpty()) {
            log.warn("No se encontraron canciones para el género con ID: {}", idGenero);
            throw new NoDataFoundException("No se encontraron canciones para el género con ID: " + idGenero);
        }
        return canciones;
    }

    @Override
    public Optional<Cancion> findCancionById(Integer idCancion) {
        Optional<Cancion> cancion = cancionRepositorio.findById(idCancion);
        if (cancion.isEmpty()) {
            log.warn("No se encontró la canción con ID: {}", idCancion);
            throw new NoDataFoundException("No se encontró la canción con ID: " + idCancion);
        }
        log.info("Se encontró la canción con ID: {}", idCancion);
        return cancion;
    }


    @Override
    public Cancion saveCancion(Cancion cancion) {
        log.info("Guardando la canción: {}", cancion.getTitulo());
        Cancion saveCancion;
        try {
            saveCancion = cancionRepositorio.save(cancion);
        } catch (
                Exception e) {
            log.error("Error al guardar la canción: {}", e.getMessage());
            throw new RuntimeException("Error al guardar la canción: " + e.getMessage());
        }
        log.info("Canción guardada con éxito: {}", saveCancion.getTitulo());
        return saveCancion;

    }

    @Override
    public void deleteCancionById(Integer idCancion) {
        log .info("Eliminando la canción con ID: {}", idCancion);
        if (cancionRepositorio.findById(idCancion).isPresent()) {
            cancionRepositorio.deleteById(idCancion);
            log.info("Canción con ID: {} eliminada con éxito", idCancion);
        } else {
            log.warn("No se encontró la canción con el ID: {}", idCancion);
            throw new NoDataFoundException("No se encontró la canción con ID: " + idCancion);
        }
    }


    @Transactional(readOnly = true) // La transacción se mantiene abierta durante el metodo
    public List<CancionDto> listarCancionesDto() {
        log.info("Buscando todas las canciones en la base de datos por Dto.");
        // Hibernate carga las relaciones dentro de esta transacción
        List<Cancion> canciones = cancionRepositorio.findAll();
        log.info("Se encontraron {} canciones", canciones.size());

        // Mapear entidades a DTOs
        return canciones.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CancionDto mapToDto(Cancion cancion) {
        CancionDto dto = new CancionDto();
        dto.setId(cancion.getId());
        dto.setTitulo(cancion.getTitulo());

        // Acceder a las propiedades de las entidades lazy-loaded
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
