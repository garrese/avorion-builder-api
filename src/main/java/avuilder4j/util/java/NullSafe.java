package avuilder4j.util.java;

import java.util.function.Supplier;

public class NullSafe {

	public static <T> T get(Supplier<T> supplier, T defaultValue) {
		try {
			return supplier.get();
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	public static <T> T get(Supplier<T> supplier) {
		return get(supplier, null);
	}

	public static void get(Procedure procedure) {
		get(procedure, null);
	}

	public static void get(Procedure procedure, Procedure exceptionAction) {
		try {
			procedure.proceed();
		} catch (NullPointerException e) {
			if (exceptionAction != null)
				exceptionAction.proceed();
		}
	}

}
