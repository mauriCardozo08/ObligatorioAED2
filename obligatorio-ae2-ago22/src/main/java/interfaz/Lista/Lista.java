package interfaz.Lista;

public class Lista<T> {

    public Lista(){
        Primero=null;
    }
    private Nodo<T> Primero;

    public Nodo<T> getPrimero() {
        return Primero;
    }
    public void setPrimero(Nodo<T> primero) {
        Primero = primero;
    }

    public void agregarAlPrincipio(T valorNuevo) {
        Nodo nuevo = new Nodo(valorNuevo);
        if(this.Primero==null){
            this.setPrimero(nuevo);
        }else{
            Nodo aux = this.getPrimero();
            this.setPrimero(nuevo);
            this.getPrimero().setSiguiente(aux);
        }
    }

    public void agregarAlFinal(T elementoNuevo) {
        Nodo nuevo = new Nodo(elementoNuevo);
        if(this.Primero==null){
            this.setPrimero(nuevo);
        }else{
            Nodo aux = this.getPrimero();
            while(aux.getSiguiente()!=null){
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
    }

    public void eliminarPrincipio() {
        if(this.getPrimero()!=null) {
            this.setPrimero(this.getPrimero().getSiguiente());
        }
    }

    public int getLargo() {
        int largo=0;
        if(this.getPrimero()!=null){
            Nodo aux = this.getPrimero();
            while(aux!=null){
                largo++;
                aux=aux.getSiguiente();
            }
        }
        return largo;
    }

    public boolean estaVacia() {
        return this.getPrimero()==null;
    }

    public T obtener(int indice) {
        if(this.getPrimero()!=null){
            int indiceAuxiliar = 0;
            Nodo aux = this.getPrimero();
            while (aux!=null){
                if(indiceAuxiliar==indice){
                    return (T) aux.getValor();
                }
                indiceAuxiliar++;
                aux = aux.getSiguiente();
            }
        }
        return null;
    }
}
