package dominio;

public class TuplaCaminoMinimo {
    private String cadenaDeVertices;
    private double costoTotalDelCamino;

    public String getCadenaDeVertices() {
        return cadenaDeVertices;
    }

    public void setCadenaDeVertices(String cadenaDeVertices) {
        this.cadenaDeVertices = cadenaDeVertices;
    }

    public double getCostoTotalDelCamino() {
        return costoTotalDelCamino;
    }

    public void setCostoTotalDelCamino(double costoTotalDelCamino) {
        this.costoTotalDelCamino = costoTotalDelCamino;
    }

    public TuplaCaminoMinimo(String cadenaDeVertices, double costoTotalDelCamino) {
        this.cadenaDeVertices = cadenaDeVertices;
        this.costoTotalDelCamino = costoTotalDelCamino;
    }
}
