package PMK.free_player.services.interfaces;
import PMK.free_player.models.Usuario;
import java.util.List;
import java.util.Optional;


public interface IUsuario{

    public List<Usuario> ListarUsuarios();

    public Optional <Usuario> findUsuarioById(Integer idUsuario);

    public Usuario saveUsuario(Usuario usuario);

    public void deleteUsuarioById(Integer idUsuario);

}
