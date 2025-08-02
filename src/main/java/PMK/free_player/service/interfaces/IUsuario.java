package PMK.free_player.service.interfaces;
import PMK.free_player.models.Usuario;
import java.util.List;
import java.util.Optional;


public interface IUsuario{

    List<Usuario> ListarUsuarios();

    Optional <Usuario> findUsuarioById(Integer idUsuario);

    Usuario saveUsuario(Usuario usuario);

    void deleteUsuarioById(Integer idUsuario);

}
