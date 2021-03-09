package com.example.dardos;

import java.util.List;

public class Jugador {
    private String nombre;
    private int puntos;
    private int[] stacks;
    public static final int[] TABLA_PUNTUACIONES = new int[]{15,16,17,18,19,20,25};

    public Jugador( String nombre){
        this.nombre=nombre;
        puntos=0;
        stacks = new int[7];
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public int[] getStacks() {
        return stacks;
    }

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public void autoincrementarPos(int pos){
        stacks[pos]++;
    }

    public void qaddImpact(int pos){
        int vecesDado= stacks[pos];
        if(vecesDado>=3 && estaAbierto(pos)){
            puntos+= TABLA_PUNTUACIONES[pos];
        }
        else if(vecesDado>=3){
            stacks[pos] = stacks[pos]+1;
        }
    }
    public boolean estaAbierto(int pos){
        boolean abierto=false;
        List<Jugador> jugadores=ListaJugadores.getJugadores();
        for (Jugador j: jugadores) {
            if(j.getStacks()[pos]<3){
                abierto=true;
            }
        }
        return abierto;
    }
}
