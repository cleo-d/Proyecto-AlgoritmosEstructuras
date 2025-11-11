package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_04InformacionMapa {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void informacionMapaOK() {
        String mapa1[][]
                = {
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "o", "E3", "o", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"E1", "o", "o", "o", "E5", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "E2", "o", "E6", "o"},
                    {"o", "o", "o", "o", "E7", "o"},
                    {"o", "o", "o", "E4", "o", "o"}
                };
        retorno = s.informaciónMapa(mapa1);
        assertEquals("3#columna|existe", retorno.getValorString());

        String mapa2[][]
                = {
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "o", "E3", "o", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"E1", "o", "o", "o", "E5", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "E2", "o", "E6", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "o", "E4", "o", "o"}
                };
        retorno = s.informaciónMapa(mapa2);
        assertEquals("2#ambas|existe", retorno.getValorString());

        String mapa3[][]
                = {
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "o", "E3", "o", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"E1", "o", "o", "o", "E5", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "E2", "o", "E6", "o"},
                    {"o", "E7", "o", "o", "o", "o"},
                    {"o", "o", "o", "E4", "o", "o"}
                };
        retorno = s.informaciónMapa(mapa3);
        assertEquals("2#ambas|no existe", retorno.getValorString());

    }

    @Test
    public void informacionMapaError() {
        String mapa1[][]
                = {
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "o", "E3", "o", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"E1", "o", "o", "o", "E5", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "o", "o", "E6", "o"},
                    {"o", "o", "o", "o", "o", "o"},
                    {"o", "o", "o", "E4", "o", "o"}
                };
        retorno = s.informaciónMapa(mapa1);
        assertNotEquals("3#columna|existe", retorno.getValorString());

    }

}
