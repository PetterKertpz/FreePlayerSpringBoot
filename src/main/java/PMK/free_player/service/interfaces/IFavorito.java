package PMK.free_player.service.interfaces;

import PMK.free_player.models.Favorito;

import java.util.List;
import java.util.Optional;

public interface IFavorito {

    List<Favorito> ListarFavoritos();

    Optional <Favorito> FindFavoritoByIdCancion(Integer idCancion);

    Favorito saveFavorito(Favorito favorito);

    void deleteFavorito(Integer idCancion);
}
