package PMK.free_player.services.interfaces;

import PMK.free_player.models.Genero;

import java.util.List;
import java.util.Optional;

public interface IGenero {

    List<Genero> listarGeneros();

    Optional<Genero> findGeneroById(Integer idGenero);

    Genero saveGenero(Genero genero);

    void deleteGeneroById(Integer idGenero);

}
