import java.awt.*;
import java.awt.geom.*;
import java.sql.SQLOutput;
/**
 * Třída reprezentující Krále
 */
public class Kral  extends Figurka{
    boolean dalsiSpusteni = false;
    private int x;
    private int y;
    private double width;
    private double height;
    private Color color;
    private Policko policko;
    private boolean pohyb;
    private boolean naTahu;
    private boolean jizOdehral;
    private boolean sach = false;

    public Kral(int x, int y, double width, double height, Color color, Policko policko, boolean pohyb, boolean naTahu) {
        super();
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
     * Vykreslení krále

     */
    public void paint(Graphics2D g2) {
        double width, height, x,y;
        if(getPolicko() == null) {
            return;
        }
        width = getPolicko().getWidth();
        height = getPolicko().getHeight();
        if (dalsiSpusteni && !pohyb) {
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
            Ellipse2D.Double head = new Ellipse2D.Double(x - width / 6, y - height / 3, width / 3, height / 2);

            Ellipse2D.Double head3 = new Ellipse2D.Double(x - width / 8, y - height / 3, width / 4, height / 2);

            Arc2D.Double podlozka = new Arc2D.Double(
                    x - width / 5 * 1, y + height / 6, width * 2 / 5, height / 8, 180, 180, 1
            );

            Arc2D.Double podlozka2 = new Arc2D.Double(
                    x - width / 5 * 2, y + height * 3 / 11, width * 4 / 5, height / 8, 180, 180, 1
            );


            double ellipseWidth = head.getWidth();
            double angleInDegrees = 45.0;
            double angleInRadians = Math.toRadians(angleInDegrees);
            double centerX = head.getCenterX();
            double centerY = head.getCenterY();
            AffineTransform rotation = AffineTransform.getRotateInstance(angleInRadians, centerX, centerY);
            AffineTransform translation = AffineTransform.getTranslateInstance(ellipseWidth, 0);
            AffineTransform combined = new AffineTransform();
            combined.concatenate(rotation);
            combined.concatenate(translation);
            Shape rotatedHead = combined.createTransformedShape(head);


            ellipseWidth = head.getWidth();

            angleInDegrees = -45.0;
            angleInRadians = Math.toRadians(angleInDegrees);
            rotation = AffineTransform.getRotateInstance(angleInRadians, centerX, centerY);
            translation = AffineTransform.getTranslateInstance(-ellipseWidth, 0);

            combined = new AffineTransform();
            combined.concatenate(rotation);
            combined.concatenate(translation);

            Shape rotatedHead2 = combined.createTransformedShape(head);
            g2.fill(podlozka);

            Path2D allShapes = new Path2D.Double();
            allShapes.append(podlozka, false);
            allShapes.append(head3, false);
            allShapes.append(rotatedHead, false);
            allShapes.append(rotatedHead2, false);
            allShapes.append(podlozka2, false);

            allShapes.moveTo(head3.getCenterX(), head3.getCenterY());
            allShapes.lineTo(head3.getCenterX(), head3.getY() - height / 7);
            allShapes.moveTo(head3.getX(), head3.getY() - height / 14);
            allShapes.lineTo(head3.getX() + width / 4, head3.getY() - height / 14);

            Stroke newStroke = new BasicStroke(3.0f);
            g2.setStroke(newStroke);


            if (color != Color.BLACK) {
                g2.setColor(Color.BLACK);
                g2.draw(allShapes);
            } else {
                g2.setColor(Color.white);
                g2.draw(allShapes);
            }
            g2.setColor(color);
            g2.fill(allShapes);
        dalsiSpusteni = true;


    }
    public void setPolicko(Policko policko) {
        this.policko = policko;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public Policko getPolicko() {
        return this.policko;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return (int) width;
    }

    @Override
    public int getHeight() {
        return (int) height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isPohyb() {
        return pohyb;
    }

    public void setPohyb(boolean pohyb) {
        this.pohyb = pohyb;
    }

    public boolean getPohyb() {
        return pohyb;
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

    public boolean isSach() {
        return sach;
    }

    public void setSach(boolean sach) {
        this.sach = sach;
    }

    /**
     * Zobrazí validní tahy
     */
    @Override
    public void zobrazValidni() {
        int x = policko.getRadka();
        int y = policko.getSloupec();
        int sloupec;
        int radka;
        if(color == Color.BLACK){
            for (int i = 0; i < DrawingPanel.figurkyBile.size(); i++) {
                DrawingPanel.figurkyBile.get(i).zobrazOhrozene(true);
            }
            radka =  DrawingPanel.kral2.getPolicko().getRadka();
            sloupec =  DrawingPanel.kral2.getPolicko().getSloupec();
        } else {
            for (int i = 0; i < DrawingPanel.figurkyCerne.size(); i++) {
                DrawingPanel.figurkyCerne.get(i).zobrazOhrozene(true);
            }
            radka =  DrawingPanel.kral.getPolicko().getRadka();
            sloupec =  DrawingPanel.kral.getPolicko().getSloupec();
        }

        if(x-1-1 >= 0 && y-1-1 >=0 && neobsahujeFigurku(x - 1 -1, y - 1 - 1, color)
                && !DrawingPanel.policka[x-1-1][y-1-1].isOhrozena()){
            int radka1 = DrawingPanel.policka[x-1-1][y-1-1].getRadka();
            int sloupec1 = DrawingPanel.policka[x-1-1][y-1-1].getSloupec();
            if(Math.abs(radka - radka1) != 1 || Math.abs(sloupec1 - sloupec) != 1){
                DrawingPanel.policka[x-1-1][y-1-1].setColor(DrawingPanel.VALIDNI_TAH);
            }
        }
        if(x-1-1 >= 0 && y-1+1 <8 && neobsahujeFigurku(x - 1 - 1, y - 1 + 1, color)
       && !DrawingPanel.policka[x-1-1][y-1+1].isOhrozena()){
            int radka1 = DrawingPanel.policka[x-1-1][y-1+1].getRadka();
            int sloupec1 = DrawingPanel.policka[x-1-1][y-1+1].getSloupec();
            if(Math.abs(radka - radka1) != 1 || Math.abs(sloupec1 - sloupec) != 1){
                DrawingPanel.policka[x-1-1][y-1+1].setColor(DrawingPanel.VALIDNI_TAH);
            }
        }
        if(x-1+1 < 8 && y-1-1 >= 0 && neobsahujeFigurku(x - 1 + 1, y - 1 - 1, color)
                && !DrawingPanel.policka[x-1+1][y-1-1].isOhrozena()){
            int radka1 = DrawingPanel.policka[x-1+1][y-1-1].getRadka();
            int sloupec1 = DrawingPanel.policka[x-1+1][y-1-1].getSloupec();
            if(Math.abs(radka - radka1) != 1 || Math.abs(sloupec1 - sloupec) != 1){
                DrawingPanel.policka[x-1+1][y-1-1].setColor(DrawingPanel.VALIDNI_TAH);
            }
        }
        if(x-1+1 < 8 && y-1+1 < 8 && neobsahujeFigurku(x - 1 + 1, y - 1 + 1, color)
                && !DrawingPanel.policka[x-1+1][y-1+1].isOhrozena()){
            int radka1 = DrawingPanel.policka[x-1+1][y-1+1].getRadka();
            int sloupec1 = DrawingPanel.policka[x-1+1][y-1+1].getSloupec();
            if(Math.abs(radka - radka1) != 1 || Math.abs(sloupec1 - sloupec) != 1){
                DrawingPanel.policka[x-1+1][y-1+1].setColor(DrawingPanel.VALIDNI_TAH);
            }
        }
        if(x-1-1 >=0 && neobsahujeFigurku( x - 1 - 1, y - 1, color)
                && !DrawingPanel.policka[x-1-1][y-1].isOhrozena()){
            int radka1 = DrawingPanel.policka[x-1-1][y-1].getRadka();
            int sloupec1 = DrawingPanel.policka[x-1-1][y-1].getSloupec();
            if(Math.abs(radka - radka1) != 1 || Math.abs(sloupec1 - sloupec) != 1){
                DrawingPanel.policka[x-1-1][y-1].setColor(DrawingPanel.VALIDNI_TAH);
            }
        }
        if(x-1+1 < 8 && neobsahujeFigurku(x - 1 + 1, y - 1, color)
                && !DrawingPanel.policka[x-1+1][y-1].isOhrozena()){
            int radka1 = DrawingPanel.policka[x-1+1][y-1].getRadka();
            int sloupec1 = DrawingPanel.policka[x-1+1][y-1].getSloupec();
            if(Math.abs(radka - radka1) != 1 || Math.abs(sloupec1 - sloupec) != 1){
                DrawingPanel.policka[x-1+1][y-1].setColor(DrawingPanel.VALIDNI_TAH);
            }
        }
        if(y-1-1 >= 0 && neobsahujeFigurku(x - 1, y - 1 - 1, color)
                && !DrawingPanel.policka[x-1][y-1-1].isOhrozena()){
            int radka1 = DrawingPanel.policka[x-1][y-1-1].getRadka();
            int sloupec1 = DrawingPanel.policka[x-1][y-1-1].getSloupec();
            if(Math.abs(radka - radka1) != 1 || Math.abs(sloupec1 - sloupec) != 1){
                DrawingPanel.policka[x-1][y-1-1].setColor(DrawingPanel.VALIDNI_TAH);
            }
        }
        if(y-1+1 < 8 && neobsahujeFigurku(x-1, y - 1 + 1, color)
                && !DrawingPanel.policka[x-1][y-1+1].isOhrozena()){
            int radka1 = DrawingPanel.policka[x-1][y-1+1].getRadka();
            int sloupec1 = DrawingPanel.policka[x-1][y-1+1].getSloupec();
            if(Math.abs(radka - radka1) != 1 || Math.abs(sloupec1 - sloupec) != 1){
                DrawingPanel.policka[x-1][y-1+1].setColor(DrawingPanel.VALIDNI_TAH);
            }
        }
        if(!jizOdehral){
            if(neobsahujeFigurku(x-1, y - 1 - 2, color) && !obsazenoNepritelem(x-1, y-1-2,color)
                && neobsahujeFigurku(x-1, y - 1 -1, color) && !obsazenoNepritelem(x-1, y-1-1, color)
            && DrawingPanel.policka[x-1][y-1-3].getFigurka() instanceof Vez){
                if(!((Vez) DrawingPanel.policka[x-1][y-1-3].getFigurka()).isJizOdehral()){
                    if(!DrawingPanel.policka[x-1][y-1].isOhrozena() && !DrawingPanel.policka[x-1][y-1-1].isOhrozena() && !DrawingPanel.policka[x-1][y-1-2].isOhrozena()) {
                        int radka1 = DrawingPanel.policka[x-1][y-1-2].getRadka();
                        int sloupec1 = DrawingPanel.policka[x-1][y-1-2].getSloupec();
                        if(Math.abs(radka - radka1) != 1 || Math.abs(sloupec1 - sloupec) != 1){
                            DrawingPanel.policka[x-1][y-1-2].setColor(DrawingPanel.VALIDNI_TAH);
                        }
                    }
                 }
            }
            if(neobsahujeFigurku(x-1, y - 1+2, color) && !obsazenoNepritelem(x-1, y-1+2, color)
            && neobsahujeFigurku(x-1, y - 1 + 1, color) && !obsazenoNepritelem(x-1, y-1+1, color)
            && neobsahujeFigurku(x-1, y-1+3, color) && !obsazenoNepritelem(x-1, y-1+3,color)
            && DrawingPanel.policka[x-1][y-1+4].getFigurka() instanceof Vez){
                if(!((Vez) DrawingPanel.policka[x-1][y-1+4].getFigurka()).isJizOdehral()){
                    if(!DrawingPanel.policka[x-1][y-1].isOhrozena() && !DrawingPanel.policka[x-1][y-1+1].isOhrozena() && !DrawingPanel.policka[x-1][y-1+2].isOhrozena()) {
                        int radka1 = DrawingPanel.policka[x-1][y-1+2].getRadka();
                        int sloupec1 = DrawingPanel.policka[x-1][y-1+2].getSloupec();
                        if(Math.abs(radka - radka1) != 1 || Math.abs(sloupec1 - sloupec) != 1){
                            DrawingPanel.policka[x-1][y-1+2].setColor(DrawingPanel.VALIDNI_TAH);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < DrawingPanel.figurkyBile.size(); i++) {
            DrawingPanel.figurkyBile.get(i).zobrazOhrozene(false);
        }
        for (int i = 0; i < DrawingPanel.figurkyCerne.size(); i++) {
            DrawingPanel.figurkyCerne.get(i).zobrazOhrozene(false);
        }
    }

    /**
     * Kontrola, zda-li je král v patu
     */
    public boolean zkontrolujPat(){
        int x = policko.getRadka();
        int y = policko.getSloupec();
        int ohrozenaPolicka = 0;

        if(color == Color.BLACK){
            for (int i = 0; i < DrawingPanel.figurkyBile.size(); i++) {
                DrawingPanel.figurkyBile.get(i).zobrazOhrozene(true);
            }
            for (int i = 0; i < DrawingPanel.figurkyCerne.size(); i++) {
                DrawingPanel.figurkyCerne.get(i).zobrazOhrozene(false);
            }
        } else {
            for (int i = 0; i < DrawingPanel.figurkyCerne.size(); i++) {
                DrawingPanel.figurkyCerne.get(i).zobrazOhrozene(true);
            }
            for (int i = 0; i < DrawingPanel.figurkyBile.size(); i++) {
                DrawingPanel.figurkyBile.get(i).zobrazOhrozene(false);
            }
        }
        if(x-1-1 >= 0 && y-1-1 >=0){
            if(DrawingPanel.policka[x-1-1][y-1-1].isOhrozena()){
                ohrozenaPolicka++;
            }
        } else{
            ohrozenaPolicka++;
        }
        if(x-1-1 >= 0 && y-1+1 <8){
            if(DrawingPanel.policka[x-1-1][y-1+1].isOhrozena()){
                ohrozenaPolicka++;
            }
        }
        else {
            ohrozenaPolicka++;
        }
        if(x-1+1 < 8 && y-1+1 < 8){
            if(DrawingPanel.policka[x-1+1][y-1+1].isOhrozena()){
                ohrozenaPolicka++;
            }
        } else {
            ohrozenaPolicka++;
        }
        if(x-1+1 < 8 && y-1-1 >= 0){
            if(DrawingPanel.policka[x-1+1][y-1-1].isOhrozena()){
                ohrozenaPolicka++;
            }
        } else {
            ohrozenaPolicka++;
        }
        if(x-1-1 >=0){
            if(DrawingPanel.policka[x-1-1][y-1].isOhrozena()){
                ohrozenaPolicka++;
            }
        } else{
            ohrozenaPolicka++;
        }
        if(x-1+1 < 8){
            if(DrawingPanel.policka[x-1+1][y-1].isOhrozena()){
                ohrozenaPolicka++;
            }
        } else {
            ohrozenaPolicka++;
        }
        if(y-1-1 >= 0){
            if(DrawingPanel.policka[x-1][y-1-1].isOhrozena()){
                ohrozenaPolicka++;
            }
        } else{
            ohrozenaPolicka++;
        }
        if(y-1+1 < 8){
            if(DrawingPanel.policka[x-1][y-1+1].isOhrozena()){
                ohrozenaPolicka++;
            }
        } else {
            ohrozenaPolicka++;
        }
        if(!policko.isOhrozena()){
            ohrozenaPolicka++;
        }


            for (int i = 0; i < DrawingPanel.figurkyBile.size(); i++) {
                DrawingPanel.figurkyBile.get(i).zobrazOhrozene(false);
            }
            for (int i = 0; i < DrawingPanel.figurkyCerne.size(); i++) {
                DrawingPanel.figurkyCerne.get(i).zobrazOhrozene(false);
            }



            if(color == Color.BLACK){
                for (int i = 0; i < DrawingPanel.figurkyCerne.size(); i++) {
                    DrawingPanel.figurkyCerne.get(i).zobrazValidni();
                    if(DrawingPanel.figurkyCerne.get(i) instanceof Kral){
                        continue;
                    }
                    for (int j = 0; j < DrawingPanel.policka.length; j++) {
                        for (int k = 0; k < DrawingPanel.policka.length; k++) {
                            if(DrawingPanel.policka[j][k].getColor() == DrawingPanel.VALIDNI_TAH){
                                ohrozenaPolicka++;
                            }
                        }
                    }
                }
                for (int i = 0; i < DrawingPanel.figurkyCerne.size(); i++) {
                    DrawingPanel.figurkyCerne.get(i).skryjValidni();
                }
            } else {
                for (int i = 0; i < DrawingPanel.figurkyBile.size(); i++) {
                    DrawingPanel.figurkyBile.get(i).zobrazValidni();
                    if(DrawingPanel.figurkyBile.get(i) instanceof Kral){
                        continue;
                    }
                    for (int j = 0; j < DrawingPanel.policka.length; j++) {
                        for (int k = 0; k < DrawingPanel.policka.length; k++) {
                            if(DrawingPanel.policka[j][k].getColor() == DrawingPanel.VALIDNI_TAH){
                                ohrozenaPolicka++;
                            }
                        }
                    }
                }
                for (int i = 0; i < DrawingPanel.figurkyBile.size(); i++) {
                    DrawingPanel.figurkyBile.get(i).skryjValidni();
                }
            }


        if(ohrozenaPolicka == 10){
            return true;
        }
        return false;
    }


    /**
     * Umožní hráči odkrýt ohrožovaného krále a tím dát mat sám sobě
     * @return true pokud je mat
     */
    public boolean hloupyMat(){
        for (int i = 0; i < DrawingPanel.figurky.size(); i++) {
            DrawingPanel.figurky.get(i).zobrazOhrozene(false);
        }
        if(color ==  Color.BLACK){
            for (int i = 0; i < DrawingPanel.figurkyCerne.size(); i++) {
                DrawingPanel.figurkyCerne.get(i).zobrazOhrozene(false);
            }
            for (int i = 0; i < DrawingPanel.figurkyBile.size(); i++) {
                DrawingPanel.figurkyBile.get(i).zobrazOhrozene(true);
            }
            if(DrawingPanel.kral.getPolicko().isOhrozena() && !DrawingPanel.kral.isNaTahu())
            {
                return true;
            }
        } else {

            for (int i = 0; i < DrawingPanel.figurkyBile.size(); i++) {
                DrawingPanel.figurkyBile.get(i).zobrazOhrozene(false);
            }
            for (int i = 0; i < DrawingPanel.figurkyCerne.size(); i++) {
                DrawingPanel.figurkyCerne.get(i).zobrazOhrozene(true);
            }
            if(DrawingPanel.kral2.getPolicko().isOhrozena() && !DrawingPanel.kral2.isNaTahu())
            {
                return true;
            }
        }
        for (int i = 0; i < DrawingPanel.figurky.size(); i++) {
            DrawingPanel.figurky.get(i).zobrazOhrozene(false);
        }
        return false;
    }

    /**
     * Metoda pro zobrazení polí, které instance této třídy ohrožuje
     * @param bool true - ohrožuje, false - neohrožuje
     */
    @Override
    public void zobrazOhrozene(boolean bool) {
        int x = policko.getRadka();
        int y = policko.getSloupec();
        if(x-1-1 >= 0 && y-1-1 >=0 && neobsahujeFigurku(x - 1 -1, y - 1 - 1, color)){
            DrawingPanel.policka[x-1-1][y-1-1].setOhrozena(bool);
        }
        if(x-1-1 >= 0 && y-1+1 <8 && neobsahujeFigurku(x - 1 - 1, y - 1 + 1, color)){
            DrawingPanel.policka[x-1-1][y-1+1].setOhrozena(bool);
        }
        if(x-1+1 < 8 && y-1-1 >= 0 && neobsahujeFigurku(x - 1 + 1, y - 1 - 1, color)){
            DrawingPanel.policka[x-1+1][y-1-1].setOhrozena(bool);
        }
        if(x-1+1 < 8 && y-1+1 < 8 && neobsahujeFigurku(x - 1 + 1, y - 1 + 1, color)){
            DrawingPanel.policka[x-1+1][y-1+1].setOhrozena(bool);
        }
        if(x-1-1 >=0 && neobsahujeFigurku( x - 1 - 1, y - 1, color)){
            DrawingPanel.policka[x-1-1][y-1].setOhrozena(bool);
        }
        if(x-1+1 < 8 && neobsahujeFigurku(x - 1 + 1, y - 1, color)){
            DrawingPanel.policka[x-1+1][y-1].setOhrozena(bool);
        }
        if(y-1-1 >= 0 && neobsahujeFigurku(x - 1, y - 1 - 1, color)){
            DrawingPanel.policka[x-1][y-1-1].setOhrozena(bool);
        }
        if(y-1+1 < 8 && neobsahujeFigurku(x-1, y - 1 + 1, color)){
            DrawingPanel.policka[x-1][y-1+1].setOhrozena(bool);
        }
        if(!jizOdehral){
            if(neobsahujeFigurku(x-1, y - 1 - 2, color) && !obsazenoNepritelem(x-1, y-1-2,color)
                    && neobsahujeFigurku(x-1, y - 1 -1, color) && !obsazenoNepritelem(x-1, y-1-1, color)){
                DrawingPanel.policka[x-1][y-1-2].setOhrozena(bool);
            }
            if(neobsahujeFigurku(x-1, y - 1+2, color) && !obsazenoNepritelem(x-1, y-1+2, color)
                    && neobsahujeFigurku(x-1, y - 1 + 1, color) && !obsazenoNepritelem(x-1, y-1+1, color)){
                DrawingPanel.policka[x-1][y-1+2].setOhrozena(bool);
            }
        }
    }
}
