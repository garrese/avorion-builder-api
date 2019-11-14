package avuilder4j.dtos.base;

public abstract class MaterialFields implements Indexable {

	protected Integer index;

	protected String name;

	protected double density;

	protected double durability;

	protected double materialCost;

	@Override
	public Integer getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public double getDensity() {
		return density;
	}

	public double getDurability() {
		return durability;
	}

	public double getMaterialCost() {
		return materialCost;
	}

}
