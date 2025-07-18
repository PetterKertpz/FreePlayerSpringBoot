package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "tema_interfaz", schema = "free_player_mejorado", uniqueConstraints = {
        @UniqueConstraint(name = "nombre_tema", columnNames = {"nombre_tema"})
})
public class TemaInterfaz {
    @Id
    @Column(name = "id_tema", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre_tema", nullable = false, length = 50)
    private String nombreTema;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @Column(name = "esquema", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> esquema;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_actualizacion")
    private Instant fechaActualizacion;

}