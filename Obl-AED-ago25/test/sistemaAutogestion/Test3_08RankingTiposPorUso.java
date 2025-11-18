package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_08RankingTiposPorUso {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();

        // Registrar usuarios
        s.registrarUsuario("11111111", "U1");
        s.registrarUsuario("22222222", "U2");
        s.registrarUsuario("33333333", "U3");
        s.registrarUsuario("44444444", "U4");

        // Registrar estaciones
        s.registrarEstacion("E1", "Centro", 5);

        // Registrar bicis
        s.registrarBicicleta("AAA111", "URBANA");
        s.registrarBicicleta("AAA222", "URBANA");
        s.registrarBicicleta("BBB111", "MOUNTAIN");
        s.registrarBicicleta("CCC111", "ELECTRICA");

        // Asignarlas a la estación
        s.asignarBicicletaAEstacion("AAA111", "E1");
        s.asignarBicicletaAEstacion("AAA222", "E1");
        s.asignarBicicletaAEstacion("BBB111", "E1");
        s.asignarBicicletaAEstacion("CCC111", "E1");

        // Alquilar múltiples veces para generar ranking
        // 2 URBANAS
        s.alquilarBicicleta("11111111", "E1");
        s.alquilarBicicleta("22222222", "E1");
        

        // 1 MOUNTAIN
        s.alquilarBicicleta("33333333", "E1");
       

        // 1 ELECTRICA
        s.alquilarBicicleta("44444444", "E1");
       
    }

    @Test
    public void RankingOK() {

        retorno = s.rankingTiposPorUso();

         System.out.println("RESULTADO RANKING OK = " + retorno.getValorString());
         
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("URBANA#2|MOUNTAIN#1|ELECTRICA#1", retorno.getValorString());
    }

    @Test
    public void RankingVacio() {
        Sistema sis = new Sistema();
        sis.crearSistemaDeGestion();

        retorno = sis.rankingTiposPorUso();
        
        System.out.println("RESULTADO RANKING VACIO = " + retorno.getValorString());

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }
}
