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
public class FavoritoPk implements Serializable {
    @Serial
    private static final long    serialVersionUID = 1990374939711076435L;
    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @NotNull
    @Column(name = "id_cancion", nullable = false)
    private Integer idCancion;

    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    FavoritoPk entity = (FavoritoPk) o;
    return Objects.equals(this.idCancion, entity.idCancion) &&
           Objects.equals(this.idUsuario, entity.idUsuario);
}


    @Override
    public int hashCode() {
        return Objects.hash(idCancion, idUsuario);
    }

}