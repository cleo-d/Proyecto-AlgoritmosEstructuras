package tads;



public class Pila<T extends Comparable> implements IPila<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantElementos;

    public Pila() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public void apilar(T n) {

        Nodo nuevo = new Nodo(n);

        if (esVacia()) {
            fin = nuevo;
        }

        nuevo.setSiguiente(inicio);
        inicio = nuevo;

        cantElementos++;

    }

  

    @Override
    public void desapilar() {
        if (!esVacia()) {

            if (inicio == fin) {
                vaciar();
            } else {
                Nodo aBorrar = inicio;
                inicio = inicio.getSiguiente();
                aBorrar.setSiguiente(null);
                cantElementos--;
            }
        }
    }

    @Override
    public void vaciar() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }

    @Override
    public void mostrar() {

        if (!esVacia()) {
            Nodo aux = inicio;

            while (aux != null) {
                System.out.println(aux.getDato());
                aux = aux.getSiguiente();
            }
        }
    }

    @Override
    public int cantElementos() {
        return cantElementos;
    }

    @Override
    public T top() {
         return inicio.getDato();
    }
    
        public Pila copiarPila(Pila p) {

        Pila aux = new Pila();
        Pila ret = new Pila();

        while (!p.esVacia()) {
            aux.apilar(p.top());
            p.desapilar();
        }
        

        while (!aux.esVacia()) {
            ret.apilar(aux.top());
            p.apilar(aux.top());
            aux.desapilar();
        }
        return ret;
    }

}
