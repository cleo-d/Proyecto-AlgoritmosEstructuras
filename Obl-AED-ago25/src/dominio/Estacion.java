package dominio;

import tads.Cola;
import tads.ListaNodos;
import tads.Pila;

public class Estacion implements Comparable<Estacion> {

    private String nombre;
    private String barrio;
    private int capacidad;
    private Pila<Bicicleta> listaBicicletas; //Nunca va a ser mas de 5 
    private Cola<Usuario> usuariosEnEspera;

    public Estacion(String unNombre, String unBarrio, int unaCapacidad) {
        this.setNombre(unNombre);
        this.setBarrio(unBarrio);
        this.setCapacidad(unaCapacidad);
        
        listaBicicletas = new Pila();
        usuariosEnEspera = new Cola();

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
    
    public boolean tieneBicicletasAncladas(){
        if(capacidad == 0) {
            return false;
        }else{
            return true;
        }
    }
    
    public boolean tieneUsuariosEnEspera(){
        if (usuariosEnEspera.esVacia()){
            return false;
        }else {
            return true;
        }
    }
    

}
