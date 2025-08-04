package PMK.free_player;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringApplication {
    private static final Logger log = LoggerFactory.getLogger(SpringApplication.class);
    public static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        log.info("Iniciando Free Player");
        SpringApplication.applicationContext = new SpringApplicationBuilder(SpringApplication.class)
                .headless(false)
                .web(org.springframework.boot.WebApplicationType.NONE)
                .run(args);

        log.info("Contexto de aplicación iniciado");
        Application.launch(PMK.free_player.application.Reproductor.class, args);
    }
}
