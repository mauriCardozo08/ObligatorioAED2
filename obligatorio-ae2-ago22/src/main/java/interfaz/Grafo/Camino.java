package interfaz.Grafo;

import interfaz.EstadoCamino;

public class Camino {
    private boolean existe;
    private double costo;
    private double tiempo;
    private double kilometros;
    private EstadoCamino estado;

    public Camino(){}

    public Camino(double costo, double tiempo, double kilometros, EstadoCamino estado) {
        this.existe = false;
        this.costo = costo;
        this.tiempo = tiempo;
        this.kilometros = kilometros;
        this.estado = estado;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public double getKilometros() {
        return kilometros;
    }

    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }

    public EstadoCamino getEstado() {
        return estado;
    }

    public void setEstado(EstadoCamino estado) {
        this.estado = estado;
    }

    public boolean validarDoubles(){
        if(costo>0 && tiempo>0 && kilometros>0){
            return true;
        }
        return false;
    }

    public boolean validarNull(){
        if(estado!=null){
            return true;
        }
        return false;
    }
}
