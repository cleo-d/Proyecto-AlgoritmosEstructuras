package sistemaAutogestion;

import dominio.Estacion;
import dominio.Usuario;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class Test3_09UsuariosEnEspera {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();

        //Creo una estacion
        Estacion e1 = new Estacion("Estacion01", "unBarrio", 5);

        //creo usuarios y los agrego en espera en la estacion 
        e1.agregarUsuarioEnEspera(new Usuario("12345685", "usu4"));
        e1.agregarUsuarioEnEspera(new Usuario("12345685", "usu2"));
        e1.agregarUsuarioEnEspera(new Usuario("12345685", "usu1"));
        e1.agregarUsuarioEnEspera(new Usuario("12345685", "usu23"));

        ((Sistema) s).agregarEstacion(e1);
        s.registrarEstacion("Estacion01", "otroBarrio", 5);

    }

    @Test
    public void usuariosEnEsperaOk() {

        retorno = s.usuariosEnEspera("Estacion01");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("usu4|usu2|usu1|usu23|", retorno.getValorString());

        retorno = s.usuariosEnEspera("Estacion02");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("La estacion no existe en el sistema", retorno.getValorString());
    }
}
