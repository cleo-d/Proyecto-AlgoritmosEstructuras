package sistemaAutogestion;

import dominio.Bicicleta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_06RepararBicicleta {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void RepararBicicletaOk() {

        s.registrarBicicleta("YYY111", "ELECTRICA");


        Bicicleta b = ((Sistema) s).buscarBiciPorCodigo("YYY111");
        b.setEstado("Alquilada");

        retorno = s.repararBicicleta("YYY111");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Disponible", b.getEstado());
    }

    @Test
    public void RepararBicicletaError01() {

        retorno = s.repararBicicleta(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.repararBicicleta("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.repararBicicleta("   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void RepararBicicletaError02() {
        retorno = s.repararBicicleta("ABC123");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void RepararBicicleta03() {

        s.registrarBicicleta("ZZZ111", "URBANA");

        Bicicleta b = ((Sistema) s).buscarBiciPorCodigo("ZZZ111");
        b.setEstado("Disponible");

        retorno = s.repararBicicleta("ZZZ111");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

    }


}
