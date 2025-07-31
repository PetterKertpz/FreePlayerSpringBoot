package PMK.free_player.services.interfaces;

import PMK.free_player.models.ConfiguracionReproduccion;


import java.util.List;
import java.util.Optional;

public interface IConfiguracionReproduccion {

    List<ConfiguracionReproduccion> listarListasReproduccion();

    Optional<ConfiguracionReproduccion> findConfiguracionReproduccionById(Integer idConfiguracionReproduccion);

    Optional<ConfiguracionReproduccion> findConfiguracionReproduccionByUsuario(Integer idUsuario);

    ConfiguracionReproduccion saveConfiguracionReproduccion(ConfiguracionReproduccion configuracionReproduccion);

    void deleteConfiguracionReproduccionById(Integer idConfiguracionReproduccion);
}
