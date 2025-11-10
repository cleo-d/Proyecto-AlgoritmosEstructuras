package tads;


public interface IPila<T> {

    public boolean esVacia();
    public void apilar(T o);
    public void desapilar();
    public T top();
    public void vaciar();
    public void mostrar();
    public int cantElementos();
  
}
