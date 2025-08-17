package PMK.free_player.controller;

import PMK.free_player.application.GestorDeEscenas;
import PMK.free_player.models.ListaReproduccion;
import PMK.free_player.service.ListaReproduccionServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class ListasController implements Initializable {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ListasController.class);

    private ListaReproduccionServicio listaReproduccionServicio;
    private ApplicationContext applicationContext;
    private GestorDeEscenas gestorDeEscenas;





    @FXML
    private Label GeneroLabel;
    @FXML
    private Label ArtistaLabel;
    @FXML
    private Label TituloLabel;
    @FXML
    private Label       AlbumLabel;
    @FXML
    private TableColumn<> TituloListaColumna;
    @FXML
    private TableView<>   ReproduccionTabla;
    @FXML
    private TableColumn FechaListaColumna;
    @FXML
    private TableColumn CategoriaListaColumna;
    @FXML
    private TableColumn ArtistaListaColumna;

    @FXML void IrACanciones() {
        log.info("Navegando a Canciones");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void manejarIrMain(ActionEvent actionEvent) {
        gestorDeEscenas.cambiarEscena("Main/Main.fxml", "FreePlayer - Biblioteca");
    }

    @FXML
    public void manejarIrListas(ActionEvent actionEvent) {
        log.info("Navegando a la vista principal");
        gestorDeEscenas.cambiarEscena("Listas/Listas.fxml", "FreePlayer - Listas");
    }
}
