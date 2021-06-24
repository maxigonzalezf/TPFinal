package Usuarios;

/**
 * Clase abstracta de la que heredan las clases UAdmin (administradores) y UNormal (usuarios finales)
 * @author Maxi Gonzalez
 * @author Ignacio Rios
 */

public abstract class Usuario {

    public Usuario(){}

    public abstract String getNombreUsuario();

    public abstract String getContrasena();

    public abstract void setNombreUsuario(String nombreUsuario);

    public abstract void setContrasena(String contrasena);
}
