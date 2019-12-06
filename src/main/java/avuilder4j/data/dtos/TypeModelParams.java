package avuilder4j.data.dtos;

public class TypeModelParams extends TypeModelFields {

	public TypeModelParams(Integer index) {
		this.index = index;
	}

	public void setIndex(Integer index) { this.index = index; }

	public void setName(String name) { this.name = name; }

	public void setDensityMod(Double densityMod) { this.densityMod = densityMod; }

	public void setDurabilityMod(Double durabilityMod) { this.durabilityMod = durabilityMod; }

	public void setMaterialCostMod(Double materialCostMod) { this.materialCostMod = materialCostMod; }

	public void setMechanics(Double mechanics) { this.mechanics = mechanics; }

	public void setEngineers(Double engineers) { this.engineers = engineers; }

	public void setProcessingMod(Double processingMod) { this.processingMod = processingMod; }

	public void setVolumeStatMod(Double volumeMod) { this.volumeStatMod = volumeMod; }

	public void setCollisionReduction(Double collisionReduction) { this.collisionReduction = collisionReduction; }

	public void setComment(String comment) { this.comment = comment; }

}
