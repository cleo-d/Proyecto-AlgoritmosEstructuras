package sistemaAutogestion;

//Mateo Bragunde 351711 - Cleo Diaz 260735
import dominio.Bicicleta;
import dominio.Estacion;
import dominio.Alquiler;
import dominio.Usuario;
import tads.Cola;
import tads.ListaNodos;
import tads.Nodo;
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
        Bicicleta b = new Bicicleta(codigo, "tipoAux");

        if (!listaBicicletas.existeElemento(b)) {
            return Retorno.error2();
        } else {
            b = listaBicicletas.buscarElemento(b);
        }

        if (codigo == null || codigo.trim().isEmpty() || motivo == null || motivo.trim().isEmpty()) {
            return Retorno.error1();
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

        Bicicleta b = new Bicicleta(codigo, "tipoAux");

        if (!listaBicicletas.existeElemento(b)) {
            return Retorno.error2();
        } else {
            b = listaBicicletas.buscarElemento(b);
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

        if (nombre == null || nombre.trim().isEmpty()) {
            return Retorno.error1();
        }

        Estacion e = new Estacion(nombre, "barrioAux", 3);

        if (!listaEstaciones.existeElemento(e)) {
            return Retorno.error2();
        } else {
            System.out.println("Entro al else en eliminarEstacion");
            e = listaEstaciones.buscarElemento(e);
            System.out.println(e);
            if (e.tieneBicicletasAncladas() || e.tieneUsuariosEnEspera() || e.tieneBicisEnEspera()) {
                return Retorno.error3();
            } else {
                System.out.println("Dejo borrar la estacion");
                listaEstaciones.borrarElemento(e);
                return Retorno.ok();
            }
        }

    }

    @Override
    public Retorno asignarBicicletaAEstacion(String codigo, String nombreEstacion) {

        //Reviso parametros que no sean null
        if (codigo == null || codigo.trim().isEmpty() || nombreEstacion == null || nombreEstacion.trim().isEmpty()) {
            return Retorno.error1();
        }

        //Creo bici auxiliar para validar si existe en el sistema
        Bicicleta b = new Bicicleta(codigo, "estadoAux");

        if (!listaBicicletas.existeElemento(b)) {
            //Que pasa si la bici no esta en la listaBicicletas y esta en una Estacion????Habria que buscar la bici dentro de TODAS las estaciones?
            return Retorno.error2();
        } else {
            b = listaBicicletas.buscarElemento(b);
            //Reviso si la bici tiene estado "Disponible"
            if (!"Disponible".equals(b.getEstado())) {
                return Retorno.error2();
            }
        }

        //Creo estacion auxiliar
        Estacion e = new Estacion(nombreEstacion, "barrioAux", 3);
        //Reviso si existe la estacion en la lista en la lista
        if (!listaEstaciones.existeElemento(e)) {
            return Retorno.error3();
        } else {
            e = listaEstaciones.buscarElemento(e);
        }
        //reviso si la estacion tiene anclajes libres
        if (!e.tieneAnclajesLibres()) {
            return Retorno.error4();
        }

        //Paso las validaciones, se procede a agregar la bicicleta a la estacion
        e.agregarBicicleta(b);
        return Retorno.ok();
    }

    @Override
    public Retorno alquilarBicicleta(String cedula, String nombreEstacion) {

        //Valido los parametros recibidos
        if (cedula == null || cedula.trim().isEmpty() || nombreEstacion == null || nombreEstacion.trim().isEmpty()) {
            return Retorno.error1();
        }

        //Creo usuario auxiliar para validar si existe en el sistema
        Usuario u = new Usuario(cedula, "usuarioAux");
        //Primero valido si existe el usuario en la lista
        if (!listaUsuarios.existeElemento(u)) {
            return Retorno.error2();
        } else {
            //En caso que exista lo busco de la lista y lo guardo en u
            u = listaUsuarios.buscarElemento(u);
        }

        //Creo Estacion auxiliar para validar si existe en el sistema
        Estacion e = new Estacion(nombreEstacion, "barrioAux", 5);
        //Valido si existe la estacion en la lista
        if (!listaEstaciones.existeElemento(e)) {
            return Retorno.error3();
        } else {
            //en caso que exista lo guardo en e
            e = listaEstaciones.buscarElemento(e);
        }

        if (e.tieneBicicletasAncladas()) {
            Bicicleta b = e.alquilarBicicleta(u);
            Alquiler a = new Alquiler(u, b, e);
            //Creo un registro de Alquiler en sistema
            this.agregarAlquiler(a);
            return Retorno.ok();

        } else {
            e.agregarUsuarioEnEspera(u);
            return Retorno.ok();
        }

    }

    @Override
    public Retorno devolverBicicleta(String cedula, String nombreEstacionDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno deshacerUltimosRetiros(int n) {

        if (n <= 0) {
            return Retorno.error1();
        } else {

            for (int i = 0; i >= n; i++) {
                //Trabajo con cada Alquiler
                Alquiler a = pilaRetiros.top();
                //Agarro la bici del alquiler
                Bicicleta b = a.getBici();
                //Agarro la estacion del Alquiler
                Estacion e = a.getEstacion();

                e.agregarBicicleta(b);

                //Luego de trabajar con el Alquiler se desapila
                pilaRetiros.desapilar();
            }

            return new Retorno(Retorno.Resultado.OK, String.valueOf(n));
        }

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
            return Retorno.ok();
        }

        String resultado = listaBicicletas.listarRecursivo("|");

        return new Retorno(Retorno.Resultado.OK, resultado);
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

        //Creo Estacion auxiliar para validar si existe en el sistema
        Estacion e = new Estacion(nombreEstacion, "barrioAux", 5);

        //Valido si existe la estacion en la lista
        if (!listaEstaciones.existeElemento(e)) {
            return new Retorno(Retorno.Resultado.OK, "No existe la estacion");
        } else {
            //Encuentro la estacion del Sistema, ahora debo trabajar con ella apra listar las bicicletas en orden ascendente por codigo
            e = listaEstaciones.buscarElemento(e);

            //ESTOY EN DUDA DE COMO RESOLVER ESTO
            //ListaNodos listaRet = e.mostrarBicicletas();
            //listaRet.mostrar();
            String stringRet = e.mostrarBicicletasPorCodigo();

            Retorno r = new Retorno(Retorno.Resultado.OK, stringRet);
            return r;

        }
    }

    @Override
    public Retorno estacionesConDisponibilidad(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno ocupacionPromedioXBarrio() {

        if (listaEstaciones.cantElementos() == 0) {
            return new Retorno(Retorno.Resultado.OK, "No hay estaciones en el Sistema");
        }

        int nEstaciones = listaEstaciones.cantElementos();

        // Como máximo puede haber nEstaciones barrios distintos
        String[] barrios = new String[nEstaciones];
        int[] bicisTotales = new int[nEstaciones];
        int[] capacidadTotal = new int[nEstaciones];
        int cantBarrios = 0;

        // 1) Recorro la lista de estaciones
        for (int i = 0; i < nEstaciones; i++) {
            Estacion est = listaEstaciones.obtenerElementoDePos(i);

            String barrio = est.getBarrio();
            int bicis = est.getCantidadBicisAncladas();
            int capacidad = est.getCapacidad();

            // Reviso si ya tengo el barrio en mi array
            int pos = -1;
            for (int j = 0; j < cantBarrios; j++) {
                if (barrios[j].equalsIgnoreCase(barrio)) {
                    pos = j;
                    break;
                }
            }
            if (pos == -1) {
                pos = cantBarrios;
                barrios[pos] = barrio;
                bicisTotales[pos] = 0;
                capacidadTotal[pos] = 0;
                cantBarrios++;
            }

            // Acumulo datos para ese barrio
            bicisTotales[pos] += bicis;
            capacidadTotal[pos] += capacidad;
        }

        // 2) Ordeno los barrios alfabéticamente con bubble sort
        for (int i = 0; i < cantBarrios - 1; i++) {
            for (int j = 0; j < cantBarrios - 1 - i; j++) {
                if (barrios[j].compareToIgnoreCase(barrios[j + 1]) > 0) {
                    // swap barrios
                    String tmpB = barrios[j];
                    barrios[j] = barrios[j + 1];
                    barrios[j + 1] = tmpB;

                    // swap bicis
                    int tmpI = bicisTotales[j];
                    bicisTotales[j] = bicisTotales[j + 1];
                    bicisTotales[j + 1] = tmpI;

                    // swap capacidad
                    tmpI = capacidadTotal[j];
                    capacidadTotal[j] = capacidadTotal[j + 1];
                    capacidadTotal[j + 1] = tmpI;
                }
            }
        }

        // 3) Construyo el String "barrio1#porcentaje|barrio2#porcentaje"
        String stringRet = "";

        for (int i = 0; i < cantBarrios; i++) {
            if (i > 0) {
                stringRet += "|";
            }

            int porcentaje = 0;
            if (capacidadTotal[i] > 0) {
                double p = (bicisTotales[i] * 100.0) / capacidadTotal[i];
                porcentaje = (int) Math.round(p);
            }

            stringRet += barrios[i] + "#" + porcentaje;
        }

        return new Retorno(Retorno.Resultado.OK, stringRet);
    }

    @Override
    public Retorno rankingTiposPorUso() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno usuariosEnEspera(String nombreEstacion) {

        Estacion e = new Estacion(nombreEstacion, "barrioAux", 5);
        //Busco la estacion en el sistema
        if(!listaEstaciones.existeElemento(e)){
            return new Retorno(Retorno.Resultado.OK, "La estacion no existe en el sistema") ;
        }
        e = listaEstaciones.buscarElemento(e);
        
        String stringRet = e.usuariosEnEspera();
        
        return new Retorno(Retorno.Resultado.OK, stringRet);
    }

    @Override
    public Retorno usuarioMayor() {
        return Retorno.noImplementada();
    }

    public void agregarEstacion(Estacion e) {
        listaEstaciones.agregarOrdenado(e);
    }

    public void agregarBicicleta(Bicicleta b) {
        listaBicicletas.agregarOrdenado(b);
    }

    public void agregarAlquiler(Alquiler a) {
        pilaRetiros.apilar(a);
    }

    public Bicicleta buscarBiciPorCodigo(String codigo) {
        Bicicleta b = new Bicicleta(codigo, "biciAux");

        if (listaBicicletas.existeElemento(b)) {
            b = listaBicicletas.buscarElemento(b);
            return b;
        }
        return null;
    }

    //PARA LA ESTRUCTURA DEL DIAGRAMA
    //TIENE QUE ESTAR JUSTIFICADO
    //las etsaciones tienen gente esprando por una bici -> IMPLEMENTADO POR X PUNTO
    //bicies esperando por un acnlaje cuando no hay -> IMPLEMENTADO POR Y PUNTO
    //deshacer N alquileres
    //PARA LAENTREGA, DIAGRAMA DE CLAS,E DOCUMENTACION Y PROYECTO
}
