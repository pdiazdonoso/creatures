package com.pdd.creatures.pantalla;

import com.pdd.creatures.seres.Ente.ENTE;

public class Laberinto {

    public ENTE matLaberinto[][];
    public ENTE matLaberintoInicial[][];
    public int maxColumna;
    public int maxFila;
    private int colIniBicho;
    private int filaIniBicho;

    public Laberinto(int maxColumna, int maxFila) {
        this.maxColumna = maxColumna;
        this.maxFila = maxFila;
        obtieneLaberinto();
    }

    private void obtieneLaberinto() {
        ENTE mLaberinto[][] = new ENTE[maxColumna][maxFila];
        
        int fila;
        int columna;

        for (columna = 0; columna < maxColumna; columna++) {
            for (fila = 0; fila < maxFila; fila++) {
                if (columna == 0 || fila == 0 || columna == maxColumna - 1 || fila == maxFila - 1) {
                    mLaberinto[columna][fila] = ENTE.MURO;
                } else {
                    mLaberinto[columna][fila] = ENTE.SUELO;
                }
            }

//Salida miAvatar
            mLaberinto[0][1] = ENTE.SALIDABICHOS;
            mLaberinto[0][2] = ENTE.SALIDABICHOS;

//Lugar de salida de bichos en el centro
            colIniBicho = maxColumna / 2;
            filaIniBicho = maxFila / 2;
            mLaberinto[getColIniBicho()][getFilaIniBicho()] = ENTE.SALIDABICHOS;
            mLaberinto[getColIniBicho()][getFilaIniBicho() + 1] = ENTE.SALIDABICHOS;
            mLaberinto[getColIniBicho() + 1][getFilaIniBicho()] = ENTE.SALIDABICHOS;
            mLaberinto[getColIniBicho() + 1][getFilaIniBicho() + 1] = ENTE.SALIDABICHOS;

            mLaberinto[getColIniBicho() - 2][getFilaIniBicho() - 2] = ENTE.MURO;
            mLaberinto[getColIniBicho() - 1][getFilaIniBicho() - 2] = ENTE.MURO;
            mLaberinto[getColIniBicho()][getFilaIniBicho() - 2] = ENTE.MURO;
            mLaberinto[getColIniBicho() + 1][getFilaIniBicho() - 2] = ENTE.MURO;
            mLaberinto[getColIniBicho() + 2][getFilaIniBicho() - 2] = ENTE.MURO;
            mLaberinto[getColIniBicho() + 3][getFilaIniBicho() - 2] = ENTE.MURO;
            mLaberinto[getColIniBicho() - 2][getFilaIniBicho() - 1] = ENTE.MURO;
            mLaberinto[getColIniBicho() - 1][getFilaIniBicho() - 1] = ENTE.MURO;
            mLaberinto[getColIniBicho()][getFilaIniBicho() - 1] = ENTE.MURO;
            mLaberinto[getColIniBicho() + 1][getFilaIniBicho() - 1] = ENTE.MURO;
            mLaberinto[getColIniBicho() + 2][getFilaIniBicho() - 1] = ENTE.MURO;
            mLaberinto[getColIniBicho() + 3][getFilaIniBicho() - 1] = ENTE.MURO;
            mLaberinto[getColIniBicho() - 2][getFilaIniBicho()] = ENTE.MURO;
            mLaberinto[getColIniBicho() - 1][getFilaIniBicho()] = ENTE.MURO;
            mLaberinto[getColIniBicho() + 2][getFilaIniBicho()] = ENTE.MURO;
            mLaberinto[getColIniBicho() + 3][getFilaIniBicho()] = ENTE.MURO;
            mLaberinto[getColIniBicho() - 2][getFilaIniBicho() + 1] = ENTE.MURO;
            mLaberinto[getColIniBicho() - 1][getFilaIniBicho() + 1] = ENTE.MURO;
            mLaberinto[getColIniBicho() + 2][getFilaIniBicho() + 1] = ENTE.MURO;
            mLaberinto[getColIniBicho() + 3][getFilaIniBicho() + 1] = ENTE.MURO;

            mLaberinto[getColIniBicho()][getFilaIniBicho() + 4] = ENTE.MURO;
            mLaberinto[getColIniBicho() + 1][getFilaIniBicho() + 4] = ENTE.MURO;

        }

        matLaberinto = mLaberinto;
        matLaberintoInicial = mLaberinto;
    }

    public void quitaSer(int colSer, int filaSer) {
        ENTE suelo = ENTE.SUELO;
        if ((colSer == getColIniBicho() && filaSer == getFilaIniBicho()) || (colSer == getColIniBicho() && filaSer == getFilaIniBicho() + 1)) {
            suelo = ENTE.SALIDABICHOS;
        }

        //Salida miAvatar
        if (colSer == 0 && filaSer == 1) {
            suelo = ENTE.SALIDABICHOS;
        }
        
        ponSer(colSer, filaSer, suelo);
    }

    public void ponSer(int colSer, int filaSer, ENTE nuevoSer) {
        matLaberinto[colSer][filaSer] = nuevoSer;
        matLaberinto[colSer][filaSer + 1] = nuevoSer;
        matLaberinto[colSer + 1][filaSer] = nuevoSer;
        matLaberinto[colSer + 1][filaSer + 1] = nuevoSer;
    }

    public boolean salidaBichosLibre() {
        boolean libre = true;

        if (matLaberinto[getColIniBicho()][getFilaIniBicho()] != ENTE.SALIDABICHOS) {
            libre = false;
        }
        if (matLaberinto[getColIniBicho()][getFilaIniBicho() + 1] != ENTE.SALIDABICHOS) {
            libre = false;
        }
        if (matLaberinto[getColIniBicho() + 1][getFilaIniBicho()] != ENTE.SALIDABICHOS) {
            libre = false;
        }
        if (matLaberinto[getColIniBicho() + 1][getFilaIniBicho() + 1] != ENTE.SALIDABICHOS) {
            libre = false;
        }

        return libre;
    }

    public int getColIniBicho() {
        return colIniBicho;
    }

    public int getFilaIniBicho() {
        return filaIniBicho;
    }


    
    

}   
        


         
/*
        maxColumna=22;
        maxFila=12;

//Plain laberinto
                 int mLaberinto[][] = {
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
};

//Complejo1 
        maxColumna=22;
        maxFila=12;

         int mLaberinto[][] = 
         {
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
{0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
{1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1},
{1,0,0,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1},
{1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1},
{1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1},
{1,0,1,0,1,0,1,0,1,0,0,0,1,0,1,1,1,1,1,1,1,1,1},
{1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,0,0,0,1},
{1,0,1,0,1,0,1,1,1,0,0,0,1,1,1,1,1,1,1,0,1,0,1},
{1,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1},
{1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1},
{1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
}; 

  
*/
