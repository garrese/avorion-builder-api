package avuilder4j.data.dtos;

import java.util.List;
import java.util.Objects;

public class BlockArchetypeFields {

	protected Integer materialIndex;
	protected String materialName;

	protected Integer typeIndex;
	protected Integer typeModelIndex;
	protected String shape;

	protected Double collisionReduction;
	protected String typeModelName;
	protected Double mechanics;
	protected Double engineers;
	protected Boolean process;
	protected Boolean hasVolume;

	protected Double cuboidFilledIn;
	protected String symmetricShape;

	protected Double density;
	protected Double durability;
	protected Double materialCost;
	protected Double creditCost;
	protected List<Double> effects;

	public Integer getMaterialIndex() { return materialIndex; }

	public String getMaterialName() { return materialName; }

	public Integer getTypeIndex() { return typeIndex; }

	public Double getCollisionReduction() { return collisionReduction; }

	public String getTypeModelName() { return typeModelName; }

	public Integer getTypeModelIndex() { return typeModelIndex; }

	public Double getMechanics() { return mechanics; }

	public Double getEngineers() { return engineers; }

	public Boolean getProcess() { return process; }

	public Boolean getHasVolume() { return hasVolume; }

	public String getShapeIndex() { return shape; }

	public Double getCuboidFilledIn() { return cuboidFilledIn; }

	public String getSymmetricShapeIndex() { return symmetricShape; }

	public Double getDensity() { return density; }

	public Double getDurability() { return durability; }

	public Double getMaterialCost() { return materialCost; }

	public Double getCreditCost() { return creditCost; }

	public List<Double> getEffects() { return effects; }

	@Override
	public int hashCode() {
		return Objects
				.hash(collisionReduction, creditCost, cuboidFilledIn, density, durability, engineers, hasVolume, materialCost, materialIndex, materialName, mechanics, process, shape, symmetricShape, typeIndex, typeModelIndex, typeModelName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BlockArchetypeFields))
			return false;
		BlockArchetypeFields other = (BlockArchetypeFields) obj;
		return Objects.equals(collisionReduction, other.collisionReduction)
				&& Objects.equals(creditCost, other.creditCost) && Objects.equals(cuboidFilledIn, other.cuboidFilledIn)
				&& Objects.equals(density, other.density) && Objects.equals(durability, other.durability)
				&& Objects.equals(engineers, other.engineers) && Objects.equals(hasVolume, other.hasVolume)
				&& Objects.equals(materialCost, other.materialCost)
				&& Objects.equals(materialIndex, other.materialIndex)
				&& Objects.equals(materialName, other.materialName) && Objects.equals(mechanics, other.mechanics)
				&& Objects.equals(process, other.process) && Objects.equals(shape, other.shape)
				&& Objects.equals(symmetricShape, other.symmetricShape) && Objects.equals(typeIndex, other.typeIndex)
				&& Objects.equals(typeModelIndex, other.typeModelIndex)
				&& Objects.equals(typeModelName, other.typeModelName);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BlockArchetype [");
		builder.append("materialName=");
		builder.append(materialName);
		builder.append(", typeIndex=");
		builder.append(typeIndex);
		builder.append(", collisionReduction=");
		builder.append(collisionReduction);
		builder.append(", typeModelName=");
		builder.append(typeModelName);
		builder.append(", typeModelIndex=");
		builder.append(typeModelIndex);
		builder.append(", mechanics=");
		builder.append(mechanics);
		builder.append(", engineers=");
		builder.append(engineers);
		builder.append(", process=");
		builder.append(process);
		builder.append(", hasVolume=");
		builder.append(hasVolume);
		builder.append(", shape=");
		builder.append(shape);
		builder.append(", cuboidFilledIn=");
		builder.append(cuboidFilledIn);
		builder.append(", symmetricShapeIndex=");
		builder.append(symmetricShape);
		builder.append(", density=");
		builder.append(density);
		builder.append(", durability=");
		builder.append(durability);
		builder.append(", materialCost=");
		builder.append(materialCost);
		builder.append(", creditCost=");
		builder.append(creditCost);
		builder.append("]");
		return builder.toString();
	}

	public String toStringBlockName() {
		if (typeModelName != null) {
			StringBuilder typeName = new StringBuilder();
			typeName.append(typeModelName);
			if (shape != null && !shape.trim().isEmpty()) {
				typeName.append(" ").append(shape);
			}
			return typeName.toString();
		} else {
			return null;
		}
	}
}
