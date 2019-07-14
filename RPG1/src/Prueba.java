

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class prueba.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class prueba
{
    Villano v;
    Heroe H_azul1;
    Heroe H_rojo1;
    Heroe H_rojo2;
    Heroe H_rojo2Duplicado;
    Mapa mapa;

    /**
     * Default constructor for test class prueba
     */
    public prueba()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        v= new Villano("villano ");
        H_azul1 = new Heroe("heroe azul 1", Faccion.EQUIPO_AZUL);
        H_rojo1=new Heroe("heroe rojo 1",Faccion.EQUIPO_ROJO);
        H_rojo2 = new Heroe("heroe rojo 2", Faccion.EQUIPO_ROJO);
        H_rojo2Duplicado = new Heroe("heroe rojo 2", Faccion.EQUIPO_ROJO);
        mapa = new Mapa (20);





    }


    @Test
    public void Test_agregar_correcto()
    {

        try{
            assertTrue(H_azul1.setMapa(mapa));
            assertTrue( H_rojo1.setMapa(mapa));
            assertTrue( H_rojo2.setMapa(mapa));
            assertTrue(v.setMapa(mapa));
        }
        catch (NombreDuplicadoException e)
        {
            assertTrue(false);

        }

    }





    @Test
    public void Test_nombre_duplicado()
    {
        try{

            H_rojo2Duplicado.setMapa(mapa);
        }catch(NombreDuplicadoException e)
        {
            assertTrue(true);

        }
    }


    @Test
    public void TestsubirNivel()
    {
        v= new Villano("villano 1");
        v.subirNivel();

        assertEquals(520, v.getEnergia());
        assertEquals(25,v.getAtaque());



    }

    @Test
    public void recibirGolpe_villano()
    {
        v= new Villano("A");
        v.recibirGolpe(20);

        assertEquals(490,v.getEnergia());

    }




    @Test
    public void atacar_villano()
    {

        v= new Villano("B");
        H_azul1 = new Heroe("H", Faccion.EQUIPO_AZUL);

        try{
            v.setMapa(mapa);
            H_azul1.setMapa(mapa);
        } catch (Exception e){}
        assertEquals(100,H_azul1.getEnergia());
        assertEquals(20,v.getAtaque());

        v.atacar();


        assertEquals(80,H_azul1.getEnergia());
    }

    @Test
    public void atacar_villano_NEUTRO()
    {

        v= new Villano("B");
        Neutro n = new Neutro("H");

        try{
            v.setMapa(mapa);
            n.setMapa(mapa);
        } catch (Exception e){}
        assertEquals(200,n.getEnergia());
        assertEquals(20,v.getAtaque());

        v.atacar();


        assertEquals(200,n.getEnergia());
    }

    @Test
    public void mover_Heroe_correcto()
    {
        Heroe h= new Heroe("H1",Faccion.EQUIPO_AZUL);
        try{
            h.setMapa(mapa);
        }catch(Exception e)
        {}

        assertEquals(0,h.getPosicion().getY().intValue());
        assertEquals(0,h.getPosicion().getX().intValue());

        h.mover();
        assertEquals(1,h.getPosicion().getY().intValue());
        assertEquals(0,h.getPosicion().getX().intValue());

        Heroe h1= new Heroe("H1",Faccion.EQUIPO_AZUL);
        try{
            h1.setMapa(mapa);
        }catch(Exception e)
        {}

        assertEquals(0,h1.getPosicion().getY().intValue());
        assertEquals(0,h1.getPosicion().getX().intValue());

        h1.mover();

        assertEquals(0,h1.getPosicion().getY().intValue());
        assertEquals(1,h1.getPosicion().getX().intValue());


    }


    @Test
    public void villano_atacar_heoe_lejano()
    {
        Villano v = new Villano("");
        Heroe h = new Heroe("",Faccion.EQUIPO_AZUL);

        try{
            v.setMapa(mapa);
            h.setMapa(mapa);
        }catch(Exception e)
        {

        }

        h.mover();
        h.mover();
        h.mover();
        h.mover();
        h.mover();
        h.mover();
        h.mover();
        h.mover();
        h.mover();
        h.mover();
        h.mover();
        h.mover();
        v.atacar();

        assertEquals(100, h.getEnergia());


    }

    @Test
    public void heroe_mover_a_lugar_ocupado()
    {
        Heroe h = new Heroe("",Faccion.EQUIPO_AZUL);
        Heroe h1 = new Heroe ("",Faccion.EQUIPO_AZUL);
        try{
            h.setMapa(mapa);
            h1.setMapa(mapa);
        }catch(Exception e){}


        assertEquals(0,h1.getPosicion().getY().intValue());
        assertEquals(0,h1.getPosicion().getX().intValue());
        h.mover();
        h1.mover();

        assertEquals(0,h1.getPosicion().getY().intValue());
        assertEquals(1,h1.getPosicion().getX().intValue());

    }

    @Test
    public void heroe_recibirGolpe()
    {
        Heroe h = new Heroe("",Faccion.EQUIPO_AZUL);
        assertEquals(100,h.getEnergia());
        h.recibirGolpe(20);
        assertEquals(80,h.getEnergia());
    }

    @Test
    public void heroe_atacar_correctamente()
    {
        Heroe h = new Heroe ("",Faccion.EQUIPO_AZUL);
        Heroe h1= new Heroe ( "",Faccion.EQUIPO_ROJO);
        Heroe h2 = new Heroe("",Faccion.EQUIPO_AZUL);
        Villano v = new Villano("");
        Neutro n = new Neutro("");

        try{

            h.setMapa(mapa);
            h1.setMapa(mapa);
            h2.setMapa(mapa);
            v.setMapa(mapa);
            n.setMapa(mapa);

        }catch(Exception e){}

        assertEquals(100,h1.getEnergia());
        assertEquals(100,h2.getEnergia());
        assertEquals(500,v.getEnergia());
        assertEquals(200,n.getEnergia());

        h.atacar();

        assertEquals(80,h1.getEnergia());
        assertEquals(100,h2.getEnergia());
        assertEquals(490,v.getEnergia());
        assertEquals(200,n.getEnergia());

    }

    @Test
    public void eliminar_Personaje()
    {
        Heroe h = new Heroe("h1", Faccion.EQUIPO_AZUL);
        Heroe h1 = new Heroe("h2", Faccion.EQUIPO_AZUL);
        Villano v = new Villano("v");

        try{
            h.setMapa(mapa);
            h1.setMapa(mapa);
        }catch(Exception e)
        { }

        assertEquals(2,mapa.obtenerNroHeroesDeFaccion(Faccion.EQUIPO_AZUL));

        assertTrue(mapa.eliminarPersonaje(h));

        assertEquals(1,mapa.obtenerNroHeroesDeFaccion(Faccion.EQUIPO_AZUL));
        assertFalse(mapa.eliminarPersonaje(v));


    }

    @Test
    public void heroesXFaccion()
    {
        Heroe h = new Heroe("h2",Faccion.EQUIPO_AZUL);
        Villano v = new Villano("holi");

        try{
            h.setMapa(mapa);
            v.setMapa(mapa);
        }catch(Exception e)
        { }

        assertEquals(1,mapa.obtenerNroHeroesDeFaccion(Faccion.EQUIPO_AZUL));
        assertEquals(0,mapa.obtenerNroHeroesDeFaccion(Faccion.EQUIPO_ROJO));

    }

    @Test
    public void eliminarPersonajeXnombre()
    {
        Heroe h = new Heroe("h2",Faccion.EQUIPO_AZUL);
        Villano v = new Villano("holi");

        try{
            h.setMapa(mapa);
            v.setMapa(mapa);
        }catch(Exception e)
        { }
        assertEquals(2,mapa.getPersonajes().size());
        assertFalse(mapa.eliminarPersonaje("belu"));
        mapa.eliminarPersonaje("h2");
        mapa.eliminarPersonaje("holi");
        assertEquals(0,mapa.getPersonajes().size());


    }
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
