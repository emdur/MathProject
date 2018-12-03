package app;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class DrawTest_2018_10_11 extends JFrame {

	public DrawTest_2018_10_11() {
		setTitle("Jenny und Isabellas Lanchester Projekt");
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// setVisible(true);
	}

	public static void main(String[] args) {
		DrawTest_2018_10_11 testObjekt = new DrawTest_2018_10_11();

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

	// Wir müssen auf jeden Fall die Parameter g, h, s, r aus der Lanchester-Klasse
	// in diese Methode stopfen
	void draw(double absT) {
		Graphics g = getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

		int x = 0;
		int y = 0;
		// Schleife für die Erstellung der Anfangspopulation g
		for (int i = 0; i < 500 /* Hier muss iwie Lanchester.g hin */; i++) {
			g.setColor(Color.RED);
			g.fillOval(Constants.WINDOW_WIDTH / 15 + x, Constants.WINDOW_HEIGHT / 15 + y, 5, 5);

			// x und y mit if, damit die Punkte sich nicht überlagern und links oben
			// erscheinen
			// Die Punkte von h könnten dann in eine der anderen Ecken, ich wollt nur erst
			// mal die
			// Animation von g hinkriegen, h wird ja dann ähnlich.
			x += 6;
			if (x > 300) {
				y += 6;
				x = 0;
			}
		}
		
		//Schleife für die Animation der Abnahme der Population beim Kampf
		for(int i = 0; i <= /*LanchesterMethods.wannistderkampfentschieden (also tdeath)*/; i++) {
		
			// LanchesterMethods.populationt(g, h, s, r, i) --> i ist der Zeitparameter t der Methode, die z.B. G(t) ausrechnet
		}
	}
}
