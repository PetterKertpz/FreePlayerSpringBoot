package PMK.free_player.services;


import PMK.free_player.exceptions.*;
import PMK.free_player.models.Usuario;
import PMK.free_player.repositorys.UsuarioRepositorio;
import PMK.free_player.services.interfaces.IUsuario;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServicio implements IUsuario {

    private static final Logger log = LoggerFactory.getLogger(UsuarioServicio.class);
    private final UsuarioRepositorio usuarioRepositorio;

    // Listar todos los usuarios
    @Override
    public List<Usuario> ListarUsuarios() {
        log.info("Iniciando Listar Usuarios");
        List<Usuario> listaUsuarios = usuarioRepositorio.findAll();
        if (listaUsuarios.isEmpty()) {
            log.warn("No se encontraron usuarios registrados en la base de datos");
            throw new NoDataFoundException("No existen usuarios registrados actualmente.");
        }else {
            log.info("Usuarios encontrados: {}", listaUsuarios.size());
        }
        log.debug("Usuarios encontrados: {}", listaUsuarios);
        return listaUsuarios;
    }

    // Buscar un usuario por su ID
    @Override
    public Optional<Usuario> findUsuarioById(Integer idUsuario) {
        log.info("Buscando Usuario por ID: {}", idUsuario);
        Optional<Usuario> usuario = usuarioRepositorio.findById(idUsuario);
        if (usuario.isEmpty()) {
            log.warn("Usuario no encontrado con ID: {}", idUsuario);
            throw new UsuarioNoEncontradoException("Usuario no encontrado con ID: " + idUsuario);
        }
        log.info("Usuario encontrado: {}", usuario.get());
        return usuario;

    }

    // Guardar o actualizar un usuario
    @Override
    public Usuario saveUsuario(Usuario usuario) {
        log.info("Guardando usuario: {}", usuario);
        try {
            return  usuarioRepositorio.save(usuario);
        } catch (Exception e) {
            log.error("Error al guardar el usuario: {}", e.getMessage());
            throw new ErrorInternoException("Error al guardar el usuario. Contacte al administrador.");
        }
    }

    // Eliminar un usuario por su ID
    @Override
    public void deleteUsuarioById(Integer idUsuario) {
        log.info("Eliminando Usuario por ID: {}", idUsuario);
        if (!usuarioRepositorio.existsById(idUsuario)) {
            log.warn("Usuario no encontrado con ID: {}", idUsuario);
            throw new RecursoNoEncontradoException("Usuario no encontrado con ID: " + idUsuario);
        }
        try {
            usuarioRepositorio.deleteById(idUsuario);
        } catch (Exception e) {
            log.error("Error al eliminar el usuario con ID {}: {}", idUsuario, e.getMessage());
            throw new ErrorInternoException("Error al eliminar el usuario. Contacte al administrador.");
        }
        log.info("Usuario con ID {} eliminado correctamente", idUsuario);
    }
}

