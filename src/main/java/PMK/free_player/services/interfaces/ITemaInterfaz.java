package PMK.free_player.services.interfaces;

import PMK.free_player.models.TemaInterfaz;
import java.util.List;
import java.util.Optional;

public interface ITemaInterfaz {

    List<TemaInterfaz> ListarTemasInterfaz();

    Optional<TemaInterfaz> FindTemaInterfazById(Integer idTema);

    TemaInterfaz SaveTemaInterfaz(TemaInterfaz temaInterfaz);

    void DeleteTemaInterfazById(Integer idTema);

}
