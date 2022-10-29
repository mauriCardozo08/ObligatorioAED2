package interfaz.Grafo;

import dominio.CentroUrbano;
import dominio.Determinante;
import dominio.TuplaCaminoMinimo;
import interfaz.ABB.VisualizadorGraphViz;
import interfaz.Lista.Nodo;
import interfaz.Pila.NodoCentroUrbano;
import interfaz.Pila.Pila;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Grafo {
    private Vertice[] vertices;
    private Camino[][] aristas;
    private final int maxVertices;
    private int largo = 0;

    public Camino[][] getAristas() {
        return aristas;
    }

    public Grafo(int maxVertices) {
        this.vertices = new Vertice[maxVertices];
        this.aristas = new Camino[maxVertices][maxVertices];
        this.maxVertices = maxVertices;
        for (int i = 0; i < maxVertices; i++) {
            for (int j = 0; j < maxVertices; j++) {
                aristas[i][j] = new Camino();
            }
        }
    }

    public Boolean aptoIngreso() {
        return largo < maxVertices;
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

    public void agregarVertice(Vertice vertice) {
        if (aptoIngreso()) {
            vertices[largo] = vertice;
            largo++;
        }
    }

    public boolean validarCaminoExistente(String codigoCaminoOrigen, String codigoCaminoDestino) {
        int indiceCaminoOrigen = -1;
        int indiceCaminoDestino = -1;

        for (int i = 0; i < largo; i++) {
            CentroUrbano centro = (CentroUrbano) getVertices()[i].getVertice();

            if (centro.getCodigo().equals(codigoCaminoOrigen)) {
                indiceCaminoOrigen = i;
            }

            if (centro.getCodigo().equals(codigoCaminoDestino)) {
                indiceCaminoDestino = i;
            }
        }

        if (indiceCaminoOrigen != -1 && indiceCaminoDestino != -1) {
            return aristas[indiceCaminoOrigen][indiceCaminoDestino].getExiste();
        }

        return false;
    }

    public int getIndicePorCodigo(String codigo) {
        for (int i = 0; i < largo; i++) {
            CentroUrbano centroUrbano = (CentroUrbano) vertices[i].getVertice();
            if (centroUrbano.getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    public void agregarCamino(int indiceCentroOrigen, int indiceCentroDestino, Camino camino) {
        aristas[indiceCentroOrigen][indiceCentroDestino] = camino;
    }

    public String toUrl() {
        return VisualizadorGraphViz.grafoToUrl(vertices, aristas, a -> a.getExiste(), v -> v.getVertice().toString(), a -> String.format("%.2f", a.getKilometros()));
    }

    public String dfs(CentroUrbano centroOrigen, int cantidadDeSaltos) {
        String retorno = "";
        Pila frontera = new Pila();
        int contador = 1;
        boolean[] visitados = new boolean[maxVertices];

        frontera.agregarElemento(new NodoCentroUrbano(centroOrigen));

        while (!frontera.esVacia()) {
            CentroUrbano vExplorar = frontera.eliminarElemento().getValor();
            int indiceCentroUrbano = getIndicePorCodigo(vExplorar.getCodigo());
            if (!visitados[indiceCentroUrbano]) {
                contador++;
                retorno += vertices[indiceCentroUrbano].getVertice().toString() + "|";
                visitados[indiceCentroUrbano] = true;
                for (int vAdy = 0; vAdy < maxVertices; vAdy++) {
                    if (esAdyacente(indiceCentroUrbano, vAdy)) {
                        CentroUrbano centroAuxiliar = (CentroUrbano) vertices[vAdy].getVertice();
                        frontera.agregarElemento(new NodoCentroUrbano(centroAuxiliar));
                    }
                }
            }

            if (contador > cantidadDeSaltos) {
                return retorno;
            }
        }
        return retorno;


    }

    private boolean esAdyacente(int indiceOrigen, int indiceDestino) {
        return aristas[indiceOrigen][indiceDestino].getExiste();
    }

    public CentroUrbano getCentro(int indice) {
        return (CentroUrbano) vertices[indice].getVertice();
    }

    public TuplaCaminoMinimo dijsktra(int centroUrbanoOrigen, int centroUrbanoDestino, Determinante determinante) {
        double[] distancias = new double[maxVertices];
        for (int i = 0; i < distancias.length; i++) {
            distancias[i] = Double.MAX_VALUE;
        }

        boolean[] visitados = new boolean[maxVertices];
        int[] padres = new int[maxVertices];

        padres[centroUrbanoOrigen] = centroUrbanoOrigen;
        distancias[centroUrbanoOrigen] = 0;

        while (!estaTodoVisitado(visitados)) {
            int vExplorar = dameElDeMenorDistanciaNoVisitado(distancias, visitados);
            double distanciaV = distancias[vExplorar];
            for (int vAdy = 0; vAdy < maxVertices; vAdy++) {
                if (esAdyacente(vExplorar, vAdy)) {
                    double valorArista;
                    if(determinante.equals(Determinante.KILOMETROS)){
                        valorArista = distanciaV + aristas[vExplorar][vAdy].getKilometros();
                    }else{
                        valorArista = distanciaV + aristas[vExplorar][vAdy].getCosto();
                    }
                    double distanciaYendoPorVAExplorar = distanciaV + valorArista;

                    if (distanciaYendoPorVAExplorar < distancias[vAdy]) {
                        distancias[vAdy] = valorArista;
                        padres[vAdy] = vExplorar;
                    }

                }
            }
            visitados[vExplorar] = true;
        }
        return reconstruirCamino(padres, centroUrbanoOrigen, centroUrbanoDestino,determinante);
    }

    private TuplaCaminoMinimo reconstruirCamino(int[] padres, int centroUrbanoOrigen, int centroUrbanoDestino, Determinante determinante) {
        StringBuilder retorno = new StringBuilder();
        int verticeActual = centroUrbanoDestino;
        String[] cadenaDeRetorno = new String[padres.length];
        int indice = padres.length-1;
        cadenaDeRetorno[0]=vertices[centroUrbanoOrigen].getVertice().toString();
        double valorMaximo = 0;

        while (verticeActual != centroUrbanoOrigen) {
            if(determinante.equals(Determinante.KILOMETROS)){
                valorMaximo += aristas[padres[verticeActual]][verticeActual].getKilometros();
            }else{
                valorMaximo += aristas[padres[verticeActual]][verticeActual].getCosto();
            }

            cadenaDeRetorno[indice] = vertices[verticeActual].getVertice().toString();
            verticeActual = padres[verticeActual];
            indice--;
        }

        for (int i = 0; i < cadenaDeRetorno.length; i++) {
            if(cadenaDeRetorno[i]!=null) {
                retorno.append(cadenaDeRetorno[i]);
                if(i+1!=cadenaDeRetorno.length) retorno.append("|");
            }
        }

        return new TuplaCaminoMinimo(retorno.toString(),valorMaximo);
    }

    private boolean estaTodoVisitado(boolean[] visitados) {
        for (int i = 0; i < visitados.length; i++) {
            if (!visitados[i]) return false;
        }
        return true;
    }

    private int dameElDeMenorDistanciaNoVisitado(double[] distancia, boolean[] visitado) {
        int minIdx = -1;
        double minValue = Double.MAX_VALUE;

        for (int i = 0; i < distancia.length; i++) {
            if (distancia[i] < minValue && !visitado[i]) {
                minIdx = i;
                minValue = distancia[i];
            }
        }

        return minIdx;
    }
}
