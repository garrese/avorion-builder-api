package avuilder4j.design.sub.functional;

import avuilder4j.design.sub.dimensional.Xyz;

public class RotationAccelerations extends Xyz<RotationAccelerations> {

	public RotationAccelerations() {
		super();
	}

	public RotationAccelerations(Number x, Number y, Number z) {
		super(x, y, z);
	}

	public RotationAccelerations(Number xyz) {
		super(xyz);
	}

	public RotationAccelerations(Xyz<?> xyz) {
		super(xyz);
	}

	@Override
	public RotationAccelerations chain() {
		return this;
	}
}
