package PMK.free_player.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label; // Ejemplo de un componente FXML
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;

@Component // ¡Importante! Marca esta clase como un bean de Spring
public class IndexController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    // Componentes de la UI definidos en tu FXML

    // Dependencias de servicio, inyectadas por Spring


    // Constructor para inyección de dependencias (preferido en Spring)


    // Este metodo se llama automáticamente después de que el FXML ha sido cargado
    // y todos los elementos @FXML han sido inyectados.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("IndexControlador inicializado.");

    }

}