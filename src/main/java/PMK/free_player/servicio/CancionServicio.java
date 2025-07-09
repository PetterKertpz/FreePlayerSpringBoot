package PMK.free_player.servicio;

import PMK.free_player.modelo.Cancion;
import PMK.free_player.repositorio.CancionRepositorio;
import PMK.free_player.servicio.interfaces.ICancion;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CancionServicio implements ICancion {

    @Autowired
    private CancionRepositorio CancionRepositorio;

    @Override
    public List<Cancion> listarCanciones() {
        return CancionRepositorio.findAll();
    }

    @Override
    public Cancion buscarCancionPorId(Integer id_cancion) {
        return CancionRepositorio.findById(id_cancion).orElse(null);
    }

    @Override
    public void guardarCancion(Cancion cancion) {
        CancionRepositorio.save(cancion);
    }

    @Override
    public void eliminarCancion(Integer id_cancion) {
        CancionRepositorio.deleteById(id_cancion);
    }
}
