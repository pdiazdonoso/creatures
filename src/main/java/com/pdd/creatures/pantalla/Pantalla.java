package com.pdd.creatures.pantalla;

import com.pdd.creatures.main.GlobalVars;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Pantalla extends Applet {

    private Laberinto miLaberinto;
    private BufferedImage muro;
    private BufferedImage suelo;
    private BufferedImage salidaBichosIMG;

    public Pantalla() {
        this.miLaberinto = GlobalVars.getLaberinto();
        try {
            muro = ImageIO.read(getClass().getClassLoader().getResource("images//muro20.jpg"));
            suelo = ImageIO.read(getClass().getClassLoader().getResource("images//suelo20.jpg"));
            salidaBichosIMG = ImageIO.read(getClass().getClassLoader().getResource("images//SalidaBichos20.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paint(Graphics grafico) {

        int fila;
        int columna;

        for (columna = 0; columna < miLaberinto.maxColumna; columna++) {
            for (fila = 0; fila < miLaberinto.maxFila; fila++) {
                    switch (miLaberinto.matLaberinto[columna][fila]) {
                        case MURO:
                            grafico.drawImage(muro, columna * GlobalVars.LADOCELDA, fila * GlobalVars.LADOCELDA, this);
                            break;
                        case SUELO:
                            grafico.drawImage(suelo, columna * GlobalVars.LADOCELDA, fila * GlobalVars.LADOCELDA, this);
                            break;
                        case SALIDABICHOS:
                            grafico.drawImage(salidaBichosIMG, columna * GlobalVars.LADOCELDA, fila * GlobalVars.LADOCELDA, this);
                            break;
                    }
                }
            }
        }
    }


