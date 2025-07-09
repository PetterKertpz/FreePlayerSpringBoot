package PMK.free_player.servicio.interfaces;

import PMK.free_player.modelo.ListaReproduccion;

import java.util.List;

public interface IListaReproduccion {
    //metodos

    public List<ListaReproduccion> obtenerListasReproduccion();

    public ListaReproduccion buscarListaReproduccionPorId(Integer id_lista);

    public void guardarListaReproduccion(ListaReproduccion listaReproduccion);

    public void eliminarListaReproduccion(Integer id_lista);

}
