package PMK.free_player.repositorio;

import PMK.free_player.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {

}
