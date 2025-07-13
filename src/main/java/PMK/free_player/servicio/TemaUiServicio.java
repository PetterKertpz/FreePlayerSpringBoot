package PMK.free_player.servicio;


import PMK.free_player.repositorio.TemasUiRepositorio;
import PMK.free_player.servicio.interfaces.ITemaUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemaUiServicio implements ITemaUi {

    @Autowired
    private TemasUiRepositorio TemasUiRepositorio;
    // Implementación de los métodos de la interfaz ITemaUi

    @Override
    public List<TemaUi> obtenerTemas() {
        return TemasUiRepositorio.findAll();
    }

    @Override
    public TemaUi obtenerTemaPorId(Integer id_tema) {
        return TemasUiRepositorio.findById(id_tema).orElse(null);
    }

    @Override
    public void guardarTema(TemaUi temaui) {
        TemasUiRepositorio.save(temaui);
    }

    @Override
    public void EliminarTema(TemaUi temaui) {
        TemasUiRepositorio.delete(temaui);

    }

}
