package PMK.free_player.controller;

import PMK.free_player.application.GestorDeEscenas; // Cambiado a 'service' por convención
import PMK.free_player.application.ServicioAutenticacionGoogle; // Cambiado a 'service' por convención
import PMK.free_player.service.UsuarioServicio;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponseException; // Importante añadir esta
import com.google.api.services.people.v1.model.Person;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File; // Importante añadir esta
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Objects;
import java.util.ResourceBundle;

@RequiredArgsConstructor
@Component
public class LoginController implements Initializable {

    // --- Dependencias Inyectadas (sin cambios) ---
    private final UsuarioServicio usuarioServicio;
    private final ServicioAutenticacionGoogle servicioAutenticacionGoogle;
    private final GestorDeEscenas gestorDeEscenas;

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    // --- Componentes FXML (sin cambios) ---
    @FXML private AnchorPane IdLoginScene;
    @FXML private Button IdBotonLoginGoogle;
    @FXML private Button IdBotonLoginLocal;
    @FXML private Button IdBotonRegisterLocal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.info("Vista de Login inicializada.");
    }

    @FXML
    public void manejarInicioSesionGoogle(ActionEvent actionEvent) {
        log.info("El usuario ha hecho clic en 'Iniciar Sesión con Google'.");
        // **MEJORA 1**: Bloqueamos todos los botones de navegación.
        setNavegacionBloqueada(true);

        new Thread(() -> {
            try {
                // ... (Lógica de autorización y obtención de perfil sin cambios) ...
                Credential credencial = servicioAutenticacionGoogle.autorizar();
                Person perfilUsuario = servicioAutenticacionGoogle.obtenerInformacionUsuario(credencial);

                String nombre;
                if (perfilUsuario.getNames() != null && !perfilUsuario.getNames().isEmpty()) {
                    nombre = perfilUsuario.getNames().getFirst().getDisplayName();
                } else {
                    nombre = "Usuario";
                }
                String email = null;
                if (perfilUsuario.getEmailAddresses() != null && !perfilUsuario.getEmailAddresses().isEmpty()) {
                    email = perfilUsuario.getEmailAddresses().getFirst().getValue();
                }

                if (email == null) {
                    log.error("No se pudo obtener el email del perfil de Google.");
                    Platform.runLater(() -> mostrarAlerta("Error de Perfil", "No se pudo obtener un email de la cuenta de Google.", Alert.AlertType.ERROR));
                    return;
                }

                // --- Lógica de navegación final (sin cambios) ---
                Platform.runLater(() -> {
                    mostrarAlerta("Éxito", "Inicio de sesión exitoso. ¡Bienvenido, " + nombre + "!", Alert.AlertType.INFORMATION);
                    gestorDeEscenas.cambiarEscena("Main/Main.fxml", "FreePlayer - Biblioteca");
                });

            } catch (TokenResponseException e) {
                // **MEJORA 2**: Captura específica para errores de token de Google.
                log.error("Error de token de Google. Probablemente el usuario no está en la lista de prueba.", e);
                Platform.runLater(() -> {
                    mostrarAlerta("Acceso Denegado",
                            "La cuenta de Google seleccionada no tiene permiso para usar esta aplicación en modo de prueba. " +
                            "Por favor, contacta al administrador o intenta con otra cuenta.", Alert.AlertType.ERROR);
                });

                // **MEJORA 3**: Borramos las credenciales malas automáticamente.
                borrarCredencialesGoogle();

            } catch (IOException | GeneralSecurityException e) {
                // Captura de error genérica (sin cambios)
                log.error("Ha ocurrido un error durante el proceso de autenticación de Google.", e);
                Platform.runLater(() -> mostrarAlerta("Error de Autenticación", "No se pudo completar el inicio de sesión. Revisa la consola.", Alert.AlertType.ERROR));
            } finally {
                // **MEJORA 4**: Nos aseguramos de que TODOS los botones se reactiven.
                Platform.runLater(() -> setNavegacionBloqueada(false));
            }
        }).start();
    }

    @FXML
    void manejarInicioSesionLocal(ActionEvent event) {
        log.info("Navegando a la vista de Login Local.");
        gestorDeEscenas.cambiarEscena("Login/LoginLocal.fxml", "FreePlayer - Iniciar Sesión Local");
    }

    @FXML
    public void registrarLocalmente(ActionEvent actionEvent) {
        log.info("Navegando a la vista de Registro Local.");
        gestorDeEscenas.cambiarEscena("Login/RegistroLocal.fxml", "FreePlayer - Crear Cuenta");
    }

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

    // --- MÉTODOS DE AYUDA AÑADIDOS ---

    /**
     * Habilita o deshabilita los botones de navegación principales.
     * @param bloqueado true para deshabilitar, false para habilitar.
     */
    private void setNavegacionBloqueada(boolean bloqueado) {
        if (IdBotonLoginGoogle != null) IdBotonLoginGoogle.setDisable(bloqueado);
        if (IdBotonLoginLocal != null) IdBotonLoginLocal.setDisable(bloqueado);
        if (IdBotonRegisterLocal != null) IdBotonRegisterLocal.setDisable(bloqueado);
        log.info("Navegación de login " + (bloqueado ? "bloqueada." : "desbloqueada."));
    }

    /**
     * Borra el directorio de credenciales guardadas para forzar una nueva autenticación.
     * Esto evita que la aplicación se quede "atascada" con un token inválido.
     */
    private void borrarCredencialesGoogle() {
        try {
            // La ruta debe coincidir con la definida en ServicioAutenticacionGoogle
            File directorioCredenciales = new File(System.getProperty("user.home"), ".credentials/freeplayer-google-auth");
            if (directorioCredenciales.exists()) {
                for (File archivo : Objects.requireNonNull(directorioCredenciales.listFiles())) {
                    if (!archivo.isDirectory()) {
                        archivo.delete();
                    }
                }
                log.info("Credenciales de Google locales borradas con éxito.");
            }
        } catch (Exception e) {
            log.error("No se pudieron borrar las credenciales locales de Google.", e);
        }
    }
}