package com.example.dardos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListaJugadores {
    private static List<Jugador> jugadores;
    private static int jugadorActual;
    private static List<String> hanAcabado;

    private ListaJugadores(){
        jugadores=new ArrayList<>();
        hanAcabado = new LinkedList<>();
        jugadorActual=0;
    }

    public static void add(Jugador jugador){
        if(jugadores ==null){
            jugadores=new ArrayList<>();
            jugadorActual=0;
        }
        jugadores.add(jugador);

    }

    public static Jugador getElemento(int pos){
        return jugadores.get(pos);
    }

    public static List<Jugador> getJugadores() {
        if(jugadores == null){
            jugadores = new ArrayList<>();
        }
        return jugadores;
    }

    public static String[] arrayDatosJugadores(){
        if(jugadores == null){
            return null;
        }
        String[] salida = new String[jugadores.size()];

        for (int j =0;j<jugadores.size();j++){
            salida[j]=jugadores.get(j).getNombre() + ".\t\t\tScore: "+ jugadores.get(j).getPuntos()
            +"\n\nOpen: " + abiertos(j);
        }
        return salida;
    }
    private static String abiertos(int pos){
        Jugador j = jugadores.get(pos);
        String salida="";
        int[] stacks=j.getStacks();
        for (int i=0;i<7;i++){
            if(stacks[i]<3){
                if (salida.isEmpty()){
                    salida = salida + Jugador.TABLA_PUNTUACIONES[i];
                }
                else {
                    salida = salida + ", " + Jugador.TABLA_PUNTUACIONES[i];
                }
            }
        }
        if(salida.isEmpty()){
            salida="All Closed";
        }
        return  salida;
    }
    public static void siguienteJugador(){
        if(jugadorActual == jugadores.size()-1){
            jugadorActual=0;
        }
        else
            jugadorActual++;
    }
    public static Jugador getJugadorActual(){
        return jugadores.get(jugadorActual%(jugadores.size()));
    }

    public static void addImpact(int pos){
        Jugador jugador=getJugadorActual();
        int vecesDado= jugador.getStacks()[pos];
        if(vecesDado>=3 && estaAbierto(pos)){
            jugador.sumarPuntos(Jugador.TABLA_PUNTUACIONES[pos]);
        }
        else if(vecesDado<3){
            jugador.autoincrementarPos(pos);
        }
        jugadores.set(jugadorActual%jugadores.size(),jugador);
    }

    public static boolean estaAbierto(int pos){
        boolean abierto = jugadores.size()==1 ;
        for (Jugador j: jugadores) {
            if(j.getStacks()[pos]<3){
                abierto=true;
            }
        }
        return abierto;
    }
    public static boolean haGanado(){

        return tieneMaxPuntuacion() && todoCerrado();
    }
    private static boolean tieneMaxPuntuacion(){
        Jugador jAct = jugadores.get(jugadorActual);
        boolean vaGanado = true;
        int puntos = jAct.getPuntos();

        for (int i =0 ;i<jugadores.size();i++){//no hay que incluir al actual
            if(i!=jugadorActual){
                if(jugadores.get(i).getPuntos()>=puntos){
                    vaGanado=false;
                    break;
                }
            }
        }
        return vaGanado;
    }
    private static boolean todoCerrado(){
        int[]stacks = jugadores.get(jugadorActual).getStacks();
        boolean cerrado = true;
        for(int i : stacks){
            if(i<3){
                cerrado=false;
                break;
            }
        }
        return cerrado;
    }
    public static void addAcabado(Jugador j ){
        if(hanAcabado==null) hanAcabado=new LinkedList<>();
        hanAcabado.add(j.getNombre());
        jugadores.remove(j);
    }

    public static List<String> getHanAcabado(){
        return hanAcabado;
    }
}
