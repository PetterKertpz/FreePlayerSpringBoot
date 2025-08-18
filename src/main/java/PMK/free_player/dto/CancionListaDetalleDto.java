// Ubicación: src/main/java/PMK/free_player/dto/CancionListaDetalleDto.java
package PMK.free_player.dto;

import lombok.Data;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO especializado para la vista que muestra las canciones de una lista.
 * Combina información tanto de la Cancion como de la ListaReproduccion
 * para poder poblar todos los componentes de la UI (tabla y panel de detalles).
 */
@Data
public class CancionListaDetalleDto {
    // --- Atributos de la Lista de Reproducción ---
    private String nombreLista;
    private String descripcionLista;
    private Instant ultimaModificacionLista;

    // --- Atributos de la Canción ---
    private String tituloCancion;
    private String nombreArtista;
    private String tituloAlbum;
    private String nombreGenero;
    private String letraCancion; // Para el label "Letra"
}