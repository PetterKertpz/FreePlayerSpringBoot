package PMK.free_player.controller;

import PMK.free_player.dto.CancionDto;
import PMK.free_player.service.CancionServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Component
public class ListasCancionesController implements Initializable {
    // Logger para registrar eventos
    private static final Logger log = LoggerFactory.getLogger(ListasCancionesController.class);
    // Inyecta el servicio de canciones
    private final CancionServicio cancionServicio;
    // Inyecta el ApplicationContext para acceder a los beans de Spring
    private final ApplicationContext applicationContext;
    // Lista observable para almacenar las canciones
    private final ObservableList<CancionDto> Canciones = FXCollections.observableArrayList();
    //Lista observable para almacenar las canciones filtradas
    private FilteredList<CancionDto> cancionesFiltradas;


    //________________________Componentes de la UI definidos en tu FXML________________________________
    @FXML private TableView<CancionDto> ReproduccionTabla;

    @FXML private TableColumn<CancionDto, String>TituloColumna;

    @FXML private TableColumn<CancionDto, String>ArtistaColumna;

    @FXML private TableColumn<CancionDto, String>AlbumColumna;

    @FXML private TableColumn<CancionDto, String>GeneroColumna;

    @FXML private Label TituloLabel;

    @FXML private Label ArtistaLabel;

    @FXML private Label AlbumLabel;

    @FXML private Label GeneroLabel;

    @FXML private TextField CampoBusqueda;


    //_____________Metodos de inicializacion______________________

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.info("Controlador inicializado.");

        // Configurar la tabla de reproducción
        ReproduccionTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Configurar las columnas de la tabla
        configurarColumnas();

        // Cargar las canciones desde el servicio
        cargarCanciones();

        // Configurar filtro de búsqueda
        cancionesFiltradas = new FilteredList<>(Canciones, p -> true);
        ReproduccionTabla.setItems(cancionesFiltradas);
        CampoBusqueda.textProperty().addListener((
                observable, oldValue, newValue) -> {
                cancionesFiltradas.setPredicate(filtroBusqueda(newValue));
        });

        //________Configurar el listener para actualizar las etiquetas cuando se selecciona una canción_________
        ReproduccionTabla.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
            if (newValue != null) {
                TituloLabel.setText(newValue.getTitulo());
                ArtistaLabel.setText(newValue.getNombreArtista());
                AlbumLabel.setText(newValue.getTituloAlbum());
                GeneroLabel.setText(newValue.getNombreGenero());
            } else {
                TituloLabel.setText("");
                ArtistaLabel.setText("");
                AlbumLabel.setText("");
                GeneroLabel.setText("");
            }
        });

    }

    //_____________Metodos de evento______________________

    @FXML private void llamarBusqueda(ActionEvent event) {
        String busqueda = CampoBusqueda.getText();
        cancionesFiltradas.setPredicate(filtroBusqueda(busqueda));
    }


    //_____________Metodos privados para configurar la tabla y cargar datos______________________

    private void configurarColumnas() {
        TituloColumna.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        ArtistaColumna.setCellValueFactory(new PropertyValueFactory<>("nombreArtista"));
        AlbumColumna.setCellValueFactory(new PropertyValueFactory<>("tituloAlbum"));
        GeneroColumna.setCellValueFactory(new PropertyValueFactory<>("nombreGenero"));
    }

    private void cargarCanciones() {
        Canciones.clear();
        Canciones.addAll(cancionServicio.listarCancionesDto());
        log.info("Canciones cargadas: {}", Canciones.size());

    }

    private Predicate<CancionDto> filtroBusqueda(String busqueda) {
        if (busqueda == null || busqueda.isEmpty()) {
            return p -> true; // No filtrar si el campo de búsqueda está vacío
        }
        String busquedaLower = busqueda.toLowerCase();
        return cancion -> {

            String titulo = cancion.getTitulo();
            String artista = cancion.getNombreArtista();
            String album = cancion.getTituloAlbum();
            String genero = cancion.getNombreGenero();

            // Verifica cada campo. IMPORTANTE: el null check (value != null) va primero
            if (titulo != null && titulo.toLowerCase().contains(busquedaLower)) {
                return true;
            }
            else if (artista != null && artista.toLowerCase().contains(busquedaLower)) {
                return true;
            }
            else if (album != null && album.toLowerCase().contains(busquedaLower)) {
                return true;
            }
            else if (genero != null && genero.toLowerCase().contains(busquedaLower)) {
                return true;
            }
            return false; // Si no hay coincidencia en ningún campo
        };
    }
}
