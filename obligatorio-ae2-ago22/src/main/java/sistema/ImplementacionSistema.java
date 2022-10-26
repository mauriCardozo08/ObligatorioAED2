package sistema;

import dominio.CentroUrbano;
import dominio.Jugador;
import dominio.JugadorBuscado;
import interfaz.*;
import interfaz.ABB.ArbolBinarioBusqueda;
import interfaz.Grafo.Camino;
import interfaz.Grafo.Grafo;
import interfaz.Grafo.Vertice;
import interfaz.Lista.Lista;
import interfaz.Lista.Nodo;

import java.util.EnumSet;

public class ImplementacionSistema implements Sistema {
    public ArbolBinarioBusqueda jugadores;
    public Lista<Lista<Jugador>> jugadoresPorTipo;
    public Grafo centrosUrbanos;

    public ImplementacionSistema(){
        jugadores = new ArbolBinarioBusqueda();
        inicializarListaJugadoresPorTipo();
    }
    private void inicializarListaJugadoresPorTipo(){
        jugadoresPorTipo = new Lista<Lista<Jugador>>();
        EnumSet.allOf(TipoJugador.class).forEach(tipo -> {
            Lista<Jugador> lista = new Lista<Jugador>();
            jugadoresPorTipo.agregarAlFinal(lista);
        });
    }
    @Override
    public Retorno inicializarSistema(int maxCentros) {
        if(maxCentros>5){
            centrosUrbanos = new Grafo(maxCentros);
            return Retorno.ok();
        }else {
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
                    int indice = nuevoJugador.getTipoJugador().getIndice();
                    jugadoresPorTipo.obtener(indice).agregarAlFinal(nuevoJugador);
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
            int indice = unTipo.getIndice();
            Lista<Jugador> jugadoresSeleccionadosPorTipo = jugadoresPorTipo.obtener(indice);
            return Retorno.ok(jugadoresSeleccionadosPorTipo.imprimirLista());
        }else{
            return Retorno.error1("El tipo no puede ser null.");
        }
    }
    @Override
    public Retorno registrarCentroUrbano(String codigo, String nombre) {
        if(centrosUrbanos.aptoIngreso()){
            CentroUrbano nuevoCentro = new CentroUrbano(codigo,nombre);
            if(nuevoCentro.validar()){
                if(validarCodigoCentroUrbano(codigo)){
                    centrosUrbanos.agregarVertice(new Vertice<CentroUrbano>(nuevoCentro));
                    return Retorno.ok();
                }else{
                    return Retorno.error2("Ya existe un centro con ese codigo");
                }
            }else{
                return Retorno.error2("Los datos no pueden ser vacios o nullos");
            }
        }else{
            return Retorno.error1("El sistema ya no puede registrar mas centros urbanos");
        }
    }
    private Boolean validarCodigoCentroUrbano(String codigoCentroUrbano){
        Vertice[] centros = new Vertice[centrosUrbanos.getMaxVertices()];
        centros = centrosUrbanos.getVertices();
        for(int i=0; i<centros.length; i++){
            if(centros[i]!=null){
                CentroUrbano centro = (CentroUrbano)centros[i].getVertice();
                if(centro.getCodigo().equals(codigoCentroUrbano)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Retorno registrarCamino(String codigoCentroOrigen,
                                   String codigoCentroDestino,
                                   double costo, double tiempo,
                                   double kilometros,
                                   EstadoCamino estadoDelCamino) {

        Camino nuevoCamino = new Camino(costo,tiempo,kilometros,estadoDelCamino);

        Retorno validacionDeCamino = validarCamino(nuevoCamino, codigoCentroOrigen, codigoCentroDestino);
        if(validacionDeCamino.isOk()){
            if(!centrosUrbanos.validarCaminoExistente(codigoCentroOrigen,codigoCentroDestino)){
                int indiceCentroOrigen = centrosUrbanos.getIndicePorCodigo(codigoCentroOrigen);
                int indiceCentroDestino = centrosUrbanos.getIndicePorCodigo(codigoCentroDestino);
                nuevoCamino.setExiste(true);
                centrosUrbanos.agregarCamino(indiceCentroOrigen, indiceCentroDestino, nuevoCamino);
            }else{
                return Retorno.error5("Ya existe un camino definido entre los dos centros");
            }

        }
        return validacionDeCamino;
    }

    private Retorno validarCamino(Camino nuevoCamino, String codigoCentroOrigen, String codigoCentroDestino){
        if(nuevoCamino.validarDoubles()){
            if(nuevoCamino.validarNull() && validarString(codigoCentroOrigen) && validarString(codigoCentroDestino)){
                if(!validarCodigoCentroUrbano(codigoCentroOrigen)){
                    if(!validarCodigoCentroUrbano(codigoCentroDestino)){
                        return  Retorno.ok();
                    }else{
                        return Retorno.error4("El centro urbano de destino no existe");
                    }
                }else{
                    return Retorno.error3("El centro urbano de origen no existe");
                }
            }else{
                return Retorno.error2("Los codigos y el estado del camino no pueden ser null o vacios.");
            }
        }else{
            return Retorno.error1("Los valores costo, tiempo y kilometros deben ser mayores a 0.");
        }
    }
    public boolean validarString(String cadenaDeTextoAValidar){
        return cadenaDeTextoAValidar!=null && !cadenaDeTextoAValidar.equals("");
    }

    @Override
    public Retorno actualizarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        Camino caminoActualizar = new Camino(costo, tiempo, kilometros, estadoDelCamino);

        Retorno validacionDeCamino = validarCamino(caminoActualizar, codigoCentroOrigen, codigoCentroDestino);

        if(validacionDeCamino.isOk()){
            if(centrosUrbanos.validarCaminoExistente(codigoCentroOrigen,codigoCentroDestino)){
                int indiceCentroOrigen = centrosUrbanos.getIndicePorCodigo(codigoCentroOrigen);
                int indiceCentroDestino = centrosUrbanos.getIndicePorCodigo(codigoCentroDestino);
                caminoActualizar.setExiste(true);
                centrosUrbanos.agregarCamino(indiceCentroOrigen, indiceCentroDestino, caminoActualizar);
            }else{
                return Retorno.error5("No existe un camino definido entre los dos centros");
            }


        }
        return validacionDeCamino;
    }

    @Override
    public Retorno listadoCentrosCantDeSaltos(String codigoCentroOrigen, int cantidad) {
        int indice = centrosUrbanos.getIndicePorCodigo(codigoCentroOrigen);

        if(cantidad>=0){
            if(indice != -1){
                if(cantidad>0){
                    return Retorno.ok(centrosUrbanos.dfs(centrosUrbanos.getCentro(indice), cantidad));
                }else{
                    return Retorno.ok(centrosUrbanos.getCentro(indice).toString());
                }
            }else{
                return Retorno.error2("El centro no está registrado en el sistema");
            }
        }else{
            return Retorno.error1("La cantidad debe ser mayor a 0");
        }
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCentroOrigen, String codigoCentroDestino) {
        if(validarString(codigoCentroDestino) && validarString(codigoCentroDestino)){
            if(validarCodigoCentroUrbano(codigoCentroOrigen)){
                if(validarCodigoCentroUrbano(codigoCentroDestino)){
                    int indiceOrigen =centrosUrbanos.getIndicePorCodigo(codigoCentroOrigen);
                    int indiceDestino = centrosUrbanos.getIndicePorCodigo(codigoCentroDestino);
                    if(centrosUrbanos.getAristas()[indiceOrigen][indiceDestino].getExiste()){

                    }else{
                        return Retorno.error4("No hay camino entre el centro de origen y el de destino");
                    }
                }else{
                    return Retorno.error3("No existe el centro de destino");
                }
            }else {
                return Retorno.error2("No existe el centro origen");
            }
        }else{
            return Retorno.error1("Los codigos no pueden ser vacios o nulos");
        }
    }

    @Override
    public Retorno viajeCostoMinimoMonedas(String codigoCentroOrigen, String codigoCentroDestino) {
        if(validarString(codigoCentroDestino) && validarString(codigoCentroDestino)){
            if(validarCodigoCentroUrbano(codigoCentroOrigen)){
                if(validarCodigoCentroUrbano(codigoCentroDestino)){
                    int indiceOrigen =centrosUrbanos.getIndicePorCodigo(codigoCentroOrigen);
                    int indiceDestino = centrosUrbanos.getIndicePorCodigo(codigoCentroDestino);
                    if(centrosUrbanos.getAristas()[indiceOrigen][indiceDestino].getExiste()){

                    }else{
                        return Retorno.error4("No hay camino entre el centro de origen y el de destino");
                    }
                }else{
                    return Retorno.error3("No existe el centro de destino");
                }
            }else {
                return Retorno.error2("No existe el centro origen");
            }
        }else{
            return Retorno.error1("Los codigos no pueden ser vacios o nulos");
        }
    }
}
