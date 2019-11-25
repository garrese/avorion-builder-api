package avuilder4j.data;

public class Material extends MaterialFields {

	public Material(MaterialParams materialParams) {
		this.index = materialParams.getIndex();
		this.creditCost = materialParams.getCreditCost();
		this.density = materialParams.getDensity();
		this.durability = materialParams.getDurability();
		this.materialCost = materialParams.getMaterialCost();
		this.name = materialParams.getName();
	}

}
