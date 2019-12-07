package avuilder4j.design.sub.dimensional;

import java.util.Objects;

import avuilder4j.design.enums.Axis;
import avuilder4j.error.AvErrors;
import avuilder4j.util.java.ReturnThis;
import avuilder4j.util.java.Copyable;
import avuilder4j.util.java.NullSafe;

@SuppressWarnings("rawtypes")
public abstract class Xyz<T extends Xyz> implements Copyable<T>, ReturnThis<T> {

	public Double x, y, z;

	public Xyz() {}

	public Xyz(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Xyz(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public abstract T returnThis();

	public T copyXyz(Xyz b) {
		if (b != null) {
			for (Axis axis : Axis.values()) {
				setXyzByAxis(axis, b.getXyzByAxis(axis));
			}
		}
		return returnThis();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Xyz))
			return false;
		Xyz other = (Xyz) obj;
		return Objects.equals(x, other.x) && Objects.equals(y, other.y) && Objects.equals(z, other.z);
	}

	@Override
	public abstract T getCopy();

	public Double getXyzByAxis(Axis axis) {
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

	public T getXyzNegation() {
		T r = getCopy();
		r.negateXYZ();
		return r;
	}

	public T getXyzSub(Xyz b) {
		return (T) getCopy().subXyz(b);
	}

	public T getXyzSum(Xyz b) {
		return (T) getCopy().sumXyz(b);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	public boolean isXyzDefined() {
		if (x != null && y != null && z != null)
			return true;
		else
			return false;
	}

	public T negateXYZ() {
		for (Axis axis : Axis.values()) {
			NullSafe.get(() -> setXyzByAxis(axis, getXyzByAxis(axis) * -1));
		}
		return returnThis();
	}

	public T setXyz(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return returnThis();
	}

	public T setXyzByAxis(Axis axis, Double value) {
		switch (axis) {
		case X:
			setX(x);
			break;
		case Y:
			setY(y);
			break;
		case Z:
			setZ(z);
			break;
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
		return returnThis();
	}

	public T subXyz(Xyz b) {
		for (Axis axis : Axis.values()) {
			NullSafe.get(() -> setXyzByAxis(axis, getXyzByAxis(axis) - b.getXyzByAxis(axis)));
		}
		return returnThis();
	}

	public T sumXyz(Xyz b) {
		for (Axis axis : Axis.values()) {
			NullSafe.get(() -> setXyzByAxis(axis, getXyzByAxis(axis) + b.getXyzByAxis(axis)));
		}
		return returnThis();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", z=");
		builder.append(z);
		builder.append("]");
		return builder.toString();
	}

	public Double getX() { return x; }

	public Double getY() { return y; }

	public Double getZ() { return z; }

	public T setX(Double x) {
		this.x = x;
		return returnThis();
	}

	public T setY(Double y) {
		this.y = y;
		return returnThis();
	}

	public T setZ(Double z) {
		this.z = z;
		return returnThis();
	}

}
