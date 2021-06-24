package Usuarios;

import Aeropuerto.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que hereda de Usuario, representa a los administradores de la aplicacion
 * @author Maxi gonzalez
 * @author Ignacio Rios
 */

public class UAdmin extends Usuario {
    Scanner scan;

    private String nombreUsuario;
    private String contrasena;

    public UAdmin(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        scan = new Scanner(System.in);
    }

    public UAdmin(){
        scan = new Scanner(System.in);
    }

    /**
     * Override de la funcion getNombreUsuario de la clase abstracta Usuario
     * @return String - nombre del administrador
     * @see Usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Override de la funcion getContrasena de la clase abstracta Usuario
     * @return String - contraseña del administrador
     * @see Usuario
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Override de la funcion setNombreUsuario de la clase abstracta Usuario
     * Setea el nombre del administrador
     * @param nombreUsuario del tipo String
     * @see Usuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Override de la funcion setContrasena de la clase abstracta Usuario
     * Setea la contraseña del administrador
     * @param contrasena del tipo String
     * @see Usuario
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Permite al administrador eliminar un vuelo
     * @param listadevuelos del tipo ListaDeVuelos
     * @see Aeropuerto.ListaDeVuelos
     * @see Aeropuerto.Vuelos
     */
    public void eliminarVuelo(ListaDeVuelos listadevuelos) {
        int opcion, opcion2;
        char opc;
        boolean control;

        do {
            control = true;
            try {
                listadevuelos.imprimir();
                System.out.println("Ingrese una opcion: ");
                opcion = scan.nextInt() - 1;
                if(opcion >= listadevuelos.cantidadOpcionesOriDes() || opcion < 0)
                    control = false;
                else {
                    listadevuelos.mostrarVuelosDisponibles(listadevuelos.getListaOriDes().toArray()[opcion].toString());
                    System.out.println("Ingrese una opcion: ");
                    opcion2 = scan.nextInt() - 1;
                    if (opcion2 >= listadevuelos.cantidadVuelos(listadevuelos.getListaOriDes().toArray()[opcion].toString())
                            || opcion2 < 0)
                        control = false;
                    else {
                        System.out.println("Usted ha seleccionado el vuelo:");
                        System.out.println(listadevuelos.getVuelo(listadevuelos.getListaOriDes().toArray()[opcion].toString(), opcion2));
                        System.out.println("Presione 's' para confirmar...");
                        opc = scan.next().charAt(0);
                        if (opc == 's') {
                            listadevuelos.borrarVuelo(opcion, opcion2);
                            System.out.println("Vuelo eliminado exitosamente");
                        } else {
                            System.out.println("No se ha eliminado ningun vuelo");
                            control = true;
                        }
                    }
                }
            } catch (InputMismatchException i) {
                System.out.println("Debe ingresar una opcion correcta");
                scan.nextLine();
                control = false;
            }
        }while(!control);

    }

    /**
     * Permite al administrador agregar un vuelo
     * @param lista del tipo ListaDeVuelos
     * @see Aeropuerto.ListaDeVuelos
     * @see Aeropuerto.Vuelos
     */
    public void agregarVuelo(ListaDeVuelos lista){
        int opcion=0, precio;
        boolean control;
        ArrayList<Vuelos> aux;
        Vuelos nuevoVuelo = new Vuelos();
        lista.imprimir();

        do {
            control = true;
            try {
                System.out.println("Ingrese una opcion: ");
                opcion = scan.nextInt() - 1;

                nuevoVuelo.setCiudadOrigen(Destinos.BUE);

                if (opcion == 0) {
                    nuevoVuelo.setCiudadDestino(Destinos.MAD);
                    System.out.println("Se ha elegido Madrid para la ciudad Destino");
                }
                if (opcion == 1) {
                    nuevoVuelo.setCiudadDestino(Destinos.TOK);
                    System.out.println("Se ha elegido Tokyo para la ciudad Destino");
                }
                if (opcion == 2) {
                    nuevoVuelo.setCiudadDestino(Destinos.SID);
                    System.out.println("Se ha elegido Sidney para la ciudad Destino");
                }
                if (opcion == 3) {
                    nuevoVuelo.setCiudadDestino(Destinos.MIA);
                    System.out.println("Se ha elegido Miami para la ciudad Destino");
                }
                if (opcion < 0 || opcion >= lista.cantidadOpcionesOriDes()) {
                    System.out.println("Debe ingresar una opcion correcta");
                    control = false;
                }
            } catch (InputMismatchException i) {
                System.out.println("Debe ingresar una opcion correcta");
                scan.nextLine();
                control = false;
            }
        } while(!control);

        aux = lista.getListaVuelos(lista.getListaOriDes().toArray()[opcion].toString());

        nuevoVuelo.setHorapartida();
        nuevoVuelo.setHoraLlegada();

        do {
            control = true;
            try {
                System.out.println("Ingrese el precio del vuelo");
                precio = scan.nextInt();
                nuevoVuelo.setCosto(precio);
            } catch (InputMismatchException i) {
                System.out.println("Debe ingresar un precio correcto");
                scan.nextLine();
                control = false;
            }
        } while(!control);

        do {
            control = true;
            try {
                System.out.println("Ingrese El avion con el que va a realizar el vuelo");
                System.out.println("1. AirBus          2.Smallbird");
                opcion = scan.nextInt();

                if(opcion != 1 && opcion != 2) {
                    System.out.println("Debe ingresar una opcion correcta");
                    control = false;
                } else {
                    String rta;
                    if (opcion == 1) {
                        System.out.println("Ingrese un id para el avion");
                        rta = scan.next();
                        AirBus Av1 = new AirBus(rta);
                        nuevoVuelo.setAv(Av1);
                    }
                    if (opcion == 2) {
                        System.out.println("Ingrese un id para el avion");
                        rta = scan.next();
                        SmallBird Av2 = new SmallBird(rta);
                        nuevoVuelo.setAv(Av2);
                    }
                    aux.add(nuevoVuelo);
                }

            } catch (InputMismatchException i) {
                System.out.println("Debe ingresar una opcion correcta");
                scan.nextLine();
                control = false;
            }
        }while(!control);
    }
}
