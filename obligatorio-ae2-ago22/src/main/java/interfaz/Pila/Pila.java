package interfaz.Pila;

import interfaz.Lista.Nodo;

public class Pila<T> {
    private Nodo<T> inicio;
    private int cantElementos;

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo<T> nodo) {
        this.inicio = nodo;
    }

    public Pila() {
        this.inicio = null;
    }

    public void agregarElemento(Nodo<T> nodo) {
            Nodo nuevo = new Nodo(nodo);
            nuevo.setSiguiente(getInicio());
            inicio = nuevo;
            cantElementos ++;
    }

    public Nodo<T> eliminarElemento() {
        Nodo<T> elementoAEliminar = null;
        if (!this.esVacia()) {
            elementoAEliminar = this.inicio;
            inicio = getInicio().getSiguiente();
            cantElementos--;
        }
        return  elementoAEliminar;
    }

    public boolean esVacia(){
        return cantElementos == 0;
    }
}
