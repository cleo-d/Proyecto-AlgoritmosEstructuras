package sistemaAutogestion;

//Mateo Bragunde 351711 - Cleo Diaz 260735
import dominio.Bicicleta;
import dominio.Estacion;
import dominio.Alquiler;
import dominio.Usuario;
import tads.Cola;
import tads.ListaNodos;
import tads.Pila;

public class Sistema implements IObligatorio {

    private ListaNodos<Bicicleta> listaBicicletas;
    private ListaNodos<Estacion> listaEstaciones;
    private ListaNodos<Usuario> listaUsuarios;
    private Pila<Alquiler> pilaRetiros;
    
    @Override
    public Retorno crearSistemaDeGestion() {

        listaBicicletas = new ListaNodos();
        listaEstaciones = new ListaNodos();
        listaUsuarios = new ListaNodos();
        pilaRetiros = new Pila();


        return Retorno.ok();
    }

    @Override
    public Retorno registrarEstacion(String nombre, String barrio, int capacidad) {

        Estacion e = new Estacion(nombre, barrio, capacidad);

        if (barrio == null || barrio.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            return Retorno.error1();
        }
        if (capacidad <= 0) {
            return Retorno.error2();
        }
        if (listaEstaciones.existeElemento(e)) {
            return Retorno.error3();
        } else {
            listaEstaciones.agregarOrdenado(e);
            return Retorno.ok();
        }

    }

    @Override
    public Retorno registrarUsuario(String cedula, String nombre) {

        if (cedula == null || cedula.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            return Retorno.error1();
        }
        Usuario u = new Usuario(cedula, nombre);
        if (!u.validarCedula(cedula)) {
            return Retorno.error2();
        }
        if (listaUsuarios.existeElemento(u)) {
            return Retorno.error3();
        } else {
            listaUsuarios.agregarOrdenado(u);
            listaUsuarios.mostrar();
            return Retorno.ok();
        }
    }

    @Override
    public Retorno registrarBicicleta(String codigo, String tipo) {

        Bicicleta b = new Bicicleta(codigo, tipo);

        if (codigo == null || codigo.trim().isEmpty() || tipo == null || tipo.trim().isEmpty()) {
            return Retorno.error1();
        }
        if (!b.validarCodigo(codigo)) {
            return Retorno.error2();
        }
        if (!b.validarTipo(tipo)) {
            return Retorno.error3();
        }
        if (listaBicicletas.existeElemento(b)) {
            return Retorno.error4();
        } else {
            listaBicicletas.agregarFinal(b);
            return Retorno.ok();
        }

    }

    @Override
    public Retorno marcarEnMantenimiento(String codigo, String motivo) {

        //Busco la bici en el elistado
        Bicicleta b = buscarBiciPorCodigo(codigo);

        if (codigo == null || codigo.trim().isEmpty() || motivo == null || motivo.trim().isEmpty()) {
            return Retorno.error1();
        }
        //Debo implementar la funcion existeBici
        if (b == null) {
            return Retorno.error2();
        }
        if ((b.getEstado().equals("Alquilada"))) {
            return Retorno.error3();
        }
        if ((b.getEstado().equals("Mantenimiento"))) {
            return Retorno.error4();
        } else {
            b.setEstado("Mantenimiento");
            return Retorno.ok();
        }
    }

    @Override
    public Retorno repararBicicleta(String codigo) {
        //Busco la bici en el elistado
        

        if (codigo == null || codigo.trim().isEmpty()) {
            return Retorno.error1();
        }
        
        codigo = codigo.trim();
        
        Bicicleta b = buscarBiciPorCodigo(codigo);
        
        if (b == null) {
            return Retorno.error2();
        }
        if (!("Mantenimiento".equals(b.getEstado()))) {
            return Retorno.error3();
        } else {
            b.setEstado("Disponible");
            return Retorno.ok();
        }

    }

    @Override
    public Retorno eliminarEstacion(String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno asignarBicicletaAEstacion(String codigo, String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno alquilarBicicleta(String cedula, String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno devolverBicicleta(String cedula, String nombreEstacionDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno deshacerUltimosRetiros(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno obtenerUsuario(String cedula) {

        if (cedula == null) {
            return Retorno.error1();
}

        cedula = cedula.trim(); //usamos cedula limpia si no es null

        if (cedula.isEmpty()) {
            return Retorno.error1();
}
        if (cedula.length() != 8) {
            return Retorno.error2();
}

        Usuario u = new Usuario(cedula, "nombreAux");

        if (!listaUsuarios.existeElemento(u)) {
            return Retorno.error3();
        } else {
            u = listaUsuarios.buscarElemento(u);
            System.out.println("DEBUG: found user: " + u);
            String nombre = u.getNombre() + "#" + u.getCedula();
            Retorno r = new Retorno(Retorno.Resultado.OK, nombre);
            return r;
        }
    }

    //QUIZA PUEDA HACER UN REFACTOR DE ESTE METODO, SE VIO EN LA CLASE DEL DIA 30/9/2025
    @Override
    public Retorno listarUsuarios() {
        String resultado = listaUsuarios.concatenar("|");
        return new Retorno(Retorno.Resultado.OK, resultado);
}


    @Override
    public Retorno listarBicisEnDeposito() {
        if (listaBicicletas.esVacia()) {
        return Retorno.ok("");
    }

        String resultado = listaBicicletas.listarRecursivo("|");

        return new Retorno(Retorno.Resultado.OK, resultado);
}


    @Override
    public Retorno informaciónMapa(String[][] mapa) {
        int filaMax = 0;
        int maxColumna = 0;
        String resultadoParcial = "";
        int consecutiva = 0;

        //TRABAJO CON LAS FILAS
        for (int i = 0; i < mapa.length; i++) {
            int countFila = 0;
            //System.out.println("trabajo con i: " + i);

            //Aca recorro cada posicion dentro de la fila
            for (int j = 0; j < mapa[i].length; j++) {
                //System.out.println("trabajo con j: " + j);
                if (mapa[i][j] != null && !mapa[i][j].trim().equalsIgnoreCase("o") && !mapa[i][j].trim().isEmpty()) { //arreglo de vacios y "O"

                    countFila++;
                    consecutiva++;
                    //System.out.println("countFila: "+ countFila);
                }
            }

            if (countFila > filaMax) {
                filaMax = countFila;
            }
        }

        //TRABAJO CON LAS COLUMNAS
        int colAnterior = 0;
        int consecutivas = 1;
        boolean existeAscendencia = false;

        for (int j = 0; j < mapa[0].length; j++) {
            int countCol = 0;
            for (int i = 0; i < mapa.length; i++) {
                if (mapa[i][j] != null && !mapa[i][j].trim().equalsIgnoreCase("o") && !mapa[i][j].trim().isEmpty()) { //arreglo de vacios y "O"
                    countCol++;
                }
            }

            //Reviso por ascendencia
            if (colAnterior == countCol - 1) {
                consecutivas++;
                if (consecutivas >= 3) {
                    existeAscendencia = true;
                }
            } else {
                consecutivas = 1;
            }
            colAnterior = countCol;
            if (countCol > maxColumna) {
                maxColumna = countCol;
            }
        }

        if (filaMax > maxColumna) {
            resultadoParcial += filaMax + "#" + "fila" + "|";
        } else if (maxColumna > filaMax) {
            resultadoParcial += maxColumna + "#" + "columna" + "|";
        } else {
            resultadoParcial += maxColumna + "#" + "ambas" + "|";
        }

        resultadoParcial += existeAscendencia ? "existe" : "no existe";


        Retorno r = new Retorno(Retorno.Resultado.OK, resultadoParcial);
        return r;
    }

    @Override
    public Retorno listarBicicletasDeEstacion(String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno estacionesConDisponibilidad(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno ocupacionPromedioXBarrio() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno rankingTiposPorUso() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno usuariosEnEspera(String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno usuarioMayor() {
        return Retorno.noImplementada();
    }

    public Bicicleta buscarBiciPorCodigo(String codigo) {
        Bicicleta b = new Bicicleta(codigo, "biciAux");

        if (listaBicicletas.existeElemento(b)) {
            b = listaBicicletas.buscarElemento(b);
            return b;
        }
        return null;
    }

    public void setEstadoBici(String codigo, String nuevoEstado) {
        Bicicleta b = buscarBiciPorCodigo(codigo);
        if (b != null) {
            b.setEstado(nuevoEstado);
        }
    }

    //PARA LA ESTRUCTURA DEL DIAGRAMA
    //TIENE QUE ESTAR JUSTIFICADO
    //las etsaciones tienen gente esprando por una bici -> IMPLEMENTADO POR X PUNTO
    //bicies esperando por un acnlaje cuando no hay -> IMPLEMENTADO POR Y PUNTO
    //deshacer N alquileres
    //PARA LAENTREGA, DIAGRAMA DE CLAS,E DOCUMENTACION Y PROYECTO
}
