
package dominio;
//Podria ser nombrada 'Alquiler' ya que representa una instancia de una bici siendo alquilada por un usuario
public class Alquiler implements Comparable <Alquiler> {
    
    //Esta clase se puede utilizar para guardar la informacion de un usuario que retira una bici de una estacion
    //Las estaciones van a tener una pila de retiros 
    private Usuario usuario;
    private Bicicleta bici;
    private Estacion estacion;
    
        public Alquiler(Usuario unUsuario, Bicicleta unaBici, Estacion unaEstacion) {
            
        this.setUsuario(unUsuario);
        this.setBici(unaBici);
        this.setEstacion(unaEstacion);

    }

    
    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Bicicleta getBici() {
        return bici;
    }


    public void setBici (Bicicleta bici) {
        this.bici = bici;
    }


    public Estacion getEstacion() {
        return estacion;
    }


    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    @Override
    public int compareTo(Alquiler o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String toString() {
        return bici.getCodigo() + "#" + usuario.getCedula() + "#" + estacion.getNombre();
}
    
    
}
