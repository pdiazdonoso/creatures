package com.pdd.creatures.seres;

import com.pdd.creatures.main.GlobalVars;
import com.pdd.creatures.pantalla.Laberinto;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pdiaz
 */
public class BichosManager {

    public ArrayList<Bicho> bichos = new ArrayList<>();
    private Laberinto miLab;
    private int numCiclos = 0;
    private final int TIEMPOCICLO = 50;
    private final int TOPEBICHOS = 10000;
    private final int CICLOSPARANUEVOBICHO = 20; // Frecuencia con que aparecen los bichos
    private final int CLICLOSPARAMOVERBICHOS = 5; //Cuanto mas grande este numero mas lento van

    public BichosManager() {
        this.miLab = GlobalVars.getLaberinto();
        hiloBichos();

    }

    public void paint(Graphics grafico) {
        for (Bicho bi : bichos) {
            bi.paint(grafico);
        }
    }

    public void addBicho() {
        int tipoBicho;
        Color colorBicho;
        tipoBicho = bichos.size() % 4;

        switch (tipoBicho) {
            case 0:
                colorBicho = Color.RED;
                break;
            case 1:
                colorBicho = Color.YELLOW;
                break;
            case 2:
                colorBicho = Color.GREEN;
                break;
            case 3:
                colorBicho = Color.BLUE;
                break;
            default: //Este color no deberia salir
                colorBicho = Color.PINK;
                break;
        }

        if (miLab.salidaBichosLibre()) {
            bichos.add(new Bicho(colorBicho));
        }

    }

    private void mueveBichos() {
        for (Bicho bi : bichos) {
            bi.mueveBicho();
        }
    }

    private void cicloHilo() {
        numCiclos++;
        if (numCiclos % CLICLOSPARAMOVERBICHOS == 0) {
            mueveBichos();
        }
        if (numCiclos % CICLOSPARANUEVOBICHO == 0 && bichos.size() < TOPEBICHOS) {
            addBicho();
        }
    }

    private void hiloBichos() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (1 == 1) {
                    cicloHilo();
                    try {
                        Thread.sleep(TIEMPOCICLO);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BichosManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
}
