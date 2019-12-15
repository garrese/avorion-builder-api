package avuilder4j.util.java;

import java.util.function.Supplier;

public class NullSafe {

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

	public static void run(Procedure procedure) {
		run(procedure, null);
	}

	public static void run(Procedure procedure, Procedure exceptionAction) {
		try {
			procedure.run();
		} catch (NullPointerException e) {
			if (exceptionAction != null)
				exceptionAction.run();
		}
	}

}
