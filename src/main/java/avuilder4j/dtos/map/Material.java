package avuilder4j.dtos.map;

public class Material extends MaterialFields {

	public Material(MaterialParams materialParams) {
		this.index = materialParams.getMapIndex();
		this.creditCost = materialParams.getCreditCost();
		this.density = materialParams.getDensity();
		this.durability = materialParams.getDurability();
		this.materialCost = materialParams.getMaterialCost();
		this.name = materialParams.getName();
	}

}
