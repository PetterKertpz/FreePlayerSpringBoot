package PMK.free_player.services;

import PMK.free_player.models.TemaInterfaz;
import PMK.free_player.repositorys.TemaInterfazRepositorio;
import PMK.free_player.services.interfaces.ITemaInterfaz;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import PMK.free_player.exceptions.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TemaInterfazServicio implements ITemaInterfaz{

    private static final Logger log = LoggerFactory.getLogger(TemaInterfazServicio.class);
    private final TemaInterfazRepositorio temaInterfazRepositorio;


    @Override
    public List<TemaInterfaz> ListarTemasInterfaz() {
        log.info("Iniciando Listar Temas de Interfaz");
        List<TemaInterfaz> listaTemas = temaInterfazRepositorio.findAll();
        if (listaTemas.isEmpty()) {
            log.warn("No se encontraron temas de interfaz registrados en la base de datos");
            throw new NoDataFoundException("No existen temas de interfaz registrados actualmente.");
        }
        return listaTemas;
    }

    @Override
    public Optional<TemaInterfaz> FindTemaInterfazById(Integer idTema) {
        log.info("Buscando tema de interfaz por ID: {}", idTema);
        Optional<TemaInterfaz> temaInterfaz = temaInterfazRepositorio.findById(idTema);
        if (temaInterfaz.isEmpty()) {
            log.warn("Tema de interfaz no encontrado con ID: {}", idTema);
            throw new UsuarioNoEncontradoException("Tema de interfaz no encontrado con ID: " + idTema);
        }
        return temaInterfaz;
    }

    @Override
    public TemaInterfaz SaveTemaInterfaz(TemaInterfaz temaInterfaz) {
        log.info("Guardando tema de interfaz: {}", temaInterfaz);
        try {
            return temaInterfazRepositorio.save(temaInterfaz);
        } catch (Exception e) {
            log.error("Error al guardar el tema de interfaz: {}", e.getMessage());
            throw new ErrorInternoException("Error al guardar el tema de interfaz. Contacte al administrador.");
        }
    }

    @Override
    public void DeleteTemaInterfazById(Integer idTema) {
        log.info("Eliminando tema de interfaz con ID: {}", idTema);
            if (!temaInterfazRepositorio.existsById(idTema)) {
                log.warn("Tema de interfaz no encontrado con ID: {}", idTema);
                throw new UsuarioNoEncontradoException("Tema de interfaz no encontrado con ID: " + idTema);
            }
            try {
                temaInterfazRepositorio.deleteById(idTema);
            } catch (Exception e) {
                log.error("Error al eliminar el tema de interfaz: {}", e.getMessage());
                throw new ErrorInternoException("Error al eliminar el tema de interfaz. Contacte al administrador.");
            }
            log.info("Tema de interfaz con ID: {} eliminado correctamente", idTema);
    }
}
