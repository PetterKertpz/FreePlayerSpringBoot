package PMK.free_player.service;

import PMK.free_player.exception.NoDataFoundException;
import PMK.free_player.models.Artista;
import PMK.free_player.repository.ArtistaRepositorio;
import PMK.free_player.service.interfaces.IArtista;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArtistaServicio implements IArtista {

    private static final Logger log = LoggerFactory.getLogger(ArtistaServicio.class);
    private final ArtistaRepositorio artistaRepositorio;

    @Override
    public List<Artista> listarArtistas() {
        log.info("Iniciando lista de artistas");
        List<Artista> artistas = artistaRepositorio.findAll();
        if (artistas.isEmpty()) {
            log.warn("No se encontraron artistas en la base de datos");
            throw new NoDataFoundException("No se encontraron artistas en la base de datos");
        }
        log.info("Artistas encontrados: {}", artistas.size());
        return artistas;
    }

    @Override
    public Optional<Artista> findArtistaById(Integer idArtista) {
        log.info("Iniciando la busqueda del artista con id: {}", idArtista);
        Optional<Artista> artista = artistaRepositorio.findById(idArtista);
        if (artista.isEmpty()) {
            log.warn("No se encontró el artista con el ID: {}", idArtista);
            throw new NoDataFoundException("No se encontró el artista con ID: " + idArtista);
        }
        log.info("Artista encontrado: {}", artista.get().getNombre());
        return artista;
    }

    @Override
    public Artista saveArtista(Artista artista) {
        log.info("Guardando el artista: {}", artista.getNombre());
        try {
            Artista savedArtista = artistaRepositorio.save(artista);
            log.info("Artista guardado exitosamente: {}", savedArtista.getNombre());
            return savedArtista;
        } catch (Exception e) {
            log.error("Error al guardar el artista: {}", e.getMessage());
            throw new RuntimeException("Error al guardar el artista: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteArtistaById(Integer idArtista) {
        log.info("Iniciando la eliminación del artista con ID: {}", idArtista);
        if (!artistaRepositorio.existsById(idArtista)) {
            log.warn("No se encontró el artista con ID: {}", idArtista);
            throw new NoDataFoundException("No se encontró el artista con ID: " + idArtista);
        }
        artistaRepositorio.deleteById(idArtista);
        log.info("Artista con ID: {} eliminado exitosamente", idArtista);

    }
}
