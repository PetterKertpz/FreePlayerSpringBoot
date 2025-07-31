package PMK.free_player.presentacion;

import PMK.free_player.FreePlayerApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class FreePlayerFx extends Application {

    // Contexto de la aplicación Spring
    private ConfigurableApplicationContext applicationContext;

    // Metodo principal para iniciar la aplicación JavaFX

    /**
    public static void main(String[] args) {
        launch(args);
    }
    **/

    // Metodo para inicializar la aplicación Spring
    @Override
    public void init() {
        this.applicationContext = new SpringApplicationBuilder(FreePlayerApplication.class).run();
    }

    // Metodo para iniciar la aplicación JavaFX
    @Override
    public void start(Stage Stage) throws Exception {
        // Cargar el archivo FXML y establecer el controlador
        FXMLLoader loader = new FXMLLoader(FreePlayerApplication.class.getResource("/templates/index.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Scene escena = new Scene(loader.load());
        Stage.setScene(escena);
        Stage.show();
    }

    @Override
    public void stop() {
        // Cerrar el contexto de la aplicación Spring al cerrar la aplicación JavaFX
        if (applicationContext != null) {
            applicationContext.close();
            Platform.exit();
        }
    }

}
