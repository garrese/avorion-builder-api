package avuilder4j.values.loaders;

import avuilder4j.dtos.Shape;

public class Shapes extends EntityMap<Shape> {

	public static final int FILLED = 0;
	public static final int EDGE = 1;
	public static final int CORNER_1 = 2;
	public static final int CORNER_2 = 3;
	public static final int CORNER_3 = 4;
	public static final int TWISTED_CORNER_1 = 5;
	public static final int TWISTED_CORNER_2 = 6;

	public Shapes() {
		add(new Shape(FILLED, 1.0));
		add(new Shape(EDGE, 0.5));
		add(new Shape(CORNER_1, (1.0 + 2.0 / 3.0) / 10.0));
		add(new Shape(CORNER_2, 1.0 - ((1.0 + 2.0 / 3.0) / 10.0)));
		add(new Shape(CORNER_3, 1.0 / 3.0));
		add(new Shape(TWISTED_CORNER_1, 1.0 / 4.0));
		add(new Shape(TWISTED_CORNER_2, 1.0 / 4.0));
	}

}
