package tads;




public interface ILista<T> {

    public boolean esVacia();
    public void agregarInicio(T o);
    public void agregarFinal(T o);
    public void borrarInicio();
    public void borrarFin();
    public void vaciar();
    public void mostrar();
    public int cantElementos();
    public void agregarOrdenado(T o);
    public boolean existeElemento(T o);
    public T darElemento(T o);
}
