
import java.lang.Integer;

/*
 * Representa una posicion determinada por
 * el eje x (Horizontal) y el eje y (Vertical)
 */
public class Posicion {
    Posicion(Integer initPosx, Integer initPosy){
        _posx = initPosx;
        _posy = initPosy;
    }
    Posicion(){
        _posx = 0;
        _posy = 0;
    }
    public void setPosicion(Integer posx, Integer posy) throws IllegalArgumentException{
        /*
         * Si la posicion es un valor valido (positivo) cambia el valor
         * de lo contrario genera una excepcion comprobada del tipo IllegalArgumentException
         * con el mensaje: "Posicion Ilegal"
         */
        if(posx < 0 || posy < 0){
            throw new IllegalArgumentException("Posicion Ilegal");
        }
        else{
            _posx = posx;
            _posy = posy;
        }
    }

    /**
     * Calcula la nueva posicion en base a la posicion actual
     * y la direccion de entrada
     * Tener en cuenta que la direccion va a afectar la posicion
     * en X o en Y de la siguiente forma:
     * Arriba: y+1
     * Abajo: y-1
     * Derecha: x+1
     * Izquierda: x-1
     * @param d Direccion a ser evaluada
     * @return la nueva posicion tentativa (sin moficiar al objeto)
     */
    public final Posicion nuevaPosicion(Direccion d) {
        Posicion pos = new Posicion(getX(), getY());
        switch(d){
            case ARRIBA:
                pos.setPosicion(getX(), getY()+1);
                break;
            case ABAJO:
                pos.setPosicion(getX(), getY()-1);
                break;
            case DERECHA:
                pos.setPosicion(getX()+1, getY());
                break;
            case IZQUIERDA:
                pos.setPosicion(getX()-1, getY());
                break;
            default:
                break;
        }
        return pos;
    }

    public Integer getX(){
        return _posx;
    }

    public Integer getY(){
        return _posy;
    }

    private Integer _posx, _posy;
}
