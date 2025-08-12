package PMK.free_player.application;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

import PMK.free_player.SpringApplication; // Importa tu clase principal de Spring Boot

public class Reproductor extends Application {

    private static final Logger log = Logger.getLogger(Reproductor.class.getName());

    @Override
    public void init() throws Exception {
        // En este patrón, el contexto de Spring ya fue inicializado por FreePlayerApplication.main().
        // Este metodo se puede usar para inicializaciones específicas de JavaFX si son necesarias antes de 'start()'.
        super.init();
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        // 1. Verificamos que el contexto de Spring esté disponible.
        if (SpringApplication.applicationContext == null) {
            log.log(Level.SEVERE, "Error Crítico: El contexto de Spring no fue inicializado.");
            // Aquí no se usa Platform.exit() porque puede no funcionar si JavaFX no está completamente levantado.
            // Simplemente no continuamos.
            return;
        }

        // 2. Obtenemos nuestra instancia del GestorDeEscenas desde el contexto de Spring.
        // Spring ya ha creado este objeto por nosotros porque tiene la anotación @Service.
        GestorDeEscenas gestorDeEscenas = SpringApplication.applicationContext.getBean(GestorDeEscenas.class);

        // 3. Le pasamos el 'Stage' (el lienzo principal de la ventana) a nuestro gestor.
        // El gestor lo guardará para poder cambiar escenas en él más adelante.
        gestorDeEscenas.inicializar(escenarioPrincipal);

        // 4. Le pedimos al gestor que cargue la primera escena: la de Login.
        // Ya no cargamos "Main.fxml" directamente aquí.
        log.info("Iniciando la aplicación. Mostrando la escena de Login.");
        gestorDeEscenas.cambiarEscena("Login/Login.fxml", "FreePlayer - Inicio de Sesión");
    }

    @Override
    public void stop() throws Exception {
        // Cierra el contexto de Spring cuando la aplicación JavaFX se detiene.
        // Tu lógica aquí es correcta y la mantenemos.
        if (SpringApplication.applicationContext != null) {
            SpringApplication.applicationContext.close();
            log.log(Level.INFO, "Contexto de Spring cerrado.");
        }
    }
}