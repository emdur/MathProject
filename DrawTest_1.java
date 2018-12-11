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

	void drawPopulation(Graphics g, Population p, int leftright, int popcolor, List<Vector> PopList) {
		int x = 0;
		int y = 0;
		// int ö = 0; // x Wert Array
		// int ä = 0; // y Wert Array

		// set color
		for (int i = 0; i < p.size; i++) {
			if (popcolor == 0) {
				g.setColor(Color.RED);
			}
			if (popcolor == 1) {
				g.setColor(Color.BLUE);
			}
			// VERSUCH ARRAYLIST
			Vector point = new Vector((Constants.WINDOW_WIDTH / 15 + x - 10) + leftright,
					Constants.WINDOW_HEIGHT / 15 + y);
			PopList.add(point);

			// VERSUCH ARRAY
			/*
			 * for(int j = 0; j<= Array.length; j++) { for(int k = 0; k<= Array.length; k++)
			 * { Array[j][k] = } }
			 */
			// Array [ö][ä] = [(Constants.WINDOW_WIDTH / 15 + x - 10) +
			// leftright][Constants.WINDOW_HEIGHT / 15 + y, 5, 5];

			// HIER WIRD JEDER PUNKT ERSTELLT --> HIER ZUM ARRAY HINZUFÜGEN
			// g.fillOval((Constants.WINDOW_WIDTH / 15 + x - 10) + leftright,
			// Constants.WINDOW_HEIGHT / 15 + y, 5, 5);

			// x und y, damit sich die Punkte nicht überlagern
			x += 6;
			if (x > Constants.WINDOW_WIDTH / 2 - 55) {
				y += 6;
				x = 0;
			}
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