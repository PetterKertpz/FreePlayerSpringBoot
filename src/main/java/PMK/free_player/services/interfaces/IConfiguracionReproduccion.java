package PMK.free_player.services.interfaces;

import PMK.free_player.models.ConfiguracionReproduccion;

import java.util.Optional;

public interface IConfiguracionReproduccion {

    public Optional<ConfiguracionReproduccion> findConfiguracionReproduccionById(Integer idConfiguracionReproduccion);

    public Optional<ConfiguracionReproduccion> findConfiguracionReproduccionByUsuario(Integer idUsuario);

    public ConfiguracionReproduccion saveConfiguracionReproduccion(ConfiguracionReproduccion configuracionReproduccion);

    public void deleteConfiguracionReproduccionById(Integer idConfiguracionReproduccion);
}
