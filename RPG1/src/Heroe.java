import java.util.*;



public class Heroe extends Personaje implements Luchador {

    private static final int ATAQUE = 20;
    private static final int ENERGIA = 100;
    private static final int INCREMENTO_ENERGIA = 10;
    private static final int INCREMENTO_ATAQUE = 5;
    private Faccion faccion;



    /**
     * Crea una nueva instancia de Heroe, con el nivel de ataque y energia
     * por defecto (para Heroe)
     * @param nombre Nombre del Heroe
     * @param faccion Faccion a la cual pertenece
     *
     */
    Heroe(String nombre, Faccion faccion)  {
        super(ATAQUE,ENERGIA,nombre);
        this.faccion = faccion;

    }

    /**
     * El heroe solo puede atacar a objetivos cercanos
     * ( ver método obtenerPersonajesCercanos clase Mapa)
     * debe ser un villano o bien un Heroe de una faccion
     * diferente a la del heroe
     * Los danios se hacen mediante el metodo RecibirGolpe
     * con el nivel de ataque propio
     */
    @Override
    public void atacar() {

        List<Personaje> personajesCercanos = mapa.obtenerPersonajesCercanos(this);

        Faccion faccionEnemiga = Faccion.EQUIPO_ROJO;

        if(faccion.equals(Faccion.EQUIPO_ROJO))
        {
            faccionEnemiga=Faccion.EQUIPO_AZUL;
        }


        Villano v=new Villano("");

        for(Personaje p : personajesCercanos)
        {
            if(this.getClass().isInstance(p)) //Si es un heroe
            {

                Heroe e = (Heroe) p;

                if(e.getFaccion().equals(faccionEnemiga))
                {


                    e.recibirGolpe(this.getAtaque());
                }
            }

            if(v.getClass().isInstance(p))
            {
                Villano b = (Villano) p;
                b.recibirGolpe(this.getAtaque());

            }

        }

    }

    /**
     * Disminuye la vida del Heroe por el valor del danio
     */
    @Override
    public void recibirGolpe(int danio) {

        this.incrementarEnergia(-danio);
    }

    /**
     * Aumenta su vida de acuerdo a la variable INCREMENTO_VIDA
     * y su ataque de acuerdo a la variable INCREMENTO_ATAQUE
     */
    @Override
    public void subirNivel() {
        this.incrementarEnergia(INCREMENTO_ENERGIA);
        this.incrementarAtaque(INCREMENTO_ATAQUE);
    }

    /**
     * Mueve UNA VEZ a la primera posicion disponible en el siguiente orden
     * 1. ARRIBA
     * 2. ABAJO
     * 3. DERECHA
     * 4. IZQUIERDA
     * Si ningun movimiento esta disponible el heroe se queda quieto
     * AYUDAS:
     * - Las direcciones se pueden recorrer como una lista en ese
     * oreden utilizando Direccion.values()
     * - Metodo realizarMovimiento de Mapa para comprobar que el
     * movimiento sea legal
     */
    @Override
    public void mover() {

        Direccion[] direcciones = Direccion.values();

        for(Direccion d:direcciones)
        {
            if(mapa.moverPersonajeEnDireccion(this, d))
            {

                return;
            }
        }


    }

    /**
     * Devuelve la faccion del Heroe
     * @return
     */
    public Faccion getFaccion(){
        // COMPLETAR
        return faccion;
    }
}
