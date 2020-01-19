package avuilder4j.design.sub.functional;

import avuilder4j.design.sub.dimensional.Vector;
import avuilder4j.util.java.Nullable;

public abstract class Linear<T extends Linear<T>> {

	protected Vector speedingUp = new Vector().setXyz(0d);
	protected Vector braking = new Vector().setXyz(0d);

	public Linear() {}

	public Linear(Linear<T> linear) {
		setSpeedingUp(new Vector(Nullable.m(() -> linear.getSpeedingUp())));
		setBraking(new Vector(Nullable.m(() -> linear.getBraking())));
	}

	public Vector getSpeedingUp() { return speedingUp; }

	public T setSpeedingUp(Vector speedingUp) {
		this.speedingUp = speedingUp;
		return chain();
	}

	public Vector getBraking() { return braking; }

	public T setBraking(Vector braking) {
		this.braking = braking;
		return chain();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName()).append(" [speedingUp=");
		builder.append(speedingUp);
		builder.append(", braking=");
		builder.append(braking);
		builder.append("]");
		return builder.toString();
	}

	public T sumLinear(T other) {
		Nullable.m(() -> getSpeedingUp().sumXyz(other.getSpeedingUp()));
		Nullable.m(() -> getBraking().sumXyz(other.getBraking()));
		return chain();
	}

	public T subLinear(T other) {
		Nullable.m(() -> getSpeedingUp().subXyz(other.getSpeedingUp()));
		Nullable.m(() -> getBraking().subXyz(other.getBraking()));
		return chain();
	}

	public T chain() {
		return (T) this;
	}

}
