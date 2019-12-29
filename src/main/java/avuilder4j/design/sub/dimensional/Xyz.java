package avuilder4j.design.sub.dimensional;

import java.util.Objects;

import avuilder4j.design.enums.Axis;
import avuilder4j.error.AvErrors;
import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.Copyable;
import avuilder4j.util.java.Nullable;

@SuppressWarnings("rawtypes")
public abstract class Xyz<T extends Xyz> implements Copyable<T>, Chainable<T> {

	public Double x, y, z;

	public Xyz() {}

	public Xyz(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public abstract T chain();

	public T copyXyz(Xyz b) {
		if (b != null) {
			for (Axis axis : Axis.values()) {
				setXyzByAxis(axis, b.getXyzByAxis(axis));
			}
		}
		return chain();
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

	public T getNegationCopy() {
		T r = getCopy();
		r.negateXYZ();
		return r;
	}

	public T getSubCopy(Xyz b) {
		return (T) getCopy().subXyz(b);
	}

	public T getSumCopy(Xyz b) {
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
			Nullable.m(() -> setXyzByAxis(axis, getXyzByAxis(axis) * -1));
		}
		return chain();
	}

	public T set(Double xyz) {
		setXyz(xyz, xyz, xyz);
		return chain();
	}

	public T setXyz(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return chain();
	}

	public T setXyz(Double xyz) {
		return setXyz(xyz, xyz, xyz);
	}

	public T setXyzByAxis(Axis axis, Double value) {
		switch (axis) {
		case X:
			setX(value);
			break;
		case Y:
			setY(value);
			break;
		case Z:
			setZ(value);
			break;
		default:
			throw new IllegalArgumentException(AvErrors.AXIS_NOT_RECOGNIZED);
		}
		return chain();
	}

	public T subXyz(Xyz b) {
		if (b != null) {
			sumXyz(b.getNegationCopy());
		}
		return chain();
	}

	public T sumXyz(Xyz b) {
		if (b != null) {
			for (Axis axis : Axis.values()) {
				Double thisValue = getXyzByAxis(axis);
				Double bValue = b.getXyzByAxis(axis);
				setXyzByAxis(axis, Nullable.sum(thisValue, bValue, () -> thisValue + bValue));
			}
		}
		return chain();
	}

	public T multiply(Double factor) {
		for (Axis axis : Axis.values()) {
			Nullable.m(() -> setXyzByAxis(axis, getXyzByAxis(axis) * factor));
		}
		return chain();
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
		return chain();
	}

	public T setY(Double y) {
		this.y = y;
		return chain();
	}

	public T setZ(Double z) {
		this.z = z;
		return chain();
	}

}
