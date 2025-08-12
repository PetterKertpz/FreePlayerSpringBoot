package PMK.free_player.security; // O el paquete de configuración que prefieras

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfiguracionSeguridad {

    @Bean
    public PasswordEncoder codificadorDeContrasena() {
        // BCrypt es el estándar de la industria para hashear contraseñas.
        return new BCryptPasswordEncoder();
    }
}