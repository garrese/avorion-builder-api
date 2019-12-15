package avuilder4j.data.beans;

import java.util.Objects;

public abstract class BeanMaterialFields {

	protected Integer index;

	protected String name;

	protected Double density;

	protected Double durability;

	protected Double creditCost;

	protected Double materialCost;

	public Integer getIndex() { return index; }

	public String getName() { return name; }

	public Double getDensity() { return density; }

	public Double getDurability() { return durability; }

	public Double getCreditCost() { return creditCost; }

	public Double getMaterialCost() { return materialCost; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Material [index=");
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
		return Objects.hash(creditCost, density, durability, index, materialCost, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BeanMaterialFields))
			return false;
		BeanMaterialFields other = (BeanMaterialFields) obj;
		return Objects.equals(creditCost, other.creditCost) && Objects.equals(density, other.density)
				&& Objects.equals(durability, other.durability) && Objects.equals(index, other.index)
				&& Objects.equals(materialCost, other.materialCost) && Objects.equals(name, other.name);
	}

}
