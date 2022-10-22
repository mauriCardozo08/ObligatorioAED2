package interfaz.Grafo;

public class Vertice<T>{
    private T vertice;

    public Vertice(T vertice) {
        this.vertice = vertice;
    }

    public T getVertice() {
        return vertice;
    }

    public void setVertice(T vertice) {
        this.vertice = vertice;
    }
}
