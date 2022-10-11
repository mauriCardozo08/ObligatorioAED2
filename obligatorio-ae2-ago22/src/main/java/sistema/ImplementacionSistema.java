package sistema;

import dominio.Jugador;
import dominio.JugadorBuscado;
import interfaz.*;
import interfaz.ABB.ArbolBinarioBusqueda;
import interfaz.Lista.Lista;

public class ImplementacionSistema implements Sistema {
    private int maximoCentros;
    public ArbolBinarioBusqueda jugadores;

    public ImplementacionSistema(int maxCentros){
        inicializarSistema(maxCentros);
        jugadores = new ArbolBinarioBusqueda();
    }
    @Override
    public Retorno inicializarSistema(int maxCentros) {
       if(maxCentros>5){
           this.maximoCentros = maxCentros;
            return Retorno.ok();
       }else{
           return Retorno.error1("La cantidad de centros debe ser mayor a 5");
       }
    }

    @Override
    public Retorno explorarCentroUrbano(boolean[] correctas, int[] puntajes, int minimo) {
        int acc = 0;
        int indicadorPuntosExtra = 0;
        if(correctas!=null && puntajes!=null){
            if(correctas.length>=3 && puntajes.length>=3){
                if(correctas.length==puntajes.length){
                    if(minimo>0){
                        for(int i = 0; i<correctas.length;i++){
                            if(correctas[i]){
                                acc+=puntajes[i];
                                indicadorPuntosExtra++;
                                if(indicadorPuntosExtra>2){
                                    if(indicadorPuntosExtra>=5){
                                        acc+=8;
                                    }else if(indicadorPuntosExtra==4){
                                        acc+=5;
                                    }else{
                                        acc+=3;
                                    }
                                }
                            }else {
                                indicadorPuntosExtra = 0;
                            }
                        }
                    }else{
                        return Retorno.error4("El mínimo debe ser mayor a 0");
                    }

                }else{
                    return Retorno.error3("Las listas deben tener la misma cantidad de elementos");
                }
            }else{
                return Retorno.error2("Las listas deben tener al menos 3 elementos");
            }
        }else{
            return Retorno.error1("No pude haber parámetros nulos");
        }
        if(acc>=minimo){
            return Retorno.ok(acc, "pasa");
        }else{
            return Retorno.ok(acc, "no pasa");
        }
    }

    @Override
    public Retorno registrarJugador(String ci, String nombre,int edad, String escuela, TipoJugador tipo) {
        Jugador nuevoJugador = new Jugador(ci,nombre,edad,escuela,tipo);
        if(nuevoJugador.validar()){
            if(nuevoJugador.validarFormatoCedula()){
                if(jugadores.insertar(nuevoJugador)){
                    return Retorno.ok();
                }else {
                    return Retorno.error3("Ya existe un jugador registrado con esa cedula");
                }
            }else{
                return Retorno.error2("La cedula no tiene el formato valido.");
            }
        }else{
            return Retorno.error1("Alguno de los parametros ingresados es vacio o null");
        }
    }

    @Override
    public Retorno filtrarJugadores(Consulta consulta) {
        return Retorno.noImplementada();
        //Preguntar a profesor, que es lo que tenemos que hacer concretamente.
    }

    @Override
    public Retorno buscarJugador(String ci) {
        Jugador jugadorAuxiliar = new Jugador();
        jugadorAuxiliar.setCedula(ci);

        if(jugadorAuxiliar.validarFormatoCedula()){
            JugadorBuscado jugadorBuscado = jugadores.obtenerJugador(jugadorAuxiliar);
            if(jugadorBuscado!=null){
                return Retorno.ok(jugadorBuscado.getCantidadDeElementosRecorridos(),
                                    jugadorBuscado.getJugador().toString());
            }else{
                return Retorno.error2("No existe un jugador registrado con esa cedula");
            }
        }else{
            return Retorno.error1("La cedula no tiene el formato valido.");
        }
    }

    @Override
    public Retorno listarJugadoresPorCedulaAscendente() {
        return Retorno.ok(jugadores.listarJugadoresPorCedulaAscendente());
    }

    @Override
    public Retorno listarJugadoresPorCedulaDescendente() {
        return Retorno.ok(jugadores.listarJugadoresPorCedulaDescendente());
    }

    @Override
    public Retorno listarJugadoresPorTipo(TipoJugador unTipo) {
        if(unTipo!=null){
            return Retorno.ok(jugadores.listarJugadoresPorTipo(unTipo));
        }else{
            return Retorno.error1("El tipo no puede ser null.");
        }
    }

    @Override
    public Retorno registrarCentroUrbano(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoCentrosCantDeSaltos(String codigoCentroOrigen, int cantidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCentroOrigen, String codigoCentroDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoMonedas(String codigoCentroOrigen, String codigoCentroDestino) {
        return Retorno.noImplementada();
    }
}
