package avuilder4j.util.java;

import java.util.function.Consumer;

public class RunAndReturn {

	public static <T> T run(T t, Consumer<T> c) {
		c.accept(t);
		return t;
	}
}
