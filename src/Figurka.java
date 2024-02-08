import javax.swing.*;
import java.awt.*;

/**
 * Třída reprezentující obecnou figurku, rodič konkrétních figurek
 */
public abstract class Figurka extends JPanel {
    private int x;
    private int y;
    private double width;
    private double height;

    private Color color;
    /**
     * Reference na políčko, na kterém se figurka zrovna nachází
     */
    private Policko policko;
    /**
     * Indikátor, zda provádíme tah figurkou
     */
    private boolean pohyb;
    private boolean naTahu;
    public Figurka(int x, int y, double width, double height, Color color, Policko policko, boolean pohyb, boolean naTahu) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.policko = policko;
        this.pohyb = pohyb;
        this.naTahu = naTahu;
    }
    public Figurka() {
    }

    @Override
    public void paint(Graphics g) {
    }
    public void setPolicko(Policko policko) {
        this.policko = policko;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    public void setColor(Color color) {
        this.color = color;
    }

    public void paint(Graphics2D g2) {
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
        return policko;
    }
    public boolean isPohyb() {
        return pohyb;
    }
    public void setPohyb(boolean pohyb) {
        this.pohyb = pohyb;
    }
    public boolean isNaTahu() {
        return naTahu;
    }
    public void setNaTahu(boolean naTahu) {
        this.naTahu = naTahu;
    }
    public void zobrazValidni(){}

    /**
     * Skyrje validní pole
     */
    public void skryjValidni(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                DrawingPanel.policka[j][i].setColor(DrawingPanel.policka[j][i].getLastColor());
            }
        }
    }

    public void zobrazOhrozene(boolean bool){}

    /**
     * Metoda, která rozhodne, jestli byl byl tah na dané políčko validní
     * @param x řádka x
     * @param y sloupec y
     * @param color barva, kterou má tažená figurka, null když nezáleží na barvě blokujících figurek
     * @return true pokud je tah validní
     */
    public boolean neobsahujeFigurku(int x, int y, Color color){
        if(DrawingPanel.policka[x][y].getFigurka() == null){
            return true;
        } else if(color != null) { // případ že jde o pěšáka, který bere jedině diagonálně
            if (DrawingPanel.policka[x][y].getFigurka().getColor() != color) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda na zjištění, jestlí je dané políčko obsazeno nepřítelem
     * @param x zkoumané pole x
     * @param y zkoumané pole y
     * @param color barva spojenců
     * @return true pokud je obsazeno nepřítelem
     */
    public boolean obsazenoNepritelem(int x, int y, Color color){
        if(DrawingPanel.policka[x][y].getFigurka() == null){
            return false;
        }
        else if (DrawingPanel.policka[x][y].getFigurka().getColor() != color) {
            return true;
        }
        return false;
    }

}

