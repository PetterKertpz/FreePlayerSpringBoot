package PMK.free_player.application;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

/**
 * Este servicio se encarga de toda la comunicación con las APIs de Google
 * para la autenticación y obtención de datos del usuario.
 */
@Service // Anotación de Spring para que la clase sea gestionada como un servicio.
public class ServicioAutenticacionGoogle {

    private static final String NOMBRE_APLICACION = "FreePlayer";
    private static final GsonFactory FABRICA_JSON = GsonFactory.getDefaultInstance();
    // Directorio donde se guardará el token del usuario para no tener que iniciar sesión cada vez.
    private static final File DIRECTORIO_DATOS_ALMACENADOS = new File(System.getProperty("user.home"), ".credentials/freeplayer-google-auth");

    // Estos son los permisos que le pediremos al usuario.
    private static final List<String> PERMISOS = Arrays.asList(
            "https://www.googleapis.com/auth/userinfo.profile", // Para obtener nombre, foto, etc.
            "https://www.googleapis.com/auth/userinfo.email"    // Para obtener el email.
    );

    private final String rutaCredenciales;
    private final NetHttpTransport transporteHttp;
    private FileDataStoreFactory fabricaAlmacenDatos;

    /**
     * El constructor del servicio. Spring se encargará de llamar a este constructor
     * e "inyectar" el valor de 'google.credentials.path' desde tu archivo application.properties.
     *
     * @param rutaCredenciales La ruta al archivo client_secret.json.
     */
    public ServicioAutenticacionGoogle(@Value("${google.credentials.path}") String rutaCredenciales) throws GeneralSecurityException, IOException {
        this.rutaCredenciales = rutaCredenciales;
        this.transporteHttp = new NetHttpTransport();
        this.fabricaAlmacenDatos = new FileDataStoreFactory(DIRECTORIO_DATOS_ALMACENADOS);
    }

    /**
     * Inicia el flujo de autorización.
     * Abrirá el navegador para que el usuario inicie sesión.
     * @return Las credenciales del usuario autenticado.
     */
    public Credential autorizar() throws IOException, GeneralSecurityException {
        // Carga los secretos del cliente desde el archivo JSON.
        try (Reader lectorDeSecretos = new FileReader(rutaCredenciales)) {
            GoogleClientSecrets secretosCliente = GoogleClientSecrets.load(FABRICA_JSON, lectorDeSecretos);

            // Construye el flujo de autorización que orquesta todo el proceso.
            GoogleAuthorizationCodeFlow flujo = new GoogleAuthorizationCodeFlow.Builder(
                    transporteHttp, FABRICA_JSON, secretosCliente, PERMISOS)
                    .setDataStoreFactory(fabricaAlmacenDatos)
                    .setAccessType("offline")
                    .build();

            // Inicia un pequeño servidor web local para recibir la respuesta de Google.
            LocalServerReceiver receptor = new LocalServerReceiver.Builder().setPort(8888).build();

            // Esta clase une todo y se encarga de abrir el navegador y esperar la respuesta.
            return new AuthorizationCodeInstalledApp(flujo, receptor).authorize("user");
        }
    }

    /**
     * Usa las credenciales para obtener la información del perfil del usuario.
     * @param credencial La credencial obtenida tras la autorización.
     * @return Un objeto Person con los datos del usuario.
     */
    public Person obtenerInformacionUsuario(Credential credencial) throws GeneralSecurityException, IOException {
        // Construye el cliente específico para la API de People.
        PeopleService servicioPeople = new PeopleService.Builder(transporteHttp, FABRICA_JSON, credencial)
                .setApplicationName(NOMBRE_APLICACION)
                .build();

        // Pide la información de "yo" (el usuario autenticado), solicitando solo los campos que necesitamos.
        return servicioPeople.people()
                             .get("people/me")
                             .setPersonFields("names,emailAddresses,photos")
                             .execute();
    }
}