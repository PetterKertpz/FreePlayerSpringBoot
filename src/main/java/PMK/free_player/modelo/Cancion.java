package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tbl_canciones", indexes = {
        @Index(name = "idx_fk_autor_cancion", columnList = "artista"),
        @Index(name = "idx_fk_genero_cancion", columnList = "genero")
})
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cancion", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull(message = "El nombre de la canción no puede ser nulo")
    @Column(name = "nombre_cancion", nullable = false)
    private String nombreCancion;

    @NotNull(message = "La duración de la canción no puede ser nula")
    @Column(name = "duracion", nullable = false)
    private LocalTime duracion;

    @NotNull(message = "El ID del artista no puede ser nulo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artista", nullable = false)
    private Artista artista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genero")
    private Genero genero;

    @Size(max = 512)
    @Column(name = "letra", length = 512)
    private String letra;

    @Size(max = 512)
    @Column(name = "ruta_portada", length = 512)
    private String rutaPortada;

    @Column(name = "fecha_lanzamiento")
    private LocalDate fechaLanzamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lista_reproduccion")
    private ListaReproduccion listaReproduccion;

}