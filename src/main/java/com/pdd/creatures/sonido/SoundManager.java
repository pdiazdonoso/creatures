package com.pdd.creatures.sonido;

import com.pdd.creatures.sonido.Sound;
import java.util.ArrayList;

/*
 https://www.youtube.com/watch?v=TKdQAYs7MU8
 */
/**
 *
 * @author Pedro
 */
public class SoundManager {

    public ArrayList<Sound> sounds = new ArrayList<Sound>();

    public SoundManager() {
        sounds.add(new Sound("Mi_mov", Sound.getURL("W_Balloon.wav")));
    }

    public void addSound(String s, Sound sound) {
        sounds.add(sound);
    }

    public void removeSound(Sound sound) {
        sounds.remove(sound);
    }

    public void playSound(String name) {
        for (Sound s : sounds) {
            if (s.name.equals(name)) {
                s.play();
            }
        }
    }

    public void loopSound(String name) {
        for (Sound s : sounds) {
            if (s.name.equals(name)) {
                s.loop();
            }
        }
    }

    public void stopSound(String name) {
        for (Sound s : sounds) {
            if (s.name.equals(name)) {
                s.stop();
            }
        }
    }

    public void stopAllSounds() {
        for (Sound s : sounds) {
            s.stop();
        }
    }
}
