package interfaz.Lista;

public class Nodo<T>{
    public T Valor;
    public Nodo<T> Siguiente;

    public Nodo(T valor){
        Siguiente=null;
        this.Valor=valor;
    }

    public T getValor() {
        return Valor;
    }

    public void setValor(T valor) {
        Valor = valor;
    }

    public Nodo<T> getSiguiente() {
        return Siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        Siguiente = siguiente;
    }
}
