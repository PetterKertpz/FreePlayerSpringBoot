package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tbl_listas_reproduccion")
public class ListaReproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lista", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artista")
    private Artista artista;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo", nullable = false)
    private TipoLista tipo;

    @Size(max = 200)
    @NotNull
    @Column(name = "nombre_lista", nullable = false, length = 200)
    private String nombreLista;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @NotNull
    @Column(name = "fecha_lanzamiento", nullable = false)
    private Instant fechaLanzamiento;

}