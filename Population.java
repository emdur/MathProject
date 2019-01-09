package app;

public class Population {

	public double size;
	public double fire;

	public Population(double size, double fire) {
		this.size = size;
		this.fire = fire;
	};

	// Population p1 zum Zeitpunkt t
	// Beispiel: G(0), H(0), s (fire von g), r (fire von h); Ergebnis: G(t)
	static int populationt(Population p1, Population p2, double t) {

		double k = Math.sqrt(p2.fire * p1.fire);

		return (int) (p1.size * Math.cosh(k * t) - (p2.fire / k) * p2.size * Math.sinh(k * t));

	}

	// Veränderungsrate der Population p1 (p1'(t)), 1. Ableitung;
	static int populationv(Population p1, Population p2, double t) {

		return (int) (-p2.fire * populationt(p2, p1, t));
	}

	// Arctanh
	private static double atanh(double x) {
		return (Math.log((1 + x) / (1 - x))) / 2;
	}

	// Bestimmung von tdeath - Wann ist eine (oder beide) Population(en) = 0?
	public static double wannistderkampfentschieden(Population p1, Population p2) {

		double x = p2.size / p1.size * Math.sqrt(p1.fire * p2.fire) / p1.fire;
		double tdeath = atanh(x) / Math.sqrt(p1.fire * p2.fire);

		return tdeath;
	}

	public static double infinityCase(Population p1, Population p2) {

		double x = p2.size / p1.size * Math.sqrt(p1.fire * p2.fire) / p1.fire - 0.5;
		double tdeath = atanh(x) / Math.sqrt(p1.fire * p2.fire);

		return tdeath;

	}

	// Wer gewinnt?
	public static void wievieleüberleben(Population p1, Population p2) {
		// Erhaltungsgröße L
		double l = (int) (p1.fire * Math.pow(p1.size, 2) - p2.fire * Math.pow(p2.size, 2));

		if (l == 0) {
			double tdeath = infinityCase(p2, p1);
			System.out.println("Alle sind tot. :-( Endzeitpunkt: " + tdeath);
		}
		if (l < 0) {
			double tdeath = wannistderkampfentschieden(p2, p1);
			int überlebende = (int) Math.sqrt(l / -p2.fire);
			System.out.println("Population 2 (H) gewinnt mit " + überlebende
					+ " Überlebenden. Population 1 (G) ist zum Zeitpunkt " + tdeath + " ausgelöscht.");
		}
		if (l > 0) {
			double tdeath = wannistderkampfentschieden(p1, p2);
			int überlebende = (int) Math.sqrt(l / p1.fire);
			System.out.println("Population 1 (G) gewinnt mit " + überlebende
					+ " Überlebenden. Population 2 (H) ist zum Zeitpunkt " + tdeath + " ausgelöscht.");
		}

	}

}
