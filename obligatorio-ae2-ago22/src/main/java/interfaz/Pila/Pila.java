package interfaz.Pila;

import dominio.CentroUrbano;
import interfaz.Lista.Nodo;

public class Pila {
    private NodoCentroUrbano inicio;
    private int cantElementos;

    public NodoCentroUrbano getInicio() {
        return inicio;
    }

    public void setInicio(NodoCentroUrbano nodo) {
        this.inicio = nodo;
    }

    public Pila() {
        this.inicio = null;
    }

    public void agregarElemento(NodoCentroUrbano nuevo) {
        nuevo.setSiguiente(getInicio());
        inicio = nuevo;
        cantElementos ++;
    }

    public NodoCentroUrbano eliminarElemento() {
        NodoCentroUrbano elementoAEliminar = null;
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
