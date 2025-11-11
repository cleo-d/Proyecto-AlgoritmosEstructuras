package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_01ObtenerUsuario {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void obtenerUsuarioOk() {
        s.registrarUsuario("12345678", "Usuario01");
        retorno = s.obtenerUsuario("12345678");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Usuario01#12345678", retorno.getValorString());
    }

    @Test
    public void obtenerUsuarioError01() {
        s.registrarUsuario("12345678", "Usuario02");
        retorno = s.obtenerUsuario("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void obtenerUsuarioError02() {
        s.registrarUsuario("12345678", "Usuario03");
        retorno = s.obtenerUsuario("1235");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void obtenerUsuarioError03() {
        s.registrarUsuario("12345678", "Usuario04");
        retorno = s.obtenerUsuario("85962563");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

}
