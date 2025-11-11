package tads;


public interface ICola<T> {

    public boolean esVacia();
    public void encolar(T o);
    public void desencolar();
    public T frente();
    public void vaciar();
    public void mostrar();
    public int cantElementos();
    public boolean existeElemento(T o);
    public T darElemento(T o);
}
