package Exceptions;

/**
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public class CaracterEspecialException extends Exception {

    String msg;

    public CaracterEspecialException(String msg) {
        super(msg);
    }
}
