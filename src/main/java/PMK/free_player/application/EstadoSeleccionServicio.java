package PMK.free_player.application;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Servicio para mantener el estado de la selección del usuario a través de diferentes vistas.
 * Actúa como un “portapapeles” para compartir datos, como el ID de la lista de reproducción
 * seleccionada, entre controladores sin acoplarlos directamente.
 */
@Service
@Getter
@Setter
public class EstadoSeleccionServicio {

    /**
     * Almacena el ID de la lista de reproducción que el usuario ha seleccionado en la vista de listas.
     * El controlador de canciones de la lista leerá este valor para saber qué canciones cargar.
     */
    public Integer idListaSeleccionada;
}