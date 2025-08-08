// Este es un objeto plano, sin anotaciones de JPA
package PMK.free_player.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CancionDto{
    private Integer id;
    private String titulo;
    private String nombreArtista;
    private String tituloAlbum;
    private String nombreGenero;

}