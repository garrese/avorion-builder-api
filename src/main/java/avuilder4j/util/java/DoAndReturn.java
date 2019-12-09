package avuilder4j.util.java;

import java.util.function.Consumer;

public class DoAndReturn {

	public static <T> T go(T t, Consumer<T> c) {
		c.accept(t);
		return t;
	}
}
