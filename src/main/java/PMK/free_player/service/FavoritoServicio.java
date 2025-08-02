package PMK.free_player.service;

import PMK.free_player.exceptions.NoDataFoundException;
import PMK.free_player.exceptions.UsuarioNoEncontradoException;
import PMK.free_player.models.Favorito;
import PMK.free_player.repository.FavoritoRepositorio;
import PMK.free_player.service.interfaces.IFavorito;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FavoritoServicio implements IFavorito {

    private static final Logger log = LoggerFactory.getLogger(FavoritoServicio.class);
    private final FavoritoRepositorio favoritoRepositorio;

    @Override
    public List<Favorito> ListarFavoritos() {
        log.debug("Listando favoritos");
        List<Favorito> favoritos = favoritoRepositorio.findAll();
        if (favoritos.isEmpty()) {
            log.info("No se encontraron favoritos");
            return List.of();
        } else {
            log.info("Se encontraron {} favoritos", favoritos.size());
            return favoritos;
        }


    }

    @Override
    public Optional<Favorito> FindFavoritoByIdCancion(Integer idCancion) {
        log.debug("Buscando favorito por ID de canción: {}", idCancion);

        List<Favorito> favoritos = favoritoRepositorio.findById_IdCancion(idCancion);
        if (favoritos.isEmpty()) {
            log.warn("No se encontró favorito con ID de canción: {}", idCancion);
            // Usar una excepción más específica.
            throw new UsuarioNoEncontradoException("No se encontró el favorito con ID de canción: " + idCancion);
        } else {
            log.info("Se encontraron {} favoritos para la canción con ID: {}", favoritos.size(), idCancion);
            return Optional.of(favoritos.getFirst()); // O reconsidera el tipo de retorno y si solo necesitas uno.
        }
    }

    @Override
    public Favorito saveFavorito(Favorito favorito) {
        log.debug("Salvando favorito: {}", favorito);
        try {
            Favorito savedFavorito = favoritoRepositorio.save(favorito);
            log.info("Favorito guardado exitosamente: {}", savedFavorito);
            return savedFavorito;
        } catch (Exception e) {
            log.error("Error al guardar el favorito: {}", e.getMessage());
            throw new RuntimeException("Error al guardar el favorito: " + e.getMessage());
        }
    }

    @Override
    public void deleteFavorito(Integer idCancion) {
        log.debug("Eliminando favoritos para el ID de canción: {}", idCancion);
        // Para eliminar por idCancion cuando la clave es compuesta,
        // primero necesitamos encontrar todos los favoritos asociados a esa canción
        // y luego eliminarlos.
        List<Favorito> favoritosToDelete = favoritoRepositorio.findById_IdCancion(idCancion);

        if (favoritosToDelete.isEmpty()) {
            log.warn("No se encontraron favoritos para la canción con ID: {}", idCancion);
            throw new NoDataFoundException("No se encontraron favoritos para la canción con ID: " + idCancion);
        }

        // Eliminar cada favorito encontrado
        favoritoRepositorio.deleteAll(favoritosToDelete); // Elimina una colección
        log.info("Se eliminaron {} favoritos para la canción con ID: {}", favoritosToDelete.size(), idCancion);
    }
}

