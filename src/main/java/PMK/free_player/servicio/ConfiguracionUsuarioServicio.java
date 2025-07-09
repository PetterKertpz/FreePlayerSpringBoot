package PMK.free_player.servicio;

import PMK.free_player.modelo.ConfiguracionUsuario;
import PMK.free_player.repositorio.ConfiguracionUsuarioRepositorio;
import PMK.free_player.servicio.interfaces.IConfiguracionUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfiguracionUsuarioServicio implements IConfiguracionUsuario {

    @Autowired
    private ConfiguracionUsuarioRepositorio ConfiguracionUsuarioRepositorio;

    @Override
    public List<ConfiguracionUsuario> listarConfiguraciones() {
        return ConfiguracionUsuarioRepositorio.findAll();
    }

    @Override
    public ConfiguracionUsuario buscarConfiguracionPorId(Integer id_configuracion) {
        return ConfiguracionUsuarioRepositorio.findById(id_configuracion).orElse(null);
    }

    @Override
    public void guardarConfiguracion(ConfiguracionUsuario configuracionUsuario) {
        ConfiguracionUsuarioRepositorio.save(configuracionUsuario);
    }

    @Override
    public void eliminarConfiguracion(ConfiguracionUsuario configuracionUsuario) {
        ConfiguracionUsuarioRepositorio.delete(configuracionUsuario);

    }


}
