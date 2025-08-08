package PMK.free_player.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fxml")
@Data
@Getter
@Setter
public class FxmlPaths {
    private String main;
    private String listas;

}
