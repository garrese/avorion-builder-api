package avuilder4j.stats.values;

import avuilder4j.stats.dtos.Shape;

public class Shapes {

	public static final Shape FILLED = new Shape(0, 1);
	public static final Shape EDGE = new Shape(1, 0.5);
	public static final Shape CORNER_1 = new Shape(2, (1 + 2 / 3) / 10);
	public static final Shape CORNER_2 = new Shape(3, 1 - ((1 + 2 / 3) / 10));
	public static final Shape CORNER_3 = new Shape(4, 1 / 3);
	public static final Shape TWISTED_CORNER_1 = new Shape(5, 1 / 4);
	public static final Shape TWISTED_CORNER_2 = new Shape(6, 1 / 4);

}
