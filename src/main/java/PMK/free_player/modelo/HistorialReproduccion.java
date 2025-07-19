package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "historial_reproduccion", schema = "free_player_mejorado", indexes = {
        @Index(name = "id_usuario", columnList = "id_usuario"),
        @Index(name = "id_cancion", columnList = "id_cancion")
})
public class HistorialReproduccion {
    @Id
    @Column(name = "id_historial", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cancion", nullable = false)
    private Cancion idCancion;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_reproduccion")
    private Instant fechaReproduccion;

    @Column(name = "duracion_reproduccion_segundos")
    private Integer duracionReproduccionSegundos;

}