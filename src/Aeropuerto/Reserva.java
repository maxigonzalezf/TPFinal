package Aeropuerto;

import java.util.Date;
import java.util.Objects;

/**
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public class Reserva {

    private Vuelos vuelo;
    private Date fechaDeReserva;

    public Reserva(Vuelos vuelo, Date fechaDeReserva) {
        this.vuelo = vuelo;
        this.fechaDeReserva = fechaDeReserva;
    }

    /// To string ----------------------------------------------///

    @Override
    public String toString() {
        return "Reserva{" +
                ", Vuelo=" + vuelo.toString() +
                ", fechaDeReserva='" + fechaDeReserva + '\'' +
                '}';
    }

    /// Setters and getters -------------------------------------///

    public Vuelos getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelos vuelo) {
        this.vuelo = vuelo;
    }

    public Date getFechaDeReserva() {
        return fechaDeReserva;
    }

    public void setFechaDeReserva(Date fechaDeReserva) {
        this.fechaDeReserva = fechaDeReserva;
    }
}


