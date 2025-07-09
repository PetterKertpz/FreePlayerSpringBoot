package PMK.free_player.servicio.interfaces;

import PMK.free_player.modelo.Cancion;

import java.util.List;

public interface ICancion {
    //metodos
    public List<Cancion> listarCanciones();

    public Cancion buscarCancionPorId(Integer id_cancion);

    public void  guardarCancion(Cancion cancion);

    public void eliminarCancion(Integer id_cancion);


}
