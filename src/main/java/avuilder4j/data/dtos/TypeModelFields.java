package avuilder4j.data.dtos;

import java.util.Objects;

public abstract class TypeModelFields {

	protected Integer index;
	protected String name;
	protected Double densityMod;
	protected Double durabilityMod;
	protected Double materialCostMod;
	protected Double mechanics;
	protected Double engineers;
	protected Boolean process;
	protected Boolean hasVolume;
	protected Double collisionReduction;
	protected String comment;

	public Integer getIndex() { return index; }

	public String getName() { return name; }

	public Double getDensityMod() { return densityMod; }

	public Double getDurabilityMod() { return durabilityMod; }

	public Double getMaterialCostMod() { return materialCostMod; }

	public Double getMechanics() { return mechanics; }

	public Double getEngineers() { return engineers; }

	public Boolean isProcess() { return process; }

	public Boolean isHasVolume() { return hasVolume; }

	public Double getCollisionReduction() { return collisionReduction; }

	public String getComment() { return comment; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TypeModelFields [index=");
		builder.append(index);
		builder.append(", name=");
		builder.append(name);
		builder.append(", densityMod=");
		builder.append(densityMod);
		builder.append(", durabilityMod=");
		builder.append(durabilityMod);
		builder.append(", materialCostMod=");
		builder.append(materialCostMod);
		builder.append(", mechanics=");
		builder.append(mechanics);
		builder.append(", engineers=");
		builder.append(engineers);
		builder.append(", process=");
		builder.append(process);
		builder.append(", hasVolume=");
		builder.append(hasVolume);
		builder.append(", collisionReduction=");
		builder.append(collisionReduction);
		builder.append(", comment=");
		builder.append("\"").append(comment).append("\"");
		builder.append("]");
		return builder.toString();
	}

	public String toStringReduced() {
		StringBuilder builder = new StringBuilder();
		builder.append("TypeModelFields [index=");
		builder.append(index);
		builder.append(", name=");
		builder.append(name);
		builder.append(", densityMod=");
		builder.append(densityMod);
		builder.append(", durabilityMod=");
		builder.append(durabilityMod);
		builder.append(", materialCostMod=");
		builder.append(materialCostMod);
		builder.append(", collisionReduction=");
		builder.append(collisionReduction);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects
				.hash(collisionReduction, comment, densityMod, durabilityMod, engineers, index, materialCostMod, mechanics, name, process, hasVolume);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TypeModelFields))
			return false;
		TypeModelFields other = (TypeModelFields) obj;
		return Objects.equals(collisionReduction, other.collisionReduction) && Objects.equals(comment, other.comment)
				&& Objects.equals(densityMod, other.densityMod) && Objects.equals(durabilityMod, other.durabilityMod)
				&& Objects.equals(engineers, other.engineers) && Objects.equals(index, other.index)
				&& Objects.equals(materialCostMod, other.materialCostMod) && Objects.equals(mechanics, other.mechanics)
				&& Objects.equals(name, other.name) && Objects.equals(process, other.process)
				&& Objects.equals(hasVolume, other.hasVolume);
	}

}
