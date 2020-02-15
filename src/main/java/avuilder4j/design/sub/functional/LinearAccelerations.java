package avuilder4j.design.sub.functional;

import avuilder4j.design.sub.dimensional.Vector;
import avuilder4j.util.java.Nullable;

public class LinearAccelerations extends Linear<LinearAccelerations> {

	public LinearAccelerations() {
		super();
	}

	public LinearAccelerations(LinearAccelerations linear) {
		super(linear);
	}

	public LinearAccelerations(LinearForces forces, Number mass) {
		calculate(forces, mass);
	}

	public void calculate(LinearForces forces, Number mass) {
		setBraking(Nullable.m(() -> new Vector(forces.getBraking()).divideV3(mass)));
		setSpeedingUp(Nullable.m(() -> new Vector(forces.getSpeedingUp()).divideV3(mass)));
	}

}
