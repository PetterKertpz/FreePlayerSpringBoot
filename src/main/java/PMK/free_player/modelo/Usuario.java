package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "usuario", schema = "free_player_mejorado", uniqueConstraints = {
        @UniqueConstraint(name = "nombre_usuario", columnNames = {"nombre_usuario"}),
        @UniqueConstraint(name = "correo", columnNames = {"correo"})
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombreUsuario;

    @Size(max = 255)
    @NotNull
    @Column(name = "correo", nullable = false)
    private String correo;

    @Size(max = 255)
    @NotNull
    @Column(name = "contrasena_hash", nullable = false)
    private String contrasenaHash;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_registro")
    private Instant fechaRegistro;

    @Lob
    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @ColumnDefault("0")
    @Column(name = "verificado")
    private Boolean verificado;

}