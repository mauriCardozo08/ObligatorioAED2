package dominio;

import interfaz.TipoJugador;

public class Jugador {
    private String Cedula;
    private String Nombre;
    private int Edad;
    private String Escuela;
    private TipoJugador TipoJugador;

    public Jugador(){

    }
    public Jugador(String cedula, String nombre, int edad, String escuela, TipoJugador tipoJugador){
        this.Cedula = cedula;
        this.Nombre = nombre;
        this.Edad = edad;
        this.Escuela = escuela;
        this.TipoJugador = tipoJugador;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public String getEscuela() {
        return Escuela;
    }

    public void setEscuela(String escuela) {
        Escuela = escuela;
    }

    public interfaz.TipoJugador getTipoJugador() {
        return TipoJugador;
    }

    public void setTipoJugador(interfaz.TipoJugador tipoJugador) {
        TipoJugador = tipoJugador;
    }

    public boolean validar(){
        if(Cedula != null && Nombre!=null && Escuela!=null && TipoJugador!=null){
            if(!Cedula.isEmpty() && !Nombre.isEmpty() && !Escuela.isEmpty()){
                return true;
            }
        }
        return false;
    }

    public boolean validarFormatoCedula(){
        String expresionRegularValidarSieteDigitos = "^([1-9]{1})[.]\\d{3}[.]\\d{3}$";
        String expresionRegularValidarSeisDigitos = "^([1-9]{3}|\\d{2}|\\d{1})[.]\\d{3}$";
        String expresionRegularValidarDigitoVerificador = "^[0-9]$";

        String cedula = this.getCedula();
        String digitoVerificador = "";

        if(cedula.contains("-")) {
             digitoVerificador = cedula.split("-")[1];
            cedula = cedula.split("-")[0];
        }

        if(digitoVerificador.matches(expresionRegularValidarDigitoVerificador))
        {
            if(cedula.matches(expresionRegularValidarSeisDigitos) || cedula.matches(expresionRegularValidarSieteDigitos))
            {
                return true;
            }
        }
        return false;
    }

    public int compareTo(Jugador jugadorAComparar){
        //Preguntar a profesor sobre como hacer la comparacion de las cedulas.
        //Como definir cual es mayor o menor?
        /*
        if(this.getCedula()<jugadorAComparar.getCedula()){
            return -1;
        }else if(this.getCedula()==jugadorAComparar.getCedula()){
            return 0;
        }
        else{
            return 1;
        }
    */
        return this.getCedulaAsInt() - jugadorAComparar.getCedulaAsInt();
    }
    private int getCedulaAsInt(){
        String cedula = this.getCedula().replace(".","");
        cedula = cedula.replace("-","");
        return Integer.parseInt(cedula);
    }
    @Override
    public String toString() {
        return Cedula+";"+Nombre+";"+Edad+";"+Escuela+";"+TipoJugador.getValor();
    }
}
