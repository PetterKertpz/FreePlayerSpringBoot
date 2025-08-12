package PMK.free_player.controller;

import PMK.free_player.application.GestorDeEscenas;
import PMK.free_player.service.UsuarioServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistroController {

    private static final Logger log = LoggerFactory.getLogger(RegistroController.class);

    private final GestorDeEscenas gestorDeEscenas;
    private final UsuarioServicio usuarioServicio;

    // --- Componentes FXML ---
    @FXML private TextField campoNombreUsuario;
    @FXML private TextField campoEmail;
    @FXML private PasswordField campoContrasena;
    @FXML private PasswordField campoConfirmarContrasena;

    /**
     * Maneja el evento de clic del botón "Registrarme".
     * Valida los datos y llama al servicio para crear el nuevo usuario.
     */
    @FXML
    void manejarRegistro(ActionEvent event) {
        String nombre = campoNombreUsuario.getText();
        String email = campoEmail.getText();
        String contrasena = campoContrasena.getText();
        String confirmacion = campoConfirmarContrasena.getText();

        // --- VALIDACIONES ---
        if (nombre.isBlank() || email.isBlank() || contrasena.isBlank()) {
            mostrarAlerta("Campos Vacíos", "Todos los campos son obligatorios.", Alert.AlertType.WARNING);
            return;
        }
        if (!contrasena.equals(confirmacion)) {
            mostrarAlerta("Error de Contraseña", "Las contraseñas no coinciden.", Alert.AlertType.ERROR);
            return;
        }
        // Aquí podrías añadir más validaciones (ej: contraseña con 8 caracteres, email con formato válido, etc.)

        // --- LÓGICA DE REGISTRO ---
        try {
            // Delegamos la creación del usuario al servicio.
            // Este método se encargará de encriptar la contraseña y guardar en la DB.
            usuarioServicio.crearUsuarioLocal(nombre, email, contrasena);
            log.info("Nuevo usuario registrado con éxito: {}", email);

            mostrarAlerta("Registro Exitoso", "¡Tu cuenta ha sido creada! Ahora puedes iniciar sesión.", Alert.AlertType.INFORMATION);

            // Llevamos al usuario de vuelta a la pantalla de login principal (el Hub).
            gestorDeEscenas.cambiarEscena("Login/Login.fxml", "FreePlayer - Inicio de Sesión");

        } catch (IllegalStateException e) {
            // Capturamos el error específico si el email ya existe.
            log.error("Error durante el registro del usuario: {}", e.getMessage());
            mostrarAlerta("Error de Registro", e.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception e) {
            // Capturamos cualquier otro error inesperado.
            log.error("Error inesperado durante el registro.", e);
            mostrarAlerta("Error Inesperado", "Ocurrió un error al intentar crear la cuenta.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Maneja el evento de clic del botón "Volver".
     */
    @FXML
    void manejarVolver(ActionEvent event) {
        gestorDeEscenas.cambiarEscena("Login/Login.fxml", "FreePlayer - Inicio de Sesión");
    }

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}