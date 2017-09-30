
package Package1;

/**
 *
 * @author Andres
 */
public class Edge {
    
    protected int nodoinicial;
    protected int nodofinal;
    protected int dist;
    protected int x1, y1, x2, y2;

    public Edge(int nodoinicial, int nodofinal, int x1, int y1, int x2, int y2, int dist) {
        this.nodoinicial = nodoinicial;
        this.nodofinal = nodofinal;
        this.dist = dist;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getNodoinicial() {
        return nodoinicial;
    }

    public void setNodoinicial(int nodoinicial) {
        this.nodoinicial = nodoinicial;
    }

    public int getNodofinal() {
        return nodofinal;
    }

    public void setNodofinal(int nodofinal) {
        this.nodofinal = nodofinal;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
    
    
    
}
