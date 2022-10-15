package interfaz.ABB;

import dominio.Jugador;
import dominio.JugadorBuscado;

public class ArbolBinarioBusqueda{
    private NodoABBJugador raiz;

    public ArbolBinarioBusqueda(){

    }
    public ArbolBinarioBusqueda(NodoABBJugador raiz) {
        this.setRaiz(raiz);
    }

    public NodoABBJugador getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoABBJugador raiz) {
        this.raiz = raiz;
    }

    public Boolean insertar(Jugador jugador){
        if(getRaiz()==null){
            setRaiz(new NodoABBJugador(jugador));
            return true;
        }else{
            return insertar(jugador, getRaiz());
        }
    }

    public Boolean existeJugador(Jugador jugadorBuscado){
        if(getRaiz()!=null){
            return existeJugador(jugadorBuscado,getRaiz());
        }
        return false;
    }
    public JugadorBuscado obtenerJugador(Jugador jugadorBuscado){
        if(getRaiz()!=null){
            return obtenerJugador(jugadorBuscado,getRaiz(),0);
        }
        return null;
    }
    private Boolean insertar(Jugador jugadorAInsertar, NodoABBJugador raiz){
        if(raiz.getJugador().compareTo(jugadorAInsertar)<0){
            if(raiz.getDerecha()!=null){
                return insertar(jugadorAInsertar,raiz.getDerecha());
            }else{
                raiz.setDerecha(new NodoABBJugador(jugadorAInsertar));
                return true;
            }
        }else if(raiz.getJugador().compareTo(jugadorAInsertar)==0){
            return false;
        }else {
            if (raiz.getIzquierda() != null) {
                return insertar(jugadorAInsertar, raiz.getIzquierda());
            } else {
                raiz.setIzquierda(new NodoABBJugador(jugadorAInsertar));
                return true;
            }
        }
    }

    private Boolean existeJugador(Jugador jugadorBuscado,NodoABBJugador raiz){
        if(raiz==null) return false;

        if(raiz.getJugador().compareTo(jugadorBuscado)==-1){
            return existeJugador(jugadorBuscado,raiz.getDerecha());
        }else if(raiz.getJugador().compareTo(jugadorBuscado)==0){
            return true;
        }else{
            return existeJugador(jugadorBuscado,raiz.getIzquierda());
        }
    }

    private JugadorBuscado obtenerJugador(Jugador jugadorBuscado, NodoABBJugador raiz, int cantidadDeElementosRecorridos){
        if(raiz==null) return null;

        if(raiz.getJugador().compareTo(jugadorBuscado)<0){
            return obtenerJugador(jugadorBuscado,raiz.getDerecha(),cantidadDeElementosRecorridos+1);
        }else if(raiz.getJugador().compareTo(jugadorBuscado)==0){
            return new JugadorBuscado(raiz.getJugador(),cantidadDeElementosRecorridos);
        }else{
            return obtenerJugador(jugadorBuscado,raiz.getIzquierda(),cantidadDeElementosRecorridos+1);
        }
    }




    public String listarJugadoresPorCedulaAscendente(){
        return listarJugadoresPorCedulaAscendente(getRaiz(),"");
    }
    private String listarJugadoresPorCedulaAscendente(NodoABBJugador raiz, String cadenaDeJugadores){
        if(raiz!=null){
            cadenaDeJugadores = listarJugadoresPorCedulaAscendente(raiz.getIzquierda(),cadenaDeJugadores);
            cadenaDeJugadores+="|"+raiz.getJugador().toString();
            cadenaDeJugadores = listarJugadoresPorCedulaAscendente(raiz.getDerecha(),cadenaDeJugadores);
        }
        return cadenaDeJugadores;
    }




    public String listarJugadoresPorCedulaDescendente(){
        return listarJugadoresPorCedulaDescendente(getRaiz(),"");
    }
    private String listarJugadoresPorCedulaDescendente(NodoABBJugador raiz, String cadenaDeJugadores){
        if(raiz!=null){
            cadenaDeJugadores = listarJugadoresPorCedulaDescendente(raiz.getDerecha(),cadenaDeJugadores);
            cadenaDeJugadores+="|"+raiz.getJugador().toString();
            cadenaDeJugadores = listarJugadoresPorCedulaDescendente(raiz.getIzquierda(),cadenaDeJugadores);
        }
        return cadenaDeJugadores;
    }




    public String toUrl() {
        return VisualizadorGraphViz.arbolBinToUrl(raiz, a -> a.jugador, a -> a.izquierda, a -> a.derecha);
    }
}
