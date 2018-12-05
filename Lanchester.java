package app;

public class Lanchester {

	public static void main(String[] args) {

		// insert (Anfangsgröße, Feuerkraft)
		Population g = new Population(500, 8);
		Population h = new Population(600, 2);

		Population.wievieleüberleben(g, h);

	}

}
