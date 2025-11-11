package sistemaAutogestion;

import dominio.Bicicleta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_05MarcarEnMantenimiento {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void MarcarEnMantenimientoOk() {
        s.registrarBicicleta("AAA111", "URBANA");
        retorno = s.marcarEnMantenimiento("AAA111", "Mantenimiento");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void MarcarEnMantenimientoError01() {

        s.registrarBicicleta("BBB111", "URBANA");

        retorno = s.marcarEnMantenimiento("BBB111", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("", "Mantenimiento");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("  ", "Mantenimiento");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("BBB111", "   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("BBB111", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento(null, "Mantenimiento");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void MarcarEnMantenimientoError02() {
        s.registrarBicicleta("CCC111", "URBANA");

        retorno = s.marcarEnMantenimiento("DDD111", "Mantenimiento");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void MarcarEnMantenimientoError03() {

        s.registrarBicicleta("EEE111", "URBANA");
        Bicicleta b = ((Sistema) s).buscarBiciPorCodigo("EEE111");
        b.setEstado("Alquilada");

        retorno = s.marcarEnMantenimiento("EEE111", "Mantenimiento");

        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

    }

    @Test
    public void MarcarEnMantenimientoError04() {
        s.registrarBicicleta("FFF111", "URBANA");
        Bicicleta b = ((Sistema) s).buscarBiciPorCodigo("FFF111");
        s.marcarEnMantenimiento("FFF111", "Mantenimiento");

        retorno = s.marcarEnMantenimiento("FFF111", "Mantenimiento");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

    }

}
