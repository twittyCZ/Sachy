import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
/**
 * Třída reprezentující Koně
 */
public class Kun extends Figurka{
    boolean bool = false;
    private int x;
    private int y;
    private double width;
    private double height;

    private Color color;
    private Policko policko;
    private boolean pohyb;
    private boolean naTahu;
    public Kun(int x, int y, double width, double height, Color color,Policko policko, boolean pohyb, boolean naTahu) {

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
     * Vykreslení koně
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

        g2.setColor(color);
        Arc2D.Double zadek = new Arc2D.Double(
                x- width*2/10,y - height/6 - height/24 ,width*3/5,height + height/5 - height/12, 0,180,1
        );

        Ellipse2D.Double telo = new Ellipse2D.Double(
                x - width/8, y - height*61/160 - height/24, width/2, height*4/5 - height/12
        );

        Ellipse2D.Double hlava = new Ellipse2D.Double(
                x- width/20,y - height*2/4,width/3,height*3/5
        );

        double ellipseWidth = hlava.getWidth();
        double angleInDegrees = 55.0;
        double angleInRadians = Math.toRadians(angleInDegrees);
        double centerX = hlava.getCenterX();
        double centerY = hlava.getCenterY();
        AffineTransform rotation = AffineTransform.getRotateInstance(angleInRadians, centerX, centerY);
        AffineTransform  translation  = AffineTransform.getTranslateInstance(-ellipseWidth/3,hlava.getHeight()/4);
        AffineTransform combined = new AffineTransform();
        combined.concatenate(rotation);
        combined.concatenate(translation);
        Shape rotatedHead = combined.createTransformedShape(hlava);

        Path2D.Double allShapes = new Path2D.Double();
        allShapes.append(zadek,false);
        allShapes.append(telo,true);
        allShapes.append(rotatedHead,false);

        Path2D.Double ucho = new Path2D.Double();
        ucho.moveTo(x - width/10, y - height/2.5);
        ucho.lineTo(x - width/10, y - height/2.1);
        ucho.lineTo(x - width/20, y - height/2.4);


        Path2D.Double jazyk = new Path2D.Double();
        jazyk.moveTo(x - width/3,y - height/20);
        jazyk.lineTo(x - width/4,y - height/8);

        Stroke newStroke = new BasicStroke(2.0f);
        g2.setStroke(newStroke);

        g2.draw(allShapes);

        Ellipse2D.Double oko = new Ellipse2D.Double(
          x - width/16 - width/8,y - height/32 - height/16*4,width/8,height/16
        );
        angleInDegrees = -20.0;
         angleInRadians = Math.toRadians(angleInDegrees);
        rotation = AffineTransform.getRotateInstance(angleInRadians, oko.getCenterX(), oko.getCenterY());
        Shape oko2 = rotation.createTransformedShape(oko);


        if(color != Color.BLACK){
            g2.setColor(Color.BLACK);
            g2.draw(allShapes);
        }
        else {
            g2.setColor(Color.white);
            g2.draw(allShapes);
        }
        g2.setColor(color);

       g2.fill(zadek);
       g2.fill(allShapes);

        if(color != Color.BLACK){
            g2.setColor(Color.BLACK);
            g2.draw(jazyk);
            g2.draw(ucho);
            g2.fill(oko2);
        }
        else {
            g2.setColor(Color.white);
            g2.draw(jazyk);
            g2.draw(ucho);
            g2.fill(oko2);
        }
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
    /**
     * Zobrazí validní tahy
     */
    @Override
    public void zobrazValidni() {
        int x = policko.getRadka();
        int y = policko.getSloupec();
        if( x - 1 + 2 < 8 && y - 1 -1 >= 0 && neobsahujeFigurku(x - 1 + 2,y - 1 -1, color)){
            DrawingPanel.policka[x-1+2][y-1 -1].setColor(DrawingPanel.VALIDNI_TAH);
        }
        if(x-1 +1 <8  && y-1 -2 >= 0 && neobsahujeFigurku(x - 1 + 1,y - 1 - 2, color)){
            DrawingPanel.policka[x-1 +1][y-1 -2].setColor(DrawingPanel.VALIDNI_TAH);
        }
        if(x-1 -1 >=0 && y-1 -2 >= 0 && neobsahujeFigurku(x - 1 - 1,y - 1 - 2, color)){
            DrawingPanel.policka[x-1 -1][y-1 -2].setColor(DrawingPanel.VALIDNI_TAH);
        }
        if(x - 1 - 2 >= 0 && y -1 - 1 >=0 && neobsahujeFigurku(x - 1 - 2,y - 1 -1, color)){
            DrawingPanel.policka[x-1 -2][y-1 -1].setColor(DrawingPanel.VALIDNI_TAH);
        }
        if(x - 1 - 2 >= 0 && y - 1 + 1 <8 && neobsahujeFigurku(x - 1 - 2,y - 1 +1, color)){
            DrawingPanel.policka[x-1 -2][y-1 +1].setColor(DrawingPanel.VALIDNI_TAH);
        }
        if(x - 1 -1 >= 0 && y - 1 +2 < 8 && neobsahujeFigurku(x - 1 -1,y - 1  +2, color)){
            DrawingPanel.policka[x-1 -1][y-1 +2].setColor(DrawingPanel.VALIDNI_TAH);
        }
        if(x - 1 + 1 < 8 && y - 1 + 2 < 8 && neobsahujeFigurku(x - 1 + 1,y - 1 + 2, color)){
            DrawingPanel.policka[x-1 +1][y-1 + 2].setColor(DrawingPanel.VALIDNI_TAH);
        }
        if( x - 1 + 2 < 8 && y - 1 +1 < 8 && neobsahujeFigurku(x - 1 + 2,y - 1 +1, color)){
            DrawingPanel.policka[x-1+2][y-1 +1].setColor(DrawingPanel.VALIDNI_TAH);
        }
    }
    /**
     * Zobrazí ohrožené políčka instancí této třídy
     */
    @Override
    public void zobrazOhrozene(boolean bool) {
        int x = policko.getRadka();
        int y = policko.getSloupec();
        if( x - 1 + 2 < 8 && y - 1 -1 >= 0 && neobsahujeFigurku(x - 1 + 2,y - 1 -1, color)){
            DrawingPanel.policka[x-1+2][y-1 -1].setOhrozena(bool);
        }
        if(x-1 +1 <8  && y-1 -2 >= 0 && neobsahujeFigurku(x - 1 + 1,y - 1 - 2, color)){
            DrawingPanel.policka[x-1 +1][y-1 -2].setOhrozena(bool);
        }
        if(x-1 -1 >=0 && y-1 -2 >= 0 && neobsahujeFigurku(x - 1 - 1,y - 1 - 2, color)){
            DrawingPanel.policka[x-1 -1][y-1 -2].setOhrozena(bool);
        }
        if(x - 1 - 2 >= 0 && y -1 - 1 >=0 && neobsahujeFigurku(x - 1 - 2,y - 1 -1, color)){
            DrawingPanel.policka[x-1 -2][y-1 -1].setOhrozena(bool);
        }
        if(x - 1 - 2 >= 0 && y - 1 + 1 <8 && neobsahujeFigurku(x - 1 - 2,y - 1 +1, color)){
            DrawingPanel.policka[x-1 -2][y-1 +1].setOhrozena(bool);
        }
        if(x - 1 -1 >= 0 && y - 1 +2 < 8 && neobsahujeFigurku(x - 1 -1,y - 1  +2, color)){
            DrawingPanel.policka[x-1 -1][y-1 +2].setOhrozena(bool);
        }
        if(x - 1 + 1 < 8 && y - 1 + 2 < 8 && neobsahujeFigurku(x - 1 + 1,y - 1 + 2, color)){
            DrawingPanel.policka[x-1 +1][y-1 + 2].setOhrozena(bool);
        }
        if( x - 1 + 2 < 8 && y - 1 +1 < 8 && neobsahujeFigurku(x - 1 + 2,y - 1 +1, color)){
            DrawingPanel.policka[x-1+2][y-1 +1].setOhrozena(bool);
        }
    }


}