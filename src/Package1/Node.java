
package Package1;

import java.awt.Color;

/**
 *
 * @author Andres
 */
public class Node {
    
    protected String name;
    protected int posx;
    protected int posy;
    protected Color color;

    public Node(String name, int posx, int posy, Color color) {
        this.name = name;
        this.posx = posx;
        this.posy = posy;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
    
    
}
