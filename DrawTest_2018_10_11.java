package app;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class DrawTest_2018_10_11 extends JFrame {

	int flag = 0;

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

	void draw(double absT) {
		Population g1 = new Population(200, 1);
		Population h1 = new Population(100, 8);

		Graphics g = getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

		int x = 0;
		int y = 0;

		double tcounter = 0;
		g1.size = Population.populationt(g1, h1, tcounter);
		tcounter += 0.01;

		if (flag == 0) {
			for (int i = 0; i < g1.size; i++) {
				g.setColor(Color.RED);
				g.fillOval(Constants.WINDOW_WIDTH / 4 + x, Constants.WINDOW_HEIGHT / 15 + y, 5, 5);

				x += 6;

				if (x > g1.size * 2) {
					y += 6;
					x = 0;
				}
			}

			for (int i = 0; i < h1.size; i++) {
				g.setColor(Color.GREEN);
				g.fillOval(Constants.WINDOW_WIDTH / 4 + x, Constants.WINDOW_HEIGHT / 2 + y, 5, 5);
				x += 6;

				if (x > h1.size * 2) {
					y += 6;
					x = 0;
				}
			}

			flag = 1;
			/*
			 * try { Thread.sleep(1000); // in Millisekunden absT += 7; } catch
			 * (InterruptedException e) { }
			 */
		}

		/*
		 * int time = 100;
		 * 
		 * for (int j = time; j <= 0; j--) {
		 * 
		 * for (int i = 0; i < g1.size; i++) { g.setColor(Color.white);
		 * g.fillOval(Constants.WINDOW_WIDTH / 4 + x, Constants.WINDOW_HEIGHT / 15 + y,
		 * 5, 5);
		 * 
		 * x += 6;
		 * 
		 * if (x > g1.size * 2) { y += 6; x = 0; }
		 * 
		 * try { Thread.sleep(100); // in Millisekunden absT += 7; } catch
		 * (InterruptedException e) { }
		 * 
		 * }
		 * 
		 * for (int i = 0; i < h1.size; i++) { g.setColor(Color.white);
		 * g.fillOval(Constants.WINDOW_WIDTH / 4 + x, Constants.WINDOW_HEIGHT / 2 + y,
		 * 5, 5); x += 6;
		 * 
		 * if (x > h1.size * 2) { y += 6; x = 0; }
		 * 
		 * try { Thread.sleep(100); // in Millisekunden absT += 7; } catch
		 * (InterruptedException e) { } } }
		 */
		// muss laufen bis tdeath
	}
}