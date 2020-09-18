package com.pdd.creatures.seres;

import com.pdd.creatures.main.GlobalVars;
import com.pdd.creatures.pantalla.Pantalla;
import com.pdd.creatures.pantalla.Laberinto;
import com.pdd.creatures.sonido.SoundManager;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import com.pdd.creatures.seres.Ente.ENTE;
import com.pdd.creatures.seres.Ente.TIPOMOV;

public class Personaje extends Applet{
    Laberinto miLab;
    
    public int colunmaPersonaje = 0;
    public int filaPersonaje = 1 ;

    private final int ancho=36;
    private final int alto=36;
    private BufferedImage miAvatar;
    static private Clip clip;
    private SoundManager sm;
 
    
    public Personaje () {
        this.sm = GlobalVars.getManejadorSonidos();
        miLab =  GlobalVars.getLaberinto();        
        
        try {
             miAvatar = ImageIO.read(getClass().getClassLoader().getResource("images//miavatar.png"));
                
            } catch (IOException ex) {  
                Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
            }

        
    }
        
    
    public void paint(Graphics grafico){
            int x;
            int y;
            
            x=colunmaPersonaje*GlobalVars.LADOCELDA;
            y=filaPersonaje*GlobalVars.LADOCELDA ;
            
            grafico.drawImage(miAvatar, x , y, this);
            
        }

    
    public void teclaPresionada(KeyEvent evento) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException{
        int nuevaColumnaPersonaje;
        int nuevaFilaPersonaje;
        TIPOMOV nuevoMov = TIPOMOV.QUIETO;
        
        nuevaColumnaPersonaje = colunmaPersonaje;
        nuevaFilaPersonaje = filaPersonaje;
        
        switch (evento.getKeyCode()){
            case 37:
                if (colunmaPersonaje > 0  ){
                    nuevoMov = TIPOMOV.LEFT;
                }
                break;
            case 39:
                if (colunmaPersonaje < miLab.maxColumna){
                    nuevoMov = TIPOMOV.RIGHT;
                }
                break;
            case 40:
                if (filaPersonaje < miLab.maxFila ){
                    nuevoMov = TIPOMOV.DOWN;
                }
                break;
            case 38:
                if (filaPersonaje >  0 ){
                    nuevoMov = TIPOMOV.UP;
                }
                break;
        }
        
        if (evaluaMov(nuevoMov)){
            miLab.quitaSer(colunmaPersonaje, filaPersonaje);
            switch (nuevoMov){
                    case UP:
                        nuevaFilaPersonaje--;
                        break;
                    case DOWN:
                        nuevaFilaPersonaje++;
                        break;
                    case LEFT:
                        nuevaColumnaPersonaje--;
                        break;
                    case RIGHT:
                        nuevaColumnaPersonaje++;
                        break;
                }            
            
            colunmaPersonaje=nuevaColumnaPersonaje;
            filaPersonaje=nuevaFilaPersonaje;
            miLab.ponSer(colunmaPersonaje, filaPersonaje, ENTE.MIAVATAR);
            sm.playSound("Mi_mov");
        }
        
    }
    
        private boolean evaluaMov(TIPOMOV movi){
        boolean movValido = false;
        switch (movi){
                case UP:
                    if ((miLab.matLaberinto[colunmaPersonaje][filaPersonaje - 1 ] == ENTE.SUELO) && (miLab.matLaberinto[colunmaPersonaje + 1 ][filaPersonaje - 1 ] == ENTE.SUELO)){
                        movValido = true;
                    }
                    break;
                case DOWN:
                    if (miLab.matLaberinto[colunmaPersonaje][filaPersonaje + 2] == ENTE.SUELO && miLab.matLaberinto[colunmaPersonaje + 1][filaPersonaje + 2] == ENTE.SUELO) {
                        movValido = true;
                    }
                    break;
                case LEFT:
                    if (miLab.matLaberinto[colunmaPersonaje - 1][filaPersonaje] == ENTE.SUELO && miLab.matLaberinto[colunmaPersonaje - 1][filaPersonaje + 1] == ENTE.SUELO){
                        movValido = true;
                    }
                    break;
                case RIGHT:
                    if (miLab.matLaberinto[colunmaPersonaje + 2][filaPersonaje] == ENTE.SUELO && miLab.matLaberinto[colunmaPersonaje + 2][filaPersonaje + 1] == ENTE.SUELO){
                        movValido = true;
                    }
                    break;
            }            
        return movValido;
    }
    
}
