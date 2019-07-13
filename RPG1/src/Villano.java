import java.util.*;











class Villano extends Personaje implements Luchador {

    private static final int ATAQUE = 20;
    private static final int ENERGIA = 500;
    private static final int INCREMENTO_ENERGIA = 20;
    private static final int INCREMENTO_ATAQUE = 5;
    /**
     * Crea un villana con el nombre pasado como argumento y los niveles
     * de energia y ataque por defecto
     * @param nombre Nombre del villano
     * en algunn otro personaje
     */
    Villano( String nombre) {

        super(ATAQUE,ENERGIA,nombre);

        // Implementar
    }

    /**
     * Aumenta tanto la vida como el ataque del villano
     * en el factor especificado por la clase (INCREMENTO_VIDA, INCREMENTO_ATAQUE)
     */
    @Override
    public void subirNivel() { //PROBADO

        super.incrementarAtaque(INCREMENTO_ATAQUE);
        super.incrementarEnergia(INCREMENTO_ENERGIA);
    }

    /**
     * El villano no se mueve en el tablero, su posicion es fija
     */
    @Override
    public void mover() {

    }

    /**
     * El villano ataca a todos los objetos que atacables que estan a
     * su vista con su valor de ataque
     * AYUDA: Metodo obtenerPersonajesCercanos de la clase mapa
     * Tener en cuenta que ese Personaje tiene que ser del tipo Atacable
     */
    @Override
    public void atacar() { //PROBADO, NO CUANDO EL HERROE ESTA LEJOS


        List<Personaje> personajesCercanos = mapa.obtenerPersonajesCercanos(this);

        Heroe h = new Heroe("h",Faccion.EQUIPO_ROJO);
        for(Personaje personajeCercano : personajesCercanos)


            if(h.getClass().isInstance(personajeCercano))
            {
                Heroe enemigo = (Heroe) personajeCercano;
                enemigo.recibirGolpe(this.getAtaque());


            }
    }




    /**
     * Decrementa la vida del villano por la mitad del valor recibido.
     */
    @Override
    public void recibirGolpe(int danio) {

        super.incrementarEnergia(-danio/2);


    }

}