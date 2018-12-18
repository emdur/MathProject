package app;

import java.util.List;

public class Lanchester {

	public static void main(String[] args) {

		// insert (Anfangsgröße, Feuerkraft)
		Population g = new Population(600, 2);
		Population h = new Population(400, 8);

		Population.wievieleüberleben(g, h);
		System.out.println(Population.populationt(g, h, 0.01));

	}

	static void fillPopList(Population p, Population p2, int leftright, int popcolor, List<Vector> PopList) {
		int x = 0;
		int y = 0;
		int xk = 0; // x Wert Array
		int yk = 0; // y Wert Array
		// ERSTELLEN DER LISTE MIT PUNKTEN
		for (int i = 0; i < p.size; i++) {

			// VERSUCH ARRAYLIST

			xk = (Constants.WINDOW_WIDTH / 15 + x - 10) + leftright;
			yk = Constants.WINDOW_HEIGHT / 15 + y;

			// Punkt zu Liste hinzufügen:
			PopList.add(new Vector(xk, yk, true));

			// muss in Extraschleife, war vorher hier: g.fillOval(xk, yk, 5, 5);

			// x und y, damit sich die Punkte nicht überlagern
			x += 6;
			if (x > Constants.WINDOW_WIDTH / 2 - 55) {
				y += 6;
				x = 0;
			}
		}
	}

}
