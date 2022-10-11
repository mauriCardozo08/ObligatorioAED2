package sistema;

import interfaz.ABB.ArbolBinarioBusqueda;
import interfaz.TipoJugador;

public class Main {
    public static void main(String[] args) {
        int cantidadCentros = 5;
        ImplementacionSistema sistema =  new ImplementacionSistema(cantidadCentros);

        //Pruebas
        //ExplorarCentroUrbano(sistema);
        IngresarJugadores(sistema);


    }

    public static void ExplorarCentroUrbano(ImplementacionSistema sistema){
        boolean[] correctas = {true,true,true,false,true,true,true,true};
        int[] puntajes = {1,2,3,6,3,4,4,2};
        int minimo = 5;

        System.out.println(sistema.explorarCentroUrbano(correctas,puntajes,minimo).getValorString());
    }

    public static void IngresarJugadores(ImplementacionSistema sistema){
        String cedula = "2.332.111-1";
        String nombre = "Juan Perez";
        int edad = 8;
        String escuela = "Escuela 2";
        TipoJugador tipo = TipoJugador.INICIAL;
        System.out.println(sistema.registrarJugador(cedula,nombre,edad,escuela,tipo).getValorString());
        //------------------------------------------------------------------------
        String cedula2 = "123.123-5";
        String nombre2 = "Jorge diaz";
        int edad2 = 4;
        String escuela2 = "Escuela 1";
        TipoJugador tipo2 = TipoJugador.AVANZADO;
        System.out.println(sistema.registrarJugador(cedula2,nombre2,edad2,escuela2,tipo2).getValorString());
    }
}
