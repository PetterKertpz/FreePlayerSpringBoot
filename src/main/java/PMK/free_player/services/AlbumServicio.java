package PMK.free_player.services;

import PMK.free_player.models.Album;
import PMK.free_player.repositorys.AlbumRepositorio;
import PMK.free_player.services.interfaces.IAlbum;
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
        log.info("Iniciando lista de albumes por artista");
        List<Album> albumes = albumRepositorio.findAllByidArtista_Id(idArtista);
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

    @Override
    public Optional<Album> findAlbumPorArtista(Integer idArtista) {
        log.info("Iniciando el album con ID {}", idArtista);
        Optional<Album> albumes = albumRepositorio.findByidArtista_Id(idArtista);
        if (albumes.isEmpty()) {
            log.error("Album no encontrado para el artista con ID: {}", idArtista);
            throw new RuntimeException("No se encontró el album para el artista con ID: " + idArtista);
        } else {
            log.info("Album encontrado para el artista con ID {}: {}", idArtista, albumes.get());
            return albumes;
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
