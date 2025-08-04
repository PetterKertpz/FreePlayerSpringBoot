package PMK.free_player.controller;
import PMK.free_player.models.Cancion;
import PMK.free_player.service.CancionServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class MainController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    // Dependencias de servicio, inyectadas por Spring
    private final CancionServicio cancionServicio;

    // Componentes de la UI definidos en tu FXML
    @FXML
    private TableView<Cancion>ReproduccionTabla;

    @FXML
    private TableColumn<Cancion, String>TituloColumna;

    @FXML
    private TableColumn<Cancion, String>ArtistaColumna;

    @FXML
    private TableColumn<Cancion, String>AlbumColumna;

    @FXML
    private TableColumn<Cancion, String>GeneroColumna;

    // Lista observable para almacenar las canciones
    private final ObservableList<Cancion>Canciones = FXCollections.observableArrayList();


    // Este metodo se llama automáticamente después de que el FXML ha sido cargado
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.info("IndexControlador inicializado.");

        ReproduccionTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnas();
        cargarCanciones();


    }

    private void configurarColumnas() {
        TituloColumna.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        ArtistaColumna.setCellValueFactory(new PropertyValueFactory<>("artista.nombre"));
        AlbumColumna.setCellValueFactory(new PropertyValueFactory<>("album.titulo"));
        GeneroColumna.setCellValueFactory(new PropertyValueFactory<>("genero.nombre"));
    }

    private void cargarCanciones() {
        Canciones.clear();
        Canciones.addAll(cancionServicio.listarCanciones());
        log.info("Canciones cargadas: {}", Canciones.size());
        ReproduccionTabla.setItems(Canciones);
    }

}