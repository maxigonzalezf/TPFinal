import Aeropuerto.*;
import Usuarios.ListaUsuarios;
import Usuarios.UAdmin;
import Usuarios.UNormal;
import Usuarios.Usuario;

import java.util.*;

/**
 * PROYECTO FINAL
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */
public class Main {

    static Scanner scan;

    public static void main(String[] args) {
        scan = new Scanner(System.in);

        ListaDeVuelos lista = new ListaDeVuelos();

        ListaUsuarios usuarios = new ListaUsuarios();

        adminHardcodeado(usuarios);

        menuPrincipal(usuarios, lista);

    }

    /**
     * Crea un administrador (para testeo de aplicacion)
     * @param usuarios del tipo ListaUsuarios
     * @see Usuario
     * @see UAdmin
     * @see ListaUsuarios
     */
    public static void adminHardcodeado(ListaUsuarios usuarios) {

        Usuario admin = new UAdmin();
        admin.setNombreUsuario("maxi.gf");
        admin.setContrasena("123maxi");
        usuarios.agregarUsuario(admin);
    }

    /**
     * Menu principal de la aplicacion
     * @param usuarios del tipo ListaUsuarios
     * @param lista del tipo ListaDeVuelos
     * @see ListaUsuarios
     * @see ListaDeVuelos
     */
    public static void menuPrincipal(ListaUsuarios usuarios, ListaDeVuelos lista) {
        int opcion = 0;

        do {
            try {
                System.out.println("\n-------------------MENU PRINCIPAL-----------------");
                System.out.println("1. Ingresar");
                System.out.println("2. Registrarse");
                System.out.println("3. Administrador");
                System.out.println("4. Salir\n");

                System.out.println("Ingrese una opcion: ");
                opcion = scan.nextInt();
            } catch (InputMismatchException i) {

                System.out.println("Opcion incorrecta");
                scan.nextLine();
            }

            switch (opcion) {

                case 1:
                    menuUsuario(usuarios, lista);
                    break;

                case 2:
                    usuarios.superRegistro();
                    break;

                case 3:
                    menuAdmin(usuarios, lista);
                    break;

                case 4:
                    System.out.println("Hasta pronto!");
                    break;
            }

        } while (opcion != 4);
    }

    /**
     * Menu de Administradores
     * @param usuarios del tipo ListaUsuarios
     * @param lista del tipo ListaDeVuelos
     * @see ListaDeVuelos
     * @see ListaUsuarios
     */
    public static void menuAdmin(ListaUsuarios usuarios, ListaDeVuelos lista) {
        int opcion = 0;
        String nombre = usuarios.superLoginAdmin();

        if (!(nombre.equalsIgnoreCase("no inicio sesion"))) {
            do {
                try {
                    System.out.println("\n--------------------MENU ADMINISTRADOR-----------------------");
                    System.out.println("\n1. Mostrar lista de vuelos");
                    System.out.println("2. Agregar un Vuelo");//AGREGAR UN VUELO
                    System.out.println("3. Borrar un vuelo");
                    System.out.println("4. Mostrar lista de usuarios");
                    System.out.println("5. Borrar un usuario");
                    System.out.println("6. Cerrar sesion\n");

                    System.out.println("Ingrese una opcion: ");
                    opcion = scan.nextInt();
                } catch (InputMismatchException i) {

                    System.out.println("Opcion incorrecta");
                    scan.nextLine();
                }

                switch (opcion) {

                    case 1:
                        lista.mostrarVuelos();
                        break;

                    case 2:
                        if(usuarios.getUsuario(nombre) instanceof UAdmin)
                            ((UAdmin) usuarios.getUsuario(nombre)).agregarVuelo(lista);
                        break;

                    case 3:
                        if(usuarios.getUsuario(nombre) instanceof UAdmin)
                            ((UAdmin) usuarios.getUsuario(nombre)).eliminarVuelo(lista);
                        break;

                    case 4:
                        usuarios.mostrarListaUsuarios();
                        break;

                    case 5:
                        System.out.println("Ingrese el usuario que desea eliminar: ");
                        boolean remove = usuarios.eliminarUsuario(scan.next());
                        if (remove)
                            System.out.println("El usuario ha sido eliminado");
                        else
                            System.out.println("No se ha encontrado el usuario solicitado");
                        break;

                    case 6:
                        break;
                }

            } while (opcion != 6);
        }
    }

    /**
     * Menu de usuarios finales
     * @param usuarios del tipo ListaUsuarios
     * @param lista del tipo ListaDeVuelos
     * @see ListaUsuarios
     * @see ListaDeVuelos
     */
    public static void menuUsuario(ListaUsuarios usuarios, ListaDeVuelos lista) {
        int opcion = 0;
        String nombre = usuarios.superLoginUsuario();

        if (!(nombre.equalsIgnoreCase("no inicio sesion"))) {
            do {
                try {
                    System.out.println("\n---------------------------MENU USUARIO--------------------------");
                    System.out.println("1. Ver vuelos");
                    System.out.println("2. Reservar asiento");
                    System.out.println("3. Ver reservas");
                    System.out.println("4. Cancelar una reserva");
                    System.out.println("5. Premium");
                    System.out.println("6. Cerrar sesion\n");

                    System.out.println("Ingrese una opcion: ");
                    opcion = scan.nextInt();
                } catch (InputMismatchException i) {

                    System.out.println("Opcion incorrecta");
                    scan.nextLine();
                }

                switch (opcion) {

                    case 1:
                        lista.mostrarVuelos();
                        break;

                    case 2:
                        if (usuarios.getUsuario(nombre) instanceof UNormal)
                            ((UNormal) usuarios.getUsuario(nombre)).elegirAvion(lista);
                        break;

                    case 3:
                        if (usuarios.getUsuario(nombre) instanceof UNormal)
                            ((UNormal) usuarios.getUsuario(nombre)).getListaDeReservas();
                        break;

                    case 4:
                        if (usuarios.getUsuario(nombre) instanceof UNormal) {
                            ((UNormal) usuarios.getUsuario(nombre)).cancelarReserva();
                        }
                        break;

                    case 5:
                        menuPremium((UNormal) usuarios.getUsuario(nombre));
                        break;

                    case 6:
                        break;
                }

            } while (opcion != 6);
        }
    }

    /**
     * Menu para convertirse en usuario Premium
     * @param usuario del tipo UNormal
     * @see Usuario
     * @see UNormal
     */
    public static void menuPremium(UNormal usuario) {
        boolean control = true;
        int opc;
        System.out.println("¿Estas pensando en viajar varias veces en un año? ¿Queres viajar con una mejor calidad y a un mejor precio?");
        System.out.println("¡Hacete Premium!");
        System.out.println("\nEl sistema Premium es una suscripcion anual en la que gozaras de varios beneficios tales como: Internet a maxima velocidad,");
        System.out.print("un mejor menu durante el vuelo, entrar primero al avion, y un descuento de 15% en todos los vuelos que compres!!!");
        System.out.println("El valor de la suscripcion es de: ????");
        System.out.println("¿Desea hacerse Usuario Premium?\n");
        System.out.println("1.SI                       2.NO");
        do {
            try {
                opc = scan.nextInt();
                if (opc != 1 && opc != 2)
                    control = false;
                else {
                    if (opc == 1) {
                        System.out.println("Se le cobraran ???? de su cuenta bancaria, ¿Desea continuar?");
                        System.out.println("1.SI         2.NO");
                        opc = scan.nextInt();
                        if(opc != 1 && opc != 2)
                            control = false;
                        else {
                            if (opc == 1) {
                                System.out.println("Felicitaciones, usted es ahora mismo un Usuario Premium");
                                usuario.setPremium(true); //CAMBIAR EL VALOR DE PREMIUM A VERDADERO
                            } else
                                System.out.println("¡Es una lastima!");
                        }
                    } else
                        System.out.println("No se ha cambiado a Premium");
                }
            }catch(InputMismatchException i){
                    System.out.println("Debe ingresar una opcion valida.");
                    control = false;
                }
        }while(!control);
    }
}




