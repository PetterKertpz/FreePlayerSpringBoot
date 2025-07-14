package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tbl_temas_ui", uniqueConstraints = {
        @UniqueConstraint(name = "uk_temas_ui", columnNames = {"nombre_tema"})
})
public class TemaUi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tema", nullable = false)
    private Integer id;

    @Size(max = 150)
    @NotNull(message = "El ID del tema no puede ser nulo")
    @NotBlank(message = "El nombre del tema no puede estar en blanco")
    @Column(name = "nombre_tema", nullable = false, length = 150)
    private String nombreTema;

    @Column(name = "configuracion_json")
    @NotBlank(message = "La configuracion no puede estar en blanco")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> configuracionJson;

}