package avuilder4j.design.sub.dimensional;

import java.util.Objects;

import avuilder4j.design.enums.Axis;
import avuilder4j.error.AvErrors;
import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.Nullable;
import avuilder4j.util.java.NumberUtils;

public abstract class V3Generic<T extends V3Generic<T>> implements Chainable<T> {

	public Double x, y, z;

	public V3Generic() {}

	public V3Generic(V3Generic<T> xyz) {
		copyV3(xyz);
	}

	public V3Generic(Number xyz) {
		setV3(xyz);
	}

	public V3Generic(Number x, Number y, Number z) {
		setV3(x, y, z);
	}

	@Override
	public abstract T chain();

	public T copyV3(V3Generic<?> b) {
		for (Axis axis : Axis.values()) {
			setV3Axis(axis, Nullable.m(() -> b.getV3Axis(axis)));
		}
		return chain();
	}

	public Double getV3Axis(Axis axis) {
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
		return Objects.hash(x, y, z);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof V3Generic))
			return false;
		V3Generic other = (V3Generic) obj;
		return Objects.equals(x, other.x) && Objects.equals(y, other.y) && Objects.equals(z, other.z);
	}

	public boolean isV3Defined() {
		if (x != null && y != null && z != null)
			return true;
		else
			return false;
	}

	public T negateXYZ() {
		for (Axis axis : Axis.values()) {
			Nullable.m(() -> setV3Axis(axis, NumberUtils.negate(getV3Axis(axis))));
		}
		return chain();
	}

	public T setV3(Number x, Number y, Number z) {
		setX(x);
		setY(y);
		setZ(z);
		return chain();
	}

	public T setV3(Number xyz) {
		return setV3(xyz, xyz, xyz);
	}

	public T setV3Axis(Axis axis, Number value) {
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

	public T subV3(V3Generic<?> b) {
		if (b != null) {
			for (Axis axis : Axis.values()) {
				setV3Axis(axis, Nullable.sub(getV3Axis(axis), b.getV3Axis(axis)));
			}
		}
		return chain();
	}

	public T sumV3(V3Generic<?> b) {
		if (b != null) {
			for (Axis axis : Axis.values()) {
				setV3Axis(axis, Nullable.sum(getV3Axis(axis), b.getV3Axis(axis)));
			}
		}
		return chain();
	}

	public T multiplyV3(Number factor) {
		for (Axis axis : Axis.values()) {
			setV3Axis(axis, Nullable.m(() -> getV3Axis(axis) * factor.doubleValue()));
		}
		return chain();
	}

	public T divideV3(Number factor) {
		for (Axis axis : Axis.values()) {
			setV3Axis(axis, Nullable.m(() -> getV3Axis(axis) / factor.doubleValue()));
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

	public Double getX() { return x; }

	public Double getY() { return y; }

	public Double getZ() { return z; }

	public T setX(Number x) {
		this.x = Nullable.m(() -> x.doubleValue());
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

}
