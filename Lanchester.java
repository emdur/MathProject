package app;

public class Lanchester {

	public static void main(String[] args) {

		// insert (Anfangsgr��e, Feuerkraft)
		Population g = new Population(300, 3);
		Population h = new Population(300, 3);

		Population.wieviele�berleben(g, h);
		System.out.println(Population.populationt(h, g, 0.01));
		System.out.println(Population.populationt(h, g, Population.wannistderkampfentschieden(h, g)));

	}
}