package PMK.free_player.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "artista", schema = "free_player_mejorado")
public class Artista {
    @Id
    @Column(name = "id_artista", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 50)
    @Column(name = "pais_origen", length = 50)
    private String paisOrigen;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

}