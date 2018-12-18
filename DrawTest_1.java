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

	public static void main(String[] args) {

		Population g1 = new Population(400, 8);
		Population h1 = new Population(600, 2);

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

	// PopList erstellen Auslagerungsmethode (jz in Lanchester)

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

	void drawPopulation(Graphics g, Population p, Population p2, int leftright, int popcolor, List<Vector> PopList,
			double tcounter) {

		// if (flag == 0) {
		// fillPopList(g, p, p2, leftright, popcolor, PopList, tcounter);
		// }
		// flag = 1;

		// TOTE PUNKTE VON DER LISTE LÖSCHEN
		// berechne Anzahl zu löschender Punkte
		int n = p.size - Population.populationt(p, p2, tcounter);
		// PopList an random Stellen löschen
		Random ran = new Random();
		int r;
		for (int l = 0; l < n; l++) {
			// random number from 0 to PopListSize-1 --> n random Stellen
			r = ran.nextInt(PopList.size());
			// an random Stelle Indikator für spätere Weißfärbung setzen (Punkt löschen)
			PopList.get(r).visible = false;
		}

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
		for (int j = 0; j < PopList.size() - 1; j++) {
			Vector v = PopList.get(j);
			if (v.visible) {
				// draw point
				g.fillOval(v.xKo, v.yKo, 5, 5);
			}
		}
	}

	double tcounter = 0;

	void draw(double absT) {
		Population g1 = new Population(400, 8);
		Population h1 = new Population(600, 2);
		Graphics g = getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

		// Erstellung der Populationen in jedem Frame;
		// Methode populationt mit Parameter tcounter für die Animation der Abnahme der
		// Population beim Kampf

		int leftright = 0;
		int popcolor = 0;
		// g1.size = Population.populationt(g1, h1, tcounter);
		drawPopulation(g, g1, h1, leftright, popcolor, PopListG, tcounter);

		popcolor = 1;
		leftright = Constants.WINDOW_WIDTH / 2;
		// h1.size = Population.populationt(h1, g1, tcounter);
		drawPopulation(g, h1, g1, leftright, popcolor, PopListH, tcounter);

		// tcounter soll sich erhöhen, bis tdeath erreicht ist
		while (tcounter < Population.wannistderkampfentschieden(g1, h1)) {
			tcounter += 0.0001;
		}

		// muss laufen bis tdeath
	}
}