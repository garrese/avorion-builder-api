package avuilder4j.data.beans;

public class BeanTypeModel extends BeanTypeModelFields {

	public BeanTypeModel(BeanTypeModelParams p) {
		if (p != null) {
			this.index = p.getIndex();
			this.name = p.getName();
			this.densityMod = p.getDensityMod();
			this.durabilityMod = p.getDurabilityMod();
			this.materialCostMod = p.getMaterialCostMod();
			this.mechanics = p.getMechanics();
			this.engineers = p.getEngineers();
			this.process = p.getProcess();
			this.hasVolume = p.getHasVolume();
			this.collisionReduction = p.getCollisionReduction();
			this.comment = p.getComment();
		}
	}

}
