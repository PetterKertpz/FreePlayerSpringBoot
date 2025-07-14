package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tbl_artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "El ID del artista no puede ser nulo")
    @Column(name = "id_artista", nullable = false)
    private Integer id;

    @NotNull
    @Size(max = 255, message = "El nombre del artista no puede exceder los 100 caracteres")
    @Column(name = "nombre_artista", nullable = false, length = 100)
    private String nombreArtista;

    @NotNull
    @Column(name = "numero_canciones", nullable = false)
    private Integer numeroCanciones;

    @Size(max = 255 , message = "La portada del artista no puede exceder los 255 caracteres")
    @Column(name = "portada", length = 255)
    private String portada;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

}