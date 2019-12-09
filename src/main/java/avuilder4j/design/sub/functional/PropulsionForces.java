package avuilder4j.design.sub.functional;

import avuilder4j.design.sub.dimensional.Vector;
import avuilder4j.util.java.ReturnThis;

public class PropulsionForces implements ReturnThis<PropulsionForces> {

	protected Vector acceleration = new Vector().setXyz(0d);
	protected Vector deceleration = new Vector().setXyz(0d);

	public Vector getAcceleration() { return acceleration; }

	public PropulsionForces setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
		return returnThis();
	}

	public Vector getDeceleration() { return deceleration; }

	public PropulsionForces setDeceleration(Vector deceleration) {
		this.deceleration = deceleration;
		return returnThis();
	}

	@Override
	public PropulsionForces returnThis() {
		return this;
	}

}
