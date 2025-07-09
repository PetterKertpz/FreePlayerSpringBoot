package PMK.free_player.servicio;


import PMK.free_player.modelo.Usuario;
import PMK.free_player.repositorio.UsuarioRepositorio;
import PMK.free_player.servicio.interfaces.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio UsuarioRepositorio;

    @Override
    public List<Usuario> listarUsuarios() {
        return UsuarioRepositorio.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer id_usuario) {
        return UsuarioRepositorio.findById(id_usuario).orElse(null);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        UsuarioRepositorio.save(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        UsuarioRepositorio.delete(usuario);
    }
}
