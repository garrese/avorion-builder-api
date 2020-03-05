package avuilder4j.design.sub.functional;

import avuilder4j.design.sub.dimensional.Xyz;

public class RotationForces extends Xyz<RotationForces> {

	public RotationForces() {
		super();
	}

	public RotationForces(Number x, Number y, Number z) {
		super(x, y, z);
	}

	public RotationForces(Number xyz) {
		super(xyz);
	}

	public RotationForces(Xyz<?> xyz) {
		super(xyz);
	}

	@Override
	public RotationForces chain() {
		return this;
	}

}
