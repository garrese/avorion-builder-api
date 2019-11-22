package avuilder4j.dtos.map;

public class TypeModelParams extends TypeModelFields {

	public TypeModelParams(MapIndex<Integer> index) {
		this.index = index;
	}

	public void setIndex(Integer index) { this.index = new MapIndex<Integer>(index); }

	public void setName(String name) { this.name = name; }

	public void setDensityMod(Double densityMod) { this.densityMod = densityMod; }

	public void setDurabilityMod(Double durabilityMod) { this.durabilityMod = durabilityMod; }

	public void setMaterialCostMod(Double materialCostMod) { this.materialCostMod = materialCostMod; }

	public void setMechanics(Double mechanics) { this.mechanics = mechanics; }

	public void setEngineers(Double engineers) { this.engineers = engineers; }

	public void setProcessingMod(Double processingMod) { this.processingMod = processingMod; }

	public void setVolumeStatMod(Double volumeMod) { this.volumeStatMod = volumeMod; }

}
