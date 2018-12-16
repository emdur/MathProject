package app;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class DrawTest_1 extends JFrame {

	public DrawTest_1() {
		setTitle("Jenny und Isabellas Lanchester Projekt");
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// setVisible(true);
	}

	public static void main(String[] args) {
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

	List<Vector> PopListG = new ArrayList<Vector>();
	List<Vector> PopListH = new ArrayList<Vector>();

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

	void drawPopulation(Graphics g, Population p, int leftright, int popcolor, List<Vector> PopList) {
		int x = 0;
		int y = 0;
		int xk = 0; // x Wert Array
		int yk = 0; // y Wert Array

		// Erstellen der Liste
		for (int i = 0; i < p.size; i++) {
			// set color
			if (popcolor == 0) {
				g.setColor(Color.RED);
			}
			if (popcolor == 1) {
				g.setColor(Color.BLUE);
			}
			// VERSUCH ARRAYLIST

			xk = (Constants.WINDOW_WIDTH / 15 + x - 10) + leftright;
			yk = Constants.WINDOW_HEIGHT / 15 + y;

			// Punkt zu Liste hinzufügen:
			PopList.add(new Vector(xk, yk));

			// muss in Extraschleife, war vorher hier: g.fillOval(xk, yk, 5, 5);

			// x und y, damit sich die Punkte nicht überlagern
			x += 6;
			if (x > Constants.WINDOW_WIDTH / 2 - 55) {
				y += 6;
				x = 0;
			}
		}
		// Schleife zum Auslesen der Liste und Zeichnen der Punkte
		for (int j = 0; j < PopList.size() - 1; j++) {
			Vector v = PopList.get(j);
			g.fillOval(v.xKo, v.yKo, 5, 5);
		}
	}

	void draw(double absT) {
		Population g1 = new Population(500, 8);
		Population h1 = new Population(600, 2);
		Graphics g = getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

		// Erstellung der Populationen in jedem Frame;
		// Methode populationt mit Parameter tcounter für die Animation der Abnahme der
		// Population beim Kampf
		double tcounter = 0;

		int leftright = 0;
		int popcolor = 0;
		g1.size = Population.populationt(g1, h1, tcounter);
		drawPopulation(g, g1, leftright, popcolor, PopListG);

		popcolor = 1;
		leftright = Constants.WINDOW_WIDTH / 2;
		h1.size = Population.populationt(h1, g1, tcounter);
		drawPopulation(g, h1, leftright, popcolor, PopListH);

		tcounter += 0.01;

		// muss laufen bis tdeath
	}
}