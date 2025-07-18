package PMK.free_player.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "detalle_lista_reproduccion", schema = "free_player_mejorado", indexes = {
        @Index(name = "id_cancion", columnList = "id_cancion")
})
public class DetalleListaReproduccion {
    @EmbeddedId
    private DetalleListaReproduccionId id;

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

    @Column(name = "orden")
    private Integer orden;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_agregado")
    private Instant fechaAgregado;

    @Lob
    @Column(name = "nota_usuario")
    private String notaUsuario;

}