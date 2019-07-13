
/*
 * Excepcion cuando se intenta crear un nombre repetido
 * Recibe el nombre que ya es existente en el constructor
 */
public class NombreDuplicadoException extends Exception{

    private final String _nombre;

    NombreDuplicadoException(String nombre){
        _nombre = nombre;
    }

    public String toString(){
        return "Ya existe un personaje con el nombre: " + _nombre;
    }
}