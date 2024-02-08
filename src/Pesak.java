import java.awt.*;
import java.awt.geom.*;
/**
 * Třída reprezentující Pěšáka
 */
public class Pesak extends Figurka{
    boolean bool = false;
    private int x;
    private int y;
    private double width;
    private double height;
    private Color color;
    private Policko policko;
    private boolean pohyb;
    private boolean naTahu;
    private boolean mimochodem;
    public Pesak(int x, int y, double width, double height, Color color, Policko policko, boolean pohyb, boolean naTahu){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.policko = policko;
        this.pohyb = pohyb;
        this.naTahu = naTahu;
        this.mimochodem = false;
    }


    /**
     * Vykreslení pěšáka
     */
    public void paint(Graphics2D g2) {
        double width, height, x,y;
        if(getPolicko() == null) {
            return;
        }
        width = getPolicko().getWidth();
        height = getPolicko().getHeight();
        x = getPolicko().getX() + width / 2;
        y = getPolicko().getY() + height / 2;

        if(pohyb){
            x = this.x;
            y = this.y;
        } else {
            this.width = getPolicko().getWidth();;
            this.height = getPolicko().getHeight();
            this.x = (int) (getPolicko().getX() + width / 2);
            this.y = (int) (getPolicko().getY() + height / 2);
        }

        Ellipse2D.Double head = new Ellipse2D.Double(x - width / 5 + width/14, y - height / 2 +height/6, width / 2.5 - width/7, height / 3 - height/7);
        Arc2D.Double foot = new Arc2D.Double(x - width / 2 + width/16, y + height / 2 - height / 3 -height/16, width - width/8, height / 1.5 - height/8, 0, 180, 1);
        Ellipse2D.Double body = new Ellipse2D.Double(x - width / 3 + width/16, y - height * 5 / 24 + height/16, width / 1.5 - width/8, height * 5 / 12 - height/8);

        Path2D allShapes = new Path2D.Double();

        allShapes.append(head, false);
        allShapes.append(foot, false);
        allShapes.append(body, false);


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
        g2.fill(body);

bool = true;
    }

    public Color getColor() {
        return color;
    }

    public Policko getPolicko() {
        return policko;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    public void setPolicko(Policko policko) {
        this.policko = policko;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return (int) width;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return (int) height;
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean isPohyb() {
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
    @Override
    public void setPohyb(boolean pohyb) {
        this.pohyb = pohyb;
    }

    public boolean isMimochodem() {
        return mimochodem;
    }

    public void setMimochodem(boolean mimochodem) {
        this.mimochodem = mimochodem;
    }

    /**
     * Metoda pro zobrazení tahů, kterou je možno figurkou provést
     */
    @Override
    public void zobrazValidni(){
        int x = policko.getRadka();
        int y = policko.getSloupec();
        // bílý
        if(color == Color.WHITE) {
            if (x == 2) {
                if(neobsahujeFigurku(x-1 +1, y-1, null) && neobsahujeFigurku(x - 1 + 2, y - 1, null)){
                    DrawingPanel.policka[x - 1 + 1][y - 1].setColor(DrawingPanel.VALIDNI_TAH);
                    DrawingPanel.policka[x - 1 + 2][y - 1].setColor(DrawingPanel.VALIDNI_TAH);
                } else if(neobsahujeFigurku(x - 1 + 1, y - 1, null)){
                    DrawingPanel.policka[x - 1 + 1][y - 1].setColor(DrawingPanel.VALIDNI_TAH);
                }
            }
            if(x-1+1 < 8 && neobsahujeFigurku(x-1+1, y-1, null)){
                DrawingPanel.policka[x - 1 + 1][y - 1].setColor(DrawingPanel.VALIDNI_TAH);
            }
        }
        // černý
        else {
            if(x == 7){
                if(neobsahujeFigurku(x - 1 - 1, y - 1, null) && neobsahujeFigurku(x - 1 - 2, y - 1, null)){
                    DrawingPanel.policka[x-1 - 1][y - 1].setColor(DrawingPanel.VALIDNI_TAH);
                    DrawingPanel.policka[x - 1 - 2][y - 1].setColor(DrawingPanel.VALIDNI_TAH);
                } else if(neobsahujeFigurku(x - 1 - 1, y - 1, null)){
                    DrawingPanel.policka[x-1 - 1][y - 1].setColor(DrawingPanel.VALIDNI_TAH);
                }
            }
            if(x-1-1 >= 0 && neobsahujeFigurku(x-1-1, y-1, null)){
                DrawingPanel.policka[x-1 - 1][y - 1].setColor(DrawingPanel.VALIDNI_TAH);
            }
        }
        Policko policko1 = zkontrolujMimochodem();
        if(policko1 != null){
            lzeZahraMimochodem(policko1);
        }
    zabirani_diagonalou();
    }

    /**
     * Metoda pro vyhodnocení, zda-li je možné provést zabrání mimochodem
     * @param policko1 policko, kam se chceme přesunout
     * @return true pokud lze zahrát
     */
    public boolean lzeZahraMimochodem(Policko policko1) {
        int sloupec1 = policko1.getSloupec();
        int radka1 = policko1.getRadka();
        int radka = policko.getRadka();
        int sloupec = policko.getSloupec();
        if((sloupec + 1 == sloupec1 && radka + 1 == radka1) || (sloupec + 1 == sloupec1 && radka - 1 == radka1)
        || (sloupec - 1 == sloupec1 && radka - 1 == radka1) || sloupec - 1 == sloupec1 && radka + 1 == radka1){
            policko1.setColor(DrawingPanel.VALIDNI_TAH);
            return true;
        }
        return false;
    }

    /**
     * Validace tahu mimochodem
     * @return true pokud je to možne
     */
    private Policko zkontrolujMimochodem() {
        if(color == Color.BLACK){
            for (int i = 0; i < DrawingPanel.figurkyBile.size(); i++) {
                if(DrawingPanel.figurkyBile.get(i) instanceof Pesak){
                    if(((Pesak) DrawingPanel.figurkyBile.get(i)).isMimochodem()){
                        int radka = DrawingPanel.figurkyBile.get(i).getPolicko().getRadka();
                        int sloupec =  DrawingPanel.figurkyBile.get(i).getPolicko().getSloupec();
                       //DrawingPanel.policka[radka-1-1][sloupec-1].setColor(DrawingPanel.VALIDNI_TAH); // - 1
                        return DrawingPanel.policka[radka-1-1][sloupec-1];
                    }
                }
            }
        } else {
            for (int i = 0; i < DrawingPanel.figurkyCerne.size(); i++) {
                if(DrawingPanel.figurkyCerne.get(i) instanceof Pesak){
                    if(((Pesak) DrawingPanel.figurkyCerne.get(i)).isMimochodem()){
                        int radka = DrawingPanel.figurkyCerne.get(i).getPolicko().getRadka();
                        int sloupec =  DrawingPanel.figurkyCerne.get(i).getPolicko().getSloupec();
                        //DrawingPanel.policka[radka-1+1][sloupec-1].setColor(DrawingPanel.VALIDNI_TAH);
                        return DrawingPanel.policka[radka-1+1][sloupec-1];
                    }
                }
            }
        }
        return null;
    }

    /**
     * Metoda pro pěšáka, která vyhodnotí možnost vzít figurku soupeře dle pravidel
     */
    public void zabirani_diagonalou() {
        int x = policko.getRadka();
        int y = policko.getSloupec();
        if (color == Color.WHITE) {
            if (x - 1 + 1 < 8 && y - 1 + 1 < 8) {
                if (DrawingPanel.policka[x - 1 + 1][y - 1 + 1].getFigurka() != null &&
                        DrawingPanel.policka[x - 1 + 1][y - 1 + 1].getFigurka().getColor() != color) {
                    DrawingPanel.policka[x - 1 + 1][y - 1 + 1].setColor(DrawingPanel.VALIDNI_TAH);
                }
            }
            if (x - 1 + 1 < 8 && y - 1 - 1 >= 0) {
                if (DrawingPanel.policka[x - 1 + 1][y - 1 - 1].getFigurka() != null &&
                        DrawingPanel.policka[x - 1 + 1][y - 1 - 1].getFigurka().getColor() != color) {
                    DrawingPanel.policka[x - 1 + 1][y - 1 - 1].setColor(DrawingPanel.VALIDNI_TAH);
                }
            }
        } else {
            if (x - 1 - 1 >= 0 && y - 1 + 1 < 8) {
                if (DrawingPanel.policka[x - 1 - 1][y - 1 + 1].getFigurka() != null &&
                        DrawingPanel.policka[x - 1 - 1][y - 1 + 1].getFigurka().getColor() != color) {
                    DrawingPanel.policka[x - 1 - 1][y - 1 + 1].setColor(DrawingPanel.VALIDNI_TAH);
                }
            }
            if (x - 1 - 1 >= 0 && y - 1 - 1 >= 0) {
                if (DrawingPanel.policka[x - 1 - 1][y - 1 - 1].getFigurka() != null &&
                        DrawingPanel.policka[x - 1 - 1][y - 1 - 1].getFigurka().getColor() != color) {
                    DrawingPanel.policka[x - 1 - 1][y - 1 - 1].setColor(DrawingPanel.VALIDNI_TAH);
                }
            }
        }
    }

    /**
     * Metoda pro určení, jaké pozice jsou figurkou ohroženy
     * @param bool true pokud je pozice ohrožena
     */
    @Override
    public void zobrazOhrozene(boolean bool){
        Policko policko1 = zkontrolujMimochodem();
        if(policko1 != null){
            lzeZahraMimochodem(policko1);
        }
         x = policko.getRadka();
         y = policko.getSloupec();
        if (color == Color.WHITE) {
            if (x - 1 + 1 < 8 && y - 1 + 1 < 8) {
                    DrawingPanel.policka[x - 1 + 1][y - 1 + 1].setOhrozena(bool);
            }
            if (x - 1 + 1 < 8 && y - 1 - 1 >= 0) {
                    DrawingPanel.policka[x - 1 + 1][y - 1 - 1].setOhrozena(bool);
            }
        } else {
            if (x - 1 - 1 >= 0 && y - 1 + 1 < 8) {
                    DrawingPanel.policka[x - 1 - 1][y - 1 + 1].setOhrozena(bool);
            }
            if (x - 1 - 1 >= 0 && y - 1 - 1 >= 0) {
                    DrawingPanel.policka[x - 1 - 1][y - 1 - 1].setOhrozena(bool);
            }
        }
    }
}





