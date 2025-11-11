package dominio;

import tads.Cola;
import tads.ListaNodos;
import tads.Pila;

public class Estacion implements Comparable<Estacion> {

    private String nombre;
    private String barrio;
    private int capacidad;
    private Pila<Bicicleta> pilaBicicletas; //Nunca va a ser mas de 5 
    private Cola<Usuario> usuariosEnEspera;
    private Cola<Bicicleta> colaAnclaje;

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

    public boolean tieneBicisEnEspera() {
        return !colaAnclaje.esVacia();
    }

    public boolean tieneCapacidad() {
        return pilaBicicletas.cantElementos() < capacidad;
    }

    public boolean tieneUsuariosEnEspera() {
        return !usuariosEnEspera.esVacia();
    }

    public void agregarBicicleta(Bicicleta b) {
        b.setEstado("Disponible");
        if (tieneCapacidad()) {
            // hay lugar en la estación,va a la pila de bicis ancladas
            pilaBicicletas.apilar(b);
        } else {
            // está llena va a la cola de Bicis en espera de anclaje
            colaAnclaje.encolar(b);
        }
    }

    public void agregarUsuarioEnEspera(Usuario u) {
        this.usuariosEnEspera.encolar(u);
    }

    public void agregarColaAnclaje(Bicicleta b) {
        this.colaAnclaje.encolar(b);
    }

    //Este metodo agarra una bici de la pila, y setea el estado de la bici en "Alquilada" (tambien retorna esa bicicleta)
    public Bicicleta alquilarBicicleta(Usuario u) {
        if (!pilaBicicletas.esVacia()) {
            Bicicleta b = pilaBicicletas.top();

            b.setEstado("Alquilada");
            pilaBicicletas.desapilar();
            return b;
        }
        return null;
    }

}
