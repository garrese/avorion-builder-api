package avuilder4j.data.loaders.dtos;

public class TypeModel extends TypeModelFields {

	public TypeModel(TypeModelParams p) {
		this.index = p.getIndex();
		this.name = p.getName();
		this.densityMod = p.getDensityMod();
		this.durabilityMod = p.getDurabilityMod();
		this.materialCostMod = p.getMaterialCostMod();
		this.mechanics = p.getMechanics();
		this.engineers = p.getEngineers();
		this.processingMod = p.getProcessingMod();
		this.volumeStatMod = p.getVolumeStatMod();
	}

}
