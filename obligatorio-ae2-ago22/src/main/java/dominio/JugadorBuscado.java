package dominio;

public class JugadorBuscado {
    private Jugador jugador;
    private int cantidadDeElementosRecorridos;

    public JugadorBuscado(Jugador jugador,int cantidad){
        this.jugador = jugador;
        this.cantidadDeElementosRecorridos = cantidad;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getCantidadDeElementosRecorridos() {
        return cantidadDeElementosRecorridos;
    }

    public void setCantidadDeElementosRecorridos(int cantidadDeElementosRecorridos) {
        this.cantidadDeElementosRecorridos = cantidadDeElementosRecorridos;
    }
}
