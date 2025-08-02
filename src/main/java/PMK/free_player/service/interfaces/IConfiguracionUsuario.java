package PMK.free_player.service.interfaces;

import PMK.free_player.models.ConfiguracionUsuario;

import java.util.List;
import java.util.Optional;

public interface IConfiguracionUsuario{

    List<ConfiguracionUsuario> listarConfiguracionesUsuario();

    Optional<ConfiguracionUsuario> findConfiguracionUsuarioById(Integer idConfiguracionUsuario);

    Optional<ConfiguracionUsuario> findConfiguracionUsuarioByUsuario(Integer idUsuario);

    ConfiguracionUsuario saveConfiguracionUsuario(ConfiguracionUsuario configuracionUsuario);

    void eliminarConfiguracionUsuarioById(Integer idConfiguracionUsuario);
}
