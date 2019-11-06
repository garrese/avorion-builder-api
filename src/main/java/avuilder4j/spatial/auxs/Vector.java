package avuilder4j.spatial.auxs;

import java.io.Serializable;

import avuilder4j.error.AvErrors;
import avuilder4j.error.Avuilder4jRuntimeException;
import avuilder4j.spatial.enums.Axis;

public class Vector implements Serializable {
	private static final long serialVersionUID = 130413434793924395L;

	public Double x, y, z;

	public static Vector deepCopy(Vector p) {
		return new Vector(p.x, p.y, p.z);
	}

	public static Vector pointDiff(Point a, Point b) {
		a.validate();
		b.validate();
		return new Vector(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public static Vector pointSum(Point a, Point b) {
		a.validate();
		b.validate();
		return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public static Vector vectorDiff(Vector a, Vector b) {
		a.validate();
		b.validate();
		return new Vector(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public static Vector vectorSum(Vector a, Vector b) {
		a.validate();
		b.validate();
		return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public Vector() {
		super();
	}

	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		if (z == null) {
			if (other.z != null)
				return false;
		} else if (!z.equals(other.z))
			return false;
		return true;
	}

	public Double getAxisComponent(Axis axis) {
		switch (axis) {
		case X:
			return x;
		case Y:
			return y;
		case Z:
			return z;
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		result = prime * result + ((z == null) ? 0 : z.hashCode());
		return result;
	}

	public boolean isDefined() {
		if (x != null && y != null && z != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	public void validate() {
		if (!isDefined())
			throw new Avuilder4jRuntimeException(AvErrors.NOT_SUFFICIENTLY_DEFINED);
	}

}
