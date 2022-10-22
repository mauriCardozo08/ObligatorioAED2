package dominio;

public class CentroUrbano {
    private String codigo;
    private String nombre;

    public CentroUrbano() {
    }

    public CentroUrbano(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean validar(){
        return getNombre()!=null && !getNombre().equals("") && getCodigo()!=null && !getCodigo().equals("");
    }

    public String toString(){
        return  "Codigo: "+this.getCodigo() + " - Nombre: "+this.getNombre();
    }

}
