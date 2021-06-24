package Aeropuerto;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public class Vuelos {
    Scanner scan;
    private Avion av;
    private Destinos ciudadOrigen;
    private Destinos ciudadDestino;
    private Date horaPartida;
    private Date horaLlegada;
    private int pasajeros;
    private int costo;

    public Vuelos(Avion av, Destinos ciudadOrigen, Destinos ciudadDestino, Date horaPartida, Date horaLlegada, int pasajeros, int costo) {
        this.av = av;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.horaPartida = horaPartida;
        this.horaLlegada = horaLlegada;
        this.pasajeros = pasajeros;
        this.costo = costo;
        scan = new Scanner(System.in);
    }

    public Vuelos() {
        this.pasajeros=0;
        scan = new Scanner(System.in);
    }

    @Override
    public String toString() {
        return "|Vuelo|" +
                " Ciudad de Origen='" + ciudadOrigen.getNombre() + '\'' +
                ", Ciudad de Destino='" + ciudadDestino.getNombre() + '\'' +
                ", Hora de partida='" + horaPartida + '\'' +
                ", Hora de llegada='" + horaLlegada + '\'' +
                ", pasajeros=" + pasajeros +
                ", Costo = $" + costo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vuelos)) return false;
        Vuelos vuelos = (Vuelos) o;
        return pasajeros == vuelos.pasajeros && costo == vuelos.costo && Objects.equals(av, vuelos.av) && Objects.equals(ciudadOrigen, vuelos.ciudadOrigen) && Objects.equals(ciudadDestino, vuelos.ciudadDestino) && Objects.equals(horaPartida, vuelos.horaPartida) && Objects.equals(horaLlegada, vuelos.horaLlegada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(av, ciudadOrigen, ciudadDestino, horaPartida, horaLlegada, pasajeros, costo);
    }

    /**
     * Suma pasajeros que reservaron un asiento en el vuelo
     */
    public void sumarPasajeros(){

        if(pasajeros < av.getCapacidad())
            pasajeros = pasajeros + 1;
    }

    /**
     * Retorna el avion que realizara el vuelo
     * @return Avion
     */
    public Avion getAv() {
        return av;
    }

    /**
     * Setea el avion que realizara el vuelo
     * @param av del tipo Avion
     */
    public void setAv(Avion av) {
        this.av = av;
    }

    /**
     * Retorna la ciudad desde donde saldra el vuelo
     * @return Destinos - ciudad de origen del vuelo
     */
    public Destinos getCiudadOrigen() {
        return ciudadOrigen;
    }

    /**
     * Setea la ciudad desde donde saldra el vuelo
     * @param ciudadOrigen del tipo Destinos
     */
    public void setCiudadOrigen(Destinos ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    /**
     * Retorna la ciudad a donde se destina el vuelo
     * @return Destinos - ciudad de destino del vuelo
     */
    public Destinos getCiudadDestino() {
        return ciudadDestino;
    }

    /**
     * Setea la ciudad a donde se dirige el vuelo
     * @param ciudadDestino del tipo Destinos
     */
    public void setCiudadDestino(Destinos ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    /**
     * Retorna la fecha de partida del vuelo
     * @return Date
     */
    public Date getHoraPartida() {
        return horaPartida;
    }

    /**
     * Setea la fecha de partida del vuelo
     */
    public void setHorapartida() {
        Date partida = new Date();
        int dia, mes, anio, hora, minutos;
        boolean control;

        do {
            control = true;
            try {
                System.out.println("Ingrese el año de partida (YYYY)");
                anio = scan.nextInt();
                if(anio < 2021 || anio > 2030)
                    control = false;
                else {
                    partida.setYear(anio);
                    System.out.println("Ingrese el mes de partida");
                    mes = scan.nextInt() - 1;
                    if(mes < 0 || mes > 11)
                        control = false;
                    else {
                        partida.setMonth(mes);
                        System.out.println("Ingrese el dia de partida");
                        dia = scan.nextInt();
                        if(dia > 31 || dia < 1 || mes == 1 && dia > 28 || mes == 3 && dia > 30 || mes == 5 && dia > 30
                                || mes == 8 && dia > 30 || mes == 10 && dia > 30)
                            control = false;
                        else {
                            partida.setDate(dia);
                            System.out.println("Ingrese la hora de partida");
                            hora = scan.nextInt();
                            if(hora < 0 || hora > 23)
                                control = false;
                            else {
                                partida.setHours(hora);
                                System.out.println("Ingrese los minutos");
                                minutos = scan.nextInt();
                                if(minutos < 0 || minutos > 59)
                                    control = false;
                                else {
                                    partida.setMinutes(minutos);
                                    partida.setSeconds(0);
                                    control = true;
                                    horaPartida = partida;
                                }
                            }
                        }
                    }
                }
            }catch (InputMismatchException i) {
                System.out.println("Debe ingresar lo que se le pide");
                scan.nextLine();
                control = false;
            }
        }while(!control);
    }

    /**
     * Retorna la fecha de llegada del vuelo
     * @return Date
     */
    public Date getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * Setea la fecha de llegada del vuelo
     */
    public void setHoraLlegada() {
        Date llegada = new Date();
        int dia, mes, anio, hora, minutos;
        boolean control;

        do {
            control = true;
            try {
                System.out.println("Ingrese el año de llegada (YYYY)");
                anio = scan.nextInt();
                if(anio < horaPartida.getYear() || anio > 2030)
                    control = false;
                else {
                    llegada.setYear(anio);
                    System.out.println("Ingrese el mes de llegada");
                    mes = scan.nextInt() - 1;
                    if(mes < 0 || mes > 11 || anio < horaPartida.getYear() && mes < horaPartida.getMonth())
                        control = false;
                    else {
                        llegada.setMonth(mes);
                        System.out.println("Ingrese el dia de llegada");
                        dia = scan.nextInt();
                        if(dia > 31 || dia < 1 || mes == 1 && dia > 28 || mes == 3 && dia > 30 || mes == 5 && dia > 30
                                || mes == 8 && dia > 30 || mes == 10 && dia > 30
                                || anio < horaPartida.getYear() && mes < horaPartida.getMonth() && dia < horaPartida.getDate()) {
                            control = false;
                        }
                        else {
                            llegada.setDate(dia);
                            System.out.println("Ingrese la hora de llegada");
                            hora = scan.nextInt();
                            if(hora < 0 || hora > 23
                               || anio < horaPartida.getYear() && mes < horaPartida.getMonth() && dia < horaPartida.getDate()
                                    && hora < horaPartida.getHours())
                                control = false;
                            else {
                                llegada.setHours(hora);
                                System.out.println("Ingrese los minutos");
                                minutos = scan.nextInt();
                                if(minutos < 0 || minutos > 59
                                   || anio < horaPartida.getYear() && mes < horaPartida.getMonth() && dia < horaPartida.getDate()
                                        && hora < horaPartida.getHours() && minutos < horaPartida.getMinutes())
                                    control = false;
                                else {
                                    llegada.setMinutes(minutos);
                                    llegada.setSeconds(0);
                                    control = true;
                                    horaLlegada = llegada;
                                }
                            }
                        }
                    }
                }
            }catch (InputMismatchException i) {
                System.out.println("Debe ingresar lo que se le pide");
                scan.nextLine();
                control = false;
            }
        }while(!control);
    }

    /**
     * Retorna la cantidad de pasajeros del vuelo
     * @return int
     */
    public int getPasajeros() {
        return pasajeros;
    }

    /**
     * Retorna el costo del pasaje del vuelo
     * @return int
     */
    public int getCosto() {
        return costo;
    }

    /**
     * Setea el costo del pasaje del vuelo
     * @param costo del tipo int
     */
    public void setCosto(int costo) {
        this.costo = costo;
    }
}
