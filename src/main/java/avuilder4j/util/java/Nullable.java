package avuilder4j.util.java;

import java.util.function.Supplier;

public class Nullable {

	public static <T> T m(Supplier<T> supplier, T defaultValue) {
		try {
			return supplier.get();
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	public static <T> T m(Supplier<T> supplier) {
		return m(supplier, null);
	}

	public static void m(Runnable procedure) {
		m(procedure, null);
	}

	public static void m(Runnable runnable, Runnable exceptionAction) {
		try {
			runnable.run();
		} catch (NullPointerException e) {
			if (exceptionAction != null)
				exceptionAction.run();
		}
	}

	public static <T> T sum(T sum1, T sum2, Supplier<T> supplier) {
		return sum(sum1, sum2, supplier, null);
	}

	public static <T> T sum(T sum1, T sum2, Supplier<T> supplier, T defaultValue) {
		if (sum1 == null && sum2 == null)
			return defaultValue;
		if (sum1 == null)
			return sum2;
		if (sum2 == null)
			return sum1;
		try {
			return supplier.get();
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

}
