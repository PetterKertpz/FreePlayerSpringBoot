package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "contrasena")
@EqualsAndHashCode(id)
@Builder
@Getter
@Setter
@Entity
@Table(name = "tbl_usuarios", uniqueConstraints = {
        @UniqueConstraint(name = "uk_nombre_usuario", columnNames = {"nombre_usuario"}),
        @UniqueConstraint(name = "uk_email", columnNames = {"email"})
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Size(max = 150)
    @NotNull
    @Column(name = "nombre_usuario", nullable = false, length = 150)
    private String nombreUsuario;

    @Size(max = 150)
    @NotNull
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Size(max = 2)
    @Column(name = "pais_iso", length = 2)
    private String paisIso;

    @NotNull
    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private Instant fechaActualizacion;

}