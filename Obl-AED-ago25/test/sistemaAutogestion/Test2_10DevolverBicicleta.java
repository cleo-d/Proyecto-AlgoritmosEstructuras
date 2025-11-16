package sistemaAutogestion;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class Test2_10DevolverBicicleta {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

   @Before
public void setUp() {
    s.crearSistemaDeGestion();

  
    s.registrarEstacion("Estacion01", "Barrio1", 5);
    s.registrarEstacion("Estacion02", "Barrio2", 5);

   
    s.registrarUsuario("12345678", "Usuario1");

  
    s.registrarBicicleta("AAA111", "URBANA");

   
    s.asignarBicicletaAEstacion("AAA111", "Estacion01");

    // Ahora sí, el usuario realmente alquila una bici
    s.alquilarBicicleta("12345678", "Estacion01");
}


    @Test
    public void DevolverBicicletaError1() {
        // null o vacíos
        retorno = s.devolverBicicleta(null, "Estacion01");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.devolverBicicleta("12345678", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.devolverBicicleta("", "Estacion01");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.devolverBicicleta("12345678", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void DevolverBicicletaError2() {
        // Usuario inexistente
        retorno = s.devolverBicicleta("99999999", "Estacion01");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        // Usuario existe pero NO tiene bici alquilada
        retorno = s.devolverBicicleta("87654321", "Estacion01");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void DevolverBicicletaError3() {
        // Estación inexistente
        retorno = s.devolverBicicleta("12345678", "EstacionInexistente");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
