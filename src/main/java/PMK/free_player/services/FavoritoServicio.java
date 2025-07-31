package PMK.free_player.services;

import PMK.free_player.models.Favorito;
import PMK.free_player.repositorys.FavoritoRepositorio;
import PMK.free_player.services.interfaces.IFavorito;
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
        Optional<Favorito> favorito = favoritoRepositorio.findById(idCancion);
        if (favorito.isPresent()) {
            log.info("Favorito encontrado: {}", favorito.get());
        } else {
            log.warn("No se encontró favorito con ID de canción: {}", idCancion);
            throw new RuntimeException("No se encontró el favorito con ID de canción: " + idCancion);
        }
        return favorito;
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
        log.debug("Eliminando favorito: {}", idCancion);
        if (!favoritoRepositorio.existsById(idCancion)) {
            log.warn("No se encontró favorito con ID canción: {}", idCancion);
            throw new RuntimeException("No se encontró el favorito con ID de canción: " + idCancion);
        }
        favoritoRepositorio.deleteById(idCancion);
        log.info("Favorito eliminado exitosamente con ID de canción: {}", idCancion);
    }
}
