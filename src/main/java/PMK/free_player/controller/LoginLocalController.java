package PMK.free_player.controller;

import PMK.free_player.application.GestorDeEscenas;
import PMK.free_player.service.UsuarioServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class LoginLocalController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(LoginLocalController.class);

    // --- Dependencias Inyectadas ---
    private final GestorDeEscenas gestorDeEscenas;
    private final UsuarioServicio usuarioServicio;

    // --- Componentes FXML ---

    @FXML private TextField campoEmail;
    @FXML private PasswordField campoContrasena;

    /**
     * Maneja el evento de clic del botón "Iniciar Sesión".
     * Recoge las credenciales y las valida usando el UsuarioServicio.
     */
    @FXML
    void manejarInicioSesionLocal(ActionEvent event) {
        String email = campoEmail.getText();
        String contrasena = campoContrasena.getText();

        if (email.isBlank() || contrasena.isBlank()) {
            mostrarAlerta("Error de Validación", "El correo y la contraseña no pueden estar vacíos.", Alert.AlertType.WARNING);
            return;
        }

        log.info("Intentando iniciar sesión local con email: {}", email);

        // --- LÓGICA DE AUTENTICACIÓN ---
        // Delegamos la validación al servicio de usuario.
        boolean autenticado = usuarioServicio.autenticarUsuario(email, contrasena);

        if (autenticado) {
            log.info("Inicio de sesión local exitoso para {}. Navegando al menú principal.", email);
            // Si la autenticación es correcta, navegamos a la escena principal.
            gestorDeEscenas.cambiarEscena("Main/Main.fxml", "FreePlayer - Biblioteca");
        } else {
            log.warn("Fallo de inicio de sesión para el email: {}. Credenciales incorrectas.", email);
            mostrarAlerta("Error de Inicio de Sesión", "El correo electrónico o la contraseña son incorrectos.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Maneja el evento de clic del botón "Volver".
     * Navega de regreso a la pantalla de login principal.
     */
    @FXML
    void manejarVolver(ActionEvent event) {
        log.info("Volviendo a la pantalla principal de login.");
        gestorDeEscenas.cambiarEscena("Login/Login.fxml", "FreePlayer - Inicio de Sesión");
    }

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}