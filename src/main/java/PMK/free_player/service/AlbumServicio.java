package PMK.free_player.service;

import PMK.free_player.models.Album;
import PMK.free_player.repository.AlbumRepositorio;
import PMK.free_player.service.interfaces.IAlbum;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AlbumServicio implements IAlbum {

    private static final Logger log = LoggerFactory.getLogger(AlbumServicio.class);
    private final AlbumRepositorio albumRepositorio;

    @Override
    public List<Album> listarAlbumes() {
        log.info("Iniciando lista de albumes");
        List<Album> albumes = albumRepositorio.findAll();
        if (albumes.isEmpty()) {
            log.error("Album no encontrado");
            throw new RuntimeException("No se encontraron albumes");
        }
        log.info("Albumes encontrados: {}", albumes.size());
        return albumes;
    }

    @Override
    public List<Album> listarAlbumesPorArtista(Integer idArtista) {
        log.info("Iniciando lista de albumes por artista con ID: {}", idArtista);
        // ¡Usa el metodo corregido del repositorio!
        List<Album> albumes = albumRepositorio.findByidArtista(idArtista);
        if (albumes.isEmpty()) {
            log.error("Album no encontrado para el artista con ID: {}", idArtista);
            throw new RuntimeException("No se encontraron albumes para el artista con ID: " + idArtista);
        }
        log.info("Albumes encontrados para el artista con ID {}: {}", idArtista, albumes.size());
        return albumes;
    }

    @Override
    public Optional<Album> findAlbumPorId(Integer idAlbum) {
        log.info("Iniciando el album con ID {}", idAlbum);
        Optional<Album> album = albumRepositorio.findById(idAlbum);
        if (album.isEmpty()) {
            log.error("Album no encontrado con ID: {}", idAlbum);
            throw new RuntimeException("No se encontró el album con ID: " + idAlbum);
        } else {
            log.info("Album encontrado con ID {}: {}", idAlbum, album.get());
            return album;
        }
    }

     public Optional<Album> findAlbumPorArtista(Integer idArtista) {
        log.info("Buscando un álbum (opcional) para el artista con ID: {}", idArtista);
        // Esto buscará una lista y tomará el primero si existe. Es una solución temporal
        // si realmente necesitas un Optional. Lo ideal es redefinir el propósito de este método.
        List<Album> albums = albumRepositorio.findByidArtista(idArtista);
        if (albums.isEmpty()) {
            log.warn("No se encontró ningún álbum para el artista con ID: {}", idArtista);
            return Optional.empty();
        } else {
            log.info("Se encontró al menos un álbum para el artista con ID {}. Devolviendo el primero.", idArtista);
            return Optional.of(albums.getFirst()); // Devuelve el primero de la lista
        }
    }

    @Override
    public Album saveAlbum(Album album) {
        log.info("Guardando el album con id {}", album.getId());
        try
        {
            return albumRepositorio.save(album);
        } catch (Exception e) {
            log.error("Error al guardar el album: {}", e.getMessage());
            throw new RuntimeException("Error al guardar el album. Contacte al administrador.");
        }

    }

    @Override
    public void deleteAlbumById(Integer idAlbum) {
        log.info("Eliminando el album con ID {}", idAlbum);
        if (!albumRepositorio.existsById(idAlbum)) {
            log.error("Album no encontrado con ID: {}", idAlbum);
            throw new RuntimeException("Album no encontrado con ID: " + idAlbum);
        }
        try {
            albumRepositorio.deleteById(idAlbum);
            log.info("Album con ID {} eliminado correctamente", idAlbum);
        } catch (Exception e) {
            log.error("Error al eliminar el album con ID {}: {}", idAlbum, e.getMessage());
            throw new RuntimeException("Error al eliminar el album. Contacte al administrador.");
        }
        log.info("Album con ID {} eliminado correctamente", idAlbum);
    }
}
