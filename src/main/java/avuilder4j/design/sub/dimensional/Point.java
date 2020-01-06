package avuilder4j.design.sub.dimensional;

import java.io.Serializable;

/**
 * Represents a point in a Cartesian coordinate system.
 */
public class Point extends DimensionalValuesInAxes<Point> implements Serializable {
	private static final long serialVersionUID = -8385581038463928346L;

	public Point() {
		super();
	}

	public Point(DimensionalValuesInAxes<Point> dimensional) {
		super(dimensional);
	}

	public Point(Number x, Number y, Number z) {
		set(x, y, z);
	}

	public void sumVector(Vector v) {
		// TODO
	}

	public void subVector(Vector v) {
		// TODO
	}

	public Point getCopy() { return new Point(this); }

}
