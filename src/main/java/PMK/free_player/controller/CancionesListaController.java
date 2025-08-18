package PMK.free_player.controller;

import PMK.free_player.application.GestorDeEscenas;
import PMK.free_player.dto.CancionListaDetalleDto; // 1. CORRECCIÓN: Usamos el DTO correcto y específico.
import PMK.free_player.service.DetalleListaReproduccionServicio;
import PMK.free_player.application.EstadoSeleccionServicio;
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
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Component
public class CancionesListaController implements Initializable {
    // --- Dependencias Correctas ---
    private static final Logger log = LoggerFactory.getLogger(CancionesListaController.class);
    // 2. CORRECCIÓN: Inyectamos solo los servicios que realmente necesitamos para esta vista.
    private final DetalleListaReproduccionServicio detalleServicio;
    private final EstadoSeleccionServicio estadoSeleccion;
    private final GestorDeEscenas gestorDeEscenas;

    // --- Listas Observables para la UI (usando el DTO correcto) ---
    private final ObservableList<CancionListaDetalleDto> cancionesDeLaLista = FXCollections.observableArrayList();
    private FilteredList<CancionListaDetalleDto> cancionesFiltradas;

    // --- Componentes FXML (Nombres de fx:id deben coincidir con tu archivo FXML) ---
    @FXML private TableView<CancionListaDetalleDto> ReproduccionTabla;
    @FXML private TableColumn<CancionListaDetalleDto, String> TituloColumna;
    @FXML private TableColumn<CancionListaDetalleDto, String> ArtistaColumna;
    @FXML private TableColumn<CancionListaDetalleDto, String> AlbumColumna;
    @FXML private TableColumn<CancionListaDetalleDto, String> GeneroColumna;
    @FXML private TextField CampoBusqueda;

    // --- Etiquetas del Panel de Detalles ---
    @FXML private Label NombreLista;
    @FXML private Label NombreCancion;
    @FXML private Label NombreArtista;
    @FXML private Label NombreAlbum;
    @FXML private Label Genero;
    @FXML private Label Descripcion;
    @FXML private Label UltimaModificacion;
    @FXML private Label Letra;

    /**
     * El método initialize se ejecuta una vez que la vista FXML ha sido cargada.
     * Es el punto de partida para configurar y poblar la interfaz.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.info("Controlador de Canciones de Lista inicializado.");

        // 3. IMPLEMENTACIÓN: Flujo lógico y limpio.
        // Primero preparamos la tabla, luego obtenemos los datos necesarios,
        // cargamos los datos y finalmente configuramos las interacciones del usuario.
        configurarColumnasDeTabla();

        Integer idListaSeleccionada = estadoSeleccion.getIdListaSeleccionada();

        // Manejo robusto de errores: ¿Qué pasa si el usuario llega aquí sin seleccionar una lista?
        if (idListaSeleccionada == null) {
            log.error("No se pudo cargar la vista de canciones. No se encontró un ID de lista en el estado.");
            NombreLista.setText("Error: Debes seleccionar una lista primero.");
            return; // Detenemos la ejecución para evitar más errores.
        }

        cargarCancionesFiltradas(idListaSeleccionada);
        configurarFiltroDeBusqueda();
        configurarListenerDeSeleccion();
    }

    /**
     * Vincula cada columna de la TableView a un atributo específico de la clase CancionListaDetalleDto.
     * El texto dentro de PropertyValueFactory DEBE coincidir exactamente con el nombre de la variable en el DTO.
     */
    private void configurarColumnasDeTabla() {
        TituloColumna.setCellValueFactory(new PropertyValueFactory<>("tituloCancion"));
        ArtistaColumna.setCellValueFactory(new PropertyValueFactory<>("nombreArtista"));
        AlbumColumna.setCellValueFactory(new PropertyValueFactory<>("tituloAlbum"));
        GeneroColumna.setCellValueFactory(new PropertyValueFactory<>("nombreGenero"));
    }

    /**
     * Llama al servicio para obtener la lista de DTOs ya filtrada por el ID de la lista
     * y la carga en la lista observable que alimenta la tabla.
     */
    private void cargarCancionesFiltradas(Integer idLista) {
        try {
            cancionesDeLaLista.setAll(detalleServicio.obtenerDetallesCancionesDeLista(idLista));
            log.info("Se cargaron {} canciones para la lista con ID: {}", cancionesDeLaLista.size(), idLista);

            // Una vez cargadas las canciones, si la lista no está vacía,
            // usamos el primer elemento para poblar los detalles que son comunes a toda la lista.
            if (!cancionesDeLaLista.isEmpty()) {
                actualizarDetallesDeLista(cancionesDeLaLista.get(0));
            } else {
                // Si la lista está vacía, aún podríamos querer mostrar el nombre de la lista.
                // (Esto requeriría un método adicional en el servicio para obtener solo los detalles de la lista).
                log.warn("La lista con ID {} no contiene canciones.", idLista);
            }

        } catch (Exception e) {
            log.error("Ocurrió un error al cargar las canciones de la lista: {}", e.getMessage(), e);
        }
    }

    /**
     * Configura el listener que actualiza el panel de detalles CADA VEZ que el usuario
     * selecciona una canción diferente en la tabla.
     */
    private void configurarListenerDeSeleccion() {
        ReproduccionTabla.getSelectionModel().selectedItemProperty().addListener(
            (obs, seleccionPrevia, cancionSeleccionada) -> {
                if (cancionSeleccionada != null) {
                    actualizarDetallesDeCancion(cancionSeleccionada);
                }
            });
    }

    /**
     * Configura el listener del campo de búsqueda. La tabla se filtrará automáticamente
     * a medida que el usuario escribe.
     */
    private void configurarFiltroDeBusqueda() {
        cancionesFiltradas = new FilteredList<>(cancionesDeLaLista, p -> true);
        ReproduccionTabla.setItems(cancionesFiltradas);
        CampoBusqueda.textProperty().addListener((obs, valorAntiguo, valorNuevo) ->
            cancionesFiltradas.setPredicate(crearPredicadoDeFiltro(valorNuevo))
        );
    }

    // --- Métodos de Actualización de la Interfaz Gráfica ---

    /**
     * Actualiza las etiquetas que muestran información de la LISTA DE REPRODUCCIÓN.
     * Esta información es la misma para todas las canciones en la vista.
     */
    private void actualizarDetallesDeLista(CancionListaDetalleDto dto) {
        NombreLista.setText(dto.getNombreLista());
        Descripcion.setText(dto.getDescripcionLista() != null ? dto.getDescripcionLista() : "Sin descripción.");

        if (dto.getUltimaModificacionLista() != null) {
            // Formateamos la fecha a un formato más legible para el usuario.
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());
            UltimaModificacion.setText("Última modificación: " + formato.format(dto.getUltimaModificacionLista()));
        } else {
            UltimaModificacion.setText("");
        }
    }

    /**
     * Actualiza las etiquetas que muestran información de la CANCIÓN seleccionada.
     */
    private void actualizarDetallesDeCancion(CancionListaDetalleDto dto) {
        NombreCancion.setText(dto.getTituloCancion());
        NombreArtista.setText(dto.getNombreArtista());
        NombreAlbum.setText(dto.getTituloAlbum());
        Genero.setText(dto.getNombreGenero());
        Letra.setText(dto.getLetraCancion() != null && !dto.getLetraCancion().isBlank() ? "Letra disponible" : "Letra no disponible");
    }

    // --- Lógica de Búsqueda (adaptada al nuevo DTO) ---

    private Predicate<CancionListaDetalleDto> crearPredicadoDeFiltro(String textoBusqueda) {
        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            return p -> true; // Muestra todos los elementos si no hay texto de búsqueda.
        }
        String busquedaEnMinusculas = textoBusqueda.toLowerCase();

        return dto ->
            (dto.getTituloCancion() != null && dto.getTituloCancion().toLowerCase().contains(busquedaEnMinusculas)) ||
            (dto.getNombreArtista() != null && dto.getNombreArtista().toLowerCase().contains(busquedaEnMinusculas)) ||
            (dto.getTituloAlbum() != null && dto.getTituloAlbum().toLowerCase().contains(busquedaEnMinusculas)) ||
            (dto.getNombreGenero() != null && dto.getNombreGenero().toLowerCase().contains(busquedaEnMinusculas));
    }

    @FXML private void llamarBusqueda(ActionEvent event) {
        cancionesFiltradas.setPredicate(crearPredicadoDeFiltro(CampoBusqueda.getText()));
    }

    // --- Métodos de Navegación del Menú (ya estaban correctos) ---

    @FXML public void manejarIrListas(ActionEvent actionEvent) {
        gestorDeEscenas.cambiarEscena("Listas/Listas.fxml", "FreePlayer - Listas");
    }

    @FXML public void manejarIrBiblioteca(ActionEvent actionEvent) {
        gestorDeEscenas.cambiarEscena("Main/Main.fxml", "FreePlayer - Biblioteca");
    }

    @FXML public void manejarIrAlbumes(ActionEvent actionEvent) {
        gestorDeEscenas.cambiarEscena("Albumes/Albumes.fxml", "FreePlayer-Albumes");
    }

    @FXML public void manejarIrGeneros(ActionEvent actionEvent) {
        gestorDeEscenas.cambiarEscena("Generos/Generos.fxml", "FreePlayer-Generos");
    }

    @FXML public void manejarIrArtista(ActionEvent actionEvent) {
        gestorDeEscenas.cambiarEscena("Artistas/Artistas.fxml", "FreePlayer-Artistas");
    }

    @FXML public void manejarIrFavoritos(ActionEvent actionEvent) {
        gestorDeEscenas.cambiarEscena("Favoritos/Favoritos.fxml", "FreePlayer - Favoritos");
    }
}