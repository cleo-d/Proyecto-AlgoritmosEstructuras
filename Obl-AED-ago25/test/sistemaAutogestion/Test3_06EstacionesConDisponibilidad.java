package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_06EstacionesConDisponibilidad {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();

       
        s.registrarEstacion("E1", "Barrio1", 5);
        s.registrarEstacion("E2", "Barrio1", 5);
        s.registrarEstacion("E3", "Barrio1", 5);

       
        s.registrarBicicleta("BIC001", "URBANA");
        s.registrarBicicleta("BIC002", "URBANA");
        s.registrarBicicleta("BIC003", "URBANA");
        s.registrarBicicleta("BIC004", "URBANA");
        s.registrarBicicleta("BIC005", "URBANA");
        s.registrarBicicleta("BIC006", "URBANA");

        //distribución de bicis:
        //E1 -> 3 bicis
        s.asignarBicicletaAEstacion("BIC001", "E1");
        s.asignarBicicletaAEstacion("BIC002", "E1");
        s.asignarBicicletaAEstacion("BIC003", "E1");

        //E2 -> 1 bici
        s.asignarBicicletaAEstacion("BIC004", "E2");

        //E3 -> 0 bicis
    }

    @Test
    public void estacionesConDisponibilidadError01() {
        //n <= 1
        retorno = s.estacionesConDisponibilidad(1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.estacionesConDisponibilidad(0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.estacionesConDisponibilidad(-5);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void estacionesConDisponibilidadOK() {
        //E1 tiene 3 bicis -> > 2
        //E2 tiene 1 bici -> no cuenta
        //E3 tiene 0 -> no cuenta

        retorno = s.estacionesConDisponibilidad(2);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(1, retorno.getValorEntero());
    }

    @Test
    public void estacionesConDisponibilidadOKNinguna() {
        //con n = 3 ninguna estación tiene más de 3
        retorno = s.estacionesConDisponibilidad(3);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(0, retorno.getValorEntero());
    }
}

