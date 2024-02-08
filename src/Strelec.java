import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
/**
 * Třída reprezentující Střelce
 */
public class Strelec extends Figurka {
    boolean bool = false;
    private int x;
    private int y;
    private double width;
    private double height;
    private Color color;
    private Policko policko;
    private boolean pohyb;
    private boolean naTahu;

    public Strelec(int x, int y, double width, double height, Color color, Policko policko, boolean pohyb, boolean naTahu) {

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
     * Vykreslení střelce
     */
    public void paint(Graphics2D g2) {
        double width, height, x, y;
        if (getPolicko() == null) {
            return;
        }
        width = getPolicko().getWidth();
        height = getPolicko().getHeight();
        if (bool && !pohyb) {
            x = getPolicko().getX() + width / 2;
            y = getPolicko().getY() + height / 2;
            this.x = (int) (getPolicko().getX() + width / 2);
            this.y = (int) (getPolicko().getY() + height / 2);
            this.width = getPolicko().getWidth();
            ;
            this.height = getPolicko().getHeight();
        } else {
            x = this.x;
            y = this.y;
        }

        Ellipse2D.Double kulicka = new Ellipse2D.Double(
                x - width / 12, y - height / 12 - height / 12 * 5 + height / 10, width / 6, height / 6
        );
        Ellipse2D.Double stred = new Ellipse2D.Double(
                x - width / 4, y - height / 4 - height / 10 + height / 10, width / 2, height / 2
        );

        Ellipse2D.Double elipsa1 = new Ellipse2D.Double(
                x - width / 8 - width / 10, y - height / 6 + height / 10 + height / 10, width / 4, height / 3
        );
        Ellipse2D.Double elipsa2 = new Ellipse2D.Double(
                x - width / 8 + width / 10, y - height / 6 + height / 10 + height / 10, width / 4, height / 3
        );

        Arc2D.Double podlozka = new Arc2D.Double(
                x - width / 5 * 2, y + height / 5 + height / 10, width * 2 / 5, height / 8, 0, 180, 1
        );
        Arc2D.Double podlozka2 = new Arc2D.Double(
                x, y + height / 5 + height / 10, width * 2 / 5, height / 8, 0, 180, 1
        );


        double ellipseWidth = elipsa1.getWidth();
        double angleInDegrees = -55.0;
        double angleInRadians = Math.toRadians(angleInDegrees);
        double centerX = elipsa1.getCenterX();
        double centerY = elipsa1.getCenterY();
        AffineTransform rotation = AffineTransform.getRotateInstance(angleInRadians, centerX, centerY);
        AffineTransform combined = new AffineTransform();
        combined.concatenate(rotation);
        Shape rotatedHead1 = combined.createTransformedShape(elipsa1);


        ellipseWidth = elipsa2.getWidth();
        angleInDegrees = 55.0;
        angleInRadians = Math.toRadians(angleInDegrees);
        centerX = elipsa2.getCenterX();
        centerY = elipsa2.getCenterY();
        rotation = AffineTransform.getRotateInstance(angleInRadians, centerX, centerY);
        combined = new AffineTransform();
        combined.concatenate(rotation);
        Shape rotatedHead2 = combined.createTransformedShape(elipsa2);

        Path2D allShapes = new Path2D.Double();
        allShapes.append(kulicka, false);
        allShapes.append(stred, false);
        allShapes.append(rotatedHead1, false);
        allShapes.append(rotatedHead2, false);
        allShapes.append(podlozka, false);
        allShapes.append(podlozka2, false);

        if (color != Color.BLACK) {
            g2.setColor(Color.BLACK);
            g2.draw(allShapes);
        } else {
            g2.setColor(Color.white);
            g2.draw(allShapes);
        }
        g2.setColor(color);
        g2.fill(allShapes);

        Path2D kriz = new Path2D.Double();
        kriz.moveTo(stred.getCenterX() - width / 10, stred.getCenterY() - height / 15);
        kriz.lineTo(stred.getCenterX() + width / 10, stred.getCenterY() - height / 15);
        kriz.moveTo(stred.getCenterX(), stred.getCenterY() - height / 10 - height / 15);
        kriz.lineTo(stred.getCenterX(), stred.getCenterY() + height / 10 - height / 15);

        if (color == Color.BLACK) {
            g2.setColor(Color.WHITE);
        } else {
            g2.setColor(Color.BLACK);
        }
        Stroke newStroke = new BasicStroke(2.0f);
        g2.setStroke(newStroke);
        g2.draw(kriz);
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
        int i = x - 1;
        int j = y - 1;
        while (j < 8 || i < 8) {
            if (i >= 8 || j >= 8) {
                break;
            }
            if (i == x - 1) {
                i++;
                j++;
                continue;
            }
            if (j == y - 1) {
                i++;
                j++;
                continue;
            }
            if (obsazenoNepritelem(i, j, color)) {
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                i++;
                j++;
                break;
            }
            if (neobsahujeFigurku(i, j, color)) {
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
        while (i >= 0 || j >= 0) {
            if (i <= -1 || j <= -1) {
                break;
            }
            if (i == x - 1) {
                i--;
                j--;
                continue;
            }
            if (j == y - 1) {
                i--;
                j--;
                continue;
            }
            if (obsazenoNepritelem(i, j, color)) {
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                break;
            }
            if (neobsahujeFigurku(i, j, color)) {
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                i--;
                j--;
            } else {
                break;
            }
        }
        i = x - 1;
        j = y - 1;
        while (i < 8 || j >= 0) {
            if (i >= 8 || j <= -1) {
                break;
            }
            if (i == x - 1) {
                i++;
                j--;
                continue;
            }
            if (j == y - 1) {
                i++;
                j--;
                continue;
            }
            if (obsazenoNepritelem(i, j, color)) {
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                break;
            }
            if (neobsahujeFigurku(i, j, color)) {
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                i++;
                j--;
            } else {
                break;
            }
        }
        i = x - 1;
        j = y - 1;
        while (i >= 0 || j < 8) {
            if (i <= -1 || j >= 8) {
                break;
            }
            if (i == x - 1) {
                i--;
                j++;
                continue;
            }
            if (j == y - 1) {
                i--;
                j++;
                continue;
            }
            if (obsazenoNepritelem(i, j, color)) {
                DrawingPanel.policka[i][j].setColor(DrawingPanel.VALIDNI_TAH);
                break;
            }
            if (neobsahujeFigurku(i, j, color)) {
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
        int i = x - 1;
        int j = y - 1;
        while (j < 8 || i < 8) {
            if (i >= 8 || j >= 8) {
                break;
            }
            if (i == x - 1) {
                i++;
                j++;
                continue;
            }
            if (j == y - 1) {
                i++;
                j++;
                continue;
            }
            if (obsazenoNepritelem(i, j, color)) {
                DrawingPanel.policka[i][j].setOhrozena(bool);
                i++;
                j++;
                break;
            }
            if (neobsahujeFigurku(i, j, color)) {
                DrawingPanel.policka[i][j].setOhrozena(bool);
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
        while (i >= 0 || j >= 0) {
            if (i <= -1 || j <= -1) {
                break;
            }
            if (i == x - 1) {
                i--;
                j--;
                continue;
            }
            if (j == y - 1) {
                i--;
                j--;
                continue;
            }
            if (obsazenoNepritelem(i, j, color)) {
                DrawingPanel.policka[i][j].setOhrozena(bool);
                break;
            }
            if (neobsahujeFigurku(i, j, color)) {
                DrawingPanel.policka[i][j].setOhrozena(bool);
                i--;
                j--;
            } else {
                break;
            }
        }
        i = x - 1;
        j = y - 1;
        while (i < 8 || j >= 0) {
            if (i >= 8 || j <= -1) {
                break;
            }
            if (i == x - 1) {
                i++;
                j--;
                continue;
            }
            if (j == y - 1) {
                i++;
                j--;
                continue;
            }
            if (obsazenoNepritelem(i, j, color)) {
                DrawingPanel.policka[i][j].setOhrozena(bool);
                break;
            }
            if (neobsahujeFigurku(i, j, color)) {
                DrawingPanel.policka[i][j].setOhrozena(bool);
                i++;
                j--;
            } else {
                break;
            }
        }
        i = x - 1;
        j = y - 1;
        while (i >= 0 || j < 8) {
            if (i <= -1 || j >= 8) {
                break;
            }
            if (i == x - 1) {
                i--;
                j++;
                continue;
            }
            if (j == y - 1) {
                i--;
                j++;
                continue;
            }
            if (obsazenoNepritelem(i, j, color)) {
                DrawingPanel.policka[i][j].setOhrozena(bool);
                break;
            }
            if (neobsahujeFigurku(i, j, color)) {
                DrawingPanel.policka[i][j].setOhrozena(bool);
                i--;
                j++;
            } else {
                break;
            }
        }
    }
}