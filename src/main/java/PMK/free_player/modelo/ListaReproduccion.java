package PMK.free_player.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class ListaReproduccion {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_lista;
    private String nombre_lista;
    private String descripcion;
    private Integer id_usuario;
    private Integer id_autor;
    private Integer id_album;
    private Date    fecha_creacion;
    private Integer tipo;

}
