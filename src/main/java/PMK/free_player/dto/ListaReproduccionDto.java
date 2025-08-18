package PMK.free_player.dto;

import lombok.Data;


import java.time.Instant;

@Data
public class ListaReproduccionDto {
    private Integer idLista;
    private String  nombre;
    private String  descripcion;
    private String  categoria;
    private String  imagen;
    private Instant fechaCreacion;
    private int numeroCanciones;
}
