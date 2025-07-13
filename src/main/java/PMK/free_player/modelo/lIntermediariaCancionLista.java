package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "`tbl_intermediaria_canciones-listas`")
public class lIntermediariaCancionLista {
    @EmbeddedId
    private lIntermediariaCancionListaId id;

    @MapsId("idLista")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_lista", nullable = false)
    private ListaReproduccion idLista;

    @MapsId("idCancion")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_cancion", nullable = false)
    private Cancion idCancion;

    @NotNull
    @Column(name = "orden", nullable = false)
    private Integer orden;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_agregado", nullable = false)
    private Instant fechaAgregado;

}