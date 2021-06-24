package Exceptions;

/**
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public class NivelException extends Exception {

    String msg;

    public NivelException(String msg) {
        super(msg);
    }
}
