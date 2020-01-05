package avuilder4j.design.sub.functional;

import avuilder4j.design.sub.dimensional.Vector;
import avuilder4j.util.java.Chainable;
import avuilder4j.util.java.Nullable;

public abstract class Linear<T extends Linear<T>> implements Chainable<T> {

	protected Vector speedingUp = new Vector().setXyz(0d);
	protected Vector braking = new Vector().setXyz(0d);

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
	public abstract T chain();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[speedingUp=");
		builder.append(speedingUp);
		builder.append(", braking=");
		builder.append(braking);
		builder.append("]");
		return builder.toString();
	}

	public T sum(T other) {
		if (other != null) {
			Vector speedingUp = Nullable.m(() -> (Vector) null);
			setSpeedingUp(speedingUp);
		}
		return chain();
	}

}
