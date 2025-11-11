package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_04RegistrarBicicleta {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void RegistrarBicicletaOk() {
        retorno = s.registrarBicicleta("AAA111", "URBANA");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void RegistrarBicicletaError01() {
        
        retorno = s.registrarBicicleta("BBB111", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarBicicleta("", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarBicicleta("  ", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarBicicleta("CCC111", "  ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarBicicleta(null, "URBANA");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarBicicleta("DDD111", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void RegistrarBicicletaError02() {
        retorno = s.registrarBicicleta("EE000", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void RegistrarBicicletaError03() {
        
        retorno = s.registrarBicicleta("FFF111", "RANDOM");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

    }
    
        @Test
    public void RegistrarBicicletaError04() {
        s.registrarBicicleta("GGG111", "URBANA");
        retorno = s.registrarBicicleta("GGG111", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

    }

}
