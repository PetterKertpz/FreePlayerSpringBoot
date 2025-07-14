package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tbl_generos", uniqueConstraints = {
        @UniqueConstraint(name = "nombre_genero", columnNames = {"nombre_genero"})
})
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero", nullable = false)
    private Integer id;

    @Size(max = 50, message = "El nombre del género no puede exceder los 50 caracteres")
    @NotNull(message = "El nombre del género no puede ser nulo")
    @Column(name = "nombre_genero", nullable = false, length = 50)
    private String nombreGenero;

}