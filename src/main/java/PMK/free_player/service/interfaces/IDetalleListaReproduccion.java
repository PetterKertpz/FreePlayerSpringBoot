package PMK.free_player.service.interfaces;

import PMK.free_player.models.DetalleListaReproduccion;

import java.util.List;
import java.util.Optional;

public interface IDetalleListaReproduccion {

    List<DetalleListaReproduccion> findDetalleListaReproduccionByIdLista(Integer idLista);

    void saveCancionALista(DetalleListaReproduccion detalleListaReproduccion);

    void deleteCancionDeLista(Integer idCancion);

}
