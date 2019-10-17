package avuilder.core.entities.dimensional;

/**
 * Represents a point in a Cartesian coordinate system.
 */
public class Point {

	public Double x, y, z;

	public Point() {
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

}
