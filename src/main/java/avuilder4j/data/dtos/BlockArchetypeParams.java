package avuilder4j.data.dtos;

import java.util.List;

public class BlockArchetypeParams extends BlockArchetypeFields {

	public void setMaterialIndex(Integer materialIndex) { this.materialIndex = materialIndex; }

	public void setMaterialName(String materialName) { this.materialName = materialName; }

	public void setTypeIndex(Integer typeIndex) { this.typeIndex = typeIndex; }

	public void setCollisionReduction(Double collisionReduction) { this.collisionReduction = collisionReduction; }

	public void setTypeModelName(String typeModelName) { this.typeModelName = typeModelName; }

	public void setTypeModelIndex(Integer typeModelIndex) { this.typeModelIndex = typeModelIndex; }

	public void setMechanics(Double mechanics) { this.mechanics = mechanics; }

	public void setEngineers(Double engineers) { this.engineers = engineers; }

	public void setProcess(Boolean process) { this.process = process; }

	public void setHasVolume(Boolean hasVolume) { this.hasVolume = hasVolume; }

	public void setShape(String shape) { this.shape = shape; }

	public void setCuboidFilledIn(Double cuboidFilledIn) { this.cuboidFilledIn = cuboidFilledIn; }

	public void setSymmetricShape(String symmetricShape) { this.symmetricShape = symmetricShape; }

	public void setDensity(Double density) { this.density = density; }

	public void setDurability(Double durability) { this.durability = durability; }

	public void setMaterialCost(Double materialCost) { this.materialCost = materialCost; }

	public void setCreditCost(Double creditCost) { this.creditCost = creditCost; }

	public void setEffects(List<Double> effects) { this.effects = effects; }

}