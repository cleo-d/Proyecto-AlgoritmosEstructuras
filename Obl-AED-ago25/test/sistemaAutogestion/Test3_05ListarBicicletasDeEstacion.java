/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaAutogestion;

import dominio.Bicicleta;
import dominio.Estacion;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author cletus
 */
public class Test3_05ListarBicicletasDeEstacion {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();

        //Creo una estacion
        Estacion e1 = new Estacion("Estacion01", "Barrio", 5);
        Estacion e2 = new Estacion("Estacion02", "Barrio", 5);

        //Creo bicicletas
        Bicicleta b1 = new Bicicleta("41235", "URBANA");
        Bicicleta b2 = new Bicicleta("12345", "URBANA");
        Bicicleta b3 = new Bicicleta("21345", "URBANA");
        Bicicleta b4 = new Bicicleta("31245", "URBANA");
        Bicicleta b5 = new Bicicleta("51234", "URBANA");

        //Agrego bicicletas a la estacion
        e1.agregarBicicleta(b1);
        e1.agregarBicicleta(b2);
        e1.agregarBicicleta(b3);
        e1.agregarBicicleta(b4);
        e1.agregarBicicleta(b5);

        //Agrego la estacion a Sistema
        ((Sistema) s).agregarEstacion(e1);
        ((Sistema) s).agregarEstacion(e2);
    }

    @Test
    public void listasBicicletasEnEstacionOk() {

        retorno = s.listarBicicletasDeEstacion("Estacion01");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("12345|21345|31245|41235|51234", retorno.getValorString());
        
    }
    
        @Test
    public void listasBicicletasEnEstacionVacia() {

        retorno = s.listarBicicletasDeEstacion("Estacion02");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("La estacion no tiene Bicicletas ancladas", retorno.getValorString());
        
    }
}
