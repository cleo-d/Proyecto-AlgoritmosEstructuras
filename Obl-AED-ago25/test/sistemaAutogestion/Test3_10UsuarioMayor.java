package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_10UsuarioMayor {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();

        // Estación
        s.registrarEstacion("E1", "Centro", 10);

        // Usuarios
        s.registrarUsuario("50000001", "U1");
        s.registrarUsuario("40000001", "U2");
        s.registrarUsuario("30000001", "U3");

        // Bicicletas
        s.registrarBicicleta("BIC001", "URBANA");
        s.registrarBicicleta("BIC002", "URBANA");
        s.registrarBicicleta("BIC003", "URBANA");
        s.registrarBicicleta("BIC004", "URBANA");
        s.registrarBicicleta("BIC005", "URBANA");
        s.registrarBicicleta("BIC006", "URBANA");
        s.registrarBicicleta("BIC007", "URBANA");

        // Asignar bicis a estación
        s.asignarBicicletaAEstacion("BIC001", "E1");
        s.asignarBicicletaAEstacion("BIC002", "E1");
        s.asignarBicicletaAEstacion("BIC003", "E1");
        s.asignarBicicletaAEstacion("BIC004", "E1");
        s.asignarBicicletaAEstacion("BIC005", "E1");
        s.asignarBicicletaAEstacion("BIC006", "E1");
        s.asignarBicicletaAEstacion("BIC007", "E1");

        // -----------------------------
        // U1 → 3 alquileres
        // -----------------------------
        s.alquilarBicicleta("50000001", "E1");
        s.devolverBicicleta("50000001", "E1");

        s.alquilarBicicleta("50000001", "E1");
        s.devolverBicicleta("50000001", "E1");

        s.alquilarBicicleta("50000001", "E1");
        s.devolverBicicleta("50000001", "E1");

        // -----------------------------
        // U2 → 3 alquileres
        // -----------------------------
        s.alquilarBicicleta("40000001", "E1");
        s.devolverBicicleta("40000001", "E1");

        s.alquilarBicicleta("40000001", "E1");
        s.devolverBicicleta("40000001", "E1");

        s.alquilarBicicleta("40000001", "E1");
        s.devolverBicicleta("40000001", "E1");

        // -----------------------------
        // U3 → 1 alquiler
        // -----------------------------
        s.alquilarBicicleta("30000001", "E1");
        s.devolverBicicleta("30000001", "E1");
    }

    @Test
    public void usuarioMayorOK() {

        // Empate U1=3 alquileres, U2=3 alquileres gana menor cedula: "40000001"
        retorno = s.usuarioMayor();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("40000001", retorno.getValorString());
    }
}
