package avuilder4j.design.sub.functional;

import avuilder4j.design.sub.dimensional.Xyz;

public class RotationForces extends Xyz<RotationForces> {

	@Override
	public RotationForces returnThis() {
		return this;
	}

	@Override
	public RotationForces getCopy() { return new RotationForces().copyXyz(this); }

}
