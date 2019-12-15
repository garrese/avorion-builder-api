package avuilder4j.data.beans;

import java.util.Objects;

public class BeanShape {

	protected String name;

	protected Double cuboidFilledIn;

	protected String symmetric;

	public BeanShape(String name, Double cuboidFilledIn, String symmetric) {
		this.name = name;
		this.cuboidFilledIn = cuboidFilledIn;
		this.symmetric = symmetric;
	}

	public String getName() { return name; }

	public Double getCuboidFilledIn() { return cuboidFilledIn; }

	public String getSymmetric() { return symmetric; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shape [");
		builder.append("name=");
		builder.append(name);
		builder.append(", volumeMod=");
		builder.append(cuboidFilledIn);
		builder.append(", symmetric=");
		builder.append(symmetric);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cuboidFilledIn, name, symmetric);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BeanShape))
			return false;
		BeanShape other = (BeanShape) obj;
		return Objects.equals(cuboidFilledIn, other.cuboidFilledIn) && Objects.equals(name, other.name)
				&& Objects.equals(symmetric, other.symmetric);
	}

}
