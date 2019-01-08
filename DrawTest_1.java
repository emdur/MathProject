package app;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class DrawTest_1 extends JFrame {

	int flag = 0;

	public DrawTest_1() {
		setTitle("Jenny und Isabellas Lanchester Projekt");
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// setVisible(true);
	}

	static List<Vector> PopListG = new ArrayList<Vector>();
	static List<Vector> PopListH = new ArrayList<Vector>();
	static Population g1 = new Population(300, 1);
	static Population h1 = new Population(200, 6);

	public static void main(String[] args) {

		Lanchester.fillPopList(g1, h1, 0, 1, PopListG);
		Lanchester.fillPopList(h1, g1, Constants.WINDOW_WIDTH / 2, 1, PopListH);

		// fillPopList(p, p2, leftright, popcolor, PopList); --> Muster

		DrawTest_1 testObjekt = new DrawTest_1();

		testObjekt.setVisible(true);
		double absT = 0;
		while (true) {
			testObjekt.draw(absT);

			try {
				Thread.sleep(1000); // in Millisekunden
				absT += 7;
			} catch (InterruptedException e) {
			}
		}

	}

	// INFO ZUR METHODE DRAWPOPULATON

	// Das Zeichnen der Punkte ist jetzt in einer Extraschleife:
	// So können wir in der ersten Schleife der Methode drawPopulation bestimmen,
	// was gezeichnet werden soll
	// und außerdem, wo und wann null-Objekte entstehen sollen (zum Punkte löschen).
	// Es wird also in der ersten Schleife die Liste (PopList) erstellt und editiert
	// und in der zweiten Schleife wird ausgelesen und gezeichnet, was sich in der
	// Liste befindet.
	// Nicht vergessen: Die Methode drawPopulation wird immer noch 1x PER FRAME
	// aufgerufen!!
	// Alles wird in jedem Frame neu gezeichnet!

	// int f = 0;
	int n = 0;
	Vector vec;

	void drawPopulation(Graphics g, Population p, Population p2, int leftright, int popcolor, List<Vector> PopList,
			double tcounter, double d) {

		// TOTE PUNKTE VON DER LISTE LÖSCHEN
		// berechne Anzahl n zu löschender Punkte

		// System.out.println(n);
		// f = n;

		int timer = 0;
		// wenn aktuelle Populationsgröße größer als endgültige Populationsgröße
		// System.out.println("1: " + Population.populationt(p, p2, tcounter));
		// System.out.println("2: " + Population.populationt(p, p2,
		// Population.wannistderkampfentschieden(p, p2)));
		// (Population.populationt(p, p2, tcounter)
		// if (PopList.size() > Population.populationt(p, p2,
		// Population.wannistderkampfentschieden(p, p2))) {

		n = Population.populationt(p, p2, tcounter - d) - Population.populationt(p, p2, tcounter);
		Random ran = new Random();
		int r = 0;
		int s;
		// if (PopList.size() > Population.populationt(p, p2,
		// Population.wannistderkampfentschieden(p, p2))) {
		// try {

		for (int l = 0; l < n; l++) {

			try {
				// random number from 0 to PopListSize-1 --> n random Stellen
				r = (int) (Math.random() * PopList.size());
				// System.out.println("r: " + r);
				vec = PopList.get(r);
				PopList.remove(r);
			} catch (IndexOutOfBoundsException e) {
				// System.out.println("This is the ennnnnd");
				// PopList.add(r, vec);
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
	double d;

	void draw(double absT) {

		Graphics g = getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

		// Erstellung der Populationen in jedem Frame;
		// Methode populationt mit Parameter tcounter für die Animation der Abnahme der
		// Population beim Kampf --> momentan in drawPopulation-Methode

		int leftright = 0;
		int popcolor = 0;

		// double td = Population.populationt(g1, h1,
		// Population.wannistderkampfentschieden(g1, h1));
		if (PopListG.size() > Population.populationt(g1, h1, Population.wannistderkampfentschieden(g1, h1))) {
			// g1.size = Population.populationt(g1, h1, tcounter);
			drawPopulation(g, g1, h1, leftright, popcolor, PopListG, tcounter, d);
		} else {
			drawOnly(popcolor, g, PopListG);
		}

		popcolor = 1;
		leftright = Constants.WINDOW_WIDTH / 2;
		// double tdd = Population.populationt(h1, g1,
		// Population.wannistderkampfentschieden(h1, g1));
		if (PopListH.size() > Population.populationt(h1, g1, Population.wannistderkampfentschieden(h1, g1))) {

			// h1.size = Population.populationt(h1, g1, tcounter);
			drawPopulation(g, h1, g1, leftright, popcolor, PopListH, tcounter, d);
		} else {
			drawOnly(popcolor, g, PopListH);
		}

		tcounter += 0.01;
		d = tcounter - 0.01;

		// muss laufen bis tdeath
	}
}