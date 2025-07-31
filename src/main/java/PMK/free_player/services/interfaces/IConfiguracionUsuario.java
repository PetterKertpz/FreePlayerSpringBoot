package PMK.free_player.services.interfaces;

import PMK.free_player.models.ConfiguracionUsuario;

import java.util.List;
import java.util.Optional;

public interface IConfiguracionUsuario{

    public List<ConfiguracionUsuario> listarConfiguracionesUsuario();

    public Optional<ConfiguracionUsuario> findConfiguracionUsuarioById(Integer idConfiguracionUsuario);

    public Optional<ConfiguracionUsuario> findConfiguracionUsuarioByUsuario(Integer idUsuario);

    public ConfiguracionUsuario saveConfiguracionUsuario(ConfiguracionUsuario configuracionUsuario);

    public void eliminarConfiguracionUsuarioById(Integer idConfiguracionUsuario);
}
