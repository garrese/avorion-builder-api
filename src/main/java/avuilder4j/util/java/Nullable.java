package avuilder4j.util.java;

import java.util.function.Supplier;

public class Nullable {

	public static <T> T run(Supplier<T> supplier, T defaultValue) {
		try {
			return supplier.get();
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	public static <T> T run(Supplier<T> supplier) {
		return run(supplier, null);
	}

	public static void run(Runnable procedure) {
		run(procedure, null);
	}

	public static void run(Runnable runnable, Runnable exceptionAction) {
		try {
			runnable.run();
		} catch (NullPointerException e) {
			if (exceptionAction != null)
				exceptionAction.run();
		}
	}

}
