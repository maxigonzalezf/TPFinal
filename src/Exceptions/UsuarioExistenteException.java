package Exceptions;

/**
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public class UsuarioExistenteException extends Exception {

    String msg;

    public UsuarioExistenteException(String msg) {
        super(msg);
    }
}
