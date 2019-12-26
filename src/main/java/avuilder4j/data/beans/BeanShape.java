package avuilder4j.data.beans;

import java.util.Objects;

public class BeanShape {

	protected Integer index;
	protected String name;

	protected Double cuboidFilledIn;

	protected Integer symmetricIdx;

	public BeanShape(Integer index, String name, Double cuboidFilledIn, Integer symmetricIdx) {
		this.index = index;
		this.name = name;
		this.cuboidFilledIn = cuboidFilledIn;
		this.symmetricIdx = symmetricIdx;
	}

	public Integer getIndex() { return index; }

	public String getName() { return name; }

	public Double getCuboidFilledIn() { return cuboidFilledIn; }

	public Integer getSymmetricIndex() { return symmetricIdx; }

	@Override
	public int hashCode() {
		return Objects.hash(cuboidFilledIn, index, name, symmetricIdx);
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
		return Objects.equals(cuboidFilledIn, other.cuboidFilledIn) && Objects.equals(index, other.index)
				&& Objects.equals(name, other.name) && Objects.equals(symmetricIdx, other.symmetricIdx);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BeanShape [index=");
		builder.append(index);
		builder.append(", name=");
		builder.append(name);
		builder.append(", cuboidFilledIn=");
		builder.append(cuboidFilledIn);
		builder.append(", symmetricIdx=");
		builder.append(symmetricIdx);
		builder.append("]");
		return builder.toString();
	}

}
