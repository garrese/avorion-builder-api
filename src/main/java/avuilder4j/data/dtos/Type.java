package avuilder4j.data.dtos;

public class Type {

	protected Integer index;
	protected Integer typeModelIndex;
	protected Integer shapeIndex;

	public Type(Integer index, Integer typeModelIndex, Integer shapeIndex) {
		this.index = index;
		this.typeModelIndex = typeModelIndex;
		this.shapeIndex = shapeIndex;
	}

	public Integer getIndex() { return index; }

	public Integer getTypeModelIndex() { return typeModelIndex; }

	public Integer getShapeIndex() { return shapeIndex; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Type [index=");
		builder.append(index);
		builder.append(", typeModelIndex=");
		builder.append(typeModelIndex);
		builder.append(", shapeIndex=");
		builder.append(shapeIndex);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((shapeIndex == null) ? 0 : shapeIndex.hashCode());
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
		Type other = (Type) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (shapeIndex == null) {
			if (other.shapeIndex != null)
				return false;
		} else if (!shapeIndex.equals(other.shapeIndex))
			return false;
		if (typeModelIndex == null) {
			if (other.typeModelIndex != null)
				return false;
		} else if (!typeModelIndex.equals(other.typeModelIndex))
			return false;
		return true;
	}

}
