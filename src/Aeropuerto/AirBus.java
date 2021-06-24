package Aeropuerto;

/**
 * Clase que hereda de Avion e implementa la interfaz Wifi
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public class AirBus extends Avion implements Wifi{
    private String wifi;

    public AirBus(String id) {
        super(id);
        setCapacidad(150);
        setCombustible(2000);
        setCantidadDePuertas(8);
        setVelocidadMaxima(900);
        this.wifi="utnairlines";
    }

    @Override
    public String wifi() {
    return wifi;
    }
}
