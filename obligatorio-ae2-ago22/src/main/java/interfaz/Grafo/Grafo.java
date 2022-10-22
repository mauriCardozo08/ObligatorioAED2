package interfaz.Grafo;

import dominio.CentroUrbano;
import interfaz.ABB.VisualizadorGraphViz;
import interfaz.Lista.Nodo;
import interfaz.Pila.Pila;

import java.util.LinkedList;
import java.util.Queue;

public class Grafo {
    private Vertice[] vertices;
    private Camino[][] aristas;
    private final int maxVertices;
    private int largo=0;

    public Grafo(int maxVertices) {
        this.vertices = new Vertice[maxVertices];
        this.aristas = new Camino[maxVertices][maxVertices];
        this.maxVertices = maxVertices;
        for (int i = 0; i < maxVertices; i++) {
            for (int j = 0; j < maxVertices; j++) {
                aristas[i][j]=new Camino();
            }
        }
    }
    public Boolean aptoIngreso(){
        return largo<maxVertices;
    }

    public Vertice[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertice[] vertices) {
        this.vertices = vertices;
    }
    public int getMaxVertices() {
        return maxVertices;
    }

    public void agregarVertice(Vertice vertice){
        if(aptoIngreso()){
            vertices[largo]=vertice;
            largo++;
        }
    }

    public boolean validarCaminoExistente(String codigoCaminoOrigen, String codigoCaminoDestino){
        int indiceCaminoOrigen = -1;
        int indiceCaminoDestino = -1;

        for(int i=0; i<largo; i++){
            CentroUrbano centro = (CentroUrbano) getVertices()[i].getVertice();

            if(centro.getCodigo().equals(codigoCaminoOrigen)){indiceCaminoOrigen=i;}

            if(centro.getCodigo().equals(codigoCaminoDestino)){indiceCaminoDestino=i;}
        }

        if(indiceCaminoOrigen!=-1 && indiceCaminoDestino!=-1){
            return aristas[indiceCaminoOrigen][indiceCaminoDestino].getExiste();
        }

        return false;
    }

    public int getIndicePorCodigo(String codigo){
        for(int i = 0; i<largo; i++){
            CentroUrbano centroUrbano = (CentroUrbano) vertices[i].getVertice();
            if(centroUrbano.getCodigo() == codigo){
                return i;
            }
        }
        return -1;
    }

    public void agregarCamino(int indiceCentroOrigen, int indiceCentroDestino, Camino camino){
        aristas[indiceCentroOrigen][indiceCentroDestino] = camino;
    }

    public String toUrl(){
        return VisualizadorGraphViz.grafoToUrl(vertices,aristas, a->a.getExiste(), v->v.getVertice().toString(), a->String.format("%.2f",a.getKilometros()));
    }

    public void bfs(CentroUrbano centroOrigen, int cantidadDeSaltos) {
        Pila<CentroUrbano> frontera = new Pila<CentroUrbano>();
        boolean[] visitados=new boolean[largo];
        Nodo<CentroUrbano> nodoCentroOrigen = new Nodo<CentroUrbano>(centroOrigen);
        frontera.agregarElemento(nodoCentroOrigen);
        int cantidadNivActual=1;
        int cantidadNivSiguiente=0;
        int cantidadSaltosHastaAhora=0;
        
        while (!frontera.esVacia()){//es vacia
            CentroUrbano vExplorar= (CentroUrbano) frontera.eliminarElemento().getValor();
            cantidadNivActual-=1;
            int indiceCentroUrbano = getIndicePorCodigo(vExplorar.getCodigo());
            if(!visitados[indiceCentroUrbano]){
                
                System.out.println("Nivel "+cantidadSaltosHastaAhora+":"+ vertices[indiceCentroUrbano]);
                visitados[indiceCentroUrbano]=true;
                for (int vAdy = 0; vAdy < maxVertices; vAdy++) {
                    if(esAdyacente(indiceCentroUrbano,vAdy)){
                        Nodo<CentroUrbano> nodoAuxiliar = (Nodo<CentroUrbano>) vertices[vAdy].getVertice();
                        frontera.agregarElemento(nodoAuxiliar);
                        cantidadNivSiguiente+=1;
                    }
                }
                if(cantidadNivActual==0){
                    cantidadSaltosHastaAhora++;
                    cantidadNivActual=cantidadNivSiguiente;
                    cantidadNivSiguiente=0;
                }

            }

        }



    }

    private boolean esAdyacente(int indiceOrigen, int indiceDestino) {
        return aristas[indiceOrigen][indiceDestino].getExiste();
    }

    public CentroUrbano getCentro(int indice) {
        return (CentroUrbano) vertices[indice].getVertice();
    }
}
