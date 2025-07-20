package PMK.free_player.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "album", schema = "free_player_mejorado", indexes = {
        @Index(name = "id_artista", columnList = "id_artista")
})
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_album", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_artista")
    private Artista idArtista;

    @Size(max = 100)
    @NotNull
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "anio")
    private Integer anio;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "duracion_total_segundos")
    private Integer duracionTotalSegundos;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Lob
    @Column(name = "portada_url")
    private String portadaUrl;

}