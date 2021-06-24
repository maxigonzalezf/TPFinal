package Aeropuerto;

/**
 * Clase que hereda de Avion
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public class SmallBird extends Avion{

    public SmallBird(String id) {
        super(id);
        setCantidadDePuertas(6);
        setCapacidad(95);
        setCombustible(1600);
        setVelocidadMaxima(750);
    }
}
