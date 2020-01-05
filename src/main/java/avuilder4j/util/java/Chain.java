package avuilder4j.util.java;

import java.util.function.Consumer;

public class Chain {

	public static <T> T m(T t, Consumer<T> c) {
		c.accept(t);
		return t;
	}
}
