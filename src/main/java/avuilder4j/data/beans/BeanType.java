package avuilder4j.data.beans;

public class BeanType {

	protected Integer index;
	protected Integer typeModelIndex;
	protected Integer shapeIdx;

	public BeanType(Integer index, Integer typeModelIndex, Integer shape) {
		this.index = index;
		this.typeModelIndex = typeModelIndex;
		this.shapeIdx = shape;
	}

	public Integer getIndex() { return index; }

	public Integer getTypeModelIndex() { return typeModelIndex; }

	public Integer getShapeIdx() { return shapeIdx; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Type [index=");
		builder.append(index);
		builder.append(", typeModelIndex=");
		builder.append(typeModelIndex);
		builder.append(", shapeIndex=");
		builder.append(shapeIdx);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((shapeIdx == null) ? 0 : shapeIdx.hashCode());
		result = prime * result + ((typeModelIndex == null) ? 0 : typeModelIndex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeanType other = (BeanType) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (shapeIdx == null) {
			if (other.shapeIdx != null)
				return false;
		} else if (!shapeIdx.equals(other.shapeIdx))
			return false;
		if (typeModelIndex == null) {
			if (other.typeModelIndex != null)
				return false;
		} else if (!typeModelIndex.equals(other.typeModelIndex))
			return false;
		return true;
	}

}
