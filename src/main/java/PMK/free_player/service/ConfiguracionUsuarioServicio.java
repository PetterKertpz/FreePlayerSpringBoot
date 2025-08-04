package PMK.free_player.service;

import PMK.free_player.exception.ErrorInternoException;
import PMK.free_player.exception.NoDataFoundException;
import PMK.free_player.exception.RecursoNoEncontradoException;
import PMK.free_player.models.ConfiguracionUsuario;
import PMK.free_player.repository.ConfiguracionUsuarioRepositorio;
import PMK.free_player.service.interfaces.IConfiguracionUsuario;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConfiguracionUsuarioServicio implements IConfiguracionUsuario {

    private static final Logger log = LoggerFactory.getLogger(ConfiguracionUsuarioServicio.class);
    private final ConfiguracionUsuarioRepositorio configuracionUsuarioRepositorio;

    // Listar todas las configuraciones de usuario
    @Override
    public List<ConfiguracionUsuario> listarConfiguracionesUsuario() {
        log.info("Iniciando listar configuraciones de usuario");
        List<ConfiguracionUsuario> configuraciones = configuracionUsuarioRepositorio.findAll();
        if (configuraciones.isEmpty()) {
            log.warn("No se encontraron configuraciones de usuario registradas.");
            throw new NoDataFoundException("No existen configuraciones de usuario registradas actualmente.");
        }
        return configuraciones;
    }

    // Buscar una configuración de usuario por su ID
    @Override
    public Optional<ConfiguracionUsuario> findConfiguracionUsuarioById(Integer idConfiguracionUsuario) {
        log.info("Buscando configuración de usuario por ID: {}", idConfiguracionUsuario);
        Optional<ConfiguracionUsuario> configuracion = configuracionUsuarioRepositorio.findById(idConfiguracionUsuario);
        if (configuracion.isEmpty()) {
            log.warn("Configuración de usuario no encontrada con ID: {}", idConfiguracionUsuario);
            throw new RecursoNoEncontradoException("Configuración de usuario no encontrada con ID: " + idConfiguracionUsuario);
        }
        return configuracion;
    }

    @Override
    public Optional<ConfiguracionUsuario> findConfiguracionUsuarioByUsuario(Integer idUsuario) {
        log.info("Buscando configuración de usuario por ID de usuario: {}", idUsuario);
        Optional<ConfiguracionUsuario> configuracion = configuracionUsuarioRepositorio.findById(idUsuario);
        if (configuracion.isEmpty()) {
            log.warn("Configuración de usuario no encontrada para el usuario con ID: {}", idUsuario);
            throw new RecursoNoEncontradoException("Configuración de usuario no encontrada para el usuario con ID: " + idUsuario);
        }
        return configuracion;
    }

    // Guardar o actualizar una configuración de usuario
    @Override
    public ConfiguracionUsuario saveConfiguracionUsuario(ConfiguracionUsuario configuracionUsuario) {
        log.info("Guardando configuración de usuario: {}", configuracionUsuario);
        try {
            return configuracionUsuarioRepositorio.save(configuracionUsuario);
        } catch (Exception e) {
            log.error("Error al guardar la configuración de usuario: {}", e.getMessage());
            throw new ErrorInternoException("No se pudo guardar la configuración del usuario.");
        }
    }

    // Eliminar una configuración de usuario por su ID
    @Override
    public void eliminarConfiguracionUsuarioById(Integer idConfiguracionUsuario) {
        log.info("Eliminando configuración de usuario con ID: {}", idConfiguracionUsuario);
        if (!configuracionUsuarioRepositorio.existsById(idConfiguracionUsuario)) {
            log.warn("No se puede eliminar. Configuración no encontrada con ID: {}", idConfiguracionUsuario);
            throw new RecursoNoEncontradoException("Configuración de usuario no encontrada con ID: " + idConfiguracionUsuario);
        }
        try {
            configuracionUsuarioRepositorio.deleteById(idConfiguracionUsuario);
        } catch (Exception e) {
            log.error("Error al eliminar la configuración de usuario con ID {}: {}", idConfiguracionUsuario, e.getMessage());
            throw new ErrorInternoException("No se pudo eliminar la configuración del usuario. Contacte al administrador.");
        }
        log.info("Usuario con ID {} eliminado correctamente", idConfiguracionUsuario);
    }
}

