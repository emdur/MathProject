package app;

public class Lanchester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int g = 500;
		int h = 600;
		double s = 4; // Feuerkraft von g
		double r = 8; // Feuerkraft von h
		Population g2 = new Population(500, 8);
		Population h2 = new Population(600, 8);

		Population.wievieleüberleben(g2, h2);

	}

}
