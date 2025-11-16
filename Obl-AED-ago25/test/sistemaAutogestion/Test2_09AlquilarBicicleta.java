/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaAutogestion;

import dominio.Bicicleta;
import dominio.Estacion;
import dominio.Usuario;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class Test2_09AlquilarBicicleta {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();

        //Agreg0 bicicletas a sistema
        s.registrarBicicleta("AAA111", "URBANA");
        s.registrarBicicleta("AAA222", "URBANA");
        s.registrarBicicleta("AAA333", "URBANA");
        s.registrarBicicleta("AAA444", "URBANA");
        s.registrarBicicleta("AAA555", "URBANA");

        s.registrarEstacion("Estacion01", "Barrio", 5);
        s.registrarEstacion("Estacion02", "Barrio", 5);
        s.registrarEstacion("Estacion03", "Barrio", 5);
        s.registrarEstacion("Estacion04", "Barrio", 5);

        //Creo usuarios para alquilar bicicletas
        s.registrarUsuario("12345685", "usu1");
        s.registrarUsuario("12345685", "usu2");
        s.registrarUsuario("12345685", "usu3");
        s.registrarUsuario("12345685", "usu4");
        s.registrarUsuario("12345685", "usu5");

        //Creo una estacion sin anclajes libres
        Estacion e5 = new Estacion("Estacion05", "unBarrio", 5);
        Bicicleta b2 = new Bicicleta("AAA777", "URBANA");
        Bicicleta b3 = new Bicicleta("AAA888", "URBANA");
        Bicicleta b4 = new Bicicleta("AAA999", "URBANA");
        Bicicleta b5 = new Bicicleta("AAA123", "URBANA");
        Bicicleta b6 = new Bicicleta("AAA321", "URBANA");
        e5.agregarBicicleta(b2);
        e5.agregarBicicleta(b3);
        e5.agregarBicicleta(b4);
        e5.agregarBicicleta(b5);
        e5.agregarBicicleta(b6);
        ((Sistema) s).agregarEstacion(e5);

        //Creo bicicletas con estado != disponible"
        Bicicleta b1 = new Bicicleta("AAA666", "URBANA", "Alquilada");
        ((Sistema) s).agregarBicicleta(b1);

    }

    @Test
    public void AlquilarBicicletaOk() {

        retorno = s.alquilarBicicleta("12345685", "Estacion05");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    }

    @Test
    public void AlquilarBicicletaError01() {

        //Parametros null o vacios
        retorno = s.alquilarBicicleta(null, "Estacion01");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.alquilarBicicleta("12345685", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.alquilarBicicleta("", "Estacion01");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.alquilarBicicleta("12345685", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.alquilarBicicleta("", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.alquilarBicicleta(null, null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void AlquilarBicicletaError02() {

        //Usuario inexistente
        retorno = s.alquilarBicicleta("98765432", "Estacion01");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void AlquilarBicicletaError03() {
        retorno = s.alquilarBicicleta("12345685", "Estacion99");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
