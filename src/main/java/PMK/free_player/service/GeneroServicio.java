package PMK.free_player.service;


import PMK.free_player.models.Genero;
import PMK.free_player.repository.GeneroRepositorio;
import PMK.free_player.service.interfaces.IGenero;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GeneroServicio implements IGenero {

    private static final Logger log = LoggerFactory.getLogger(GeneroServicio.class);
    private final GeneroRepositorio generoRepositorio;

    @Override
    public List<Genero> listarGeneros() {
        log.info("Iniciando lista de generos");
        List<Genero> generos = generoRepositorio.findAll();
        if (generos.isEmpty()) {
            log.info("Genero no encontrado");
            throw new RuntimeException("No se encontraron géneros en la base de datos");
        }
        log.info("Lista de generos obtenida con éxito, total: {}", generos.size());
        return generos;
    }

    @Override
    public Optional<Genero> findGeneroById(Integer idGenero) {
        log.info("Iniciando busca genero con id: {}", idGenero);
        Optional<Genero> genero = generoRepositorio.findById(idGenero);
        if (genero.isEmpty()) {
            log.info("Genero con id {} no encontrado", idGenero);
            throw new RuntimeException("Género no encontrado con el ID proporcionado: " + idGenero);
        }
        log.info("Genero con id {} encontrado: {}", idGenero, genero.get());
        return genero;
    }

    @Override
    public Genero saveGenero(Genero genero) {
        log.info("Iniciando save genero con genero: {}", genero);
        try {
            Genero savedGenero = generoRepositorio.save(genero);
            log.info("Genero guardado con éxito: {}", savedGenero);
            return savedGenero;
        } catch (Exception e) {
            log.error("Error al guardar el género: {}", e.getMessage());
            throw new RuntimeException("Error al guardar el género: " + e.getMessage());
        }
    }

    @Override
    public void deleteGeneroById(Integer idGenero) {
        log.info("Iniciando delete genero con id: {}", idGenero);
        if (!generoRepositorio.existsById(idGenero)) {
            log.info("Genero con id {} no encontrado para eliminar", idGenero);
            throw new RuntimeException("Género no encontrado con el ID proporcionado: " + idGenero);
        }
        generoRepositorio.deleteById(idGenero);
        log.info("Genero con id {} eliminado con éxito", idGenero);

    }
}
