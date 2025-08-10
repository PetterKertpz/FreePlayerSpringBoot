package PMK.free_player.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class ListasController implements Initializable {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ListasController.class);

    @FXML void IrACanciones() {
        log.info("Navegando a Canciones");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
