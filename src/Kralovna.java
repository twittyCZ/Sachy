import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
/**
 * Třída reprezentující Královny
 */
public class Kralovna extends Figurka{

    boolean bool = false;
    private int x;
    private int y;
    private double width;
    private double height;

    private Color color;
    private Policko policko;
    private boolean pohyb;
    private boolean naTahu;
    public Kralovna(int x, int y, double width, double height, Color color, Policko policko, boolean pohyb, boolean naTahu) {

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
     * Vykreslení královny
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
        Rectangle2D.Double stred  = new Rectangle2D.Double(
                x - width/5*1.5 , y - height/16, width*3/5,height/4
        );
        Ellipse2D.Double kulicka1 = new Ellipse2D.Double(
            x - width*2/5,y - height*2/8,width/16,height/16
        );
        Ellipse2D.Double kulicka2 = new Ellipse2D.Double(
                x - width*1/5,y - height*3/8,width/16,height/16
        );
        Ellipse2D.Double kulicka3 = new Ellipse2D.Double(
                x + width*0/5,y - height*4/8,width/16,height/16
        );
        Ellipse2D.Double kulicka4 = new Ellipse2D.Double(
                x + width*1/5,y - height*3/8,width/16,height/16
        );
        Ellipse2D.Double kulicka5 = new Ellipse2D.Double(
                x + width*2/6,y - height*2/8,width/16,height/16
        );

        Arc2D.Double podlozka = new Arc2D.Double(
                x - width/5*1.5,y + height/6,width*3/5,height/8, 180,180,1
        );

        Arc2D.Double podlozka2 = new Arc2D.Double(
                x - width/5*2,y + height*3/11,width*4/5,height/8, 180,180,1
        );

        Path2D allShapes = new Path2D.Double();
        allShapes.append(stred,false);

        allShapes.moveTo(kulicka1.getCenterX(),kulicka1.getCenterY());
        allShapes.lineTo(stred.getX(), stred.getY());
        allShapes.lineTo(stred.getCenterX() - width/5, stred.getY());
        allShapes.lineTo(kulicka1.getCenterX(),kulicka1.getCenterY());

        allShapes.moveTo(kulicka2.getCenterX(),kulicka2.getCenterY());
        allShapes.lineTo(stred.getX(), stred.getY());
        allShapes.lineTo(stred.getCenterX(), stred.getY());
        allShapes.lineTo(kulicka2.getCenterX(),kulicka2.getCenterY());

        allShapes.moveTo(kulicka3.getCenterX(),kulicka3.getCenterY());
        allShapes.lineTo(stred.getCenterX() - width/10, stred.getY());
       allShapes.lineTo(stred.getCenterX() + width/10, stred.getY());
        allShapes.lineTo(kulicka3.getCenterX(),kulicka3.getCenterY());

        allShapes.moveTo(kulicka4.getCenterX(),kulicka4.getCenterY());
        allShapes.lineTo(stred.getCenterX(), stred.getY());
        allShapes.lineTo(stred.getCenterX() + width/5*1.5, stred.getY());
        allShapes.lineTo(kulicka4.getCenterX(),kulicka4.getCenterY());

        allShapes.moveTo(kulicka5.getCenterX(),kulicka5.getCenterY());
        allShapes.lineTo(stred.getX() + width/2, stred.getY());
        allShapes.lineTo(stred.getCenterX() + width/5*1.5, stred.getY());
        allShapes.lineTo(kulicka5.getCenterX(),kulicka5.getCenterY());

        allShapes.append(kulicka1,false);
        allShapes.append(kulicka2,false);
        allShapes.append(kulicka3,false);
        allShapes.append(kulicka4,false);
        allShapes.append(kulicka5,false);
        allShapes.append(podlozka,false);
        allShapes.append(podlozka2,false);


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

        g2.fill(kulicka1);
        g2.fill(kulicka2);
        g2.fill(kulicka3);
        g2.fill(kulicka4);
        g2.fill(kulicka5);
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
    public boolean isPohyb() {
        return pohyb;
    }

    @Override
    public void setPohyb(boolean pohyb) {
        this.pohyb = pohyb;
    }

    @Override
    public void setPolicko(Policko policko) {
        this.policko = policko;
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
        int i = x - 1;
        int j = y - 1;
        while(j < 8 || i < 8){
            if(i >= 8 || j >= 8){
                break;
            }
            if(i == x - 1){
                i++;
                j++;
                continue;
            }
            if(j == y - 1){
                i++;
                j++;
                continue;
            }
            if(obsazenoNepritelem(i,  j, color)){
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                i++;
                j++;
                break;
            } if(neobsahujeFigurku(i,  j, color)){
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                i++;
                j++;
            } else {
                i++;
                j++;
                break;
            }
        }
        i = x - 1;
        j = y - 1;
        while(i >= 0 || j >= 0){
            if(i <= - 1 || j <= -1){
                break;
            }
            if(i == x - 1){
                i--;
                j--;
                continue;
            }
            if(j == y - 1){
                i--;
                j--;
                continue;
            }
            if(obsazenoNepritelem(i,  j, color)){
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                break;
            } if(neobsahujeFigurku(i,  j, color)){
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                i--;
                j--;
            } else {
                break;
            }
        }
        i = x - 1;
        j = y - 1;
        while(i < 8 || j >= 0){
            if(i >= 8 || j <= -1){
                break;
            }
            if(i == x - 1){
                i++;
                j--;
                continue;
            }
            if(j == y - 1){
                i++;
                j--;
                continue;
            }
            if(obsazenoNepritelem(i,  j, color)){
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                break;
            } if(neobsahujeFigurku(i,  j, color)){
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                i++;
                j--;
            } else {
                break;
            }
        }
        i = x - 1;
        j = y - 1;
        while(i >= 0 || j < 8){
            if(i <= - 1 || j >= 8){
                break;
            }
            if(i == x - 1){
                i--;
                j++;
                continue;
            }
            if(j == y - 1){
                i--;
                j++;
                continue;
            }
            if(obsazenoNepritelem(i,  j, color)){
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                break;
            } if(neobsahujeFigurku(i,  j, color)){
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                i--;
                j++;
            } else {
                break;
            }
        }
    }
    /**
     * Zobrazí políčka ohrožené instancí této třídy
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
                DrawingPanel.policka[i][y - 1].setOhrozena(bool);;
                break;
            }
            if (neobsahujeFigurku(i, y - 1, color)) {
                DrawingPanel.policka[i][y - 1].setOhrozena(bool);;
            }else {
                break;
            }
        }
        for (int i = x - 1; i >= 0 ; i--) {
            if( i == x - 1){
                continue;
            }
            if(obsazenoNepritelem(i, y - 1, color)){
                DrawingPanel.policka[i][y - 1].setOhrozena(bool);;
                break;
            }
            if (neobsahujeFigurku(i, y - 1, color)) {
                DrawingPanel.policka[i][y - 1].setOhrozena(bool);;
            } else {
                break;
            }
        }
        for (int i = y - 1; i < 8; i++) {
            if( i == y - 1){
                continue;
            }
            if(obsazenoNepritelem(x - 1, i, color)){
                DrawingPanel.policka[x-1][i].setOhrozena(bool);;
                break;
            }
            if (neobsahujeFigurku(x - 1, i, color)) {
                DrawingPanel.policka[x-1][i].setOhrozena(bool);;
            } else {
                break;
            }

        }
        for (int i = y - 1; i >= 0 ; i--) {
            if( i == y - 1){
                continue;
            }
            if(obsazenoNepritelem(x - 1, i, color)){
                DrawingPanel.policka[x-1][i].setOhrozena(bool);;
                break;
            }
            if (neobsahujeFigurku(x - 1, i, color)) {
                DrawingPanel.policka[x-1][i].setOhrozena(bool);;
            } else {
                break;
            }
        }
        int i = x - 1;
        int j = y - 1;
        while(j < 8 || i < 8){
            if(i >= 8 || j >= 8){
                break;
            }
            if(i == x - 1){
                i++;
                j++;
                continue;
            }
            if(j == y - 1){
                i++;
                j++;
                continue;
            }
            if(obsazenoNepritelem(i,  j, color)){
                DrawingPanel.policka[i][j].setOhrozena(bool);;
                i++;
                j++;
                break;
            } if(neobsahujeFigurku(i,  j, color)){
                DrawingPanel.policka[i][j].setOhrozena(bool);;
                i++;
                j++;
            } else {
                i++;
                j++;
                break;
            }
        }
        i = x - 1;
        j = y - 1;
        while(i >= 0 || j >= 0){
            if(i <= - 1 || j <= -1){
                break;
            }
            if(i == x - 1){
                i--;
                j--;
                continue;
            }
            if(j == y - 1){
                i--;
                j--;
                continue;
            }
            if(obsazenoNepritelem(i,  j, color)){
                DrawingPanel.policka[i][j].setOhrozena(bool);;
                break;
            } if(neobsahujeFigurku(i,  j, color)){
                DrawingPanel.policka[i][j].setOhrozena(bool);;
                i--;
                j--;
            } else {
                break;
            }
        }
        i = x - 1;
        j = y - 1;
        while(i < 8 || j >= 0){
            if(i >= 8 || j <= -1){
                break;
            }
            if(i == x - 1){
                i++;
                j--;
                continue;
            }
            if(j == y - 1){
                i++;
                j--;
                continue;
            }
            if(obsazenoNepritelem(i,  j, color)){
                DrawingPanel.policka[i][j].setOhrozena(bool);;
                break;
            } if(neobsahujeFigurku(i,  j, color)){
                DrawingPanel.policka[i][j].setOhrozena(bool);;
                i++;
                j--;
            } else {
                break;
            }
        }
        i = x - 1;
        j = y - 1;
        while(i >= 0 || j < 8){
            if(i <= - 1 || j >= 8){
                break;
            }
            if(i == x - 1){
                i--;
                j++;
                continue;
            }
            if(j == y - 1){
                i--;
                j++;
                continue;
            }
            if(obsazenoNepritelem(i,  j, color)){
                DrawingPanel.policka[i][j].setOhrozena(bool);;
                break;
            } if(neobsahujeFigurku(i,  j, color)){
                DrawingPanel.policka[i][j].setOhrozena(bool);;
                i--;
                j++;
            } else {
                break;
            }
        }
    }


}