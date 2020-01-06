package avuilder4j.design.sub.dimensional;

import java.io.Serializable;

public class Vector extends DimensionalValuesInAxes<Vector> implements Serializable {
	private static final long serialVersionUID = 130413434793924395L;

	public Vector() {
		super();
	}

	public Vector(DimensionalValuesInAxes<Vector> dimensional) {
		super(dimensional);
	}

	public Vector(Double x, Double y, Double z) {
		set(x, y, z);
	}

	public static Vector pointDiff(Point a, Point b) {
		Point copy = a.getCopy();
		return new Vector().copyDimensions(copy.sub(b));
	}

	public static Vector pointSum(Point a, Point b) {
		Point copy = a.getCopy();
		return new Vector().copyDimensions(copy.sum(b));
	}

	public static Vector vectorDiff(Vector a, Vector b) {
		return new Vector().copyXyz(a.getSubCopy(b));
	}

	public static Vector vectorSum(Vector a, Vector b) {
		return new Vector().copyXyz(a.getSumCopy(b));
	}

	public Vector getCopy() { return new Vector(this); }

}
