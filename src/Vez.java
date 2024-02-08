import java.awt.*;
import java.awt.geom.*;
/**
 * Třída reprezentující Věž
 */
public class Vez extends Figurka{
    boolean bool = false;
    private int x;
    private int y;
    private double width;
    private double height;
    private Color color;
    private Policko policko;
    private boolean pohyb;
    private boolean naTahu;
    private boolean jizOdehral;
    public Vez(int x, int y, double width, double height, Color color, Policko policko, boolean pohyb, boolean naTahu) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.policko = policko;
        this.pohyb = pohyb;
        this.naTahu = naTahu;
    }

    /**
     * Vykreslení věže
     */
    public void paint(Graphics2D g2) {
        double width, height, x,y;
        if(getPolicko() == null) {
            return;
        }
        width = getPolicko().getWidth();
        height = getPolicko().getHeight();
        if (bool && !pohyb) {
            x = getPolicko().getX() + width / 2;
            y = getPolicko().getY() + height / 2;
            this.x = (int) (getPolicko().getX() + width / 2);
            this.y = (int) (getPolicko().getY() + height / 2);
            this.width = getPolicko().getWidth();;
            this.height = getPolicko().getHeight();

        }
        else {
            x = this.x;
            y = this.y;
        }

        Rectangle2D.Double podstava = new Rectangle2D.Double(
                x - width/2 + width/12, y - height/4 + height/2 - height/14, width - width/6, height/4 - height/10
        );
        Rectangle2D.Double telo = new Rectangle2D.Double(
            x - width/3 + width/16, y - height/4 + height/16, width*2/3 - width/8,height/2 - height/8
        );
        Rectangle2D.Double horniPodstava = new Rectangle2D.Double(
                x -width/2 + width/12,y - height/4 - height/8 + height/10,width - width/6,height/10
        );
        Rectangle2D.Double hradba1 = new Rectangle2D.Double(
                x - width/2 + width/12,y - height/2 + height/6,width/5 - width/20, height/8 - height/20
        );

        Rectangle2D.Double hradba2 = new Rectangle2D.Double(
                x - width/10 + width/40,y - height/2 + height/6,width/5 - width/20, height/8 - height/20
        );

        Rectangle2D.Double hradba3 = new Rectangle2D.Double(
                x + width/2 -width/5 - width/30,y - height/2 + height/6,width/5 - width/20, height/8 - height/20
        );

        g2.setColor(color);
        Path2D allShapes = new Path2D.Double();
        allShapes.append(podstava,false);
        allShapes.append(telo,false);
        allShapes.append(horniPodstava, false);
        allShapes.append(hradba1, false);
        allShapes.append(hradba2, false);
        allShapes.append(hradba3,false);

        if(color != Color.BLACK){
            g2.setColor(Color.BLACK);
            g2.draw(allShapes);
        }
        else {
            g2.setColor(Color.white);
            g2.draw(allShapes);
        }
        g2.setColor(color);
        g2.fill(allShapes);
        bool = true;

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return (int) width;
    }

    @Override
    public int getHeight() {
        return (int) height;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Policko getPolicko() {
        return policko;
    }
    @Override
    public void setPolicko(Policko policko) {
        this.policko = policko;
    }

    @Override
    public boolean isPohyb() {
        return pohyb;
    }

    @Override
    public void setPohyb(boolean pohyb) {
        this.pohyb = pohyb;
    }
    @Override
    public boolean isNaTahu() {
        return naTahu;
    }
    @Override
    public void setNaTahu(boolean naTahu) {
        this.naTahu = naTahu;
    }

    public boolean isJizOdehral() {
        return jizOdehral;
    }

    public void setJizOdehral(boolean jizOdehral) {
        this.jizOdehral = jizOdehral;
    }

    /**
     * Zobrazí validní tahy
     */
    @Override
    public void zobrazValidni() {
        int x = policko.getRadka();
        int y = policko.getSloupec();

        for (int i = x - 1; i < 8; i++) {
            if( i == x - 1) {
                continue;
            }
            if(obsazenoNepritelem(i, y - 1, color)){
                DrawingPanel.policka[i][y - 1].setColor(DrawingPanel.VALIDNI_TAH);
                break;
            }
            if (neobsahujeFigurku(i, y - 1, color)) {
                DrawingPanel.policka[i][y - 1].setColor(DrawingPanel.VALIDNI_TAH);
            }else {
                break;
            }
        }
        for (int i = x - 1; i >= 0 ; i--) {
            if( i == x - 1){
                continue;
            }
            if(obsazenoNepritelem(i, y - 1, color)){
                DrawingPanel.policka[i][y - 1].setColor(DrawingPanel.VALIDNI_TAH);
                break;
                }
            if (neobsahujeFigurku(i, y - 1, color)) {
                DrawingPanel.policka[i][y - 1].setColor(DrawingPanel.VALIDNI_TAH);
            } else {
                break;
            }
        }
        for (int i = y - 1; i < 8; i++) {
            if( i == y - 1){
                continue;
            }
        if(obsazenoNepritelem(x - 1, i, color)){
            DrawingPanel.policka[x-1][i].setColor(DrawingPanel.VALIDNI_TAH);
            break;
        }
        if (neobsahujeFigurku(x - 1, i, color)) {
                DrawingPanel.policka[x-1][i].setColor(DrawingPanel.VALIDNI_TAH);
            } else {
                break;
            }
        }
        for (int i = y - 1; i >= 0 ; i--) {
            if( i == y - 1){
                continue;
            }
            if(obsazenoNepritelem(x - 1, i, color)){
                DrawingPanel.policka[x-1][i].setColor(DrawingPanel.VALIDNI_TAH);
                break;
            }
            if (neobsahujeFigurku(x - 1, i, color)) {
                DrawingPanel.policka[x-1][i].setColor(DrawingPanel.VALIDNI_TAH);
            } else {
                break;
            }
        }
    }
    /**
     * Zobrazí políčka ohrožená instancí této třídy
     */
    @Override
    public void zobrazOhrozene(boolean bool) {
        int x = policko.getRadka();
        int y = policko.getSloupec();

        for (int i = x - 1; i < 8; i++) {
            if( i == x - 1) {
                continue;
            }
            if(obsazenoNepritelem(i, y - 1, color)){
                DrawingPanel.policka[i][y - 1].setOhrozena(bool);
                break;
            }
            if (neobsahujeFigurku(i, y - 1, color)) {
                DrawingPanel.policka[i][y - 1].setOhrozena(bool);
            }else {
                break;
            }
        }
        for (int i = x - 1; i >= 0 ; i--) {
            if( i == x - 1){
                continue;
            }
            if(obsazenoNepritelem(i, y - 1, color)){
                DrawingPanel.policka[i][y - 1].setOhrozena(bool);
                break;
            }
            if (neobsahujeFigurku(i, y - 1, color)) {
                DrawingPanel.policka[i][y - 1].setOhrozena(bool);
            } else {
                break;
            }
        }
        for (int i = y - 1; i < 8; i++) {
            if( i == y - 1){
                continue;
            }
            if(obsazenoNepritelem(x - 1, i, color)){
                DrawingPanel.policka[x-1][i].setOhrozena(bool);
                break;
            }
            if (neobsahujeFigurku(x - 1, i, color)) {
                DrawingPanel.policka[x-1][i].setOhrozena(bool);
            } else {
                break;
            }

        }
        for (int i = y - 1; i >= 0 ; i--) {
            if( i == y - 1){
                continue;
            }
            if(obsazenoNepritelem(x - 1, i, color)){
                DrawingPanel.policka[x-1][i].setOhrozena(bool);
                break;
            }
            if (neobsahujeFigurku(x - 1, i, color)) {
                DrawingPanel.policka[x-1][i].setOhrozena(bool);
            } else {
                break;
            }
        }
    }
}
