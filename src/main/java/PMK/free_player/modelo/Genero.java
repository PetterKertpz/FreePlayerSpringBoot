package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "genero", schema = "free_player_mejorado", uniqueConstraints = {
        @UniqueConstraint(name = "nombre", columnNames = {"nombre"})
})
public class Genero {
    @Id
    @Column(name = "id_genero", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

}