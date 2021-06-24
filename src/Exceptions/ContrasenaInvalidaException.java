package Exceptions;

/**
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public class ContrasenaInvalidaException extends Exception {

    String msg;

    public ContrasenaInvalidaException(String msg) {
        super(msg);
    }
}
