package avuilder4j.data.beans;

public class BeanMaterial extends BeanMaterialFields {

	public BeanMaterial(BeanMaterialParams materialParams) {
		if (materialParams != null) {
			this.index = materialParams.getIndex();
			this.creditCost = materialParams.getCreditCost();
			this.density = materialParams.getDensity();
			this.durability = materialParams.getDurability();
			this.materialCost = materialParams.getMaterialCost();
			this.name = materialParams.getName();
		}
	}

}
