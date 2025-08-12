package PMK.free_player.repository;

import PMK.free_player.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByCorreo(String correo);
}
