package PMK.free_player.servicio.interfaces;

import PMK.free_player.modelo.Usuario;

import java.util.List;

public interface IUsuarioServicio {

    //Metodos para gestionar usuarios   
    public List<Usuario> listarUsuarios();

    public Usuario buscarUsuarioPorId(Integer id_usuario);

    public void guardarUsuario(Usuario usuario);

    public void eliminarUsuario(Usuario usuario);
}
