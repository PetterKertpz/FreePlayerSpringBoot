package PMK.free_player.application;

import javafx.application.Application;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; // Necesaria para el escaneo de componentes
import org.springframework.context.ConfigurableApplicationContext; // Necesaria para el contexto

@SpringBootApplication
public class FreePlayerApplicationSpring {

    private static final Logger logger = LoggerFactory.getLogger(FreePlayerApplicationSpring.class);

    public static void main(String[] args) {
        logger.info("Iniciando la aplicación FreePlayer");
    }

}