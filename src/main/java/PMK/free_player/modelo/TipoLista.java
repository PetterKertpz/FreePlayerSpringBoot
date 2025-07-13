package PMK.free_player.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_tipos_lista")
public class TipoLista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo", nullable = false)
    private Integer id;

    @Column(name = "tipo")
    private Integer tipo;

}