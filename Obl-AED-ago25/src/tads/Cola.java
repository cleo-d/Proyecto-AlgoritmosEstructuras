package tads;

public class Cola<T extends Comparable> implements ICola<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantElementos;

    public Cola() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    //DEBERIA TENER UNA OPCION DE AGREGAR INICIO?
    private void agregarInicio(T n) {

        Nodo nuevo = new Nodo(n);

        if (esVacia()) {
            fin = nuevo;
        }

        nuevo.setSiguiente(inicio);
        inicio = nuevo;

        cantElementos++;

    }

    @Override
    public void encolar(T n) {

        if (esVacia()) {
            agregarInicio(n);
        } else {
            Nodo nuevo = new Nodo(n);
            fin.setSiguiente(nuevo);
            fin = nuevo;
            cantElementos++;

        }
    }

    @Override
    public void desencolar() {
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

        String muestra = "";
        
        if (!esVacia()) {
            Nodo aux = inicio;

            while (aux != null) {
                muestra = aux.getDato() + " - " + muestra;
                aux = aux.getSiguiente();
            }
        }
        
        System.out.println(muestra);
    }

    @Override
    public int cantElementos() {
        return cantElementos;
    }

    @Override
    public boolean existeElemento(T o) {

        Nodo aux = inicio;
        boolean existe = false;

        while (aux != null && !existe) {
            if (aux.getDato().equals(o)) {
                existe = true;
            }
            aux = aux.getSiguiente();
        }
        return existe;
    }

    @Override
    public T darElemento(T o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Nodo<T> getInicio() {
        return inicio;
    }

    public T frente() {
        return this.getInicio().getDato();
    }

}
