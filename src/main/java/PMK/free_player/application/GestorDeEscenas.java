package PMK.free_player.application; // O 'application' si lo dejaste ahí

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Service
public class GestorDeEscenas {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(GestorDeEscenas.class);

    private final ApplicationContext contextoSpring;
    private       Stage              escenarioPrincipal;

    public GestorDeEscenas(ApplicationContext contextoSpring) {
        this.contextoSpring = contextoSpring;
    }

    public void inicializar(Stage escenario) {
        this.escenarioPrincipal = escenario;
    }

    /**
     * Cambia la escena actual por una nueva.
     * Ahora la ruta FXML incluye la subcarpeta.
     *
     * @param rutaFxml La ruta relativa al archivo FXML desde la carpeta 'fxml'.
     *                 Ej: "Login/Login.fxml" o "Listas/ListasCanciones.fxml"
     * @param titulo   El título para la ventana.
     */
    public void cambiarEscena(String rutaFxml, String titulo) {
        try {
            // Construimos la ruta completa al recurso
            String rutaCompleta = "/fxml/" + rutaFxml;
            URL urlRecurso = Objects.requireNonNull(getClass().getResource(rutaCompleta));

            FXMLLoader cargadorFxml = new FXMLLoader(urlRecurso);
            cargadorFxml.setControllerFactory(contextoSpring::getBean);

            Parent panelRaiz = cargadorFxml.load();
            Scene nuevaEscena = new Scene(panelRaiz);

            escenarioPrincipal.setScene(nuevaEscena);
            escenarioPrincipal.setTitle(titulo);
            escenarioPrincipal.show();

        } catch (IOException e) {
            System.err.println("No se pudo cargar la escena: " + rutaFxml);
            log.error("Error al cargar la escena {}: {}", rutaFxml, e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Error Crítico: No se encontró el archivo FXML en la ruta: /fxml/" + rutaFxml);
            log.error("Error al cargar la escena {}: {}", rutaFxml, e.getMessage());
        }
    }
}