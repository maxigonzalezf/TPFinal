package Exceptions;

/**
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public class UsuarioInvalidoException extends Exception {

    String msg;

    public UsuarioInvalidoException(String msg) {
        super(msg);
    }
}
