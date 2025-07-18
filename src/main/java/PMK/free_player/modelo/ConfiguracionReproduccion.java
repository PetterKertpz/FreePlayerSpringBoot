package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "configuracion_reproduccion", schema = "free_player_mejorado", uniqueConstraints = {
        @UniqueConstraint(name = "id_usuario", columnNames = {"id_usuario"})
})
public class ConfiguracionReproduccion {
    @Id
    @Column(name = "id_configuracion_audio", nullable = false)
    private Integer id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @ColumnDefault("'estándar'")
    @Lob
    @Column(name = "calidad_audio")
    private String calidadAudio;

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