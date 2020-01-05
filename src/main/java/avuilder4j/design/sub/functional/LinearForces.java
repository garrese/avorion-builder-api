package avuilder4j.design.sub.functional;

public class LinearForces extends Linear<LinearForces> {

	@Override
	public LinearForces chain() {
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LinearForces [speedingUp=");
		builder.append(speedingUp);
		builder.append(", braking=");
		builder.append(braking);
		builder.append("]");
		return builder.toString();
	}

}
