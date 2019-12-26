package avuilder4j.design.sub.functional;

import avuilder4j.design.sub.dimensional.Vector;
import avuilder4j.util.java.Chainable;

public class PropulsionForces implements Chainable<PropulsionForces> {

	protected Vector acceleration = new Vector().setXyz(0d);
	protected Vector deceleration = new Vector().setXyz(0d);

	public Vector getAcceleration() { return acceleration; }

	public PropulsionForces setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
		return chain();
	}

	public Vector getDeceleration() { return deceleration; }

	public PropulsionForces setDeceleration(Vector deceleration) {
		this.deceleration = deceleration;
		return chain();
	}

	@Override
	public PropulsionForces chain() {
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PropulsionForces [acceleration=");
		builder.append(acceleration);
		builder.append(", deceleration=");
		builder.append(deceleration);
		builder.append("]");
		return builder.toString();
	}

}
