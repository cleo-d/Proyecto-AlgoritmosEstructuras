package dominio;

import tads.ListaNodos;

public class Estacion implements Comparable<Estacion> {

    private String nombre;
    private String barrio;
    private int capacidad;
    //No estoy seguro si deberia inluir una lista de Bicicletas aca 
    private ListaNodos<Bicicleta> listaBicicletas;

    public Estacion(String unNombre, String unBarrio, int unaCapacidad) {
        this.setNombre(unNombre);
        this.setBarrio(unBarrio);
        this.setCapacidad(unaCapacidad);
        //Si las instancias creadas de Estacion incluyen una lista de bicicletas deberia tener lo siguiente:
        listaBicicletas = new ListaNodos();

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

}
