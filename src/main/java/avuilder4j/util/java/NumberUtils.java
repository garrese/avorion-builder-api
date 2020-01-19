package avuilder4j.util.java;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class NumberUtils {

	public static Double negate(Double x) {
		if (x != 0)
			return x * -1;
		else
			return x;
	}

	public static int randomBetween(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static boolean trueEach(int each) {
		if (randomBetween(1, each) == 1)
			return true;
		else
			return false;
	}

	public static String formatDecimal(Double v, int zeros) {
		return Nullable.m(() -> {
			String formula = "#";
			for (int i = 0; i < zeros; i++) {
				if (i == 0)
					formula += ".";
				formula += "0";
			}
			DecimalFormat f = new DecimalFormat(formula, new DecimalFormatSymbols(Locale.US));
			return f.format(v);
		});
	}

	public static Double round(Double v, int zeros) {
		return Nullable.m(() -> {
			Double rounding = 1d;
			for (int i = 0; i < zeros; i++) {
				rounding *= 10;
			}
			return Math.round(v * rounding) / rounding;
		});
	}

}
