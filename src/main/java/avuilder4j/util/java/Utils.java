package avuilder4j.util.java;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Utils {

	public static Double round(Double v, int zeros) {
		return NullSafe.go(() -> {
			Double rounding = 1d;
			for (int i = 0; i < zeros; i++) {
				rounding *= 10;
			}
			return Math.round(v * rounding) / rounding;
		});
	}

	public static String formatDecimal(Double v, int zeros) {
		return NullSafe.go(() -> {
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

}
