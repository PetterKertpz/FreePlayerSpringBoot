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
public class IntermediariaTemaConfiguracionId implements Serializable {
    private static final long    serialVersionUID = -1745242251054736320L;
    @NotNull
    @Column(name = "id_configuracion", nullable = false)
    private              Integer idConfiguracion;

    @NotNull
    @Column(name = "id_tema", nullable = false)
    private Integer idTema;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IntermediariaTemaConfiguracionId entity = (IntermediariaTemaConfiguracionId) o;
        return Objects.equals(this.idConfiguracion, entity.idConfiguracion) &&
                Objects.equals(this.idTema, entity.idTema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConfiguracion, idTema);
    }

}