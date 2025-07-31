package PMK.free_player.repositorys;
import PMK.free_player.models.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritoRepositorio extends JpaRepository<Favorito, Integer> {

}
