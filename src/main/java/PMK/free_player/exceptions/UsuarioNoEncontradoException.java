package PMK.free_player.exceptions;
// Extiende RuntimeException para que sea una excepción no chequeada (unchecked exception).
// Esto evita la necesidad de declarar 'throws UsuarioNoEncontradoException' en cada método que pueda lanzarla.
public class UsuarioNoEncontradoException extends RuntimeException {

    // Constructor que toma un mensaje como argumento
    public UsuarioNoEncontradoException(String mensaje) {
        // Llama al constructor de la clase padre (RuntimeException) con el mensaje.
        // Este mensaje será accesible a través de getMessage() en la excepción.
        super(mensaje);
    }

    // Opcionalmente, puedes añadir otro constructor si quieres incluir la causa original (Throwable)
    public UsuarioNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}