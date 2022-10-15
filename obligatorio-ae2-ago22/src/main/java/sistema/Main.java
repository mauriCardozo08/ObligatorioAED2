package sistema;

import interfaz.ABB.ArbolBinarioBusqueda;
import interfaz.Retorno;
import interfaz.TipoJugador;

public class Main {
    public static void main(String[] args) {
        int cantidadCentros = 6;
        ImplementacionSistema sistema =  new ImplementacionSistema(cantidadCentros);

        //Pruebas
        //explorarCentroUrbano(sistema);
        registrarJugadores(sistema);
        //Falta filtrar jugadores
        //buscarJugadorPorCedula(sistema);
        //listarJugadoresDescendente(sistema);
        //listarJugadoresAscendente(sistema);
        //listarJugadoresPorTipo(sistema);
        registrarCentroUrbano(sistema);
    }

    public static void explorarCentroUrbano(ImplementacionSistema sistema){
        boolean[] correctas = {true,true,true,false,true,true,true,true};
        int[] puntajes = {1,2,3,6,3,4,4,2};
        int minimo = 5;

        System.out.println(sistema.explorarCentroUrbano(correctas,puntajes,minimo).getValorString());
    }

    public static void registrarJugadores(ImplementacionSistema sistema){
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
        //------------------------------------------------------------------------
        String cedula3 = "2.332.111-3";
        String nombre3 = "Alberto Perez";
        int edad3 = 12;
        String escuela3 = "Escuela 2";
        TipoJugador tipo3 = TipoJugador.MEDIO;
        System.out.println(sistema.registrarJugador(cedula3,nombre3,edad3,escuela3,tipo3).getValorString());

        System.out.println(sistema.jugadores.toUrl());
    }

    public static void buscarJugadorPorCedula(ImplementacionSistema sistema){
        String cedulaDeAlbertoPerez = "2.332.111-3";
        Retorno retorno = sistema.buscarJugador(cedulaDeAlbertoPerez);
        System.out.println(retorno.getValorString());
        System.out.println("Cantidad de iteraciones "+retorno.getValorInteger());
    }

    public static void listarJugadoresAscendente(ImplementacionSistema sistema){
        System.out.println(sistema.listarJugadoresPorCedulaAscendente().getValorString());
    }

    public static void listarJugadoresDescendente(ImplementacionSistema sistema){
        System.out.println(sistema.listarJugadoresPorCedulaDescendente().getValorString());
    }

    public static void listarJugadoresPorTipo(ImplementacionSistema sistema){
        TipoJugador tipo = TipoJugador.MEDIO;
        System.out.println(sistema.listarJugadoresPorTipo(tipo).getValorString());
    }

    public static void registrarCentroUrbano(ImplementacionSistema sistema){
        String codigo = "CU1";
        String nombre = "Centro Urbano 1";
        System.out.println(sistema.registrarCentroUrbano(codigo,nombre).getValorString());
        String codigo2 = "CU2";
        String nombre2 = "Centro Urbano 2";
        System.out.println(sistema.registrarCentroUrbano(codigo2,nombre2).getValorString());
    }
}
