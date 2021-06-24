package Aeropuerto;

/**
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public abstract class Avion {
    private int capacidad;
    private String id;
    private int combustible;
    private int velocidadMaxima;
    private int cantidadDePuertas;

    public Avion(String id) {
        this.id = id;
    }



    /// Getter and Setter ---------------------------------------///
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCombustible() {
        return combustible;
    }

    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public int getCantidadDePuertas() {
        return cantidadDePuertas;
    }

    public void setCantidadDePuertas(int cantidadDePuertas) {
        this.cantidadDePuertas = cantidadDePuertas;
    }
}
