package PMK.free_player.servicio.interfaces;

import PMK.free_player.modelo.ConfiguracionUsuario;

import java.util.List;

public interface IConfiguracionUsuario {
    //Metodos

    public List<ConfiguracionUsuario> listarConfiguraciones();

    public ConfiguracionUsuario buscarConfiguracionPorId(Integer id_configuracion);

    public void guardarConfiguracion(ConfiguracionUsuario configuracionUsuario);

    public void eliminarConfiguracion(ConfiguracionUsuario configuracionUsuario);
}
