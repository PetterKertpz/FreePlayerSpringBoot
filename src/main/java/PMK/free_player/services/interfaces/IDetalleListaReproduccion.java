package PMK.free_player.services.interfaces;

import PMK.free_player.models.DetalleListaReproduccion;

import java.util.Optional;

public interface IDetalleListaReproduccion {

    Optional<DetalleListaReproduccion> findDetalleListaReproduccionByIdLista(Integer idLista);

    void saveCancionALista(DetalleListaReproduccion detalleListaReproduccion);

    void deleteCancionDeLista(Integer idCancion);

}
