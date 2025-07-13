package PMK.free_player.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`tbl_intermediaria_temas-configuraciones`")
public class IntermediariaTemaConfiguracion {
    @EmbeddedId
    private IntermediariaTemaConfiguracionId id;

    @MapsId("idConfiguracion")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_configuracion", nullable = false)
    private Configuracion idConfiguracion;

    @MapsId("idTema")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tema", nullable = false)
    private TemaUi idTema;

}