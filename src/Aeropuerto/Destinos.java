package Aeropuerto;

/**
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public enum Destinos {
    BUE("Buenos Aires"),
    MAD("Madrid"),
    TOK("Tokyo"),
    SID("Sidney"),
    MIA("Miami");

    private String nombre;

    Destinos(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
