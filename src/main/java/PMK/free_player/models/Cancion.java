package PMK.free_player.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "cancion", schema = "free_player_mejorado", indexes = {
        @Index(name = "id_album", columnList = "id_album"),
        @Index(name = "id_genero", columnList = "id_genero")
})
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cancion", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_artista")
    private Artista artista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_album")
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero")
    private Genero genero;

    @Size(max = 100)
    @NotNull
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @NotNull
    @Min(0)
    @Column(name = "duracion_segundos", nullable = false)
    private Integer duracionSegundos;

    @NotNull
    @Lob
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Lob
    @Column(name = "portada")
    private String portada;

    @ColumnDefault("0")
    @Column(name = "reproducciones")
    private Integer reproducciones;

    @Column(name = "fecha_lanzamiento")
    private LocalDate fechaLanzamiento;

    @Lob
    @Column(name = "letra")
    private String letra;

    @Column(name = "bitrate_kbps")
    private Integer bitrateKbps;

    @Size(max = 10)
    @Column(name = "formato_audio", length = 10)
    private String formatoAudio;

    @Column(name = "`tamanio_mb`", precision = 6, scale = 2)
    private BigDecimal tamanioMb;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "ultima_reproduccion")
    private Instant ultimaReproduccion;

}