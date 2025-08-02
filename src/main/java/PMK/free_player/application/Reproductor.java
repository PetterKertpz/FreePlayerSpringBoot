package PMK.free_player.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import PMK.free_player.SpringApplication; // Importa tu clase principal de Spring Boot

public class Reproductor extends Application {

    @Override
    public void init() throws Exception {
        // En este patrón, el contexto de Spring ya fue inicializado por FreePlayerApplication.main().
        // Este metodo se puede usar para inicializaciones específicas de JavaFX si son necesarias antes de 'start()'.
        super.init();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/templates/index.fxml"));

            if (SpringApplication.applicationContext == null) {
                Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, "El contexto de Spring no fue inicializado " +
                        "correctamente.");
                Platform.exit();
                return;
            }
            fxmlLoader.setControllerFactory(SpringApplication.applicationContext::getBean);

            Parent root = fxmlLoader.load();
            Scene escena = new Scene(root);
            primaryStage.setScene(escena);
            primaryStage.setTitle("FreePlayer - Tu Reproductor de Música");
            primaryStage.show();

        } catch (IOException e) {
            Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, "Error al cargar la vista FXML: " + e.getMessage(),
                    e);
            // Considera mostrar un diálogo de error al usuario
        } catch (Exception e) {
            Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE,
                    "Error inesperado al iniciar la aplicación: " + e.getMessage(), e);
            // Considera mostrar un diálogo de error al usuario
        }
    }

    @Override
    public void stop() throws Exception {
        // Cierra el contexto de Spring cuando la aplicación JavaFX se detiene.
        if (SpringApplication.applicationContext != null) {
            SpringApplication.applicationContext.close();
            Logger.getLogger(Reproductor.class.getName()).log(Level.INFO, "Contexto de Spring cerrado.");
        }
        super.stop();
        Platform.exit(); // Asegura que todos los hilos de JavaFX se cierren
    }
}