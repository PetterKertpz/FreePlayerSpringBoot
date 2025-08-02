package PMK.free_player.repository;

import PMK.free_player.models.ListaReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListaReproduccionRepositorio extends JpaRepository<ListaReproduccion, Integer> {
    Optional<ListaReproduccion> findByidUsuario_Id(Integer idUsuario);
}
