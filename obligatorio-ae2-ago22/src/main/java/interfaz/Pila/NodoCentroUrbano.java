package interfaz.Pila;

import dominio.CentroUrbano;

public class NodoCentroUrbano{
    public CentroUrbano Valor;
    public NodoCentroUrbano Siguiente;

    public NodoCentroUrbano(CentroUrbano valor){
        Siguiente=null;
        this.Valor=valor;
    }

    public CentroUrbano getValor() {
        return Valor;
    }

    public void setValor(CentroUrbano valor) {
        Valor = valor;
    }

    public NodoCentroUrbano getSiguiente() {
        return Siguiente;
    }

    public void setSiguiente(NodoCentroUrbano siguiente) {
        Siguiente = siguiente;
    }
}
