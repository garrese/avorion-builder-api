package avuilder4j.design.sub.functional;

public class PropulsionAccelerations extends SpeedingUpBraking<PropulsionAccelerations> {

	@Override
	public PropulsionAccelerations chain() {
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PropulsionAccelerations [speedingUp=");
		builder.append(speedingUp);
		builder.append(", braking=");
		builder.append(braking);
		builder.append("]");
		return builder.toString();
	}

}
