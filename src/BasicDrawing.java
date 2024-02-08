import org.jfree.svg.SVGGraphics2D;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Třída vytvářející okno, pracující se vstupem z myši
 */
public class BasicDrawing {
public static boolean drzimObjekt = false; // indikátor, zda jsme v myši drželi objekt
	public static void main(String[] args) {
		JFrame okno = new JFrame();
		okno.setTitle("Chess_SP_2023 - A21B0316P");
		okno.setSize(8000, 600);
		DrawingPanel panel = new DrawingPanel();

		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Menu");
		JMenuItem menuItem1 = new JMenuItem("Rastrový export ");
		menu.add(menuItem1);
		JMenuItem menuItem2 = new JMenuItem("Vektorový export");
		menu.add(menuItem2);
		JMenuItem menuItem3 = new JMenuItem("Graf");
		menu.add(menuItem3);

		menuBar.add(menu);
		okno.setJMenuBar(menuBar);

		okno.add(panel);
		okno.pack();
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setLocationRelativeTo(null);
		okno.setVisible(true);

		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				BufferedImage bufferedImage = new BufferedImage(okno.getWidth(), okno.getHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics graphics = bufferedImage.getGraphics();
				panel.paint(graphics);
				try {
					ImageIO.write(bufferedImage, "png", new File("vystup.png"));
					JOptionPane.showMessageDialog(okno, "Rastrový export byl proveden.");
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(okno, "Došlo k chybě rastrového exportu");
					throw new RuntimeException(ex);
				}

			}
		});

		menuItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				SVGGraphics2D svgGraphics2D = new SVGGraphics2D(okno.getWidth(), okno.getHeight());
				panel.paint(svgGraphics2D);
					try {
						FileWriter fileWriter = new FileWriter("vystup.svg");
						fileWriter.write(svgGraphics2D.getSVGElement());
						fileWriter.close();
						JOptionPane.showMessageDialog(okno, "Vektorový export byl proveden.");
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(okno, "Došlo k chybě vektorového exportu");
						throw new RuntimeException(ex);
					}


			}
		});

		menuItem3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graf.zobraz();
			}
		});

		panel.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
					if(drzimObjekt == false){
						if (panel.isObjectHit(e.getX(), e.getY())) {
							panel.posun(e.getX(), e.getY());
							drzimObjekt = true;
						}
					}
					if(drzimObjekt == true){
						panel.posun(e.getX(), e.getY());
					}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}
		});

		panel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(drzimObjekt){
				panel.vycentruj();
				}
				drzimObjekt = false;


			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				//panel.zmenBarvuZpet();
			}
		});



	}



}
