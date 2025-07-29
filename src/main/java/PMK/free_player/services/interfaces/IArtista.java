package PMK.free_player.services.interfaces;

import PMK.free_player.models.Artista;

import java.util.List;
import java.util.Optional;

public interface IArtista {

    List<Artista> listarArtistas();

    Optional<Artista> findArtistaById(Integer idArtista);

    Artista saveArtista(Artista artista);

    void deleteArtistaById(Integer idArtista);

}
