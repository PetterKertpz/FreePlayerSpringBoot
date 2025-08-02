package PMK.free_player.service;

import PMK.free_player.exceptions.NoDataFoundException;
import PMK.free_player.exceptions.RecursoNoEncontradoException;
import PMK.free_player.exceptions.UsuarioNoEncontradoException;
import PMK.free_player.models.ListaReproduccion;
import PMK.free_player.repository.ListaReproduccionRepositorio;
import PMK.free_player.service.interfaces.IListaReproduccion;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ListaReproduccionServicio implements IListaReproduccion {

    private static final Logger log = LoggerFactory.getLogger(ListaReproduccionServicio.class);
    private final ListaReproduccionRepositorio listaReproduccionRepositorio;

    @Override
    public List<ListaReproduccion> ListarListasReproduccion() {
        log.debug("Listando Listas de Reproduccion");
        List<ListaReproduccion> listas = listaReproduccionRepositorio.findAll();
        if (listas.isEmpty()) {
            log.warn("No se encontraron listas de reproduccion");
        } else {
            log.info("Se encontraron {} listas de reproduccion", listas.size());
        }
        return listas;
    }

    @Override
    public Optional<ListaReproduccion> FindListaReproduccionById(Integer idLista) {
        log.debug("Buscando Lista de Reproduccion por ID: {}", idLista);
        Optional<ListaReproduccion> listaReproduccion = listaReproduccionRepositorio.findById(idLista);
        if (listaReproduccion.isEmpty()) {
            log.warn("Lista de reproduccion no encontrada con ID: {}", idLista);
            // Usar una excepción más apropiada
            throw new RecursoNoEncontradoException("Lista de reproduccion no encontrada con ID: " + idLista);
        }
        log.info("Lista de reproduccion encontrada: {}", listaReproduccion.get());
        return listaReproduccion;
    }

    @Override
    public Optional<ListaReproduccion> FindListaReproduccionByUsuario(Integer idUsuario) {
        log.debug("Buscando Lista de Reproduccion por Usuario ID: {}", idUsuario);
        // Usa el metodo correcto del repositorio para buscar por el ID del usuario
        Optional<ListaReproduccion> listaReproduccion = listaReproduccionRepositorio.findByidUsuario_Id(idUsuario);
        if (listaReproduccion.isEmpty()) {
            log.warn("Lista de reproduccion no encontrada para el usuario con ID: {}", idUsuario);
            // Usar una excepción más apropiada
            throw new RecursoNoEncontradoException("Lista de reproduccion no encontrada para el usuario con ID: " + idUsuario);
        }
        log.info("Lista de reproduccion encontrada para el usuario: {}", listaReproduccion.get());
        return listaReproduccion;
    }

    @Override
    public ListaReproduccion SaveListaReproduccion(ListaReproduccion listaReproduccion) {
        log.debug("Guardando Lista de Reproduccion: {}", listaReproduccion);
        ListaReproduccion ListaAGuardar;
        try {
            ListaAGuardar = listaReproduccionRepositorio.save(listaReproduccion);
        } catch (
                Exception e) {
            log.error("Error al guardar la lista de reproduccion: {}", e.getMessage());
            throw new RuntimeException("Error al guardar la lista de reproduccion: " + e.getMessage(), e);
        }
        log.info("Lista de reproduccion guardada con ID: {}", ListaAGuardar.getId());
        return ListaAGuardar;
    }

    @Override
    public void DeleteListaReproduccionById(Integer idLista) {
        log.debug("Eliminando Lista de Reproduccion por ID: {}", idLista);
        if (!listaReproduccionRepositorio.existsById(idLista)) {
            log.warn("No se pudo encontrar la lista de reproduccion con ID: {}", idLista);
            throw new NoDataFoundException("Lista de reproduccion no encontrada con ID: " + idLista);
        }
        listaReproduccionRepositorio.deleteById(idLista);
        log.info("Lista de reproduccion eliminada con ID: {}", idLista);

    }
}
