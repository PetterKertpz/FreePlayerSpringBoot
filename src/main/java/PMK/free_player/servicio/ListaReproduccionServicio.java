package PMK.free_player.servicio;

import PMK.free_player.modelo.ListaReproduccion;
import PMK.free_player.repositorio.ListaReproduccionRepositorio;
import PMK.free_player.servicio.interfaces.IListaReproduccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaReproduccionServicio implements IListaReproduccion {

    @Autowired
    private ListaReproduccionRepositorio ListaReproduccionRepositorio;

    @Override
    public List<ListaReproduccion> obtenerListasReproduccion() {
        return ListaReproduccionRepositorio.findAll();
    }

    @Override
    public ListaReproduccion buscarListaReproduccionPorId(Integer id_lista) {
        return ListaReproduccionRepositorio.findById(id_lista).orElse(null);
    }

    @Override
    public void guardarListaReproduccion(ListaReproduccion listaReproduccion) {
        ListaReproduccionRepositorio.save(listaReproduccion);

    }

    @Override
    public void eliminarListaReproduccion(Integer id_lista) {
        ListaReproduccionRepositorio.deleteById(id_lista);
    }

}
