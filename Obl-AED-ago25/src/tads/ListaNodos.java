package tads;

public class ListaNodos<T extends Comparable> implements ILista<T> {


    private Nodo inicio;
    private Nodo fin;
    private int cantElementos;

    public ListaNodos() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }

    public T buscarElemento(T elem) {
        Nodo<T> aux = getInicio();
        while (aux != null) {
            if (aux.getDato().equals(elem)) {
                return aux.getDato();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public T obtenerElementoDePos(int pos) {

        int posAct = 0;
        Nodo<T> aux = getInicio();

        while (pos != posAct) {
            aux = aux.getSiguiente();
            posAct++;
        }

        return aux.getDato();

    }

    @Override
    public boolean existeElemento(T n) {
        boolean existe = false;
        Nodo aux = getInicio();

        while (aux != null && !existe) {
            if (aux.getDato().equals(n)) {
                existe = true;
            }
            aux = aux.getSiguiente();
        }
        return existe;
    }

    @Override
    public boolean esVacia() {
        return getInicio() == null;
    }

    @Override
    public void agregarInicio(T n) {

        Nodo nuevo = new Nodo(n);

        if (esVacia()) {
            fin = nuevo;
        }

        nuevo.setSiguiente(getInicio());
        setInicio(nuevo);
        cantElementos++;

    }

    @Override
    public void agregarFinal(T n) {
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
    public void borrarInicio() {
        if (!esVacia()) {

            Nodo aBorrar = getInicio();
            setInicio(getInicio().getSiguiente());
            aBorrar.setSiguiente(null);
            cantElementos--;
        }
    }

    @Override
    public void borrarFin() {

        if (!esVacia()) {

            if (getInicio().getSiguiente() == null) { //solo tengo 1
                vaciar();
            } else {
                Nodo aux = getInicio();

                while (aux.getSiguiente().getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }

                aux.setSiguiente(null);
                cantElementos--;
            }
        }
    }

    @Override
    public void vaciar() {
        setInicio(null);
        fin = null;
        cantElementos = 0;
    }

    @Override
    public void mostrar() {

        if (!esVacia()) {
            Nodo aux = getInicio();

            while (aux != null) {
                System.out.print(aux.getDato() + " - ");
                aux = aux.getSiguiente();
            }
        }
    }
    
    //metodo para respetar el tema de caja negra con usuarios
    public String concatenar(String separador) {
    String resultado = "";
    Nodo<T> aux = inicio;

    while (aux != null) {
        resultado += aux.getDato();
        aux = aux.getSiguiente();
        if (aux != null) {
            resultado += separador;
        }
    }

    return resultado;
}

    

    @Override
    public int cantElementos() {
        return cantElementos;
        /*
        int cant = 0;

        if (!esVacia()) {
            Nodo aux = inicio;

            while (aux != null) {
                cant++;
                aux = aux.getSiguiente();
            }
        }
        return cant;
         */
    }

    public void agregarOrdenado(T o) {

        if (esVacia() || getInicio().getDato().compareTo(o) >= 0) {
            // Agrego al principio
            agregarInicio(o);
        } else if (fin.getDato().compareTo(o) <= 0) {
            // Agrego al Final
            agregarFinal(o);
        } else {
            Nodo aux = getInicio();
            while (aux.getSiguiente() != null
                    && aux.getSiguiente().getDato().compareTo(o) < 0) {
                aux = aux.getSiguiente();
            }

            Nodo nuevo = new Nodo(o);
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
            cantElementos++;

        }

    }
    
        public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    @Override
    public T darElemento(T o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    //metodos para seguir respetando caja negra en listarBicisDeposito
    public String listarRecursivo(String separador) {
    return listarRecursivoInterno(inicio, separador);
}

private String listarRecursivoInterno(Nodo<T> nodo, String separador) {
    if (nodo == null) {
        return "";
    }

    String actual = nodo.getDato().toString();

    if (nodo.getSiguiente() != null) {
        return actual + separador + listarRecursivoInterno(nodo.getSiguiente(), separador);
    } else {
        return actual;
    }
}

}
