package app;

public class Population {

	public int size;
	public double fire;

	public Population(int size, double fire) {
		this.size = size;
		this.fire = fire;
	};

	// Population p1 zum Zeitpunkt t
	// Populationen zum Zeitpunkt null bzw t: p1n, p2n, p1t;
	// Feuerkraft der Populationen 1 und 2: f1, f2
	// Beispiel: G(0), H(0), s (f von g), r (f von h); Ergebnis: G(t)
	private int populationt(Population p1n, Population p2n, int t) {

		double k = Math.sqrt(p2n.fire * p1n.fire);
		return (int) (p1n.size * Math.cosh(k * t) - (p2n.fire / k) * p2n.size * Math.sinh(k * t));
		/*
		 * return (int) (Math.sqrt((p1n - (f2 / k) * p2n) / 2 * Math.exp(k * t)) +
		 * Math.sqrt((p1n + (f2 / k) * p2n) / 2 * Math.exp(-k * t)));
		 */
	}

	// Veränderungsrate der Population p1 (p1'(t)), 1. Ableitung;
	// f = Feuerkraft der jeweils anderen Population
	private double populationv(Population p1n, Population p2n, int t) {

		return -p1n.fire * populationt(p2n, p1n, t);
	}

	// Arctanh
	private static double atanh(double x) {
		return (Math.log((1 + x) / (1 - x))) / 2;
	}

	// Bestimmung von tdeath - Wann ist eine (oder beide) Population(en) = 0?
	public static double wannistderkampfentschieden(Population p1n, Population p2n) {
		double x = (p2n.size / p1n.size) * (Math.sqrt(p1n.fire * p2n.fire) / p1n.fire);
		double tdeath = 1 / Math.sqrt(p1n.fire * p2n.fire) * atanh(x);
		tdeath = tdeath * (180 / Math.PI); // Umrechnung Rad --> DEG
		return tdeath;

	}

	// Wer gewinnt?
	public static void wievieleüberleben(Population p1n, Population p2n) {
		// Erhaltungsgröße L
		double l = (int) (p1n.fire * Math.pow(p1n.size, 2) - p2n.fire * Math.pow(p2n.size, 2));
		if (l == 0) {
			System.out.println("Alle sind tot. :-(");
		}
		if (l < 0) {
			double tdeath = wannistderkampfentschieden(p2n, p1n);
			int überlebende = (int) Math.sqrt(l / -p2n.fire);
			System.out.println("Population 2 (H) gewinnt mit " + überlebende
					+ " Überlebenden. Population 1 (G) ist zum Zeitpunkt " + tdeath + " ausgelöscht.");
		}
		if (l > 0) {
			double tdeath = wannistderkampfentschieden(p1n, p2n);
			int überlebende = (int) Math.sqrt(l / p1n.fire);
			System.out.println("Population 1 (G) gewinnt mit " + überlebende
					+ " Überlebenden. Population 2 (H) ist zum Zeitpunkt " + tdeath + " ausgelöscht.");
		}

	}

}
