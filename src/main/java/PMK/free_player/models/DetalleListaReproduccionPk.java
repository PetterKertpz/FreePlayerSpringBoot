package PMK.free_player.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Embeddable
public class DetalleListaReproduccionPk implements Serializable {
    @Serial
    private static final long serialVersionUID = -8648288561081378065L;
    @NotNull
    @Column(name = "id_lista", nullable = false)
    private Integer idLista;

    @NotNull
    @Column(name = "id_cancion", nullable = false)
    private Integer idCancion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DetalleListaReproduccionPk entity = (DetalleListaReproduccionPk) o;
        return Objects.equals(this.idCancion, entity.idCancion) &&
                Objects.equals(this.idLista, entity.idLista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCancion, idLista);
    }

}