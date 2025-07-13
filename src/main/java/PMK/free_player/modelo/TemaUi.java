package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Setter
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
    @NotNull
    @Column(name = "nombre_tema", nullable = false, length = 150)
    private String nombreTema;

    @Column(name = "configuracion_json")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> configuracionJson;

}