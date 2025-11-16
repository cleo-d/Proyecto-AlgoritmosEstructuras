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

public class Test2_11DeshacerUltimosRetiros {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();

        //Creo Estaciones
        Estacion e1 = new Estacion("Estacion01", "Barrio", 5);
        Estacion e2 = new Estacion("Estacion02", "Barrio", 5);
        Estacion e3 = new Estacion("Estacion03", "Barrio", 5);
        Estacion e4 = new Estacion("Estacion04", "Barrio", 5);
        Estacion e5 = new Estacion("Estacion05", "Barrio", 5);
        Estacion e6 = new Estacion("Estacion06", "Barrio", 5);
        Estacion e7 = new Estacion("Estacion07", "Barrio", 5);
        Estacion e8 = new Estacion("Estacion08", "Barrio", 5);
        Estacion e9 = new Estacion("Estacion09", "Barrio", 5);

        //Creo Bicicletas
        Bicicleta b1 = new Bicicleta("AAA111", "URBANA");
        Bicicleta b2 = new Bicicleta("AAA222", "URBANA");
        Bicicleta b3 = new Bicicleta("AAA333", "URBANA");
        Bicicleta b4 = new Bicicleta("AAA444", "URBANA");
        Bicicleta b5 = new Bicicleta("AAA555", "URBANA");
        Bicicleta b6 = new Bicicleta("AAA666", "URBANA");
        Bicicleta b7 = new Bicicleta("AAA777", "URBANA");
        Bicicleta b8 = new Bicicleta("AAA888", "URBANA");
        Bicicleta b9 = new Bicicleta("AAA999", "URBANA");

        //Creo Usuarios
        Usuario u1 = new Usuario("12345685", "usu8");
        Usuario u2 = new Usuario("12345686", "usu9");
        Usuario u3 = new Usuario("12345687", "usu10");
        Usuario u4 = new Usuario("12345688", "usu11");
        Usuario u5 = new Usuario("12345689", "usu12");
        Usuario u6 = new Usuario("12345690", "usu12");
        Usuario u7 = new Usuario("12345691", "usu12");
        Usuario u8 = new Usuario("12345692", "usu12");
        Usuario u9 = new Usuario("12345693", "usu12");

        Alquiler a1 = new Alquiler(u1, b1, e1);
        Alquiler a2 = new Alquiler(u2, b2, e2);
        Alquiler a3 = new Alquiler(u3, b3, e3);
        Alquiler a4 = new Alquiler(u4, b4, e4);
        Alquiler a5 = new Alquiler(u5, b5, e5);
        Alquiler a6 = new Alquiler(u6, b6, e6);
        Alquiler a7 = new Alquiler(u7, b7, e7);
        Alquiler a8 = new Alquiler(u8, b8, e8);
        Alquiler a9 = new Alquiler(u9, b9, e9);

        ((Sistema) s).agregarAlquiler(a1);
        ((Sistema) s).agregarAlquiler(a2);
        ((Sistema) s).agregarAlquiler(a3);
        ((Sistema) s).agregarAlquiler(a4);
        ((Sistema) s).agregarAlquiler(a5);
        ((Sistema) s).agregarAlquiler(a6);
        ((Sistema) s).agregarAlquiler(a7);
        ((Sistema) s).agregarAlquiler(a8);
        ((Sistema) s).agregarAlquiler(a9);

    }

    @Test
    public void DeshacerUltimosRetirosOk() {

        retorno = s.deshacerUltimosRetiros(5);
        
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("5", retorno.getValorString());
    }
    
        @Test
    public void DeshacerUltimosRetirosError1() {

        retorno = s.deshacerUltimosRetiros(-1);
        
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }
}
