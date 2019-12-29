package avuilder4j.design.sub.functional;

public class PropulsionForces extends SpeedingUpBraking<PropulsionForces> {

	@Override
	public PropulsionForces chain() {
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PropulsionForces [speedingUp=");
		builder.append(speedingUp);
		builder.append(", braking=");
		builder.append(braking);
		builder.append("]");
		return builder.toString();
	}

}
