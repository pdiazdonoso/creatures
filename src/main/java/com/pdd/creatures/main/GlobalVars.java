package com.pdd.creatures.main;

import com.pdd.creatures.pantalla.Laberinto;
import com.pdd.creatures.sonido.SoundManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Pedro
 */
public class GlobalVars {
    public static final Logger LOG = LogManager.getLogger();

    static public final int LADOCELDA = 20; // En pixels
    static private Laberinto laberinto;
    static private int numFilas;
    static private int numColumnas;
    static private final SoundManager manejadorSonidos = new SoundManager();

    public static void initGlobals() {
        LOG.info("-- Inicializando variables globales ---");
        LOG.log(Level.INFO, "user.dir: ", System.getProperty("user.dir"));
        LOG.info("user.home: " + System.getProperty("user.home"));
        LOG.log(Level.INFO, "java.class.path: ", System.getProperty("java.class.path"));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        screenHeight = screenHeight - GlobalVars.LADOCELDA - 10;
        screenWidth = screenWidth - GlobalVars.LADOCELDA;
        numFilas = screenHeight / GlobalVars.LADOCELDA;
        numColumnas = screenWidth / GlobalVars.LADOCELDA;
        laberinto = new Laberinto(getNumColumnas(), getNumFilas());

        System.getProperty("user.dir");

        LOG.info("Variables globales inicializadas.");
        LOG.warn("Entering application.");
    }

    public static int getNumFilas() {
        return numFilas;
    }

    public static int getNumColumnas() {
        return numColumnas;
    }

    public static Laberinto getLaberinto() {
        return laberinto;
    }

    public static SoundManager getManejadorSonidos() {
        return manejadorSonidos;
    }

}
