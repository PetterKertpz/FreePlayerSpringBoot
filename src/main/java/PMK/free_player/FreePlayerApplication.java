package PMK.free_player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class FreePlayerApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(FreePlayerApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando la aplicación FreePlayer");
		//Levantar la aplicación Spring Boot


		SpringApplication.run(FreePlayerApplication.class, args);
		logger.info("Cerrando la aplicación FreePlayer");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("***FREEPLAYER***");
	}


}
