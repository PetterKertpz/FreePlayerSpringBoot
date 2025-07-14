package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = "contrasena")
@EqualsAndHashCode(of = "id")
@Table(name = "tbl_usuarios", uniqueConstraints = {
        @UniqueConstraint(name = "uk_nombre_usuario", columnNames = {"nombre_usuario"}),
        @UniqueConstraint(name = "uk_email", columnNames = {"email"})
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false, unique = true)
    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Integer id;


    @Size(max = 150, message = "El nombre de usuario no puede exceder los 150 caracteres")
    @NotBlank(message = "El nombre de usuario no puede estar en blanco")
    @Column(name = "nombre_usuario", nullable = false, length = 150)
    private String nombreUsuario;


    @Size(max = 150 , message = "El email no puede exceder los 150 caracteres")
    @Email
    @NotBlank(message = "El email no puede estar en blanco")
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Size(min = 8,max = 255, message = "La contraseña debe tener entre 8 y 255 caracteres")
    @NotBlank(message = "La contraseña no puede estar en blanco")
    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Size(max = 2)
    @NotBlank(message = "El código de país no puede estar en blanco")
    @Column(name = "pais_iso", length = 2)
    private String paisIso;

    @NotNull
    @Timestamp
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private Instant fechaRegistro;

    @Column(name = "fecha_actualizacion")
    @UpdateTimestamp
    private Instant fechaActualizacion;

}