/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaAutogestion;

import dominio.Alquiler;
import dominio.Bicicleta;
import dominio.Estacion;
import dominio.Usuario;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class Test2_07EliminarEstacion {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
        //Creo Estaciones
        Estacion e1 = new Estacion("Estacion01","Barrio", 5 );
        Estacion e2 = new Estacion("Estacion02","Barrio", 5 );
        Estacion e3 = new Estacion("Estacion03","Barrio", 5 );
        Estacion e4 = new Estacion("Estacion04","Barrio", 5 );
        Estacion e5 = new Estacion("Estacion05","Barrio", 5 );
        
        //Creo Bicicletas
        Bicicleta b1 = new Bicicleta("AAA111", "URBANA");
        Bicicleta b2 = new Bicicleta("AAA222", "URBANA");
        Bicicleta b3 = new Bicicleta("AAA333", "URBANA");
        Bicicleta b4 = new Bicicleta("AAA444", "URBANA");
        
        //Agrego bicicletas a estacion 1
        e1.agregarBicicleta(b1);
        e1.agregarBicicleta(b2);
        e1.agregarBicicleta(b3);
        
        //Creo Usuarios
        Usuario u1 = new Usuario("12345685", "usu8");
        Usuario u2 = new Usuario("12345686", "usu9");
        Usuario u3 = new Usuario("12345687", "usu10");
        Usuario u4 = new Usuario("12345688", "usu11");
        Usuario u5 = new Usuario("12345689", "usu12");
        
        //Agrego Estaciones a sistema
        s.registrarEstacion("Estacion06","Barrio", 5 );
        s.registrarEstacion("Estacion07","Barrio", 5 );
        s.registrarEstacion("Estacion08","Barrio", 5 );
        s.registrarEstacion("Estacion09","Barrio", 5 );
        
        
        //Registro Alquileres
       ((Sistema) s).agregarEstacion(e1);
        System.out.println("Tiene bicicletas ancladas: "+ e1.tieneBicicletasAncladas());
        System.out.println("Tiene bicicletas en espera: "+e1.tieneBicisEnEspera());
        
    }

    @Test
    public void EliminarEstacionOk() {
        retorno = s.eliminarEstacion("Estacion06");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void EliminarEstacionError01() {
        retorno = s.eliminarEstacion("");

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.eliminarEstacion(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

    }

    @Test
    public void EliminarEstacionError02() {
        retorno = s.eliminarEstacion("Estacion99");

        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void EliminarEstacionError03() {
        
             retorno = s.eliminarEstacion("Estacion01");

        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        //FALTA VALIDAR CON UNA ESTACION QUE TENGA UNA COLA PENDIENTE
        retorno = s.eliminarEstacion("Estacion01");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
     
    }
}
