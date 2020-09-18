/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdd.creatures.seres;

/**
 *
 * @author Pedro
 */
public class Ente {
    public final int SUELO = 0;
    public final int MURO = 1;
    public final int SALIDABICHOS = 2;
    public final int MIAVATAR = 3;
    public final int BICHO = 4;
    public enum ENTE{SUELO, MURO, SALIDABICHOS, MIAVATAR, BICHO};
    
    public final int UP = 1;
    public final int DOWN = 2;
    public final int RIGHT = 3;
    public final int LEFT = 4;
    public enum TIPOMOV {UP, DOWN, RIGHT, LEFT, QUIETO};
    
}
