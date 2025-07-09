package PMK.free_player.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TemaUi {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_tema;
    private String nombre_tema;
    private String configuracion_json;
    private Integer id_propietario;

}
