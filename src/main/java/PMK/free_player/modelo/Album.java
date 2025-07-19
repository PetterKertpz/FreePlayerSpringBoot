package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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

    @Lob
    @Column(name = "portada_url")
    private String portadaUrl;

}