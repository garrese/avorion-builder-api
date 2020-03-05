package avuilder4j.design.sub.dimensional;

import java.util.Objects;

import avuilder4j.design.enums.Axis;
import avuilder4j.error.AvErrors;
import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.Nullable;
import avuilder4j.util.java.NumberUtils;

public abstract class Xyz<T extends Xyz<T>> implements Chainable<T> {

	public Double x, y, z;

	public Xyz() {}

	public Xyz(Number xyz) {
		setXyz(xyz);
	}

	public Xyz(Number x, Number y, Number z) {
		setXyz(x, y, z);
	}

	public Xyz(Xyz<?> xyz) {
		copyXyz(xyz);
	}

	@Override
	public abstract T chain();

	public T copyXyz(Xyz<?> xyz) {
		for (Axis axis : Axis.values()) {
			setXyzAxis(axis, Nullable.m(() -> xyz.getXyzAxis(axis)));
		}
		return chain();
	}

	public T divideXyz(Number factor) {
		for (Axis axis : Axis.values()) {
			setXyzAxis(axis, getXyzAxis(axis) / factor.doubleValue());
		}
		return chain();
	}

	public T divideXyz(Xyz<?> xyz) {
		for (Axis axis : Axis.values()) {
			setXyzAxis(axis, getXyzAxis(axis) / xyz.getXyzAxis(axis));
		}
		return chain();
	}

	@SuppressWarnings("rawtypes")
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

	public Double getX() { return x; }

	public Double getXyzAxis(Axis axis) {
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

	public Double getY() { return y; }

	public Double getZ() { return z; }

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

	public T multiplyXyz(Number factor) {
		for (Axis axis : Axis.values()) {
			setXyzAxis(axis, Nullable.m(() -> getXyzAxis(axis) * factor.doubleValue()));
		}
		return chain();
	}

	public T multiplyXyz(Xyz<?> xyz) {
		for (Axis axis : Axis.values()) {
			setXyzAxis(axis, getXyzAxis(axis) * xyz.getXyzAxis(axis));
		}
		return chain();
	}

	public T negateXyz() {
		for (Axis axis : Axis.values()) {
			setXyzAxis(axis, NumberUtils.negate(getXyzAxis(axis)));
		}
		return chain();
	}

	public T setX(Number x) {
		this.x = Nullable.m(() -> x.doubleValue());
		return chain();
	}

	public T setXyz(Number xyz) {
		return setXyz(xyz, xyz, xyz);
	}

	public T setXyz(Number x, Number y, Number z) {
		setX(x);
		setY(y);
		setZ(z);
		return chain();
	}

	public T setXyzAxis(Axis axis, Number value) {
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

	public T setY(Number y) {
		this.y = Nullable.m(() -> y.doubleValue());
		return chain();
	}

	public T setZ(Number z) {
		this.z = Nullable.m(() -> z.doubleValue());
		return chain();
	}

	public T subXyz(Xyz<?> b) {
		for (Axis axis : Axis.values()) {
			setXyzAxis(axis, getXyzAxis(axis) - b.getXyzAxis(axis));
		}
		return chain();
	}

	public T sumXyz(Xyz<?> b) {
		for (Axis axis : Axis.values()) {
			setXyzAxis(axis, getXyzAxis(axis) + b.getXyzAxis(axis));
		}
		return chain();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName());
		builder.append(" [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", z=");
		builder.append(z);
		builder.append("]");
		return builder.toString();
	}

}
