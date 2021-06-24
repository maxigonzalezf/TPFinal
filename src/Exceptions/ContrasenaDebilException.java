package Exceptions;

/**
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public class ContrasenaDebilException extends Exception {

    String msg;

    public ContrasenaDebilException(String msg) {
        super(msg);
    }
}
