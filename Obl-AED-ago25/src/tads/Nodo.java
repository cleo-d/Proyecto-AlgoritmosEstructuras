package tads;




public class Nodo<T extends Comparable> {
    
    private T dato;
    private Nodo siguiente;
    
    public Nodo(T elDato){
        dato = elDato;
        siguiente = null;
    }

    Nodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     public T getDato() {
        return dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
}
