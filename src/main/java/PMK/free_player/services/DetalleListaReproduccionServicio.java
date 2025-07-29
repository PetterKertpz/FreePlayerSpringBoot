package PMK.free_player.services;

import PMK.free_player.models.DetalleListaReproduccion;
import PMK.free_player.repositorys.DetalleListaReproduccionRepositorio;
import PMK.free_player.services.interfaces.IDetalleListaReproduccion;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DetalleListaReproduccionServicio implements IDetalleListaReproduccion {

    private static final Logger log = LoggerFactory.getLogger(DetalleListaReproduccionServicio.class);
    private final DetalleListaReproduccionRepositorio detalleListaReproduccionRepositorio;

    @Override
    public Optional<DetalleListaReproduccion> findDetalleListaReproduccionByIdLista(Integer idLista) {
        return detalleListaReproduccionRepositorio.findById(idLista);
    }

    @Override
    public void saveCancionALista(DetalleListaReproduccion detalleListaReproduccion) {
        detalleListaReproduccionRepositorio.save(detalleListaReproduccion);
    }

    @Override
    public void deleteCancionDeLista(Integer idCancion) {
        detalleListaReproduccionRepositorio.deleteById(idCancion);
    }
}
