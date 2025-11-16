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

public class Test3_07OcupacionPromedioXBarrio {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();

        //Creo 10 estaciones 
        Estacion e1 = new Estacion("Estacion01", "Parque Rodo", 5);
        Estacion e2 = new Estacion("Estacion02", "Aguada", 5);
        Estacion e3 = new Estacion("Estacion01", "Parque Rodo", 5);
        Estacion e4 = new Estacion("Estacion02", "Parque Rodo", 5);
        Estacion e5 = new Estacion("Estacion01", "Aguada", 5);
        Estacion e6 = new Estacion("Estacion02", "Parque Rodo", 5);
        Estacion e7 = new Estacion("Estacion01", "Aguada", 5);
        Estacion e8 = new Estacion("Estacion02", "Palermo", 5);
        Estacion e9 = new Estacion("Estacion01", "Aguada", 5);
        Estacion e10 = new Estacion("Estacion02", "Palermo", 5);

        //Creo las bicicletas
        Bicicleta b1 = new Bicicleta("41235", "URBANA");
        Bicicleta b2 = new Bicicleta("12345", "URBANA");
        Bicicleta b3 = new Bicicleta("21345", "URBANA");
        Bicicleta b4 = new Bicicleta("31245", "URBANA");
        Bicicleta b5 = new Bicicleta("51234", "URBANA");
        Bicicleta b6 = new Bicicleta("24864", "URBANA");
        Bicicleta b7 = new Bicicleta("83575", "URBANA");
        Bicicleta b8 = new Bicicleta("23785", "URBANA");
        Bicicleta b9 = new Bicicleta("23865", "URBANA");
        Bicicleta b10 = new Bicicleta("13586", "URBANA");
        Bicicleta b11 = new Bicicleta("77346", "URBANA");
        Bicicleta b12 = new Bicicleta("23475", "URBANA");
        Bicicleta b13 = new Bicicleta("34724", "URBANA");
        Bicicleta b14 = new Bicicleta("56747", "URBANA");
        Bicicleta b15 = new Bicicleta("98765", "URBANA");
        Bicicleta b16 = new Bicicleta("41235", "URBANA");
        Bicicleta b17 = new Bicicleta("12345", "URBANA");
        Bicicleta b18 = new Bicicleta("21345", "URBANA");
        Bicicleta b19 = new Bicicleta("31245", "URBANA");
        Bicicleta b20 = new Bicicleta("51234", "URBANA");
        Bicicleta b21 = new Bicicleta("24864", "URBANA");
        Bicicleta b22 = new Bicicleta("83575", "URBANA");
        Bicicleta b23 = new Bicicleta("23785", "URBANA");
        Bicicleta b24 = new Bicicleta("23865", "URBANA");
        Bicicleta b25 = new Bicicleta("13586", "URBANA");
        Bicicleta b26 = new Bicicleta("77346", "URBANA");
        Bicicleta b27 = new Bicicleta("23475", "URBANA");
        Bicicleta b28 = new Bicicleta("34724", "URBANA");
        Bicicleta b29 = new Bicicleta("56747", "URBANA");
        Bicicleta b30 = new Bicicleta("98765", "URBANA");

        //Agrego bicicletas a las estaciones
        e1.agregarBicicleta(b1);
        e1.agregarBicicleta(b2);
        e1.agregarBicicleta(b3);

        e2.agregarBicicleta(b3);
        e2.agregarBicicleta(b4);
        e2.agregarBicicleta(b5);
        e2.agregarBicicleta(b6);
        e2.agregarBicicleta(b7);

        e3.agregarBicicleta(b8);
        e3.agregarBicicleta(b9);
        e3.agregarBicicleta(b10);
        e3.agregarBicicleta(b11);

        e4.agregarBicicleta(b12);
        e4.agregarBicicleta(b13);

        e5.agregarBicicleta(b14);
        e5.agregarBicicleta(b15);
        e5.agregarBicicleta(b16);
        
        e6.agregarBicicleta(b17);
        e6.agregarBicicleta(b18);
        e7.agregarBicicleta(b19);
        
        e8.agregarBicicleta(b20);
        e8.agregarBicicleta(b21);
        e8.agregarBicicleta(b22);
        
        e9.agregarBicicleta(b23);
        e9.agregarBicicleta(b24);
        
        e10.agregarBicicleta(b25);
        e10.agregarBicicleta(b26);
        e10.agregarBicicleta(b27);
        e10.agregarBicicleta(b28);
        
        //Agrego las bicicletas a sistema
        ((Sistema) s).agregarEstacion(e1);
        ((Sistema) s).agregarEstacion(e2);
        ((Sistema) s).agregarEstacion(e3);
        ((Sistema) s).agregarEstacion(e4);
        ((Sistema) s).agregarEstacion(e5);
        ((Sistema) s).agregarEstacion(e6);
        ((Sistema) s).agregarEstacion(e7);
        ((Sistema) s).agregarEstacion(e8);
        ((Sistema) s).agregarEstacion(e9);
        ((Sistema) s).agregarEstacion(e10);
        
    }

    @Test
    public void ocupacionPromedioXBarrioOk() {
        
        
        retorno = s.ocupacionPromedioXBarrio();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Aguada#55|Palermo#70|Parque Rodo#55", retorno.getValorString());
    }
}
