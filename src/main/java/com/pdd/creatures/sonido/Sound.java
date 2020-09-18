package com.pdd.creatures.sonido;

import com.pdd.creatures.main.GlobalVars;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/*
 https://www.youtube.com/watch?v=TKdQAYs7MU8
 */
/**
 *
 * @author Pedro
 */
public class Sound {

    private static Sound staticSound = new Sound();

    public String name;
    public AudioClip sound;

    public Sound() {
    }

    public Sound(String name, URL url) {
        this.name = name;
        GlobalVars.LOG.info("NOMBRE SONIDO: " + name );
        GlobalVars.LOG.info("url: " + url);

        sound = Applet.newAudioClip(url);

    }

    public void play() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                if (sound != null) {
                    sound.play();
                }
            }
        }).start();
    }

    public void loop() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                if (sound != null) {
                    sound.loop();
                }
            }
        }).start();
    }

    public void stop() {
        if (sound != null) {
            sound.stop();
        }
    }

    public static URL getURL(String fileName) {
        String nombreFichero = fileName;
//            nombreFichero =  System.getProperty("user.dir") + nombreFichero;
/*
        nombreFichero = "c:\\temp\\" + nombreFichero;
        URL ruta = null;
        File fichero = new File(nombreFichero);
        try {
            ruta = fichero.toURI().toURL();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);

        }
        if (fichero.exists()) {
            GlobalVars.LOG.info("Fichero existe");
        }
        if (fichero.isFile()) {
            GlobalVars.LOG.info("Es fichero");
        }
          return ruta;
*/
        nombreFichero = "sounds//" + fileName;
//        GlobalVars.LOG.log(Level.TRACE,"Cargando sonido ", nombreFichero);
//        return staticSound.getClass().getResource(nombreFichero);

        return staticSound.getClass().getClassLoader().getResource(nombreFichero);
    }
}
