package avuilder4j.data;

public class Shape {

	protected Integer index;

	protected String name;

	protected Double volumeMod;

	public Shape(Integer index, String name, Double volumeMod) {
		this.index = index;
		this.name = name;
		this.volumeMod = volumeMod;
	}

	public Integer getIndex() { return index; }

	public String getName() { return name; }

	public Double getVolumeMod() { return volumeMod; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shape [index=");
		builder.append(index);
		builder.append(", name=");
		builder.append(name);
		builder.append(", volumeMod=");
		builder.append(volumeMod);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((volumeMod == null) ? 0 : volumeMod.hashCode());
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
		Shape other = (Shape) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (volumeMod == null) {
			if (other.volumeMod != null)
				return false;
		} else if (!volumeMod.equals(other.volumeMod))
			return false;
		return true;
	}

}
