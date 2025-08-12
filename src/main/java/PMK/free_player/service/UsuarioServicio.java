package PMK.free_player.service;


import PMK.free_player.exception.*;
import PMK.free_player.models.Usuario;
import PMK.free_player.repository.UsuarioRepositorio;
import PMK.free_player.service.interfaces.IUsuario;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServicio implements IUsuario {

    private static final Logger log = LoggerFactory.getLogger(UsuarioServicio.class);
    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder codificadorContrasena;

    public Usuario findUsuarioPorCorreo(String correo) {
        log.info("Buscando usuario por correo: {}", correo);
        return usuarioRepositorio.findByCorreo(correo).orElse(null);
    }


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

    public void crearUsuarioLocal(String nombre, String email, String contrasenaPlana) {
    // 1. Verificamos si el email ya existe para evitar duplicados.
    if (usuarioRepositorio.findByCorreo(email).isPresent()) {
        throw new IllegalStateException("El correo electrónico '" + email + "' ya está registrado.");
    }

    // 2. Creamos una nueva instancia de nuestra entidad Usuario.
    Usuario nuevoUsuario = new Usuario();
    nuevoUsuario.setNombreUsuario(nombre);
    nuevoUsuario.setCorreo(email);

    // 3. Encriptamos la contraseña. Este es el paso de seguridad más importante.
    nuevoUsuario.setContrasenaHash(codificadorContrasena.encode(contrasenaPlana));

    // 4. Asignamos valores por defecto.
    nuevoUsuario.setFechaRegistro(Instant.now());
    nuevoUsuario.setVerificado(false);

    // 5. **LLAMAMOS A TU MÉTODO EXISTENTE** para guardar el objeto Usuario.
    // En lugar de llamar a 'usuarioRepositorio.save()', reutilizamos tu lógica.
    this.saveUsuario(nuevoUsuario);

    log.info("Nuevo usuario creado y guardado a través de saveUsuario: {}", email);
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

    /**
 * Autentica a un usuario local.
 *
 * @param email El email que el usuario introduce en el formulario.
 * @param contrasenaPlana La contraseña en texto plano que el usuario introduce.
 * @return `true` si el email existe y la contraseña coincide, `false` en caso contrario.
 */
public boolean autenticarUsuario(String email, String contrasenaPlana) {
    // 1. Busca al usuario por su email en la base de datos.
    // Usar Optional es una buena práctica para evitar errores de NullPointerException.
    Optional<Usuario> usuarioOptional = usuarioRepositorio.findByCorreo(email);

    // 2. Si el Optional está vacío, significa que no se encontró ningún usuario con ese email.
    if (usuarioOptional.isEmpty()) {
        log.warn("Intento de login para un email no registrado: {}", email);
        return false; // Falla la autenticación.
    }

    // 3. Si se encontró, obtenemos el objeto Usuario.
    Usuario usuarioEncontrado = usuarioOptional.get();

    // 4. Comparamos la contraseña del formulario con la guardada en la base de datos.
    // NUNCA compares contraseñas en texto plano. El método `matches()` se encarga de
    // comparar de forma segura la contraseña introducida con la versión encriptada (hash)
    // que tienes en la base de datos.
    return codificadorContrasena.matches(contrasenaPlana, usuarioEncontrado.getContrasenaHash());
}

}

