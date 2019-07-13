import java.util.*;

public class Mapa{

    private List<Personaje> personajes;
    private final int tamano;
    private final static int DISTANCIA_VISUALIZACION = 5;
    private static final int TAMANO_DEFAULT = 100;

    /**
     * Inicializacion de mapa. Guarda el tamanio
     * e inicializa la lista de personajes
     *
     * @param tamano Se asume un tablero cuadrado del valor recibido
     */
    public Mapa(int tamano){
        if (tamano < 1)
            this.tamano= TAMANO_DEFAULT;
        else
            this.tamano = tamano;
        personajes = new ArrayList<Personaje>();
    }

    /**
     * Agrega un personaje a la lista en caso de que no se encuentre en ella.
     * Si el nombre del personaje ya se encuentra en la lista
     * arroja una excepcion
     *
     * @param p Personaje a ser agregado
     */
    public boolean agregarPersonaje(Personaje p) throws NombreDuplicadoException{
        // Implementar
        if(personajes.contains(p))
        {
            throw new NombreDuplicadoException("El nombre esta duplicado.");
        }

        personajes.add(p);
        return true;
        // no olvide modificar esta linea

    }

    /**
     * Elimina el personaje de la lista en caso de que exista
     * si el personaje no existe en la lista devuelve False,
     * de lo contrario retorna True
     *
     * @param p Personaje a ser eliminado
     *
     */
    public boolean eliminarPersonaje(Personaje p){
        // Implementar
        if(!personajes.contains(p))
        {
            return false;
        }else
        {
            personajes.remove(p);
            return true;
        }
    }

    /**
     * Elimina el personaje de la lista en base a su nombre
     * si el personaje no existe en la lista devuelve False,
     * de lo contrario retorna True
     *
     * @param nombre Nombre del personaje a ser eliminado
     *
     */
    public boolean eliminarPersonaje(String nombre){
        // Implementar
        for( Personaje p: personajes)
        {
            if(p.getNombre().equals(nombre))
            {
                personajes.remove(p);
                return true;
            }
        }

        return false;

    }

    /**
     * @return Devuelve la lista de personajes
     */
    List<Personaje> getPersonajes(){
        return personajes;
    }

    /**
     * Mueve el personaje especificado en la direccion indicada.
     * Si es posible, setea una nueva posicion al personaje
     * Debe cumplirse que:
     * 1. La nueva posicion este dentro de los limites del tablero
     * 2. La nueva posicion este disponible (-> no este ocupada por otro personaje)
     *
     *
     * Ayuda: Utilizar metodos privados de esta misma clase (posicionDisponible, nuevaPosicion -> clase Posicion)
     *
     * @param p el Personaje
     * @param d la Direccion
     * @return true si se pudo efectuar el movimiento, false si no se pudo.
     */
    public boolean moverPersonajeEnDireccion(Personaje p, Direccion d){

        Posicion posicionActual =p.getPosicion();
        Posicion nuevaPosicion;

        try{
            nuevaPosicion = posicionActual.nuevaPosicion(d);
        }catch(IllegalArgumentException e)
        {
            return false;
        }

        if((posicionDisponible(nuevaPosicion))&&((nuevaPosicion.getX().intValue()<=tamano)&&(nuevaPosicion.getY().intValue()<=tamano)))
        {

            p.setPosicion(nuevaPosicion);



            System.out.println("Se movio a "+d);
            return true;

        }else return false;





    }


    /**
     * @param nuevapos posicion tentativa del personaje. No debe haber ningunn personaje en esa posicion
     * @return true si la posicion esta disponible, false si no lo esta
     */
    public boolean posicionDisponible(Posicion nuevapos){
        // Implementar
        for( Personaje p: personajes)
        {
            Posicion posPersonaje = p.getPosicion();

            if((posPersonaje.getX().intValue() == nuevapos.getX().intValue())&&(posPersonaje.getY().intValue() == nuevapos.getY().intValue()))
            {
                return false;
            }



        }

        return true;
    }

    /**
     * Retorna una lista con los personajes que se encuentran cercanos a un personaje
     * El personaje se considera cercano si la distancia entre los dos personajes
     * tanto en x como en y es menor a la distancia de visualizacion
     *
     * AYUDA: Ningun personaje puede estar cerca de si mismo (la lista no debe incluir una referencia a si mismo)
     *
     * @param p el Personaje
     * @return una List<Personaje> con los personajes cercanos
     */
    List<Personaje> obtenerPersonajesCercanos(Personaje p){

        List<Personaje> personajesCercanos = new ArrayList<Personaje>();

        for( Personaje per: personajes)
        {
            int distanciaEnX = (p.getPosicion().getX().intValue())-(per.getPosicion().getX().intValue());
            int distanciaEnY = (p.getPosicion().getY().intValue())-(per.getPosicion().getY().intValue());

            if(distanciaEnX <0) distanciaEnX=distanciaEnX*(-1);
            if(distanciaEnY<0) distanciaEnY = distanciaEnY*(-1);


            if((!p.equals(per))&&((distanciaEnX<DISTANCIA_VISUALIZACION)&&(distanciaEnY<DISTANCIA_VISUALIZACION)))
            {
                personajesCercanos.add(per);


            }

        }
        return personajesCercanos;
    }


    /**
     * Retorna la cantidad de Heroes de una faccion determinada
     * @param f la faccion
     * @return el numero de heroes de esa faccion
     */
    int obtenerNroHeroesDeFaccion (Faccion f) {

        int cont = 0;
        for(Personaje p: personajes)
        {
            Heroe h= new Heroe("",Faccion.EQUIPO_AZUL);

            if(h.getClass().isInstance(p))
            {
                h = (Heroe) p;

                if(h.getFaccion().equals(f))
                {
                    cont++;
                }
            }
        }
        return cont;
    }
}
