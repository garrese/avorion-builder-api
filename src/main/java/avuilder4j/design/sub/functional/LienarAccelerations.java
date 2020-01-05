package avuilder4j.design.sub.functional;

public class LienarAccelerations extends Linear<LienarAccelerations> {

	@Override
	public LienarAccelerations chain() {
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LienarAccelerations [speedingUp=");
		builder.append(speedingUp);
		builder.append(", braking=");
		builder.append(braking);
		builder.append("]");
		return builder.toString();
	}

}
