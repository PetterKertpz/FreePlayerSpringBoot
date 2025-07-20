package PMK.free_player.services;

import PMK.free_player.exceptions.NoDataFoundException;
import PMK.free_player.models.ConfiguracionReproduccion;
import PMK.free_player.repositorys.ConfiguracionReproduccionRepositorio;
import PMK.free_player.services.interfaces.IConfiguracionReproduccion;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConfiguracionReproduccionServicio implements IConfiguracionReproduccion {

    private static final Logger log = LoggerFactory.getLogger(ConfiguracionReproduccionServicio.class);
    private final ConfiguracionReproduccionRepositorio configuracionReproduccionRepositorio;

    @Override
    public Optional<ConfiguracionReproduccion> findConfiguracionReproduccionById(Integer idConfiguracionReproduccion) {
        log.info("Buscando configuración de reproducción por ID: {}", idConfiguracionReproduccion);
        Optional<ConfiguracionReproduccion> configuracionReproduccion = configuracionReproduccionRepositorio.findById(idConfiguracionReproduccion);
        if (configuracionReproduccion.isEmpty()) {
            log.warn("Configuración de reproducción no encontrada con ID: {}", idConfiguracionReproduccion);
            throw new NoDataFoundException("No se encontró la configuración de reproducción con ID: " + idConfiguracionReproduccion);
        }
        return configuracionReproduccion;
    }

    @Override
    public Optional<ConfiguracionReproduccion> findConfiguracionReproduccionByUsuario(Integer idUsuario) {
        log.info("Buscando configuración de reproducción para el usuario con ID: {}", idUsuario);
        Optional<ConfiguracionReproduccion> configuracionReproduccion = configuracionReproduccionRepositorio.findById(idUsuario);
        if (configuracionReproduccion.isEmpty()) {
            log.warn("Configuración de reproducción no encontrada para el usuario con ID: {}", idUsuario);
            throw new NoDataFoundException("No se encontró la configuración de reproducción para el usuario con ID: " + idUsuario);
        }
        return configuracionReproduccion;
    }

    @Override
    public ConfiguracionReproduccion saveConfiguracionReproduccion(ConfiguracionReproduccion configuracionReproduccion) {
        log.info("Guardando configuración de reproducción: {}", configuracionReproduccion);
        try {
            return configuracionReproduccionRepositorio.save(configuracionReproduccion);
        } catch (Exception e) {
            log.error("Error al guardar la configuración de reproducción: {}", e.getMessage());
            throw new RuntimeException("Error al guardar la configuración de reproducción. Contacte al administrador.");
        }
    }

    @Override
    public void deleteConfiguracionReproduccionById(Integer idConfiguracionReproduccion) {
        log.info("Eliminando configuración de reproducción con ID: {}", idConfiguracionReproduccion);
        if (!configuracionReproduccionRepositorio.existsById(idConfiguracionReproduccion)) {
            log.warn("No se encontró la configuración de reproducción con ID: {}", idConfiguracionReproduccion);
            throw new NoDataFoundException("No se encontró la configuración de reproducción con ID: " + idConfiguracionReproduccion);
        }
        try {
            configuracionReproduccionRepositorio.deleteById(idConfiguracionReproduccion);
        } catch (Exception e) {
            log.info("Configuración de reproducción con ID: {} eliminada correctamente", idConfiguracionReproduccion);
            throw new RuntimeException("Error al eliminar la configuración de reproducción. Contacte al administrador.");
        }
        log.info("Configuración de reproducción con ID: {} eliminada correctamente", idConfiguracionReproduccion);
    }
}
