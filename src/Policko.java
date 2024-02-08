import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Třída reprezentující políčko šachovnice
 */
public class Policko extends JPanel {

    private int x;
    private int y;
    private double width;
    private double height;
    private Color color;
    private String name;
    private Color lastColor;
    private Figurka figurka;
    private int radka;
    private int sloupec;
    private boolean ohrozena;
    public Policko(String name, int x, int y, double width, double height, Color lastColor, Figurka figurka, int radka,
                   int sloupec) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.lastColor = lastColor;
        this.name = name;
        this.figurka = figurka;
        this.color = lastColor;
        this.radka = radka;
        this.sloupec = sloupec;
    }

    /**
     * Vykreslení políčka
     */
    public void paint(Graphics2D g2){
        g2.setColor(color);
        g2.fill(new Rectangle2D.Double(x,y,width,height));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return (int) width;
    }

    public int getHeight() {
        return (int) height;
    }

    public Color getColor() {
        return color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString(){
        return "Já jsem políčko na pozici X_Y: " + name;
    }

    public Color getLastColor() {
        return lastColor;
    }

    public void setLastColor(Color lastColor) {
        this.lastColor = lastColor;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Figurka getFigurka() {
        return figurka;
    }

    public void setFigurka(Figurka figurka) {
        this.figurka = figurka;
    }

    public int getRadka() {
        return radka;
    }

    public void setRadka(int radka) {
        this.radka = radka;
    }

    public int getSloupec() {
        return sloupec;
    }

    public void setSloupec(int sloupec) {
        this.sloupec = sloupec;
    }

    public boolean isOhrozena() {
        return ohrozena;
    }

    public void setOhrozena(boolean ohrozena) {
        this.ohrozena = ohrozena;
    }

    /**
     * Metoda na zjištění, zda je dané pole obsazené figurkou stejné barvy
     * @param barva2 spojenecká barva
     * @return true pokud je pole obsazene
     */
    public boolean obsahujeFigurku(Color barva2){
        if(getFigurka() != null){
            if(getFigurka().getColor() == barva2){
                return true;
            }
        }
        return false;
    }

}


