
public class Neutro extends Personaje{

    private static final int ATAQUE = 0;
    private static final int ENERGIA = 200;

    /**
     * Crea un nuevo personaje Neutro
     * @param mapa Mapa al que esta asociado
     * @param nombre Nombre del personaje
     * @throws NombreDuplicadoException Si el nombre ya existe
     */
    Neutro(String nombre) {
        super(ATAQUE, ENERGIA, nombre);
    }


    /**
     * Los neutros no hacen nada al subir de nivel
     */
    @Override
    public void subirNivel() {

    }

    /**
     * Intenta mover aleatoriamente, despues de 3 intentos
     * desiste de moverse
     */
    @Override
    public void mover() {
        int direccion = (int) Math.floor(Math.random() * 4.);
        Direccion []direcciones = Direccion.values();
        int intentos = 0;
        while(!mapa.moverPersonajeEnDireccion(this, direcciones[direccion])){
            intentos += 1;
            if(intentos == 3){
                return;
            }
            direccion = (int) Math.floor(Math.random() * 4.);
        }
    }

}
