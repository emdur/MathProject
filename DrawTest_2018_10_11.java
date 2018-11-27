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
		while (true) {
			testObjekt.draw();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}

	}

	void draw() {
		Graphics g = getGraphics();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		g.setColor(Color.RED);
		g.fillOval(Constants.WINDOW_WIDTH / 15, Constants.WINDOW_HEIGHT / 15, 5, 5);

	}
	/*
	 * void draw() { Graphics g = getGraphics();
	 * 
	 * g.setColor(Color.RED); g.fillRect(0, 0, Constants.WINDOW_WIDTH,
	 * Constants.WINDOW_HEIGHT); g.setColor(Color.BLACK);
	 * g.drawLine(Constants.WINDOW_WIDTH / 2, 0, Constants.WINDOW_WIDTH / 2,
	 * Constants.WINDOW_HEIGHT); g.drawLine(0, Constants.WINDOW_HEIGHT / 2,
	 * Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT / 2);
	 * g.setColor(Color.GREEN); g.drawLine(Constants.WINDOW_WIDTH * 3 / 8,
	 * Constants.WINDOW_HEIGHT / 2, Constants.WINDOW_WIDTH / 2,
	 * Constants.WINDOW_HEIGHT / 2 - (int) (Math.sqrt(3) * Constants.WINDOW_WIDTH *
	 * 1 / 8)); g.drawLine(Constants.WINDOW_WIDTH * 5 / 8, Constants.WINDOW_HEIGHT /
	 * 2, Constants.WINDOW_WIDTH / 2, Constants.WINDOW_HEIGHT / 2 - (int)
	 * (Math.sqrt(3) * Constants.WINDOW_WIDTH * 1 / 8));
	 * g.drawLine(Constants.WINDOW_WIDTH * 3 / 8, Constants.WINDOW_HEIGHT / 2,
	 * Constants.WINDOW_WIDTH * 5 / 8, Constants.WINDOW_HEIGHT / 2);
	 * g.setColor(Color.BLUE); g.fillOval(Constants.WINDOW_WIDTH / 4,
	 * Constants.WINDOW_HEIGHT / 2, 100, 100); }
	 */
}
