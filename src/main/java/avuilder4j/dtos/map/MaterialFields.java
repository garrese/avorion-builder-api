package avuilder4j.dtos.map;

public abstract class MaterialFields implements Indexable {

	protected MapIndex<Integer> index;

	protected String name;

	protected Double density;

	protected Double durability;

	protected Double creditCost;

	protected Double materialCost;

	@Override
	public MapIndex<Integer> getMapIndex() { return index; }

	public String getName() { return name; }

	public Double getDensity() { return density; }

	public Double getDurability() { return durability; }

	public Double getCreditCost() { return creditCost; }

	public Double getMaterialCost() { return materialCost; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MaterialFields [index=");
		builder.append(index);
		builder.append(", name=");
		builder.append(name);
		builder.append(", density=");
		builder.append(density);
		builder.append(", durability=");
		builder.append(durability);
		builder.append(", creditCost=");
		builder.append(creditCost);
		builder.append(", materialCost=");
		builder.append(materialCost);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditCost == null) ? 0 : creditCost.hashCode());
		result = prime * result + ((density == null) ? 0 : density.hashCode());
		result = prime * result + ((durability == null) ? 0 : durability.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((materialCost == null) ? 0 : materialCost.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		MaterialFields other = (MaterialFields) obj;
		if (creditCost == null) {
			if (other.creditCost != null)
				return false;
		} else if (!creditCost.equals(other.creditCost))
			return false;
		if (density == null) {
			if (other.density != null)
				return false;
		} else if (!density.equals(other.density))
			return false;
		if (durability == null) {
			if (other.durability != null)
				return false;
		} else if (!durability.equals(other.durability))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (materialCost == null) {
			if (other.materialCost != null)
				return false;
		} else if (!materialCost.equals(other.materialCost))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
