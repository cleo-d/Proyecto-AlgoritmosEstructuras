package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_03ListarBicicletasEnDeposito {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void listarBicicletasVacio() {
        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    public void listarBicicletasSoloUnaBicicleta() {
        s.registrarBicicleta("AAA111", "URBANA");

        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("AAA111#URBANA#Disponible", retorno.getValorString());
    }

    @Test
    public void listarVariasBicisEnDepositoEnOrden() {
        s.registrarBicicleta("AER345", "URBANA");
        s.registrarBicicleta("UTR112", "ELECTRICA");
        s.registrarBicicleta("DDD111", "URBANA");

        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(
                "AER345#URBANA#Disponible|UTR112#ELECTRICA#Disponible|DDD111#URBANA#Disponible",
                retorno.getValorString()
        );
    }

}
