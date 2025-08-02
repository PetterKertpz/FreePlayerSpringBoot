package PMK.free_player.service.interfaces;
import PMK.free_player.models.ListaReproduccion;
import java.util.List;
import java.util.Optional;

public interface IListaReproduccion {

    List<ListaReproduccion> ListarListasReproduccion();

    Optional<ListaReproduccion> FindListaReproduccionById(Integer idLista);

    Optional<ListaReproduccion> FindListaReproduccionByUsuario(Integer idUsuario);

    ListaReproduccion SaveListaReproduccion(ListaReproduccion listaReproduccion);

    void DeleteListaReproduccionById(Integer idLista);

}
