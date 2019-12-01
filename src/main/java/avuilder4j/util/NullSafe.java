package avuilder4j.util;

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

}
