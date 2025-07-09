package PMK.free_player.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cancion;
    private String  nombre_cancion;
    private Time    duracion;
    private Integer id_autor;
    private Integer id_genero;
    private String  url;
    private String  ruta_portada;
    private Date    fecha_lanzamiento;
    private Integer lista_reproduccion;

}
