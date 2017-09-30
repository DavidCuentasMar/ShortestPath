/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package1;

import java.awt.Color;

/**
 *
 * @author Andres
 */
class Nodo {
    public int name;
    public int posx, posy;
    public Color color;

    public Nodo(int name, int posx, int posy, Color color) {
        this.name = name;
        this.posx = posx;
        this.posy = posy;
        this.color = color;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
