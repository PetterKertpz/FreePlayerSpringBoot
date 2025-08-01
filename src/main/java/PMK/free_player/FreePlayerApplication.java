package PMK.free_player;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FreePlayerApplication{

	private static final Logger logger = LoggerFactory.getLogger(FreePlayerApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando la aplicación FreePlayer");
		System.out.println("Iniciando la aplicación FreePlayer");
		SpringApplication.run(FreePlayerApplication.class, args);
	}


}
