package PMK.free_player.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class lIntermediariaCancionListaId implements Serializable {
    private static final long    serialVersionUID = -655642304438451073L;
    @NotNull
    @Column(name = "id_lista", nullable = false)
    private              Integer idLista;

    @NotNull
    @Column(name = "id_cancion", nullable = false)
    private Integer idCancion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        lIntermediariaCancionListaId entity = (lIntermediariaCancionListaId) o;
        return Objects.equals(this.idCancion, entity.idCancion) &&
                Objects.equals(this.idLista, entity.idLista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCancion, idLista);
    }

}