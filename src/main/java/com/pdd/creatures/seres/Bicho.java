package com.pdd.creatures.seres;

import com.pdd.creatures.main.GlobalVars;
import com.pdd.creatures.pantalla.Laberinto;
import com.pdd.creatures.sonido.SoundManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import com.pdd.creatures.seres.Ente.ENTE;
import com.pdd.creatures.seres.Ente.TIPOMOV;

/**
 *
 * @author Pdiaz
 */
public class Bicho {

    TIPOMOV lastMov = TIPOMOV.DOWN;

    private int colBicho;
    private int filaBicho;
    private final Color colorSer;
    private boolean seHaMovido = false;
    private Laberinto miLab;
    private SoundManager sm;
    BufferedImage miAvatar;
    ArrayList<Integer> histColBicho = new ArrayList();
    ArrayList<Integer> histFilaBicho = new ArrayList();

    Bicho(Color colorSer) {
        this.miLab = GlobalVars.getLaberinto();
        this.colorSer = colorSer;
        this.colBicho = miLab.getColIniBicho();
        this.filaBicho = miLab.getFilaIniBicho();
        this.histColBicho.add(colBicho);
        this.histFilaBicho.add(filaBicho);
        miLab.ponSer(colBicho, filaBicho, ENTE.BICHO);
    }

    public void paint(Graphics grafico) {
        int x;
        int y;

        if (!seHaMovido) {
            return;
        }

        x = colBicho * GlobalVars.LADOCELDA + 2;
        y = filaBicho * GlobalVars.LADOCELDA + 2;

        grafico.setColor(colorSer);
        grafico.fillOval(x, y, (GlobalVars.LADOCELDA * 2) - 4, (GlobalVars.LADOCELDA * 2) - 4);
        grafico.setColor(Color.black);
        grafico.drawOval(x, y, (GlobalVars.LADOCELDA * 2) - 4, (GlobalVars.LADOCELDA * 2) - 4);

        seHaMovido = false;
    }

    private boolean evaluaMov(TIPOMOV movi) {
        boolean movValido = false;
        switch (movi) {
            case UP:
                if ((miLab.matLaberinto[colBicho][filaBicho - 1] == ENTE.SUELO) && (miLab.matLaberinto[colBicho + 1][filaBicho - 1] == ENTE.SUELO)) {
                    movValido = true;
                }
                break;
            case DOWN:
                if //                            ((miLab.matLaberinto[colBicho][filaBicho + 1] == ENTE.SALIDABICHOS)  ||
                        (miLab.matLaberinto[colBicho][filaBicho + 2] == ENTE.SUELO && miLab.matLaberinto[colBicho + 1][filaBicho + 2] == ENTE.SUELO) {
                    movValido = true;
                }
                break;
            case LEFT:
                if (miLab.matLaberinto[colBicho - 1][filaBicho] == ENTE.SUELO && miLab.matLaberinto[colBicho - 1][filaBicho + 1] == ENTE.SUELO) {
                    movValido = true;
                }
                break;
            case RIGHT:
                if (miLab.matLaberinto[colBicho + 2][filaBicho] == ENTE.SUELO && miLab.matLaberinto[colBicho + 2][filaBicho + 1] == ENTE.SUELO) {
                    movValido = true;
                }
                break;
        }
        return movValido;
    }

    public void mueveBicho() {
        int newFilaBicho;
        int newColBicho;
        TIPOMOV newMov;
        ArrayList<TIPOMOV> posibleMov = new ArrayList<>();

        newFilaBicho = filaBicho;
        newColBicho = colBicho;

        if (evaluaMov(TIPOMOV.UP)) {
            posibleMov.add(TIPOMOV.UP);
        }
        if (evaluaMov(TIPOMOV.DOWN)) {
            posibleMov.add(TIPOMOV.DOWN);
        }
        if (evaluaMov(TIPOMOV.LEFT)) {
            posibleMov.add(TIPOMOV.LEFT);
        }
        if (evaluaMov(TIPOMOV.RIGHT)) {
            posibleMov.add(TIPOMOV.RIGHT);
        }

        comportamientoBicho(posibleMov);

        if (posibleMov.size() > 0) {
            //initialization
            Random generator = new Random();
            int randomIndex = generator.nextInt(posibleMov.size());
            newMov = posibleMov.get(randomIndex);

            switch (newMov) {
                case UP:
                    newFilaBicho--;
                    break;
                case DOWN:
                    newFilaBicho++;
                    break;
                case RIGHT:
                    newColBicho++;
                    break;
                case LEFT:
                    newColBicho--;
                    break;
            }

            if ((colBicho != newColBicho) || (filaBicho != newFilaBicho)) {
                miLab.quitaSer(colBicho, filaBicho);
                colBicho = newColBicho;
                filaBicho = newFilaBicho;
                lastMov = newMov;
                miLab.ponSer(colBicho, filaBicho, ENTE.BICHO);
                this.histColBicho.add(colBicho);
                this.histFilaBicho.add(filaBicho);
                seHaMovido = true;
            }

        }
    }

    private void comportamientoBicho(ArrayList<TIPOMOV> posibleMov) {
        int cont;
        if (posibleMov.contains(lastMov)) {
            //Posibilidades de escoger el camino anterior.
            for (cont = 0; cont < 80; cont++) {
                posibleMov.add(lastMov);
            }
        }
    }

}
