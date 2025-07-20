package PMK.free_player.aspects;

import PMK.free_player.exceptions.UsuarioNoEncontradoException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect // Declara que esta clase es un Aspecto
@Component // Para que Spring la detecte y la gestione como un bean
public class ExceptionHandlingAspect {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlingAspect.class);

    /**
     * Este 'advice' se ejecutará después de que un método en los servicios
     * de PMK.free_player.services lance una excepción.
     *
     * @param joinPoint Representa el metodo que lanzó la excepción.
     * @param ex La excepción lanzada.
     */
    @AfterThrowing(
        pointcut = "within(PMK.free_player.services.*)", // Aplica a todos los métodos dentro del paquete services
        throwing = "ex" // Captura la excepción y la pasa al parámetro 'ex'
    )
    public void logAndHandleServiceException(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();

        // Logueo de la excepción
        log.error("Excepción en {}.{}(): {}", className, methodName, ex.getMessage());

        // --- Lógica para manejo automático de excepciones ---
        if (ex instanceof UsuarioNoEncontradoException) {
            log.warn("Se intentó acceder a un usuario inexistente. Detalle: {}", ex.getMessage());
            // Aquí podrías, por ejemplo, notificar a un servicio de UI para mostrar un mensaje.
            // Ya no necesitas re-lanzar, porque AOP lo está interceptando.
            // Si la quieres re-lanzar para que una capa superior la maneje, puedes hacerlo:
            // throw (UsuarioNoEncontradoException) ex;
        } else if (ex instanceof DataAccessException) {
            log.error("Error de acceso a datos en {}.{}. Por favor, revise la conexión a la base de datos o la integridad de los datos. Causa: {}",
                      className, methodName, ex.getMessage());
            // Aquí podrías notificar a la UI sobre un problema de DB (ej. "Error de base de datos. Intente más tarde.")
            // throw (DataAccessException) ex; // Si aún quieres que se propague
        } else {
            log.error("Una excepción inesperada ocurrió en {}.{}. Detalles: {}", className, methodName, ex.getMessage(), ex);
            // Aquí podrías notificar a la UI sobre un error genérico o crítico
        }
        // Importante: Si no re-lanzas la excepción aquí, la ejecución normal del programa continuaría
        // como si no hubiera pasado nada, lo cual no es lo que quieres para un error.
        // Por lo general, se re-lanza, pero el logueo se hace de forma centralizada.
        // Para tu caso de app local, simplemente loguear y que la excepción siga su curso hacia el punto de llamada
        // donde puedes decidir si mostrar algo en la UI, es una opción válida.
        // No obstante, si el objetivo es *evitar try-catch por completo en los llamadores*,
        // entonces aquí tendrías que envolver las excepciones en un "resultado" o "estado"
        // que el llamador pueda verificar, pero eso complica el servicio.
        // Para mantener la simplicidad y el contrato de que el servicio lanza excepciones,
        // AOP es para *observar* y *loguear* centralizadamente.
    }
}