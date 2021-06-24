package Usuarios;

import Aeropuerto.ListaDeVuelos;
import Aeropuerto.Reserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que hereda de Usuario, representa a los usuarios finales de la aplicacion
 * @author Maxi gonzalez
 * @author Ignacio Rios
 */

public class UNormal extends Usuario {
    Scanner scan;

    private String nombreUsuario;
    private String contrasena;
    private boolean premium;
    private ArrayList<Reserva> listaDeReservas;

    public UNormal(){
        listaDeReservas = new ArrayList<>();
        premium = false;
        scan = new Scanner(System.in);
    }

    public UNormal(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        listaDeReservas = new ArrayList<>();
        premium = false;
        scan = new Scanner(System.in);
    }

    /**
     * Override de la funcion getNombreUsuario de la clase abstracta Usuario
     * @return String - nombre del usuario
     * @see Usuario
     */

    @Override
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Override de la funcion getContrasena de la clase abstracta Usuario
     * @return String - contraseña del usuario
     * @see Usuario
     */
    @Override
    public String getContrasena() {
        return contrasena;
    }

    /**
     *
     * @return boolean - true si el usuario es premium; false si el usuario no es premium
     */
    public boolean isPremium() {
        return premium;
    }

    /**
     * Override de la funcion setNombreUsuario de la clase abstracta Usuario
     * Setea el nombre del usuario
     * @param nombreUsuario del tipo String
     * @see Usuario
     */
    @Override
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Override de la funcion setContrasena de la clase abstracta Usuario
     * Setea la contraseña del usuario
     * @param contrasena del tipo String
     * @see Usuario
     */
    @Override
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Establece si el usuario es premium o no
     * @param premium del tipo boolean
     */
    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    /**
     * Agrega una reserva efectuada por el usuario a su lista de reservas
     * @param res del tipo Reserva
     * @see UNormal#getListaDeReservas()
     * @see Aeropuerto.Reserva
     */
    public void agregarReserva(Reserva res) {
        listaDeReservas.add(res);
    }

    /**
     * Elimina la reserva seleccionada de la lista de reservas del usuario
     * @see UNormal#getListaDeReservas()
     * @see Aeropuerto.Reserva
     */
    public void cancelarReserva() {
        boolean control;
        this.getListaDeReservas();

        do {
            control = true;
            try {
                System.out.println("\nELIJA LA RESERVA QUE DESEA CANCELAR");
                int i = scan.nextInt() - 1;

                if(i < 0 || i > listaDeReservas.size())
                    control = false;
                else
                    listaDeReservas.remove(i);
            } catch (InputMismatchException i) {
                System.out.println("Debe ingresar una opcion valida");
                scan.nextLine();
                control = false;
            }
        }while(!control);
    }

    /**
     * Muestra la lista de reservas del usuario
     * @see Aeropuerto.Reserva
     */
    public void getListaDeReservas() {
        for(int i=0;i<listaDeReservas.size();i++)
            if(listaDeReservas.get(i) != null)
                System.out.println((i+1)+"- "+listaDeReservas.get(i));
    }

    /**
     * Le permite al usuario efectuar la reserva de un vuelo y lo agrega a su lista de reservas
     * Si la reserva se hace efectiva, le suma un pasajero al vuelo
     * @param listadevuelos del tipo ListaDeVuelos
     * @see Aeropuerto.ListaDeVuelos
     * @see Aeropuerto.Vuelos
     */
    public void elegirAvion(ListaDeVuelos listadevuelos) {
        int opcion, opcion2;
        char opc;
        boolean control;

        do {
            control = true;
            try {
                System.out.println("\n---------------------------Elija su Origen y Destino--------------------------");
                listadevuelos.imprimir();
                System.out.println("Ingrese una opcion: ");
                System.out.println(listadevuelos.cantidadOpcionesOriDes());
                opcion = scan.nextInt() - 1;
                if(opcion < 0 || opcion >= listadevuelos.cantidadOpcionesOriDes())
                    control = false;
                else {
                    System.out.println("\n---------------------------Elija su Vuelo--------------------------");
                    listadevuelos.mostrarVuelosDisponibles(listadevuelos.getListaOriDes().toArray()[opcion].toString());
                    System.out.println("Ingrese una opcion: ");
                    opcion2 = scan.nextInt() - 1;
                    if(opcion2 < 0 || opcion2 >= listadevuelos.cantidadVuelos(listadevuelos.getListaOriDes().toArray()[opcion].toString()))
                        control = false;
                    else {
                        System.out.println("Usted ha seleccionado el vuelo:");
                        System.out.println(listadevuelos.getVuelo(listadevuelos.getListaOriDes().toArray()[opcion].toString(), opcion2));
                        System.out.println("Confirmar Reserva: s/n");
                        opc = scan.next().charAt(0);
                        if (opc == 's') {
                            if(listadevuelos.getVuelo(listadevuelos.getListaOriDes().toArray()[opcion].toString(), opcion2).getPasajeros() <
                               listadevuelos.getVuelo(listadevuelos.getListaOriDes().toArray()[opcion].toString(), opcion2).getAv().getCapacidad()) {// Controla que no este lleno el avion
                                Reserva res = new Reserva(listadevuelos.getVuelo(listadevuelos.getListaOriDes().toArray()[opcion].toString(), opcion2), new Date());
                                this.agregarReserva(res); //Guardamos la reserva a la lista de reservas del usuario
                                listadevuelos.getVuelo(listadevuelos.getListaOriDes().toArray()[opcion].toString(), opcion2).sumarPasajeros(); // Le agregamos un pasajero al Vuelo elegido
                                System.out.println("Reserva realizada con exito!");
                            } else {
                                System.out.println("Lamentablemente el vuelo ya esta completo.");
                            }
                        } else
                            System.out.println("No se ha completado la reserva");
                    }
                }
            }catch (InputMismatchException i) {
                System.out.println("Debe ingresar una opcion valida.");
                scan.nextLine();
                control = false;
            }
        }while(!control);
    }
}
