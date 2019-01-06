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
	static Population g1 = new Population(600, 3);
	static Population h1 = new Population(500, 6);

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

	void drawPopulation(Graphics g, Population p, Population p2, int leftright, int popcolor, List<Vector> PopList,
			double tcounter, double d) {

		// TOTE PUNKTE VON DER LISTE LÖSCHEN
		// berechne Anzahl n zu löschender Punkte

		// n = Populationsgröße des letzten Frames - aktuelle Populationsgröße
		int n = Population.populationt(p, p2, tcounter - d) - Population.populationt(p, p2, tcounter);
		// f = n;

		int timer = 0;
		// wenn aktuelle Populationsgröße größer als endgültige Populationsgröße
		if (Population.populationt(p, p2, tcounter) > Population.populationt(p, p2,
				Population.wannistderkampfentschieden(p, p2))) {
			// PopList an random Stellen löschen
			Random ran = new Random();
			int r;
			int s;
			for (int l = 0; l < n; l++) {
				// random number from 0 to PopListSize-1 --> n random Stellen
				r = ran.nextInt(PopList.size());
				// an random Stelle Indikator für's Nichtzeichnen setzen (Punkt "löschen")

				// Muss iwie einen neuen Punkt randomisieren, wenn der aktuell anvisierte schon
				// visible=false ist!! Die Liste bleibt fest und wird nicht in jedem Frame
				// zurückgesetzt, ansonsten tauchen die Lücken in jedem Frame woanders auf
				// Daher das while...:

				if (PopList.get(r).visible) {
					PopList.get(r).visible = false;
				} else {

					while (timer == 0) {
						s = ran.nextInt(PopList.size());
						if (PopList.get(s).visible) {
							PopList.get(s).visible = false;
							timer = 1;
						}
					}
				}
			}
		} // Ende if

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
	double d;

	void draw(double absT) {

		Graphics g = getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

		// Erstellung der Populationen in jedem Frame;
		// Methode populationt mit Parameter tcounter für die Animation der Abnahme der
		// Population beim Kampf

		int leftright = 0;
		int popcolor = 0;
		// g1.size = Population.populationt(g1, h1, tcounter);
		drawPopulation(g, g1, h1, leftright, popcolor, PopListG, tcounter, d);

		popcolor = 1;
		leftright = Constants.WINDOW_WIDTH / 2;
		// h1.size = Population.populationt(h1, g1, tcounter);
		drawPopulation(g, h1, g1, leftright, popcolor, PopListH, tcounter, d);

		tcounter += 0.01;
		d = tcounter - 0.01;

		// muss laufen bis tdeath
	}
}