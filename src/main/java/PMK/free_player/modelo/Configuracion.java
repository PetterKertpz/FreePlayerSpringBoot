package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tbl_configuraciones")
public class Configuracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion", nullable = false)
    private Integer id;

    @NotNull(message = "El ID del usuario no puede ser nulo")
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @Timestamp
    private Instant fechaCreacion;

    @NotNull
    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false)
    private Instant fechaActualizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario")
    private Usuario usuario;

}