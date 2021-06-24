package Aeropuerto;

import java.util.*;

/**
 * Clase que contiene la lista de los vuelos disponibles
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public class ListaDeVuelos {
    Scanner scan;
    HashMap<String, ArrayList<Vuelos>> vuelosDisponibles;

    public ListaDeVuelos() {
        this.vuelosDisponibles = new HashMap<>();
        scan = new Scanner(System.in);
        cargarVuelosHardcodeados();
    }

    /**
     * Carga inicial de vuelos (para testeo de la aplicacion)
     * @see ListaDeVuelos#crearArregloVuelos(Destinos, Destinos)
     * @see Destinos
     */
    private void cargarVuelosHardcodeados(){

       this.vuelosDisponibles.put(Destinos.BUE +" - "+ Destinos.MAD, crearArregloVuelos(Destinos.BUE, Destinos.MAD));
       this.vuelosDisponibles.put(Destinos.BUE +" - "+ Destinos.MIA, crearArregloVuelos(Destinos.BUE, Destinos.MIA));
       this.vuelosDisponibles.put(Destinos.BUE +" - "+ Destinos.SID, crearArregloVuelos(Destinos.BUE, Destinos.SID));
       this.vuelosDisponibles.put(Destinos.BUE +" - "+ Destinos.TOK, crearArregloVuelos(Destinos.BUE, Destinos.TOK));
    }

    /**
     * Carga inicial de vuelos (para testeos de la aplicacion)
     * @param ori del tipo Destinos (desde donde sale el vuelo)
     * @param des del tipo Destinos (a donde se dirige el vuelo)
     * @return ArrayList de vuelos
     * @see ListaDeVuelos#cargarVuelosHardcodeados()
     */
    private ArrayList<Vuelos> crearArregloVuelos(Destinos ori, Destinos des){
        ArrayList<Vuelos> aux=new ArrayList<Vuelos>();
        AirBus A2=new AirBus("105ER");
        SmallBird A1=new SmallBird("106IT");
        aux.add(new Vuelos(A1, ori, des, new Date(2021,03,05,16,30), new Date(2021,3,5,22,30), 0, 1600));
        aux.add(new Vuelos(A2, ori, des, new Date(2021,07,07,15,27), new Date(2021,7, 7,22,21), 0, 1200));
        return aux;
    }

    /**
     * Retorna la lista de las distintas opciones de Origen-Destino
     * @return Set<String>
     *
     */
    public Set<String> getListaOriDes(){
        return this.vuelosDisponibles.keySet();
    }

    /**
     * Retorna la cantidad de opciones de Origen-Destino
     * @return int
     */
    public int cantidadOpcionesOriDes() {
        return vuelosDisponibles.size();
    }

    /**
     * Retorna la lista de vuelos disponibles de la ruta solicitada
     * @param clave del tipo String (Origen-Destino)
     * @return ArrayList<Vuelos>
     */
    public ArrayList<Vuelos> getListaVuelos(String clave){
        return this.vuelosDisponibles.get(clave);
    }

    /**
     * Retorna la cantidad de vuelos disponibles de la ruta solicitada
     * @param clave del tipo String (Origen-Destino)
     * @return int
     */
    public int cantidadVuelos(String clave) {
        return vuelosDisponibles.get(clave).size();
    }

    /**
     * Retorna el vuelo del Origen-Destino solicitado, en la posicion solicitada
     * @param clave del tipo String (Origen-Destino)
     * @param index del tipo int (posicion)
     * @return Vuelos - vuelo solicitado
     * @see ListaDeVuelos#getListaVuelos(String)
     */
    public Vuelos getVuelo(String clave, int index){
        return this.vuelosDisponibles.get(clave).get(index);
    }

    /**
     * Elimina el vuelo solicitado
     * @param opcion del tipo int (para elegir Origen-Destino)
     * @param opcion2 del tipo int (para elegir posicion en la lista de los vuelos de la ruta solicitada)
     * @see ListaDeVuelos#getListaVuelos(String)
     * @see ListaDeVuelos#getListaOriDes()
     */
    public void borrarVuelo(int opcion, int opcion2) {
        this.getListaVuelos(this.getListaOriDes().toArray()[opcion].toString()).remove(opcion2);
    }

    /**
     * Muestra la lista de rutas disponibles
     */
    public void imprimir() {
        int i = 1;
        Iterator<String> listaorigenDestino = vuelosDisponibles.keySet().iterator();
        while (listaorigenDestino.hasNext()) {
            System.out.println(i + " - " + listaorigenDestino.next());
            i++;
        }
    }

    /**
     * Muestra la lista de los vuelos disponibles en la ruta solicitada
     * @param origenDestino del tipo String
     * @see ListaDeVuelos#getListaVuelos(String) 
     */
    public void mostrarVuelosDisponibles(String origenDestino) {
        Iterator<Vuelos> listaVuelos = this.getListaVuelos(origenDestino).iterator();
        int i = 1;
        while (listaVuelos.hasNext()) {
            System.out.println(i + " - " + listaVuelos.next());
            i++;
        }
    }

    /**
     * Le pide al usuario que elija una ruta determinada y llama a la funcion que muestra los vuelos de dicha ruta
     * @see ListaDeVuelos#imprimir() 
     * @see ListaDeVuelos#mostrarVuelosDisponibles(String)
     */
    public void mostrarVuelos() {
        this.imprimir();
        boolean control;
        int opcion;
        do {
            control = true;
            try {
                System.out.println("Ingrese una opcion: ");
                opcion = scan.nextInt() - 1;
                if(opcion > vuelosDisponibles.size() || opcion < 0)
                    control = false;
                else
                    this.mostrarVuelosDisponibles(this.getListaOriDes().toArray()[opcion].toString());
            } catch (InputMismatchException i) {
                System.out.println("Debe ingresar una opcion valida.");
                scan.nextLine();
                control = false;
            }
        }while(!control);
    }
}
