package app;

public class LanchesterMethods {

	// Population p1 zum Zeitpunkt t
	// Populationen zum Zeitpunkt null bzw t: p1n, p2n, p1t;
	// Feuerkraft der Populationen 1 und 2: f1, f2
	// Beispiel: G(0), H(0), s (f von g), r (f von h); Ergebnis: G(t)
	private int populationt(int p1n, int p2n, double f1, double f2, int t) {

		double k = Math.sqrt(f2 * f1);
		return (int) (p1n * Math.cosh(k * t) - (f2 / k) * p2n * Math.sinh(k * t));
		/*
		 * return (int) (Math.sqrt((p1n - (f2 / k) * p2n) / 2 * Math.exp(k * t)) +
		 * Math.sqrt((p1n + (f2 / k) * p2n) / 2 * Math.exp(-k * t)));
		 */
	}

	// Veränderungsrate der Population p1 (p1'(t)), 1. Ableitung;
	// f = Feuerkraft der jeweils anderen Population
	private double populationv(int p1n, int p2n, double f1, double f2, int t) {

		return -f1 * populationt(p2n, p1n, f1, f2, t);
	}

	// Arctanh
	private static double atanh(double x) {
		return (Math.log((1 + x) / (1 - x))) / 2;
	}

	// Bestimmung von tdeath - Wann ist eine (oder beide) Population(en) = 0?
	public static double wannistderkampfentschieden(int p1n, int p2n, double f1, double f2) {
		double x = (p2n / p1n) * (Math.sqrt(f1 * f2) / f1);
		double tdeath = 1 / Math.sqrt(f1 * f2) * atanh(x);
		tdeath = tdeath * (180 / Math.PI); // Umrechnung Rad --> DEG
		return tdeath;

	}

	// Wer gewinnt?
	public static void wievieleüberleben(int p1n, int p2n, double f1, double f2) {
		// Erhaltungsgröße L
		double l = (int) (f1 * Math.pow(p1n, 2) - f2 * Math.pow(p2n, 2));
		if (l == 0) {
			System.out.println("Alle sind tot. :-(");
		}
		if (l < 0) {
			double tdeath = wannistderkampfentschieden(p2n, p1n, f2, f1);
			int überlebende = (int) Math.sqrt(l / -f2);
			System.out.println("Population 2 (H) gewinnt mit " + überlebende
					+ " Überlebenden. Population 1 (G) ist zum Zeitpunkt " + tdeath + " ausgelöscht.");
		}
		if (l > 0) {
			double tdeath = wannistderkampfentschieden(p1n, p2n, f1, f2);
			int überlebende = (int) Math.sqrt(l / f1);
			System.out.println("Population 1 (G) gewinnt mit " + überlebende
					+ " Überlebenden. Population 2 (H) ist zum Zeitpunkt " + tdeath + " ausgelöscht.");
		}

	}

}
