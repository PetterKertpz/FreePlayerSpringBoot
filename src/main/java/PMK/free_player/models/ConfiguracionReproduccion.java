package PMK.free_player.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault; // Keep this import if you have other fields with default
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.util.Map;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "configuracion_reproduccion", schema = "free_player_mejorado", uniqueConstraints = {
        @UniqueConstraint(name = "id_configuracion_reproduccion", columnNames = {"id_configuracion_reproduccion"}),
        @UniqueConstraint(name = "id_usuario", columnNames = {"id_usuario"})
})
public class ConfiguracionReproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_reproduccion", nullable = false)
    private Integer id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    // REMOVE @ColumnDefault here
    @ColumnDefault("estándar")
    @Column(name = "calidad_audio")
    private String calidadAudio = "estándar"; // You can set a default in the constructor or here.

    @Column(name = "ecualizador")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> ecualizador;

    @ColumnDefault("1")
    @Column(name = "normalizacion_volumen")
    private Boolean normalizacionVolumen;

    @ColumnDefault("0")
    @Column(name = "efecto_espacial")
    private Boolean efectoEspacial;

    @ColumnDefault("0")
    @Column(name = "realce_graves")
    private Boolean realceGraves;

}