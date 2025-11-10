
package dominio;

import tads.ListaNodos;
import tads.Nodo;
//Agrego un comentario (Cleo)
//Podria ser nombrada 'Alquiler' ya que representa una instancia de una bici siendo alquilada por un usuario
public class Alquiler implements Comparable <Alquiler> {
   
    //Esta clase se puede utilizar para guardar la informacion de un usuario que retira una bici de una estacion
    //Las estaciones van a tener una pila de retiros 
    private Nodo<Usuario> usuario;
    private Nodo<Bicicleta> bici;
    private Nodo<Estacion> estacion;
    
        public Alquiler(Nodo<Usuario> unUsuario, Nodo<Bicicleta> unaBici, Nodo<Estacion> unaEstacion) {
            
        this.setUsuario(unUsuario);
        this.setBici(unaBici);
        this.setEstacion(unaEstacion);

    }

    
    public Nodo<Usuario> getUsuario() {
        return usuario;
    }


    public void setUsuario(Nodo<Usuario> usuario) {
        this.usuario = usuario;
    }


    public Nodo<Bicicleta> getBici() {
        return bici;
    }


    public void setBici(Nodo<Bicicleta> bici) {
        this.bici = bici;
    }


    public Nodo<Estacion> getEstacion() {
        return estacion;
    }


    public void setEstacion(Nodo<Estacion> estacion) {
        this.estacion = estacion;
    }

    @Override
    public int compareTo(Alquiler o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
