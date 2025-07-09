package PMK.free_player.servicio.interfaces;

import PMK.free_player.modelo.TemaUi;

import java.util.List;

public interface ITemaUi {
    //metodos
    public List<TemaUi> obtenerTemas();

    public TemaUi obtenerTemaPorId(Integer id_tema);

    public void guardarTema(TemaUi temaui);

    public void EliminarTema(TemaUi temaui);


}
