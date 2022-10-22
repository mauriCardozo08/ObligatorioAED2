package interfaz.Grafo;

import dominio.CentroUrbano;

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

}
