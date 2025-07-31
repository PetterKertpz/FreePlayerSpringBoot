package PMK.free_player.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault; // Mantén la importación si otras columnas aún usan @ColumnDefault
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "configuracion_usuario", schema = "free_player_mejorado", indexes = {
        @Index(name = "id_tema", columnList = "id_tema"),
        @Index(name = "id_cancion_ultima", columnList = "id_cancion_ultima"),
        @Index(name = "id_cancion_favorita", columnList = "id_cancion_favorita")
}, uniqueConstraints = {
        @UniqueConstraint(name = "id_configuracion", columnNames = {"id_configuracion"}),
        @UniqueConstraint(name = "id_tema", columnNames = {"id_tema"}),
        @UniqueConstraint(name = "id_usuario", columnNames = {"id_usuario"})
})
public class ConfiguracionUsuario {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion", nullable = false)
    private Integer id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tema")
    private TemaInterfaz idTema;

    @Size(max = 10)
    @ColumnDefault("'es'")
    @Column(name = "idioma", length = 10)
    private String idioma;

    // ELIMINADO: @ColumnDefault("'auto'")
    @Lob // Se mantiene si realmente necesita ser un TEXT/LONGTEXT
    @Pattern(regexp = "^(auto|claro|oscuro)$", message = "Modo de interfaz no válido")
    @Column(name = "modo_interfaz")
    private String modoInterfaz = "auto"; // Se asigna el valor predeterminado en Java

    // ELIMINADO: @ColumnDefault("'normal'")
    @Lob // Se mantiene si realmente necesita ser un TEXT/LONGTEXT
    @Column(name = "orden_reproduccion")
    private String ordenReproduccion = "normal"; // Se asigna el valor predeterminado en Java

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cancion_ultima")
    private Cancion idCancionUltima;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cancion_favorita")
    private Cancion idCancionFavorita;

}