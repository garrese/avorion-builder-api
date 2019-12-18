package avuilder4j.util.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Utils {

	public static Double round(Double v, int zeros) {
		return Nullable.run(() -> {
			Double rounding = 1d;
			for (int i = 0; i < zeros; i++) {
				rounding *= 10;
			}
			return Math.round(v * rounding) / rounding;
		});
	}

	public static String formatDecimal(Double v, int zeros) {
		return Nullable.run(() -> {
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

	public static long getBytesFromList(List<? extends Object> list) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(baos);
		out.writeObject(list);
		out.close();
		return baos.toByteArray().length;
	}

	public static int randomBetween(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static boolean oneRandomEach(int onePer) {
		if (randomBetween(1, onePer) == 1)
			return true;
		else
			return false;
	}

}
