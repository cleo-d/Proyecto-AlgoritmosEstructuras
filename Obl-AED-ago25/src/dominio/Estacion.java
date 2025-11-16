package dominio;

import tads.Cola;
import tads.ListaNodos;
import tads.Pila;

public class Estacion implements Comparable<Estacion> {

    private String nombre;
    private String barrio;
    private int capacidad;
    private int anclajesLibres = 5;
    private Pila<Bicicleta> pilaBicicletas; //Nunca va a ser mas de 5 
    private Cola<Usuario> usuariosEnEspera;
    private Cola<Usuario> colaAnclaje;

    public Estacion(String unNombre, String unBarrio, int unaCapacidad) {
        this.setNombre(unNombre);
        this.setBarrio(unBarrio);
        this.setCapacidad(unaCapacidad);

        pilaBicicletas = new Pila();
        usuariosEnEspera = new Cola();
        colaAnclaje = new Cola();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return this.getNombre() + "-" + this.getBarrio();
    }

    @Override
    public boolean equals(Object o) {
        //Reviso si es el mismo objecto en memoria
        if (this == o) {
            return true;
        }
        //Aseguro que el objeto sea de tipo Estacion
        if (!(o instanceof Estacion)) {
            return false;
        }
        //Reviso si contienen el mismo nombre
        Estacion otra = (Estacion) o;
        return this.nombre.equals(otra.nombre); // Nombre único define igualdad
    }

    @Override
    public int compareTo(Estacion o) {
        return this.getNombre().compareToIgnoreCase(o.getNombre());
    }

    public boolean tieneBicicletasAncladas() {
        return !pilaBicicletas.esVacia();
    }

    //Las bicis en espera son una cola de Usuarios creeeeo
    public boolean tieneBicisEnEspera() {
        return !colaAnclaje.esVacia();
    }

    public boolean tieneAnclajesLibres() {
        return anclajesLibres != 0;
    }

    public boolean tieneUsuariosEnEspera() {
        return !usuariosEnEspera.esVacia();
    }

    public void agregarBicicleta(Bicicleta b) {
        b.setEstado("Disponible");
        if (tieneAnclajesLibres()) {
            // hay lugar en la estación,va a la pila de bicis ancladas
            pilaBicicletas.apilar(b);
        }
        anclajesLibres--;
    }

    public void agregarUsuarioEnEspera(Usuario u) {
        this.usuariosEnEspera.encolar(u);
    }

    public void agregarColaAnclaje(Usuario u) {
        this.colaAnclaje.encolar(u);
    }

    public int getCantidadBicisAncladas() {
        return pilaBicicletas.cantElementos();
    }

    //Este metodo agarra una bici de la pila, y setea el estado de la bici en "Alquilada" (tambien retorna esa bicicleta)
    public Bicicleta alquilarBicicleta(Usuario u) {

        Bicicleta b = pilaBicicletas.top();

        b.setEstado("Alquilada");
        pilaBicicletas.desapilar();
        anclajesLibres++;

        return b;
    }

    //NO ESTOY SEGURO SI LO ESTOY HACIENDO BIEN
    public String mostrarBicicletasPorCodigo() {
        int n = pilaBicicletas.cantElementos();

        if (n == 0) {
            return "La estacion no tiene Bicicletas ancladas";
        }
        //Creo ListaNodos para ordenar los codigos, y pilaAux para reestructurar la pila original
        Bicicleta[] arrBicis = new Bicicleta[n];
        Pila<Bicicleta> pilaAux = new Pila<>();

        int i = 0;

        // 1) Recorro la pila UNA sola vez
        while (!pilaBicicletas.esVacia()) {
            Bicicleta b = pilaBicicletas.top();
            pilaBicicletas.desapilar();

            // Guardo cada bici en un array
            arrBicis[i] = b;
            i++;

            // Guardo en pilaAux para rearmar la original
            pilaAux.apilar(b);
        }

        // 2) Restaura la pila original
        while (!pilaAux.esVacia()) {
            Bicicleta b = pilaAux.top();
            pilaAux.desapilar();
            pilaBicicletas.apilar(b);
        }

        // 3) Ordeno el array por código (insertion sort)
        for (int j = 1; j < n; j++) {
            Bicicleta clave = arrBicis[j];
            int k = j - 1;
            while (k >= 0 && arrBicis[k].getCodigo().compareTo(clave.getCodigo()) > 0) {
                arrBicis[k + 1] = arrBicis[k];
                k--;
            }
            arrBicis[k + 1] = clave;
        }

        // 4) Armo el String con los codigos
        String stringRet = "";
        for (int j = 0; j < n; j++) {
            if (j > 0) {
                stringRet += ("|");
            }
            stringRet += arrBicis[j].getCodigo();
        }

        return stringRet;

    }

    
    
    public Usuario getPrimerUsuarioEnEspera() {
    return usuariosEnEspera.frente();
}

    
    public void quitarUsuarioEnEspera() {
    usuariosEnEspera.desencolar();
}

}
