package avuilder4j.data.beans;

public class BeanType {

	protected Integer index;
	protected Integer typeModelIndex;
	protected String shape;

	public BeanType(Integer index, Integer typeModelIndex, String shape) {
		this.index = index;
		this.typeModelIndex = typeModelIndex;
		this.shape = shape;
	}

	public Integer getIndex() { return index; }

	public Integer getTypeModelIndex() { return typeModelIndex; }

	public String getShape() { return shape; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Type [index=");
		builder.append(index);
		builder.append(", typeModelIndex=");
		builder.append(typeModelIndex);
		builder.append(", shapeIndex=");
		builder.append(shape);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((shape == null) ? 0 : shape.hashCode());
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
		if (shape == null) {
			if (other.shape != null)
				return false;
		} else if (!shape.equals(other.shape))
			return false;
		if (typeModelIndex == null) {
			if (other.typeModelIndex != null)
				return false;
		} else if (!typeModelIndex.equals(other.typeModelIndex))
			return false;
		return true;
	}

}
