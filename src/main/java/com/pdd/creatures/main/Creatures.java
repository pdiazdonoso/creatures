package com.pdd.creatures.main;

import com.pdd.creatures.pantalla.Pantalla;
import com.pdd.creatures.seres.BichosManager;
import com.pdd.creatures.seres.Personaje;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.logging.log4j.Level;


/**
 *
 * @author Pedro
 *
 * Ideas de: https://www.youtube.com/watch?v=bQjfmB3lRoA
 */
public class Creatures extends JPanel {

    static Pantalla miPanta;
    static Personaje personaje;
    static BichosManager bichosManager;
    
    Creatures() {

        miPanta = new Pantalla();
        bichosManager = new BichosManager();
        personaje = new Personaje();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    personaje.teclaPresionada(e);
                } catch (LineUnavailableException ex) {
                    GlobalVars.LOG.error(ex);
//                    Logger.getLogger(Creatures.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    GlobalVars.LOG.error(ex);
//                    Logger.getLogger(Creatures.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    GlobalVars.LOG.error(ex);
  //                  Logger.getLogger(Creatures.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    GlobalVars.LOG.error(ex);
 //                   Logger.getLogger(Creatures.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setFocusable(true);
    }

    @Override
    public void paint(Graphics grafico) {
        miPanta.paint(grafico);
        personaje.paint(grafico);
        bichosManager.paint(grafico);
    }

    public static void main(String[] args) {
        //       System.setProperty("log4j.configuration", "C:\\Users\\Pedro\\log4j.xml");

        GlobalVars.LOG.log(Level.INFO,"java.class.path: ", System.getProperty("java.class.path"));
        GlobalVars.LOG.log(Level.INFO, "file.separator: ", System.getProperty("file.separator"));

        String workingDir = System.getProperty("user.dir");
        GlobalVars.LOG.log(Level.INFO,"Creatures user.dir: " , workingDir);

        GlobalVars.initGlobals();

        int indexOfSrcFolder = workingDir.indexOf("creatures");

        JFrame miVentana = new JFrame(workingDir.substring(indexOfSrcFolder));

        Creatures game = new Creatures();
        miVentana.add(game);
        initVentana(miVentana);

        while (true) {
            try {
                Thread.sleep(0, 100);
            } catch (InterruptedException ex) {
                GlobalVars.LOG.log(Level.ERROR,Creatures.class.getName(), ex);
            }
            game.repaint();
        }
    }

    private static void initVentana(JFrame miVentana) {
        int screenHeight;
        int screenWidth;

        screenHeight = GlobalVars.getNumFilas() * GlobalVars.LADOCELDA + GlobalVars.LADOCELDA + 5;
        screenWidth = GlobalVars.getNumColumnas() * GlobalVars.LADOCELDA + 5;

        miVentana.setResizable(false);
        miVentana.setSize(screenWidth, screenHeight);
        miVentana.setVisible(true);
        miVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
