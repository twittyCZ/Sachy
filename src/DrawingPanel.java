import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * Třída starající se o vykreslení všech procesů
 */
public class DrawingPanel extends JPanel {
	private final int width = 800; // počáteční šířka
	private final int height = 600; // počáteční výška
	private static Graphics2D g2 = null;
	public static final Color VALIDNI_TAH = Color.GREEN;
	public static final Color AKTUALNI_POZICE = Color.ORANGE;
	double vyskaSachovnice;
	double sirkaSachovnice;
	double poziceXSachovnice;
	double poziceYSachovnice;
	 boolean dalsiSpusteni = false; // ohraničuje akce, které se provedou jen při prvním volání metody paint
	public static Policko [][] policka = new Policko [8][8]; // pole políček
	public static ArrayList<Figurka> figurky = new ArrayList<>(); // pole figurek
	public static ArrayList<Figurka> figurkyBile = new ArrayList<>();
	public static ArrayList<Figurka> figurkyCerne = new ArrayList<>();
	public static Figurka figurka; // figurka, kterou je právě taženo
	Color barva = new Color(192, 186, 186);
	Rectangle2D.Double sachovnice;
	Policko policko; // vybrané políčko k dalšímu použití
	public static Kral kral2;
	public static Kral kral;
	public static ArrayList<Long> casyTahu = new ArrayList<Long>();
	public static long start = System.currentTimeMillis();

	public DrawingPanel() {
		this.setPreferredSize(new Dimension(width, height));
	}


	@Override
	public void paint(Graphics g) {
		g2 = (Graphics2D) g;

		/*
		  Přepočítání velikosti políček při dle aktuální velikosti okna.
		  Dle velikosti políček se mění i velikost figurek
		 */
		double y = this.getHeight();
		double x = this.getWidth();
		double side = Math.min(x, y);
		sachovnice = new Rectangle2D.Double((x - side) / 2, (y - side) / 2, side, side);
		g2.setColor(barva);
		g2.fill(sachovnice);
		vyskaSachovnice = (sachovnice.getHeight());
		sirkaSachovnice = (sachovnice.getWidth());
		poziceXSachovnice = (sachovnice.getX());
		poziceYSachovnice = (sachovnice.getY());

		/*
		  Vytváření instance tříd figurek a policka jen při prvním volání metody paint
		  V dalších voláních se tak nebudou vytvářet nové instance
		  Instance figurek jsou poprvé vykresleny a přidány do ArrayListu
		 */

	if (!dalsiSpusteni) {
		for (int j = 1; j < 9; j++) {
			for (int i = 1; i < 9; i++) {
				 policko = new Policko( j + " "  + i, (int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * i), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * j),
						sirkaSachovnice / 8, vyskaSachovnice / 8, Color.BLACK, null, j, i);
				policka[j - 1][i - 1] = policko;
			}
		}
			Pesak pesak = new Pesak(policka[6][7].getX(), policka[6][7].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.BLACK, policka[6][7],false, false);
			policka[6][7].setFigurka(pesak);
			Pesak pesak1 = new Pesak(policka[6][6].getX(), policka[6][6].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.BLACK, policka[6][6],false, false);
			Pesak pesak2 = new Pesak(policka[6][5].getX(), policka[6][5].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.BLACK, policka[6][5],false, false);
			Pesak pesak3 = new Pesak(policka[6][4].getX(), policka[6][4].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.BLACK, policka[6][4],false, false);
			Pesak pesak4 = new Pesak(policka[6][3].getX(), policka[6][3].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.BLACK, policka[6][3],false, false);
			Pesak pesak5 = new Pesak(policka[6][2].getX(), policka[6][2].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.BLACK, policka[6][2],false, false);
			Pesak pesak6 = new Pesak(policka[6][1].getX(), policka[6][1].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.BLACK, policka[6][1],false, false);
			Pesak pesak7 = new Pesak(policka[6][0].getX(), policka[6][0].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.BLACK, policka[6][0],false, false);
			policka[6][6].setFigurka(pesak1);
			policka[6][5].setFigurka(pesak2);
			policka[6][4].setFigurka(pesak3);
			policka[6][3].setFigurka(pesak4);
			policka[6][2].setFigurka(pesak5);
			policka[6][1].setFigurka(pesak6);
			policka[6][0].setFigurka(pesak7);
			pesak.paint(g2);
			pesak1.paint(g2);
			pesak2.paint(g2);
			pesak3.paint(g2);
			pesak4.paint(g2);
			pesak5.paint(g2);
			pesak6.paint(g2);
			pesak7.paint(g2);

			Vez vez = new Vez((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 7.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 7.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.BLACK, policka[7][7],false, false);
			Vez vez1 = new Vez((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 0.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 7.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.BLACK, policka[7][0],false, false);
			vez.paint(g2);
			vez1.paint(g2);
			policka[7][7].setFigurka(vez);
			policka[7][0].setFigurka(vez1);

			Kun kun = new Kun((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 6.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 7.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.BLACK, policka[7][6],false, false);
			Kun kun1 = new Kun((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 1.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 7.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.BLACK, policka[7][1],false, false);
			kun.paint(g2);
			kun1.paint(g2);
			policka[7][6].setFigurka(kun);
			policka[7][1].setFigurka(kun);

			Strelec strelec = new Strelec((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 5.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 7.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.BLACK, policka[7][5],false, false);
			Strelec strelec2 = new Strelec((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 2.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 7.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.BLACK, policka[7][2],false, false);
			strelec.paint(g2);
			strelec2.paint(g2);
			policka[7][5].setFigurka(strelec);
			policka[7][2].setFigurka(strelec);

			Kralovna kralovna = new Kralovna((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 4.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 7.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.BLACK, policka[7][4], false, false);
			kralovna.paint(g2);
			policka[7][4].setFigurka(kralovna);

			kral = new Kral((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 3.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 7.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.BLACK, policka[7][3], false, false);
			kral.paint(g2);
			policka[7][3].setFigurka(kral);

			Pesak pesak02 = new Pesak(policka[1][7].getX(), policka[1][7].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.WHITE, policka[1][7],false, true);
			Pesak pesak12 = new Pesak(policka[1][6].getX(), policka[1][6].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.WHITE, policka[1][6],false, true);
			Pesak pesak22 = new Pesak(policka[1][5].getX(), policka[1][5].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.WHITE, policka[1][5],false, true);
			Pesak pesak32 = new Pesak(policka[1][4].getX(), policka[1][4].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.WHITE, policka[1][4],false, true);
			Pesak pesak42 = new Pesak(policka[1][3].getX(), policka[1][3].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.WHITE, policka[1][3],false, true);
			Pesak pesak52 = new Pesak(policka[1][2].getX(), policka[1][2].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.white, policka[1][2],false, true);
			Pesak pesak62 = new Pesak(policka[1][1].getX(), policka[1][1].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.WHITE, policka[1][1], false, true);
			Pesak pesak72 = new Pesak(policka[1][0].getX(), policka[1][0].getY(), policka[6][7].getWidth(), policka[6][7].getHeight(), Color.WHITE, policka[1][0], false, true);
			policka[1][7].setFigurka(pesak02);
			policka[1][6].setFigurka(pesak12);
			policka[1][5].setFigurka(pesak22);
			policka[1][4].setFigurka(pesak32);
			policka[1][3].setFigurka(pesak42);
			policka[1][2].setFigurka(pesak52);
			policka[1][1].setFigurka(pesak62);
			policka[1][0].setFigurka(pesak72);
			pesak02.paint(g2);
			pesak12.paint(g2);
			pesak22.paint(g2);
			pesak32.paint(g2);
			pesak42.paint(g2);
			pesak52.paint(g2);
			pesak62.paint(g2);
			pesak72.paint(g2);


			Vez vez02 = new Vez((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 7.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 0.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.WHITE, policka[0][7],false, true);
			Vez vez12 = new Vez((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 0.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 0.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.WHITE, policka[0][0],false, true);
			vez02.paint(g2);
			vez12.paint(g2);
			policka[0][7].setFigurka(vez02);
			policka[0][0].setFigurka(vez12);

			Kun kun02 = new Kun((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 6.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 0.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.WHITE, policka[0][6],false, true);
			Kun kun12 = new Kun((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 1.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 0.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.WHITE, policka[0][1],false, true);
			kun02.paint(g2);
			kun12.paint(g2);
			policka[0][6].setFigurka(kun02);
			policka[0][1].setFigurka(kun12);


			Strelec strelec02 = new Strelec((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 5.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 0.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.WHITE, policka[0][5],false, true);
			Strelec strelec22 = new Strelec((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 2.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 0.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.WHITE, policka[0][2],false, true);
			strelec02.paint(g2);
			strelec22.paint(g2);
			policka[0][5].setFigurka(strelec02);
			policka[0][2].setFigurka(strelec22);


			Kralovna kralovna2 = new Kralovna((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 4.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 0.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.WHITE, policka[0][4], false, true);
			kralovna2.paint(g2);
			policka[0][4].setFigurka(kralovna2);

			 kral2 = new Kral((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * 3.5), (int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * 0.5), sirkaSachovnice / 8, vyskaSachovnice / 8, Color.WHITE, policka[0][3], false, true);
			 kral2.paint(g2);
			policka[0][3].setFigurka(kral2);

			figurkyCerne.add(pesak);
			figurkyCerne.add(pesak1);
			figurkyCerne.add(pesak2);
			figurkyCerne.add(pesak3);
			figurkyCerne.add(pesak4);
			figurkyCerne.add(pesak5);
			figurkyCerne.add(pesak6);
			figurkyCerne.add(pesak7);
			figurkyCerne.add(kral);
			figurkyCerne.add(kralovna);
			figurkyCerne.add(strelec);
			figurkyCerne.add(strelec2);
			figurkyCerne.add(vez);
			figurkyCerne.add(vez1);
			figurkyCerne.add(kun);
			figurkyCerne.add(kun1);



			figurkyBile.add(pesak02);
			figurkyBile.add(pesak12);
			figurkyBile.add(pesak32);
			figurkyBile.add(pesak22);
			figurkyBile.add(pesak42);
			figurkyBile.add(pesak52);
			figurkyBile.add(pesak62);
			figurkyBile.add(pesak72);
			figurkyBile.add(kral2);
			figurkyBile.add(kralovna2);
			figurkyBile.add(vez02);
			figurkyBile.add(vez12);
			figurkyBile.add(strelec02);
			figurkyBile.add(strelec22);
			figurkyBile.add(kun02);
			figurkyBile.add(kun12);


			figurky.add(kral);
			figurky.add(kral2);
			figurky.add(kralovna2);
			figurky.add(kralovna);
			figurky.add(strelec02);
			figurky.add(strelec22);
			figurky.add(strelec2);
			figurky.add(strelec);
			figurky.add(kun);
			figurky.add(kun1);
			figurky.add(kun12);
			figurky.add(kun02);
			figurky.add(vez1);
			figurky.add(vez);
			figurky.add(vez02);
			figurky.add(vez12);
			figurky.add(pesak1);
			figurky.add(pesak2);
			figurky.add(pesak3);
			figurky.add(pesak4);
			figurky.add(pesak5);
			figurky.add(pesak6);
			figurky.add(pesak7);
			figurky.add(pesak);
			figurky.add(pesak12);
			figurky.add(pesak02);
			figurky.add(pesak22);
			figurky.add(pesak32);
			figurky.add(pesak42);
			figurky.add(pesak52);
			figurky.add(pesak62);
			figurky.add(pesak72);
			dalsiSpusteni = true;

			for (int i = 0; i < policka.length; i++) {
				for (int j = 0; j < policka.length; j++) {
					if (i % 2 == 0 && j % 2 == 0) {
						g2.setPaint(barva);
						policka[i][j].setColor(barva);
						policka[i][j].setLastColor(barva);
						policka[i][j].paint(g2);
					} else if (i % 2 == 0) {
						policka[i][j].setColor(Color.gray);
						policka[i][j].setLastColor(Color.gray);
						policka[i][j].paint(g2);
					} else if (j % 2 == 1) {
						policka[i][j].setColor(barva);
						policka[i][j].setLastColor(barva);
						policka[i][j].paint(g2);
					} else {
						policka[i][j].setColor(Color.gray);
						policka[i][j].setLastColor(Color.gray);
						policka[i][j].paint(g2);
					}
				}
			}


		}
		/*
		  Nové vykreslení všech komponent, které proběhne při každém dalším volání metody paint
		 */
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRect(0 ,0 , this.getWidth(), this.getHeight());
			for (int i = 0; i < policka.length; i++) {
				for (int j = 0; j < policka.length; j++) {
					policka[j][i].setX((int) (poziceXSachovnice + sirkaSachovnice - sirkaSachovnice / 8 * i - sirkaSachovnice / 8));
					policka[j][i].setY((int) (poziceYSachovnice + vyskaSachovnice - vyskaSachovnice / 8 * j - vyskaSachovnice / 8));
					policka[j][i].setWidth(sirkaSachovnice / 8);
					policka[j][i].setHeight(vyskaSachovnice / 8);
				}
			}
		for (int i = 0; i < policka.length; i++) {
			for (int j = 0; j < policka.length; j++) {
				if (i % 2 == 0 && j % 2 == 0) {
					g2.setPaint(policka[i][j].getColor());
					policka[i][j].setColor(policka[i][j].getColor());
					policka[i][j].paint(g2);
				} else if (i % 2 == 0) {
					policka[i][j].setColor(policka[i][j].getColor());
					policka[i][j].paint(g2);
				} else if (j % 2 == 1) {
					policka[i][j].setColor(policka[i][j].getColor());
					policka[i][j].paint(g2);
				} else {
					policka[i][j].setColor(policka[i][j].getColor());
					policka[i][j].paint(g2);
				}
			}
		}
			for (int i = 0; i < figurky.size(); i++) {
				figurky.get(i).paint(g2);
			}
		}

	/**
	 * Kompletní proměna pěšaka, pokud dojde na konec šachovnice
	 * Zobrazí se dialogové okno a hráč si může vybrat, ve kterou figurku se má jeho pěšák proměnit
	 */
		public void promena(){
		if(figurka instanceof Pesak){
			if(figurkyCerne.contains(figurka)){
				if(figurka.getPolicko().getRadka() == 1){
					int result = JOptionPane.showOptionDialog(null, "V jakou figurku chcete proměnit pěšáka?",
							"Promena", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Kun", "Strelec", "Vez", "Dama" }, "Dama");
					figurky.remove(figurka);
					figurkyCerne.remove(figurka);
					if(result == 3){ // dáma
						Kralovna kralovna = new Kralovna(figurka.getX(), figurka.getY(),
								figurka.getWidth(), figurka.getHeight(), figurka.getColor(),
								figurka.getPolicko(), figurka.isPohyb(), figurka.isNaTahu());
						figurky.add(kralovna);
						figurkyCerne.add(kralovna);
						kralovna.paint(g2);
					}
					else if(result == 2){ // věž
						Vez vez = new Vez(figurka.getX(), figurka.getY(),
								figurka.getWidth(), figurka.getHeight(), figurka.getColor(),
								figurka.getPolicko(), figurka.isPohyb(), figurka.isNaTahu());
						figurky.add(vez);
						figurkyCerne.add(vez);
						vez.paint(g2);
					}
					else if(result == 1) { // střelec
						Strelec strelec = new Strelec(figurka.getX(), figurka.getY(),
								figurka.getWidth(), figurka.getHeight(), figurka.getColor(),
								figurka.getPolicko(), figurka.isPohyb(), figurka.isNaTahu());
						figurky.add(strelec);
						figurkyCerne.add(strelec);
						strelec.paint(g2);
					} else { // kun
						Kun kun = new Kun(figurka.getX(), figurka.getY(),
								figurka.getWidth(), figurka.getHeight(), figurka.getColor(),
								figurka.getPolicko(), figurka.isPohyb(), figurka.isNaTahu());
						figurky.add(kun);
						figurkyCerne.add(kun);
						kun.paint(g2);
					}
				}
			} else {
				if(figurka.getPolicko().getRadka() == 8){
					int result = JOptionPane.showOptionDialog(null, "V jakou figurku chcete proměnit pěšáka?",
							"Promena", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Kun", "Strelec", "Vez", "Dama" }, "Dama");
					figurky.remove(figurka);
					figurkyBile.remove(figurka);
					if(result == 3){
						Kralovna kralovna = new Kralovna(figurka.getX(), figurka.getY(),
								figurka.getWidth(), figurka.getHeight(), figurka.getColor(),
								figurka.getPolicko(), figurka.isPohyb(), figurka.isNaTahu());
						figurky.add(kralovna);
						figurkyBile.add(kralovna);
						kralovna.paint(g2);
					}
					else if(result == 2){
						Vez vez = new Vez(figurka.getX(), figurka.getY(),
								figurka.getWidth(), figurka.getHeight(), figurka.getColor(),
								figurka.getPolicko(), figurka.isPohyb(), figurka.isNaTahu());
						figurky.add(vez);
						figurkyBile.add(vez);
						vez.paint(g2);
					}
					else if(result==1){
						Strelec strelec = new Strelec(figurka.getX(), figurka.getY(),
								figurka.getWidth(), figurka.getHeight(), figurka.getColor(),
								figurka.getPolicko(), figurka.isPohyb(), figurka.isNaTahu());
						figurky.add(strelec);
						figurkyBile.add(strelec);
						strelec.paint(g2);
					}else {
						Kun kun = new Kun(figurka.getX(), figurka.getY(),
								figurka.getWidth(), figurka.getHeight(), figurka.getColor(),
								figurka.getPolicko(), figurka.isPohyb(), figurka.isNaTahu());
						figurky.add(kun);
						figurkyBile.add(kun);
						kun.paint(g2);
					}

				}
			}
		}
		repaint(
		);
	}
	/**
	 * Hlavní metoda
	 * Metoda, která se stará o přiřazení figurky k políčku
	 * Figurka je vycentrována na středu políčka
	 * Zároveň se stará o vše, co musí proběhnout při tahu (validace, nastavení atributů)
	 */
	public void vycentruj() {
		for (int i = 0; i < policka.length; i++) {
			for (int j = 0; j < policka.length; j++) {
					if(leziVPoli(policka[i][j], figurka)){
						if(!revizeSach(policka[i][j])){
							figurka.setPohyb(false);
							figurka.skryjValidni();
							figurka.getPolicko().setColor(Color.BLUE);
							figurka.setX(figurka.getPolicko().getX());
							figurka.setY(figurka.getPolicko().getY());
							repaint();
							return;
						}
						// validace správného pohybu
						if(policka[i][j].getColor() == VALIDNI_TAH && !vlastni_sach(policka[i][j])) { // mimochodem
							Policko puvodni = figurka.getPolicko();
							if (figurka instanceof Pesak) {
								if (((Pesak) figurka).lzeZahraMimochodem(policka[i][j])) {
									if (policka[i][j].getFigurka() == null) {
										for (int k = 0; k < figurky.size(); k++) {
											if (figurky.get(k) instanceof Pesak) {
												if (((Pesak) figurky.get(k)).isMimochodem()) {
													if (figurky.get(k).getColor() == Color.BLACK) {
														figurky.get(k).getPolicko().setFigurka(null);
														figurky.get(k).setPolicko(null);
														figurkyCerne.remove(figurky.get(k));

													} else {
														figurky.get(k).getPolicko().setFigurka(null);
														figurky.get(k).setPolicko(null);
														figurkyBile.remove(figurky.get(k));
													}
													figurky.remove(figurky.get(k));
												}
											}
										}
									}
								}
							}
							int a = figurka.getPolicko().getX() + figurka.getPolicko().getWidth() / 2;
							int b = figurka.getPolicko().getY() + figurka.getPolicko().getHeight() / 2;

							mimochodem(policka[i][j]);

							figurka.skryjValidni();
							figurka.getPolicko().setColor(AKTUALNI_POZICE);
							figurka.getPolicko().setFigurka(null);
							figurka.setPohyb(false);
							figurka.setPolicko(policka[i][j]);
							policka[i][j].setFigurka(figurka);
							figurka.getPolicko().setColor(AKTUALNI_POZICE);
							//testPrekryti(figurka);
							//figurka.setPohyb(false);
							BasicDrawing.drzimObjekt = false;
							figurka.paint(g2);

							Vez vez = rosada();

							//animace

							animace(figurka, a, b, i, j);
							if (vez != null) {
								a = vez.getPolicko().getX() + vez.getPolicko().getWidth() / 2;
								b = vez.getPolicko().getY() + vez.getPolicko().getHeight() / 2;
								if (vez.getColor() == Color.BLACK) {
									if (vez.getPolicko() == policka[7][0]) {
										j = 2;
									} else {
										j = 4;
									}

									animace(vez, a, b, 7, j);
								} else {
									if (vez.getPolicko() == policka[0][0]) {
										j = 2;
									} else {
										j = 4;
									}
									animace(vez, a, b, 0, j);
								}
							}
							// animace
							figurka.setPohyb(false);

							if(figurka instanceof Kral){ // rosada
								((Kral) figurka).setJizOdehral(true);
							}
							if(figurka instanceof Vez){
								((Vez) figurka).setJizOdehral(true);
							}
							odehrano(figurka.getColor());
							figurka.getPolicko().setColor(AKTUALNI_POZICE);
							puvodni.setColor(AKTUALNI_POZICE);

						} else {
							figurka.setPohyb(false);
							figurka.skryjValidni();
							figurka.getPolicko().setColor(Color.BLUE);
							figurka.setX(figurka.getPolicko().getX());
							figurka.setY(figurka.getPolicko().getY());
						}
						repaint();
						//figurka = null;
						break;
				}
			}
		}

	}

	/**
	 * Zkontroluje, jestli nedošlo k odkrytí postavení krále
	 * @param policko políčko kam chceme jít
	 * @return true pokud bychom si dali vlastní sach (toto nebude povoleno)
	 */
	private boolean vlastni_sach(Policko policko) {
		boolean zakazany_tah = false;
		Policko puvodni = figurka.getPolicko();
		Color color = figurka.getColor();
		Figurka odebrana_figurka;
		if(color == Color.BLACK){
			policko.setFigurka(figurka);
			puvodni.setFigurka(null);
			figurka.setPolicko(policko);
			odebrana_figurka = testPrekryti(figurka);
			kral.getPolicko().setOhrozena(false);
			for (int i = 0; i < figurkyBile.size(); i++) {
				figurkyBile.get(i).zobrazOhrozene(true);
			}
			if(kral.getPolicko().isOhrozena()){
				zakazany_tah = true;
			}
			policko.setFigurka(null);
			puvodni.setFigurka(figurka);
			figurka.setPolicko(puvodni);
			if(odebrana_figurka != null){
				odebrana_figurka.setPolicko(policko);
				policko.setFigurka(odebrana_figurka);
				figurky.add(odebrana_figurka);
				figurkyBile.add(odebrana_figurka);
			}
			for (int i = 0; i < figurkyBile.size(); i++) {
				figurkyBile.get(i).zobrazOhrozene(false);
			}
		} else {
			policko.setFigurka(figurka);
			puvodni.setFigurka(null);
			figurka.setPolicko(policko);
			odebrana_figurka = testPrekryti(figurka);
			kral2.getPolicko().setOhrozena(false);
			for (int i = 0; i < figurkyCerne.size(); i++) {
				figurkyCerne.get(i).zobrazOhrozene(true);
			}
			if(kral2.getPolicko().isOhrozena()){
				zakazany_tah = true;
			}
			policko.setFigurka(null);
			puvodni.setFigurka(figurka);
			figurka.setPolicko(puvodni);
			if(odebrana_figurka != null){
				odebrana_figurka.setPolicko(policko);
				policko.setFigurka(odebrana_figurka);
				figurky.add(odebrana_figurka);
				figurkyCerne.add(odebrana_figurka);
			}
			for (int i = 0; i < figurkyCerne.size(); i++) {
				figurkyCerne.get(i).zobrazOhrozene(false);
			}
		}
		if(zakazany_tah){
			return true;
		}
		return false;
	}

	/**
	 * Zjistí, jestli je hra v patové situaci
	 * @param barva poslední odehraná barva
	 * @return true pokud je hra v patu
	 */
	boolean pat(Color barva){
		boolean sach_lze_vyresit = false;
		boolean sach_nastal = false;
		for (int i = 0; i < figurky.size(); i++) {
			figurky.get(i).zobrazOhrozene(false);
		}
		if(barva == Color.WHITE){
			for (int i = 0; i < figurkyBile.size(); i++) {
				figurkyBile.get(i).zobrazOhrozene(true);
			}
			if(!kral.getPolicko().isOhrozena()) {
				sach_nastal = true;
				for (int i = 0; i < figurkyCerne.size(); i++) {
					figurkyCerne.get(i).zobrazValidni();
					for (int j = 0; j < policka.length; j++) {
						for (int k = 0; k < policka.length; k++) {
							for (int q = 0; q < figurkyBile.size(); q++) {
								figurkyBile.get(q).zobrazOhrozene(true);
								Policko policko2 = figurkyBile.get(q).getPolicko();
								policko2.setFigurka(figurkyBile.get(q));
							}
							if (policka[j][k].getColor() == VALIDNI_TAH) {
								Policko puvodni = figurkyCerne.get(i).getPolicko();
								puvodni.setFigurka(null);
								figurkyCerne.get(i).setPolicko(policka[j][k]);
								policka[j][k].setFigurka(figurkyCerne.get(i));
								Figurka odstranena_figurka = testPrekryti(figurkyCerne.get(i));
								kral.getPolicko().setOhrozena(false);
								for (int q = 0; q < figurkyBile.size(); q++) {
									figurkyBile.get(q).zobrazOhrozene(false);
								}
								for (int q = 0; q < figurkyBile.size(); q++) {
									figurkyBile.get(q).zobrazOhrozene(true);
									Policko policko2 = figurkyBile.get(q).getPolicko();
									policko2.setFigurka(figurkyBile.get(q));
								}
								if(!kral.getPolicko().isOhrozena()){
									sach_lze_vyresit = true;
								}
								if(odstranena_figurka != null){
									odstranena_figurka.setPolicko(policka[j][k]);
									policka[j][k].setFigurka(odstranena_figurka);
									figurkyBile.add(odstranena_figurka);
									figurky.add(odstranena_figurka);
								} else {
									policka[j][k].setFigurka(null);
								}
								puvodni.setFigurka(figurkyCerne.get(i));
								figurkyCerne.get(i).setPolicko(puvodni);
							}
						}
					}
					figurkyCerne.get(i).skryjValidni();
				}
			}
			for (int q = 0; q < figurkyBile.size(); q++) {
				Policko policko2 = figurkyBile.get(q).getPolicko();
				if(policko2 != null) {
					if (policko2.getFigurka() != null) {
						if (policko2.getFigurka().getColor() == Color.BLACK) {
							Figurka figurka2 = policko2.getFigurka();
							figurka2.setPolicko(null);
							policko2.setFigurka(null);
							if(figurkyCerne.contains(figurka2)){
								figurkyCerne.remove(figurka2);
							}
							if(figurkyBile.contains(figurka2)){
								figurkyBile.remove(figurka2);
							}
							if(figurky.contains(figurka2)){
								figurky.remove(figurka2);
							}
						}
					}
					policko2.setFigurka(figurkyBile.get(q));
					figurkyBile.get(q).setPolicko(policko2);
				}
			}
		}else{
			for (int i = 0; i < figurkyCerne.size(); i++) {
				figurkyCerne.get(i).zobrazOhrozene(true);
			}
			if(!kral2.getPolicko().isOhrozena()) {
				sach_nastal = true;
				for (int i = 0; i < figurkyBile.size(); i++) {
					figurkyBile.get(i).zobrazValidni();
					for (int j = 0; j < policka.length; j++) {
						for (int k = 0; k < policka.length; k++) {
							for (int q = 0; q < figurkyCerne.size(); q++) {
								figurkyCerne.get(q).zobrazOhrozene(true);
								Policko policko2 = figurkyCerne.get(q).getPolicko();
								policko2.setFigurka(figurkyCerne.get(q));
							}
							if (policka[j][k].getColor() == VALIDNI_TAH) {
								Policko puvodni = figurkyBile.get(i).getPolicko();
								puvodni.setFigurka(null);
								figurkyBile.get(i).setPolicko(policka[j][k]);
								policka[j][k].setFigurka(figurkyBile.get(i));
								Figurka odstranena_figurka = testPrekryti(figurkyBile.get(i));
								kral2.getPolicko().setOhrozena(false);
								for (int q = 0; q < figurkyCerne.size(); q++) {
									figurkyCerne.get(q).zobrazOhrozene(false);
								}
								for (int q = 0; q < figurkyCerne.size(); q++) {
									figurkyCerne.get(q).zobrazOhrozene(true);
									Policko policko2 = figurkyCerne.get(q).getPolicko();
									policko2.setFigurka(figurkyCerne.get(q));
								}
								if(!kral2.getPolicko().isOhrozena()){
									sach_lze_vyresit = true;
								}
								if(odstranena_figurka != null){
									odstranena_figurka.setPolicko(policka[j][k]);
									policka[j][k].setFigurka(odstranena_figurka);
									figurkyCerne.add(odstranena_figurka);
									figurky.add(odstranena_figurka);
								} else {
									policka[j][k].setFigurka(null);
								}
								puvodni.setFigurka(figurkyBile.get(i));
								figurkyBile.get(i).setPolicko(puvodni);
							}
						}
					}
					figurkyBile.get(i).skryjValidni();
				}
			}
			for (int q = 0; q < figurkyCerne.size(); q++) {
				Policko policko2 = figurkyCerne.get(q).getPolicko();
				if(policko2 != null) {
					if (policko2.getFigurka() != null) {
						if (policko2.getFigurka().getColor() == Color.WHITE) {
							Figurka figurka2 = policko2.getFigurka();
							figurka2.setPolicko(null);
							policko2.setFigurka(null);
							if(figurkyCerne.contains(figurka2)){
								figurkyCerne.remove(figurka2);
							}
							if(figurkyBile.contains(figurka2)){
								figurkyBile.remove(figurka2);
							}
							if(figurky.contains(figurka2)){
								figurky.remove(figurka2);
							}
						}
					}
					policko2.setFigurka(figurkyCerne.get(q));
					figurkyCerne.get(q).setPolicko(policko2);
				}
			}
		}
		for (int i = 0; i < figurky.size(); i++) {
			figurky.get(i).zobrazOhrozene(false);

		}
		if(sach_nastal && !sach_lze_vyresit){
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Zjistí, jestli je hra v matové situaci
	 * @param barva odehraná barva hráče
	 * @return true, pokud došlo k matu
	 */
	boolean mat(Color barva){
		boolean sach_lze_vyresit = false;
		boolean sach_nastal = false;
		for (int i = 0; i < figurky.size(); i++) {
			figurky.get(i).zobrazOhrozene(false);
		}
		if(barva == Color.WHITE){
			for (int i = 0; i < figurkyBile.size(); i++) {
				figurkyBile.get(i).zobrazOhrozene(true);
			}
			if(kral.getPolicko().isOhrozena()) {
				sach_nastal = true;
				for (int i = 0; i < figurkyCerne.size(); i++) {
					figurkyCerne.get(i).zobrazValidni();
					for (int j = 0; j < policka.length; j++) {
						for (int k = 0; k < policka.length; k++) {
							for (int q = 0; q < figurkyBile.size(); q++) {
								figurkyBile.get(q).zobrazOhrozene(true);
								Policko policko2 = figurkyBile.get(q).getPolicko();
								if(policko2 != null){
								policko2.setFigurka(figurkyBile.get(q));
								figurkyBile.get(q).setPolicko(policko2);
								}
							}
							if (policka[j][k].getColor() == VALIDNI_TAH) {
								Policko puvodni = figurkyCerne.get(i).getPolicko();
								puvodni.setFigurka(null);
								figurkyCerne.get(i).setPolicko(policka[j][k]);
								policka[j][k].setFigurka(figurkyCerne.get(i));
								Figurka odstranena_figurka = testPrekryti(figurkyCerne.get(i));
								kral.getPolicko().setOhrozena(false);
								for (int q = 0; q < figurky.size(); q++) {
									figurky.get(q).zobrazOhrozene(false);
								}
								for (int q = 0; q < figurkyBile.size(); q++) {
									figurkyBile.get(q).zobrazOhrozene(true);
									Policko policko2 = figurkyBile.get(q).getPolicko();
									policko2.setFigurka(figurkyBile.get(q));
								}
								if(!kral.getPolicko().isOhrozena()){
									sach_lze_vyresit = true;
								}
								if(odstranena_figurka != null){
									odstranena_figurka.setPolicko(policka[j][k]);
									policka[j][k].setFigurka(odstranena_figurka);
									figurkyBile.add(odstranena_figurka);
									figurky.add(odstranena_figurka);
								} else {
									policka[j][k].setFigurka(null);
								}
								puvodni.setFigurka(figurkyCerne.get(i));
								figurkyCerne.get(i).setPolicko(puvodni);
							}
						}
					}
					figurkyCerne.get(i).skryjValidni();
				}
			}
			for (int q = 0; q < figurkyBile.size(); q++) {
				Policko policko2 = figurkyBile.get(q).getPolicko();
				if(policko2 != null) {
					if (policko2.getFigurka() != null) {
						if (policko2.getFigurka().getColor() == Color.BLACK) {
							Figurka figurka2 = policko2.getFigurka();
							figurka2.setPolicko(null);
							policko2.setFigurka(null);
							if(figurkyCerne.contains(figurka2)){
								figurkyCerne.remove(figurka2);
							}
							if(figurkyBile.contains(figurka2)){
								figurkyBile.remove(figurka2);
							}
							if(figurky.contains(figurka2)){
								figurky.remove(figurka2);
							}
						}
					}
					policko2.setFigurka(figurkyBile.get(q));
					figurkyBile.get(q).setPolicko(policko2);
				}
			}
		}else{
			for (int i = 0; i < figurkyCerne.size(); i++) {
				figurkyCerne.get(i).zobrazOhrozene(true);
			}
			if(kral2.getPolicko().isOhrozena()) {
				sach_nastal = true;
				for (int i = 0; i < figurkyBile.size(); i++) {
					figurkyBile.get(i).zobrazValidni();
					for (int j = 0; j < policka.length; j++) {
						for (int k = 0; k < policka.length; k++) {
							for (int q = 0; q < figurkyCerne.size(); q++) {
								figurkyCerne.get(q).zobrazOhrozene(true);
								Policko policko2 = figurkyCerne.get(q).getPolicko();
								if(policko2 != null){
								policko2.setFigurka(figurkyCerne.get(q));
								figurkyCerne.get(q).setPolicko(policko2);
								}
							}
							if (policka[j][k].getColor() == VALIDNI_TAH) {
								Policko puvodni = figurkyBile.get(i).getPolicko();
								puvodni.setFigurka(null);
								figurkyBile.get(i).setPolicko(policka[j][k]);
								policka[j][k].setFigurka(figurkyBile.get(i));
								Figurka odstranena_figurka = testPrekryti(figurkyBile.get(i));
								kral2.getPolicko().setOhrozena(false);
								for (int q = 0; q < figurky.size(); q++) {
									figurky.get(q).zobrazOhrozene(false);
								}
								for (int q = 0; q < figurkyCerne.size(); q++) {
									figurkyCerne.get(q).zobrazOhrozene(true);
									Policko policko2 = figurkyCerne.get(q).getPolicko();
									policko2.setFigurka(figurkyCerne.get(q));
								}
								if(!kral2.getPolicko().isOhrozena()){
									sach_lze_vyresit = true;
								}
								if(odstranena_figurka != null){
									odstranena_figurka.setPolicko(policka[j][k]);
									policka[j][k].setFigurka(odstranena_figurka);
									figurkyCerne.add(odstranena_figurka);
									figurky.add(odstranena_figurka);
								} else {
									policka[j][k].setFigurka(null);
								}
								puvodni.setFigurka(figurkyBile.get(i));
								figurkyBile.get(i).setPolicko(puvodni);
							}
						}
					}
					figurkyBile.get(i).skryjValidni();
				}
			}
			for (int q = 0; q < figurkyCerne.size(); q++) {
				Policko policko2 = figurkyCerne.get(q).getPolicko();
				if(policko2 != null) {
					if (policko2.getFigurka() != null) {
						if (policko2.getFigurka().getColor() == Color.WHITE) {
							Figurka figurka2 = policko2.getFigurka();
							figurka2.setPolicko(null);
							policko2.setFigurka(null);
							if(figurkyCerne.contains(figurka2)){
								figurkyCerne.remove(figurka2);
							}
							if(figurkyBile.contains(figurka2)){
								figurkyBile.remove(figurka2);
							}
							if(figurky.contains(figurka2)){
								figurky.remove(figurka2);
							}
						}
					}
					policko2.setFigurka(figurkyCerne.get(q));
					figurkyCerne.get(q).setPolicko(policko2);
				}
			}
		}
		for (int i = 0; i < figurky.size(); i++) {
			figurky.get(i).zobrazOhrozene(false);
		}

		if(sach_nastal && !sach_lze_vyresit){
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Omezení pohybu figurek, které je možné provést, pokud je král v šachu
	 * @param policko tah, kam bychom chtěli táhnout
	 * @return true, pokud je tento tah přípustný
	 */
	private boolean revizeSach(Policko policko) {
		if(policko.getColor() == VALIDNI_TAH) {
			if (figurka.getColor() == Color.BLACK) {
				if (kral.isSach()) {
					Policko puvodni_policko = figurka.getPolicko();
					kral.getPolicko().setOhrozena(false);
					figurka.getPolicko().setFigurka(null);
					figurka.setPolicko(policko);
					policko.setFigurka(figurka);
					Figurka odebrana_figurka = testPrekryti(figurka);
					revizeOdehrano(figurka.getColor());
					if (!kral.isSach()) {
						policko.setFigurka(null);
						puvodni_policko.setFigurka(figurka);
						figurka.setPolicko(puvodni_policko);
						policko.setColor(VALIDNI_TAH);
						return true;
					} else {
						policko.setFigurka(null);
						puvodni_policko.setFigurka(figurka);
						figurka.setPolicko(puvodni_policko);
						if(odebrana_figurka != null){
							odebrana_figurka.setPolicko(policko);
							policko.setFigurka(odebrana_figurka);
							figurky.add(odebrana_figurka);
							figurkyBile.add(odebrana_figurka);
							paint(g2);
						}
						return false;
					}
				} else {
					return true;
				}
			} else {
				if (kral2.isSach()) {
					Policko puvodni_policko = figurka.getPolicko();
					kral2.getPolicko().setOhrozena(false);
					figurka.getPolicko().setFigurka(null);
					figurka.setPolicko(policko);
					policko.setFigurka(figurka);
					Figurka odebrana_figurka = testPrekryti(figurka);
					revizeOdehrano(figurka.getColor());
					if (!kral2.isSach()) {
						policko.setFigurka(null);
						puvodni_policko.setFigurka(figurka);
						figurka.setPolicko(puvodni_policko);
						policko.setColor(VALIDNI_TAH);
						return true;
					} else {
						policko.setFigurka(null);
						puvodni_policko.setFigurka(figurka);
						figurka.setPolicko(puvodni_policko);
						if(odebrana_figurka != null){
							odebrana_figurka.setPolicko(policko);
							policko.setFigurka(odebrana_figurka);
							figurky.add(odebrana_figurka);
							figurkyCerne.add(odebrana_figurka);
							paint(g2);
						}
						return false;
					}
				} else {
					return true;
				}
			}
		} return false;
	}

	/**
	 * Provedeni rosady
	 * @return vez kterou je potřeba taktež tahnout
	 */
	public Vez rosada(){

		if(figurka instanceof Kral){
			Kral kral = (Kral) figurka;
			if(!kral.isJizOdehral()){
				if(policka[0][0].getFigurka() instanceof Vez){
					Vez vez = (Vez) policka[0][0].getFigurka(); // mala Rosada
					if(!vez.isJizOdehral() && kral.getPolicko() == policka[0][1]) { // po přesunu krále
						if (!kral.getPolicko().isOhrozena() && !policka[0][2].isOhrozena() && !policka[0][3].isOhrozena()){
							return vez;
						}
					}
				}
				if(policka[0][7].getFigurka() instanceof Vez){

					Vez vez = (Vez) policka[0][7].getFigurka(); // velka rosada
					if(!vez.isJizOdehral() && kral.getPolicko() == policka[0][5]) {
						if (!kral.getPolicko().isOhrozena() && !policka[0][3].isOhrozena() && !policka[0][4].isOhrozena()) {
							return vez;
						}
					}
				}
				if(policka[7][7].getFigurka() instanceof Vez) {

					Vez vez = (Vez) policka[7][7].getFigurka(); // velka rosada
					if (!vez.isJizOdehral() && kral.getPolicko() == policka[7][5]) {
						if (!kral.getPolicko().isOhrozena() && !policka[7][3].isOhrozena() && !policka[7][4].isOhrozena()) {
							return vez;
						}
					}
				}
				if(policka[7][0].getFigurka() instanceof Vez) {

					Vez vez = (Vez) policka[7][0].getFigurka(); // mala rosada

					if (!vez.isJizOdehral() && kral.getPolicko() == policka[7][1]) {

						if (!kral.getPolicko().isOhrozena() && !policka[7][2].isOhrozena() && !policka[7][3].isOhrozena()){
							return vez;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Provedeni tahu mimochodem
	 * @param cilovePolicko policko, kam je taženo
	 */
	private void mimochodem(Policko cilovePolicko) {
		if(figurka instanceof Pesak){

			int i = cilovePolicko.getRadka();
			int j = cilovePolicko.getSloupec();
			int i1 = figurka.getPolicko().getRadka()  ;
			int j1 = figurka.getPolicko().getSloupec()  ;
			if(figurka.getColor() == Color.BLACK){
				if(i1 - 2 == i && j1 == j){
					((Pesak) figurka).setMimochodem(true);
				}
			}
			else {
				if(i1 + 2 == i && j1 == j){
					((Pesak) figurka).setMimochodem(true);
				}
			}
		}
	}

	/**
	 * @param figurka - figurka, se kterou je taženo
	 * @param puvodniX - x odkud jdeme
	 * @param puvodniY - y odkud jdeme
	 * @param i index x pole kam tahneme
	 * @param j index y pole kam tahneme
	 */
	public void animace(Figurka figurka, int puvodniX, int puvodniY, int i, int j) {
		int noveX = policka[i][j].getX() + policka[i][j].getWidth()/2;
		int noveY = policka[i][j].getY() + policka[i][j].getHeight()/2;
		int distanceX = noveX  - puvodniX;
		int distanceY = noveY  - puvodniY;
		double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
		int steps = (int) Math.ceil(distance / 10.0);
		int deltaX = (int) Math.round(distanceX / (double) steps);
		int deltaY = (int) Math.round(distanceY / (double) steps);
		int delay = 500 / steps;
		Timer timer = new Timer(delay, new ActionListener() {
			int count = 0;

			public void actionPerformed(ActionEvent e) {
				if(count == 0){
					figurka.setX(puvodniX);
					figurka.setY(puvodniY);
				}
				figurka.setPohyb(true);
				figurka.setX((figurka.getX() + deltaX));
				figurka.setY((figurka.getY() + deltaY));
				repaint();
				count++;
			if (count == steps ) {
					((Timer)e.getSource()).stop();
					testPrekryti(figurka);
					figurka.getPolicko().setFigurka(null);
					figurka.setPolicko(policka[i][j]);
					policka[i][j].setFigurka(figurka);
					figurka.setPohyb(false);
					BasicDrawing.drzimObjekt = false;
					figurka.paint(g2);
					promena();
				}
			}
		});
		timer.start();
	}

	/**
	 * Pokud je tah validní, je na řadě druhý hráč
	 * @param color barva, která právě odehrála validní tah
	 */
	private void odehrano(Color color) {

		long stop = System.currentTimeMillis();
		long rozdil = stop - start;
		casyTahu.add(rozdil);
		start = System.currentTimeMillis();
		if(color == Color.BLACK){
			for (int i = 0; i < figurkyBile.size(); i++) {
				figurkyBile.get(i).setNaTahu(true);
				if(figurkyBile.get(i) instanceof Pesak){
					((Pesak) figurkyBile.get(i)).setMimochodem(false);
				}
			}
			for (int i = 0; i < figurkyCerne.size(); i++) {
				figurkyCerne.get(i).setNaTahu(false);
			}
		} else {
			for (int i = 0; i < figurkyBile.size(); i++) {
				figurkyBile.get(i).setNaTahu(false);

			}
			for (int i = 0; i < figurkyCerne.size(); i++) {
				figurkyCerne.get(i).setNaTahu(true);
				if(figurkyCerne.get(i) instanceof Pesak){
					((Pesak) figurkyCerne.get(i)).setMimochodem(false);
				}
			}
		}
		if(color == Color.white){
			if(mat(Color.WHITE)) {

				int result = JOptionPane.showOptionDialog(null, "Gratulujeme, bílí vyhráli! Chcete spustit novou hru?",
						"Konec hry", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"OK", "End"}, "OK");
				if (result == JOptionPane.YES_OPTION) {
					figurky.clear();
					figurkyCerne.clear();
					figurkyBile.clear();
					dalsiSpusteni = false;

					paint(g2);

				} else if (result == JOptionPane.NO_OPTION) {
					System.exit(0);
				}
			}
			else if(pat(Color.white)){
				int result = JOptionPane.showOptionDialog(null, "Remíza, došlo k patové situaci!",
						"Konec hry", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"OK", "End"}, "OK");

				if (result == JOptionPane.YES_OPTION) {
					figurky.clear();
					figurkyCerne.clear();
					figurkyBile.clear();
					dalsiSpusteni = false;

					paint(g2);

				} else if (result == JOptionPane.NO_OPTION) {
					System.exit(0);
				}
			}
		} else {
			if(mat(Color.BLACK)) {

				int result = JOptionPane.showOptionDialog(null, "Gratulujeme, černí vyhráli! Chcete spustit novou hru?",
						"Konec hry", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"OK", "End"}, "OK");
				if (result == JOptionPane.YES_OPTION) {
					figurky.clear();
					figurkyCerne.clear();
					figurkyBile.clear();
					dalsiSpusteni = false;

					paint(g2);

				} else if (result == JOptionPane.NO_OPTION) {
					System.exit(0);
				}
			}
			else if(pat(Color.BLACK)){
					int result = JOptionPane.showOptionDialog(null, "Remíza, došlo k patové situaci!",
							"Konec hry", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"OK", "End"}, "OK");

					if (result == JOptionPane.YES_OPTION) {
						figurky.clear();
						figurkyCerne.clear();
						figurkyBile.clear();
						dalsiSpusteni = false;

						paint(g2);

					} else if (result == JOptionPane.NO_OPTION) {
						System.exit(0);
					}
				}
		}
		if(color == Color.BLACK){
			for (int i = 0; i < figurkyBile.size(); i++) {
				figurkyBile.get(i).zobrazOhrozene(false);
			}
			for (int i = 0; i < figurkyCerne.size(); i++) {
				figurkyCerne.get(i).zobrazOhrozene(true);
			}
			if(kral2.getPolicko().isOhrozena()){
				kral2.setSach(true);
			} else {
				kral2.setSach(false);
			}
		} else {
			for (int i = 0; i < figurkyCerne.size(); i++) {
				figurkyCerne.get(i).zobrazOhrozene(false);
			}
			for (int i = 0; i < figurkyBile.size(); i++) {
				figurkyBile.get(i).zobrazOhrozene(true);
			}
			if(kral.getPolicko().isOhrozena()){
				kral.setSach(true);
			} else {
				kral.setSach(false);
			}
		}
	}

	/**
	 * Zobrazí stav po tom, co byl simulován možný tah (třeba pro zjištění patu)
	 * @param color barva hráče, jehož možné tahy jsou zkoumané
	 */
	public void revizeOdehrano(Color color){
		if(color == Color.BLACK) {
			for (int i = 0; i < figurkyCerne.size(); i++) {
				figurkyCerne.get(i).zobrazOhrozene(false);
			}
			for (int i = 0; i < figurkyBile.size(); i++) {
				figurkyBile.get(i).zobrazOhrozene(true
				);
			}

			if(kral.getPolicko().isOhrozena()){
				kral.setSach(true);
			} else {
				kral.setSach(false);
			}
		} else {
			for (int i = 0; i < figurkyBile.size(); i++) {
				figurkyBile.get(i).zobrazOhrozene(false);
			}
			for (int i = 0; i < figurkyCerne.size(); i++) {
				figurkyCerne.get(i).zobrazOhrozene(true);
			}


			if(kral2.getPolicko().isOhrozena()){
				kral2.setSach(true);
			} else {
				kral2.setSach(false);
			}
		}
		for (int i = 0; i < policka.length; i++) {
			for (int j = 0; j < policka.length; j++) {
				if(policka[i][j].isOhrozena()){
					policka[i][j].setColor(Color.MAGENTA);
				}
			}
		}
	}


	/**
	 * Metoda, která otestuje, zda-li jsou dvě figurky na jednom políčku
	 * Pokud je ano, tak se původní figurka smaže
	 * @param figurka figurka, se kterou je taženo
	 */
	public Figurka testPrekryti(Figurka figurka){
		for (int i = 0; i < figurky.size(); i++) {
			if(figurka.getPolicko() == figurky.get(i).getPolicko()){
				if (figurka == figurky.get(i)){
					continue;
				}

				figurky.get(i).getPolicko().setFigurka(null);
				figurky.get(i).setPolicko(null);
				if(figurky.get(i).getColor() == Color.BLACK){
					figurkyCerne.remove(figurky.get(i));
				} else {
					figurkyBile.remove(figurky.get(i));
				}
				Figurka odebrana_figurka = figurky.get(i);
				figurky.remove(figurky.get(i));
				return odebrana_figurka;
			}
		}
		return null;
	}

	/**
	 * Určení, ve kterém poli se figurka právě nachází
	 * @param policko předpokládané políčko
	 * @param figurka tažená figurka
	 * @return true, pokud je figurka v poli
	 */
	public boolean leziVPoli(Policko policko, Figurka figurka){
		double Xstrana = policko.getX();
		double Ystrana = policko.getY();
		if((Xstrana <= figurka.getX()) &&
				figurka.getX() <= Xstrana + policko.getWidth() &&
				figurka.getY() <= Ystrana + policko.getHeight() &&
				(Ystrana <= figurka.getY())){
			return true;
		}
		return  false;
	}

	/**
	 * Zjistí, jestli právě táhneme nějakou figurkou
	 * @param x x-ová souřadnice myši
	 * @param y y-ová souřadnice myši
	 * @return true, pokud máme uchycenou nějakou figurku v myši
	 */
	public boolean isObjectHit(double x, double y) {
		for (Figurka hodnotaF : figurky) {
			double souradniceX = hodnotaF.getX();
			double souradniceY = hodnotaF.getY();
			double R = hodnotaF.getHeight() / 2.0;
			double vzdalenost = Math.sqrt(Math.pow(souradniceX - x, 2) + Math.pow(souradniceY - y, 2));
			if (vzdalenost <= R) {
				if(hodnotaF.isNaTahu()) {
					figurka = hodnotaF;
					figurka.getPolicko().setColor(AKTUALNI_POZICE);
					figurka.zobrazValidni();
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Provádí posun figurky, kterou zrovna táhneme
	 * @param x x-ová souřadnice myši
	 * @param y y-ová souřadnice myši
	 */
	public void posun(double x, double y){
		figurka.setPohyb(true);
		figurka.setX((int) x);
		figurka.setY((int) y);
		repaint();
	}
}
