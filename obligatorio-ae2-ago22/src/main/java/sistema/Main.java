package sistema;

import interfaz.ABB.ArbolBinarioBusqueda;
import interfaz.Consulta;
import interfaz.EstadoCamino;
import interfaz.Grafo.Camino;
import interfaz.Lista.Lista;
import interfaz.Lista.Nodo;
import interfaz.Retorno;
import interfaz.TipoJugador;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int cantidadCentros = 6;
        ImplementacionSistema sistema =  new ImplementacionSistema();
        sistema.inicializarSistema(cantidadCentros);

        //Pruebas
        //explorarCentroUrbano(sistema);
        registrarJugadores(sistema);
        //Falta filtrar jugadores
        //buscarJugadorPorCedula(sistema);
        //listarJugadoresDescendente(sistema);
        //listarJugadoresAscendente(sistema);
        //listarJugadoresPorTipo(sistema);
        //registrarCentroUrbano(sistema);
        //registrarCaminos(sistema);
        //actualizarCaminos(sistema);
        //System.out.println(sistema.centrosUrbanos.toUrl());
        //listarCentrosUrbanos(sistema);
        //calcularCaminoMinimo(sistema);
        filtrarJugadores(sistema);
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
        sistema.registrarJugador(cedula,nombre,edad,escuela,tipo);
        //------------------------------------------------------------------------
        String cedula2 = "123.123-5";
        String nombre2 = "Jorge";
        int edad2 = 6;
        String escuela2 = "Escuela 1";
        TipoJugador tipo2 = TipoJugador.AVANZADO;
        sistema.registrarJugador(cedula2,nombre2,edad2,escuela2,tipo2);
        //------------------------------------------------------------------------
        String cedula3 = "2.332.111-3";
        String nombre3 = "Alberto Perez";
        int edad3 = 2;
        String escuela3 = "Escuela 2";
        TipoJugador tipo3 = TipoJugador.MEDIO;
        sistema.registrarJugador(cedula3,nombre3,edad3,escuela3,tipo3);

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
        String codigo3 = "CU3";
        String nombre3 = "Centro Urbano 3";
        System.out.println(sistema.registrarCentroUrbano(codigo3,nombre3).getValorString());
        String codigo4 = "CU4";
        String nombre4 = "Centro Urbano 4";
        System.out.println(sistema.registrarCentroUrbano(codigo4,nombre4).getValorString());
        String codigo5 = "CU5";
        String nombre5 = "Centro Urbano 5";
        System.out.println(sistema.registrarCentroUrbano(codigo5,nombre5).getValorString());
        String codigo6 = "CU6";
        String nombre6 = "Centro Urbano 6";
        System.out.println(sistema.registrarCentroUrbano(codigo6,nombre6).getValorString());
    }


    public static void registrarCaminos(ImplementacionSistema sistema){
        EstadoCamino estadoCamino1 = EstadoCamino.BUENO;
        System.out.println(sistema.registrarCamino("CU1", "CU2", 2, 14, 1, estadoCamino1).getValorString());

        EstadoCamino estadoCamino2 = EstadoCamino.MALO;
        System.out.println(sistema.registrarCamino("CU2", "CU3", 2, 20, 1, estadoCamino2).getValorString());

        EstadoCamino estadoCamino3 = EstadoCamino.EXCELENTE;
        System.out.println(sistema.registrarCamino("CU3", "CU4", 200, 50, 1, estadoCamino3).getValorString());

        EstadoCamino estadoCamino4 = EstadoCamino.EXCELENTE;
        System.out.println(sistema.registrarCamino("CU4", "CU6", 2, 50, 1, estadoCamino4).getValorString());

        EstadoCamino estadoCamino5 = EstadoCamino.EXCELENTE;
        System.out.println(sistema.registrarCamino("CU1", "CU6", 200, 50, 1, estadoCamino5).getValorString());

        EstadoCamino estadoCamino6 = EstadoCamino.EXCELENTE;
        System.out.println(sistema.registrarCamino("CU5", "CU4", 2, 50, 1, estadoCamino6).getValorString());

        EstadoCamino estadoCamino7 = EstadoCamino.EXCELENTE;
        System.out.println(sistema.registrarCamino("CU3", "CU5", 2, 50, 1, estadoCamino6).getValorString());
    }

    public static void actualizarCaminos(ImplementacionSistema sistema) {
        EstadoCamino estadoCamino1 = EstadoCamino.BUENO;
        System.out.println(sistema.actualizarCamino("CU1", "CU2", 1, 14, 25, estadoCamino1).getValorString());

        EstadoCamino estadoCamino2 = EstadoCamino.MALO;
        System.out.println(sistema.actualizarCamino("CU3", "CU2", 2, 20, 100, estadoCamino2).getValorString());
    }

    public static void listarCentrosUrbanos(ImplementacionSistema sistema) {
        System.out.println(sistema.listadoCentrosCantDeSaltos("CU1", 0).getValorString());
    }

    public static void calcularCaminoMinimo(ImplementacionSistema sistema){
        Retorno retorno = sistema.viajeCostoMinimoMonedas("CU1","CU6");
        System.out.println(retorno.getValorInteger()+"");
        System.out.println(retorno.getValorString());
    }

    public static void filtrarJugadores(ImplementacionSistema sistema){
        Consulta.NodoConsulta edadNodo =
                new Consulta.NodoConsulta(
                        Consulta.TipoNodoConsulta.EdadMayor,
                        5,
                        null,
                        null,
                        null);

        Consulta.NodoConsulta nombreNodo =
                new Consulta.NodoConsulta(
                        Consulta.TipoNodoConsulta.NombreIgual,
                        0,
                        "Jorge",
                        null,
                        null);

        Consulta.NodoConsulta escuelaNodo =
                new Consulta.NodoConsulta(
                        Consulta.TipoNodoConsulta.EscuelaIgual,
                        0,
                        "Escuela 2",
                        null,
                        null);

        Consulta.NodoConsulta orNodo =
                new Consulta.NodoConsulta(
                        Consulta.TipoNodoConsulta.Or,
                        0,
                        null,
                        nombreNodo,
                        escuelaNodo);

        Consulta.NodoConsulta andNodo =
                new Consulta.NodoConsulta(
                        Consulta.TipoNodoConsulta.And,
                        0,
                        null,
                        edadNodo,
                        orNodo);

        Consulta consultaDePrueba = new Consulta(andNodo);

        System.out.println(sistema.filtrarJugadores(consultaDePrueba).getValorString());
    }
}
