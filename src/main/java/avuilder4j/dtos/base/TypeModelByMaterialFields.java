package avuilder4j.dtos.base;

public class TypeModelByMaterialFields implements Indexable {

	protected Integer index;
	protected Integer typeModelIndex;
	protected Integer materialIndex;
	protected Integer creditCostMod;

	@Override
	public Integer getIndex() {
		return index;
	}

	public Integer getTypeModelIndex() {
		return typeModelIndex;
	}

	public Integer getMaterialIndex() {
		return materialIndex;
	}

	public Integer getCreditCostMod() {
		return creditCostMod;
	}

}
