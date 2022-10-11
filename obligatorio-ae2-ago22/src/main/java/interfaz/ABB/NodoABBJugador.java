package interfaz.ABB;

import dominio.Jugador;

public class NodoABBJugador{
    Jugador jugador;
    NodoABBJugador izquierda;
    NodoABBJugador derecha;

    public NodoABBJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public NodoABBJugador(Jugador jugador, NodoABBJugador izquierdo, NodoABBJugador derecho) {
        this.jugador = jugador;
        this.izquierda = izquierdo;
        this.derecha = derecho;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public NodoABBJugador getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoABBJugador izquierda) {
        this.izquierda = izquierda;
    }

    public NodoABBJugador getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoABBJugador derecha) {
        this.derecha = derecha;
    }
}
