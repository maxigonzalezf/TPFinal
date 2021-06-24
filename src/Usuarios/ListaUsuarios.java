package Usuarios;

import Exceptions.*;

import java.util.Vector;
import java.util.Scanner;

/**
 * Clase que contiene la lista de los usuarios de la aplicacion (administradores y finales)
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public class ListaUsuarios {
Scanner scan;
int CARACTERES_USUARIO = 6;
int CARACTERES_CONTRASENA = 6;
    private Vector<Usuario> lista;

    public ListaUsuarios() {
        lista = new Vector<>();
        scan = new Scanner(System.in);
    }

    /**
     * El usuario ingresa nombre y contraseña para crearse un usuario (valga la redundancia) nuevo en la aplicacion
     * @return Usuario nuevo
     * @throws UsuarioInvalidoException
     * @throws UsuarioExistenteException
     * @throws NullPointerException
     * @throws ContrasenaInvalidaException
     * @throws NivelException
     * @throws CaracterEspecialException
     * @throws ContrasenaDebilException
     *
     * @see ListaUsuarios#superRegistro()
     */
    public Usuario registroUsuario() throws UsuarioInvalidoException, UsuarioExistenteException, NullPointerException,
                                            ContrasenaInvalidaException, NivelException, CaracterEspecialException,
                                            ContrasenaDebilException {

        String nombreUsuario;
        String contrasena;

        System.out.println("Ingrese su usuario (6 caracteres como minimo, solo se permiten letras, numeros y punto(.)): ");
        nombreUsuario = scan.nextLine();
        if (nombreUsuario.isEmpty()) throw new UsuarioInvalidoException("Usuario invalido");
        if (existeUsuario(nombreUsuario)) throw new UsuarioExistenteException("El usuario ya existe");
        if (nombreUsuario.length() < CARACTERES_USUARIO) throw new UsuarioInvalidoException("Debe contener 6 caracteres como minimo.");
        if (nombreUsuario == null) throw new NullPointerException("Debe enviar algo...");
        for(int i=0;i<nombreUsuario.length();i++) {
            if(nombreUsuario.charAt(i) > 31 && nombreUsuario.charAt(i) < 46
                    || nombreUsuario.charAt(i) == 47
                    || nombreUsuario.charAt(i) > 57 && nombreUsuario.charAt(i) < 65
                    || nombreUsuario.charAt(i) > 90 && nombreUsuario.charAt(i) < 97
                    || nombreUsuario.charAt(i) > 122 && nombreUsuario.charAt(i) < 209
                    || nombreUsuario.charAt(i) > 209 && nombreUsuario.charAt(i) < 241
                    || nombreUsuario.charAt(i) > 241
                    || Character.isSpaceChar(nombreUsuario.charAt(i))) throw new CaracterEspecialException("No puede contener caracteres especiales.");
        }

        System.out.println("Ingrese su contraseña (6 caracteres como minimo, con al menos 1 numero y 1 mayuscula): ");
        contrasena = scan.nextLine();
        if (contrasena == null) throw new NullPointerException("Debe enviar algo...");
        if (contrasena.isEmpty()) throw new ContrasenaInvalidaException("Contraseña vacia");
        for(int i=0;i<contrasena.length();i++) {
            if (Character.isSpaceChar(contrasena.charAt(i)))
                throw new CaracterEspecialException("No puede contener espacios");
        }

        int numeros = 0, mayusculas = 0;
        for(int i=0;i<contrasena.length();i++) {
            if ((contrasena.charAt(i) > 47 && contrasena.charAt(i) < 58))
                numeros++;
            else if((contrasena.charAt(i) > 64 && contrasena.charAt(i) < 91))
                mayusculas++;
        }

        if(numeros < 1 || mayusculas < 1 || contrasena.length() < CARACTERES_CONTRASENA) throw new ContrasenaDebilException("Contraseña debil.");

        Usuario usuario = new UNormal();
        usuario.setNombreUsuario(nombreUsuario.toLowerCase());
        usuario.setContrasena(contrasena);

        return usuario;

    }

    /**
     * Registra el usuario nuevo en la aplicacion
     * @see ListaUsuarios#registroUsuario()
     */
    public void superRegistro() {

        Usuario nuevo;
        boolean reg;

        do {
            reg = true;
            try {
                nuevo = registroUsuario();
                lista.add(nuevo);
                System.out.println("Registro exitoso!");
            } catch (UsuarioInvalidoException u) {

                reg = false;
                System.out.println("Usuario invalido. Debe contener 6 caracteres como minimo.");

            } catch (UsuarioExistenteException e) {

                reg = false;
                System.out.println("El usuario ya existe. Intente con uno nuevo");

            } catch (ContrasenaInvalidaException c) {

                reg = false;
                System.out.println("Contraseña invalida.");

            } catch (ContrasenaDebilException d) {

                reg = false;
                System.out.println("Contraseña muy debil. Recuerde colocar al menos 1 mayuscula y 1 numero.");

            } catch (NullPointerException n) {

                reg = false;
                System.out.println("Debe enviar algo...");

            } catch (NivelException a) {

                reg = false;
                System.out.println("Elija una opcion valida.");
            } catch (CaracterEspecialException s) {

                reg = false;
                System.out.println("El usuario o contraseña no puede contener caracteres especiales ni espacios");
            }
        }while(!reg);

    }

    /**
     * Determina si ya existe un usuario con el nombre solicitado
     * @param nombreUsuario del tipo String
     * @return boolean - true si ya existe el usuario; false si no existe
     */
    public boolean existeUsuario(String nombreUsuario) {
        boolean rta = false;

        for(int i=0; i<lista.size(); i++) {
            if (lista.get(i).getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                rta = true;
                break;
            }
        }

        return rta;
    }

    /**
     * Loguea en la aplicacion al usuario
     * @return String - nombre del usuario si inicio sesion; "no inicio sesion" si no lo hizo
     * @see ListaUsuarios#login()
     */
    public String superLoginUsuario() {
        int intentos=3;
        boolean continuar = false;
        boolean menu=false;
        String nombre = "";

        do {
            try {
                nombre = login();
                if(getUsuario(nombre) instanceof UNormal) {
                    System.out.println("\nBienvenido "+getUsuario(nombre).getNombreUsuario()+" !");
                    continuar = true;
                    menu = true;
                }
                else
                {
                    intentos--;
                    System.out.println("Usuario inexistente. Le quedan " + intentos + " intentos.");
                }
            } catch (UsuarioInvalidoException e) {
                intentos--;
                System.out.println("Usuario inexistente. Le quedan " + intentos + " intentos.");

            } catch (NullPointerException n) {
                intentos--;
                System.out.println("Debe enviar algo... Le quedan "+ intentos + " intentos.");

            } catch (CaracterEspecialException f) {
                intentos--;
                System.out.println("El usuario o contraseña no puede contener caracteres especiales ni espacios." +
                        " Le quedan " + intentos + " intentos.");

            } catch (ContrasenaInvalidaException c) {
                intentos--;
                System.out.println("Contraseña incorrecta. Le quedan " + intentos + " intentos.");

            } finally {

                if(intentos == 0)
                    menu = true;
            }
        }while(!menu);

        if(intentos==0) {
            System.out.println("Olvido su contraseña? s/n");
            char rta = scan.nextLine().toLowerCase().charAt(0);
            if (rta == 's') {
                System.out.println("Ingrese su usuario: ");
                String nombre2 = scan.nextLine();
                if(existeUsuario(nombre2))
                    System.out.println("Le enviamos su nueva contraseña al correo electronico");
                else {
                    System.out.println("El usuario no existe. Desea registrarse? s/n");
                    char a = scan.nextLine().toLowerCase().charAt(0);
                    if (a == 's')
                        superRegistro();
                }
            } else {
                System.out.println("Desea registrarse? s/n");
                char b = scan.nextLine().toLowerCase().charAt(0);
                if (b == 's')
                    superRegistro();
            }
        }

        if(continuar)
            return nombre;
        else
            return "no inicio sesion";
    }

    /**
     * Loguea en la aplicacion al administrador
     * @return String - nombre del administrador si inicio sesion; "no inicio sesion" si no lo hizo
     * @see ListaUsuarios#login()
     */
    public String superLoginAdmin() {
        int intentos=3;

        boolean menu=false;
        boolean continuar=false;
        String nombre = "";

        do {
            try {
                nombre = login();
                if(getUsuario(nombre) instanceof UAdmin) {
                    System.out.println("\nBienvenido Administrador");
                    continuar = true;
                    menu = true;
                }
                else {
                    intentos--;
                    System.out.println("Administrador inexistente. Le quedan " + intentos + " intentos.");
                }
            } catch (UsuarioInvalidoException e) {
                intentos--;
                System.out.println("Usuario inexistente. Le quedan " + intentos + " intentos.");

            } catch (NullPointerException n) {
                intentos--;
                System.out.println("Debe enviar algo... Le quedan "+ intentos + " intentos.");

            } catch (CaracterEspecialException f) {
                intentos--;
                System.out.println("El usuario o contraseña no puede contener caracteres especiales ni espacios. Le quedan " + intentos + " intentos.");
            }
            catch (ContrasenaInvalidaException c) {
                intentos--;
                System.out.println("Contraseña incorrecta. Le quedan " + intentos + " intentos.");

            } finally {

                if(intentos == 0)
                    menu = true;
            }
        }while(!menu);

        if(continuar)
            return nombre;
        else
            return "no inicio sesion";

    }

    /**
     * Solicita nombre de usuario y contraseña
     * @return String - nombre del usuario
     * @throws UsuarioInvalidoException
     * @throws NullPointerException
     * @throws ContrasenaInvalidaException
     * @throws CaracterEspecialException
     * @see ListaUsuarios#superLoginUsuario()
     * @see ListaUsuarios#superLoginAdmin()
     */
    public String login() throws UsuarioInvalidoException, NullPointerException, ContrasenaInvalidaException,
                                    CaracterEspecialException {

        String usuario;
        String contrasena;

        System.out.println("Ingrese su usuario: ");
        usuario = scan.nextLine();
        if(!existeUsuario(usuario)) throw new UsuarioInvalidoException("Usuario invalido");
        if(usuario == null) throw new NullPointerException("Debe enviar algo...");
        for(int i=0;i<usuario.length();i++) {
            if (usuario.charAt(i) > 31 && usuario.charAt(i) < 46
                    || usuario.charAt(i) == 47
                    || usuario.charAt(i) > 57 && usuario.charAt(i) < 65
                    || usuario.charAt(i) > 90 && usuario.charAt(i) < 97
                    || usuario.charAt(i) > 122 && usuario.charAt(i) < 209
                    || usuario.charAt(i) > 209 && usuario.charAt(i) < 241
                    || usuario.charAt(i) > 241 || Character.isSpaceChar(usuario.charAt(i)))
                throw new CaracterEspecialException("No puede contener caracteres especiales ni espacios");
        }

        System.out.println("Ingrese su contraseña: ");
        contrasena = scan.nextLine();
        if(!compruebaLogin(usuario,contrasena)) throw new ContrasenaInvalidaException("Contraseña invalida");
        if(contrasena == null) throw new NullPointerException("Debe enviar algo...");
        for(int i=0;i<contrasena.length();i++) {
            if (Character.isSpaceChar(contrasena.charAt(i)))
                throw new CaracterEspecialException("No puede contener espacios");
        }
        return usuario;

    }

    /**
     * Comprueba si el nombre de usuario y contraseña coinciden con las de algun usuario registrado
     * @param nombreUsuario del tipo String
     * @param contrasena del tipo String
     * @return boolean - true si coinciden; false si no coinciden
     */
    public boolean compruebaLogin(String nombreUsuario, String contrasena) {
        boolean rta = false;

        for(int i=0; i<lista.size(); i++) {
            if (lista.get(i).getNombreUsuario().equalsIgnoreCase(nombreUsuario) && lista.get(i).getContrasena().equalsIgnoreCase(contrasena)) {
                rta = true;
                break;
            }
        }

        return rta;
    }

    /**
     * Elimina un usuario de la lista
     * @param nombreUsuario del tipo String
     * @return boolean - true si se elimino el usuario; false si no se encontro el usuario solicitado
     */
    public boolean eliminarUsuario(String nombreUsuario) {
        boolean control=false;
        for(int i=0; i<lista.size(); i++) {
            if (lista.get(i).getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                lista.removeElementAt(i);
                control = true;
            }
        }

        return control;
    }

    /**
     * Muestra la lista de usuarios finales
     */
    public void mostrarListaUsuarios() {
        int pos = 1;
        System.out.println("--------------------LISTA USUARIOS---------------------");
        if(lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i) instanceof UNormal) {
                    System.out.println(pos + ". " + lista.get(i).getNombreUsuario());
                    pos++;
                }
            }
        } else
            System.out.println("No hay usuarios");

    }

    /**
     * Muestra la lista de administradores
     */
    public void mostrarListaAdmins() {
        int pos = 1;
        System.out.println("--------------------LISTA ADMINISTRADORES---------------------");
        if(lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i) instanceof UAdmin) {
                    System.out.println(pos + ". " + lista.get(i).getNombreUsuario());
                    pos++;
                }
            }
        } else
            System.out.println("No hay administradores");
    }

    /**
     *
     * @return int - cantidad de usuarios (finales y administradores)
     */
    public int cantidadUsuarios() {
        return lista.size();
    }

    /**
     * Devuelve el usuario que tenga el nombre pasado por parametro
     * @param nombreUsuario del tipo String
     * @return Usuario - usuario si lo encuentra; null si no lo encuentra
     */
    public Usuario getUsuario(String nombreUsuario) {
        Usuario usuario = null;
        for(int i=0; i<lista.size(); i++) {
            if (lista.get(i).getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                usuario = lista.get(i);
                break;
            }
        }

        return usuario;
    }

    /**
     * Agrega un usuario a la lista
     * @param usuario del tipo Usuario
     */
    public void agregarUsuario(Usuario usuario) {
        lista.add(usuario);
    }
}
