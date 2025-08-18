package PMK.free_player.controller;

import PMK.free_player.application.EstadoSeleccionServicio;
import PMK.free_player.application.GestorDeEscenas;
import PMK.free_player.dto.ListaReproduccionDto;
import PMK.free_player.service.ListaReproduccionServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class ListasController implements Initializable {
    // --- Dependencias Inyectadas (correctas) ---
    private static final Logger log = LoggerFactory.getLogger(ListasController.class);
    private final ListaReproduccionServicio listaReproduccionServicio;
    private final GestorDeEscenas gestorDeEscenas;
    private final EstadoSeleccionServicio estadoSeleccionServicio;

    // --- Etiquetas para el Panel de Detalles ---
    @FXML private Label TituloLabel;
    @FXML private Label ArtistaLabel; // Reutilizado para "Categoría"
    @FXML private Label AlbumLabel;   // Reutilizado para "Fecha de Creación"
    @FXML private Label GeneroLabel;  // Reutilizado para "Número de Canciones"
    @FXML
    private TableView<ListaReproduccionDto> ListasTabla;
    @FXML
    private TableColumn<ListaReproduccionDto, String> NombreListaColumna;
    @FXML
    private TableColumn<ListaReproduccionDto, String> CategoriaListaColumna;
    @FXML
    private TableColumn<ListaReproduccionDto, String> NumeroCancionesListaColumna;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarTablaListas();
        cargarListasDto();

        // Listener para actualizar el panel de detalles Y para la navegación
        ListasTabla.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, listaSeleccionada) -> {
                    if (listaSeleccionada != null) {
                        // 1. Actualiza el panel de detalles con la info de la lista
                        actualizarPanelDetalles(listaSeleccionada);

                        // 2. Aquí manejamos el doble clic o una acción explícita para navegar.
                        // Por ahora, lo dejamos listo para un futuro botón de "Ver Canciones".
                    }
                }
        );

        // Listener para la navegación al hacer doble clic en una fila
        ListasTabla.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && ListasTabla.getSelectionModel().getSelectedItem() != null) {
                manejarNavegacionACanciones(ListasTabla.getSelectionModel().getSelectedItem());
            }
        });
    }

    /**
     * Configura las columnas de la tabla. Vincula cada columna con un atributo del DTO.
     * Es crucial que el string dentro de PropertyValueFactory sea el nombre exacto del atributo en la clase DTO.
     */
    private void configurarTablaListas() {
        NombreListaColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        CategoriaListaColumna.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        NumeroCancionesListaColumna.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));

        // Suponiendo que has añadido una columna para el número de canciones
        if (NumeroCancionesListaColumna != null) {
            NumeroCancionesListaColumna.setCellValueFactory(new PropertyValueFactory<>("numeroDeCanciones"));
        }
    }

    /**
     * Llama al servicio para obtener los DTOs y los carga en la tabla.
     * Encapsulado en un try-catch para manejar cualquier error durante la carga.
     */
    private void cargarListasDto() {
        try {
            ListasTabla.getItems().setAll(listaReproduccionServicio.obtenerListasComoDto());
            log.info("Tabla de listas de reproducción cargada exitosamente.");
        } catch (Exception e) {
            log.error("Ocurrió un error al cargar las listas de reproducción: {}", e.getMessage(), e);
            // Idealmente, aquí se mostraría una alerta al usuario.
        }
    }

    /**
     * Actualiza las etiquetas del panel izquierdo con la información de la lista seleccionada.
     */
    private void actualizarPanelDetalles(ListaReproduccionDto listaDto) {
        TituloLabel.setText(listaDto.getNombre());
        ArtistaLabel.setText("Categoría: " + (listaDto.getCategoria() != null ? listaDto.getCategoria() : "N/A"));

        // Formateamos la fecha para que sea legible
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd 'de' MMMM, yyyy").withZone(ZoneId.systemDefault());
        AlbumLabel.setText("Creada: " + formato.format(listaDto.getFechaCreacion()));

        GeneroLabel.setText(listaDto.getNumeroCanciones() + " canciones");
    }

    /**
     * Guarda el ID de la lista seleccionada en el servicio de estado y navega a la vista de canciones.
     */
    private void manejarNavegacionACanciones(ListaReproduccionDto listaDto) {
        log.info("Navegando a canciones de la lista '{}' (ID: {})", listaDto.getNombre(), listaDto.getIdLista());

        // 1. Guardamos el ID en nuestro servicio compartido.
        estadoSeleccionServicio.setIdListaSeleccionada(listaDto.getIdLista());

        // 2. Navegamos a la siguiente vista.
        gestorDeEscenas.cambiarEscena("Listas/CancionesLista.fxml", "Canciones de: " + listaDto.getNombre());
    }

    // --- Métodos de Navegación del Menú (ya estaban bien) ---

    @FXML
    public void manejarIrListas(ActionEvent actionEvent) {
        log.info("Navegando a la vista principal");
        gestorDeEscenas.cambiarEscena("Listas/CancionesLista.fxml", "FreePlayer - Listas");
    }

    @FXML
    public void manejarIrBiblioteca(ActionEvent actionEvent) {
        log.info("Navegando a la vista de Biblioteca");
        gestorDeEscenas.cambiarEscena("Main/Main.fxml", "FreePlayer - Biblioteca");
    }

    @FXML
    public void manejarIrAlbumes(ActionEvent actionEvent) {
        log.info("Navegando a la vista de Albumes");
        gestorDeEscenas.cambiarEscena("Albumes/Albumes.fxml", "FreePlayer - Albumes");
    }

    @FXML
    public void manejarIrGeneros(ActionEvent actionEvent) {
        log.info("Navegando a la vista de Generos");
        gestorDeEscenas.cambiarEscena("Generos/Generos.fxml", "FreePlayer - Generos");
    }

    @FXML
    public void manejarIrFavoritos(ActionEvent actionEvent) {
        log.info("Navegando a la vista de Favoritos");
        gestorDeEscenas.cambiarEscena("Favoritos/Favoritos.fxml", "FreePlayer - Favoritos");
    }

    @FXML
    public void manejarIrArtista(ActionEvent actionEvent) {
        log.info("Navegando a la vista de Artista");
        gestorDeEscenas.cambiarEscena("Artistas/Artistas.fxml", "FreePlayer - Artista");
    }
}