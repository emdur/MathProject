package app;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class DrawTest_2 extends JFrame {

	int flag = 0;

	public DrawTest_2() {
		setTitle("Jenny und Isabellas Lanchester Projekt");
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// setVisible(true);
	}

	static List<Vector> PopListG = new ArrayList<Vector>();
	static List<Vector> PopListH = new ArrayList<Vector>();
	static Population g1 = new Population(10, 1);
	static Population h1 = new Population(14, 1);

	public static void main(String[] args) {

		Population.wievieleüberleben(g1, h1);

		// FÜLLE DIE BEIDEN VEKTORLISTEN FÜR DIE POPULATIONEN G UND H
		fillPopList(g1, h1, 0, PopListG);
		fillPopList(h1, g1, Constants.WINDOW_WIDTH / 2, PopListH);

		DrawTest_2 testObjekt = new DrawTest_2();

		testObjekt.setVisible(true);
		double absT = 0;
		while (true) {
			testObjekt.draw(absT);

			try {
				Thread.sleep(1000); // in Millisekunden
				absT += Constants.TPF;
			} catch (InterruptedException e) {
			}
		}

	}

	static void fillPopList(Population p, Population p2, int leftright, List<Vector> PopList) {
		int x = 0;
		int y = 0;
		int xk = 0; // x Wert in der Arraylist
		int yk = 0; // y Wert in der Arraylist
		// ERSTELLEN DER LISTE MIT PUNKTEN
		for (int i = 0; i < p.size; i++) {

			// VERSUCH ARRAYLIST

			xk = (Constants.WINDOW_WIDTH / 15 + x - 10) + leftright;
			yk = Constants.WINDOW_HEIGHT / 15 + y;

			// Punkt zu Liste hinzufügen:
			PopList.add(new Vector(xk, yk));

			// x und y, damit sich die Punkte nicht überlagern --> Offset
			x += 6;
			if (x > Constants.WINDOW_WIDTH / 2 - 55) {
				y += 6;
				x = 0;
			}
		}
	}

	int n = 0;

	void drawPopulation(Graphics g, Population p, Population p2, int popcolor, List<Vector> PopList, double tcounter,
			double d) {

		// TOTE PUNKTE VON DER LISTE LÖSCHEN

		// wenn aktuelle Listenlänge (= Populationsgröße) größer ist als die
		// Endpopulation beim Zeitpunkt tdeath, sollen Punkte gelöscht werden
		if (PopList.size() > Population.populationt(p, p2, Population.wannistderkampfentschieden(p, p2))) {

			// berechne Anzahl n zu löschender Punkte
			// n = Populationsgröße aus letztem Frame - Aktuelle Populationsgröße
			n = Population.populationt(p, p2, d) - Population.populationt(p, p2, tcounter);

			int r = 0;
			for (int l = 0; l < n; l++) {

				try {
					// random number from 0 to PopListSize-1 --> n random Stellen
					r = (int) (Math.random() * PopList.size());
					PopList.remove(r);

				} catch (IndexOutOfBoundsException e) {

				}
			}
		}

		// }
		// } // Ende if
		drawOnly(popcolor, g, PopList);

	}

	void drawOnly(int popcolor, Graphics g, List<Vector> PopList) {

		// AUSLESEN DER LISTEN UND ZEICHNEN DER PUNKTE IM AKTUELLEN FRAME
		// Es malt jz weiße Punkte, statt welche ganz zu löschen(edit: es malt sie jz
		// einfach nicht, um das zu erreichen, gibt es beim Vector Objekt nun den
		// Bestandteil visibility (boolean))
		// set color
		if (popcolor == 0) {
			g.setColor(Color.RED);
		}
		if (popcolor == 1) {
			g.setColor(Color.BLUE);
		}
		for (int j = 0; j < PopList.size(); j++) {
			Vector v = PopList.get(j);
			// draw point
			g.fillOval(v.xKo, v.yKo, 5, 5);
		}
	}

	double tcounter = 0;
	double d = 0;

	void draw(double absT) {

		Graphics g = getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

		// Erstellung der Populationen in jedem Frame;
		// Methode populationt mit Parameter tcounter für die Animation der Abnahme der
		// Population beim Kampf --> momentan in drawPopulation-Methode

		// ERSTELLEN DER POPULATION G PRO FRAME
		int popcolor = 0;
		drawPopulation(g, g1, h1, popcolor, PopListG, tcounter, d);

		// ERSTELLEN DER POPULATION H PRO FRAME
		popcolor = 1;
		drawPopulation(g, h1, g1, popcolor, PopListH, tcounter, d);

		// d = nachher Zeit aus jeweils letztem Frame
		d = tcounter;
		tcounter += 0.01;

		// muss laufen bis tdeath
	}
}