package avuilder4j.util.java;

import java.util.function.Supplier;

public class NullSafe {

	public static <T> T go(Supplier<T> supplier, T defaultValue) {
		try {
			return supplier.get();
		} catch (NullPointerException e) {
			return defaultValue;
		}
	}

	public static <T> T go(Supplier<T> supplier) {
		return go(supplier, null);
	}

	public static void go(Procedure procedure) {
		go(procedure, null);
	}

	public static void go(Procedure procedure, Procedure exceptionAction) {
		try {
			procedure.proceed();
		} catch (NullPointerException e) {
			if (exceptionAction != null)
				exceptionAction.proceed();
		}
	}

}
