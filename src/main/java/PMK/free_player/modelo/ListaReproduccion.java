package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Table(name = "tbl_listas_reproduccion")

public class ListaReproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lista", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artista")
    private Artista artista;

    @NotNull(message = "El tipo de lista no puede ser nulo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo", nullable = false)
    private TipoLista tipo;

    @Size(max = 200)
    @NotNull(message = "El nombre de la lista no puede ser nulo")
    @Column(name = "nombre_lista", nullable = false, length = 200)
    private String nombreLista;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull(message = "El ID del usuario no puede ser nulo")
    @Timestamp
    @Column(name = "fecha_creacion", nullable = false , updatable = false)
    private Instant fechaCreacion;

    @NotNull(message = "La fecha de lanzamiento no puede ser nula")
    @UpdateTimestamp
    @Column(name = "fecha_lanzamiento", nullable = false)
    private Instant fechaLanzamiento;

}