package avuilder4j.stats.dtos;

/**
 * Immutable Avorion material reference.
 */
public class MaterialStats {

	/**
	 * Material's index in game.
	 */
	protected int index;
	/**
	 * Material's name.
	 */
	protected String name;
	/**
	 * Material's density in t/m^3.
	 */
	protected double baseDensity;
	/**
	 * Material's durability in hp/m^3.
	 */
	protected double baseDurability;
	/**
	 * Material's credit cost in credits/m^3.
	 */
	protected double baseCreditCost;
	/**
	 * Material's material cost in ore/m^3.
	 */
	protected double baseMaterialCost;

	/**
	 * Material reference constructor.
	 * 
	 * @param index            the {@link #index}
	 * @param baseDensity      the {@link #baseDensity}
	 * @param baseDurability   the {@link #baseDurability}
	 * @param baseCreditCost   the {@link #baseCreditCost}
	 * @param baseMaterialCost the {@link #baseMaterialCost}
	 */
	public MaterialStats(int index, String name, double baseDensity, double baseDurability, double baseCreditCost,
			double baseMaterialCost) {

		super();
		this.index = index;
		this.name = name;
		this.baseDensity = baseDensity;
		this.baseDurability = baseDurability;
		this.baseCreditCost = baseCreditCost;
		this.baseMaterialCost = baseMaterialCost;
	}

	/**
	 * Gets the {@link #index}.
	 * 
	 * @return the {@link #index}.
	 */
	public int getIndex() { return index; }

	/**
	 * Gets the {@link #baseDensity}.
	 * 
	 * @return the {@link #baseDensity}.
	 */
	public double getBaseDensity() { return baseDensity; }

	/**
	 * Gets the {@link #baseDurability}.
	 * 
	 * @return the {@link #baseDurability}.
	 */
	public double getBaseDurability() { return baseDurability; }

	/**
	 * Gets the {@link #baseCreditCost}.
	 * 
	 * @return the {@link #baseCreditCost}.
	 */
	public double getBaseCreditCost() { return baseCreditCost; }

	/**
	 * Gets the {@link #baseMaterialCost}.
	 * 
	 * @return the {@link #baseMaterialCost}.
	 */
	public double getBaseMaterialCost() { return baseMaterialCost; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(baseCreditCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(baseDensity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(baseDurability);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(baseMaterialCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + index;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterialStats other = (MaterialStats) obj;
		if (Double.doubleToLongBits(baseCreditCost) != Double.doubleToLongBits(other.baseCreditCost))
			return false;
		if (Double.doubleToLongBits(baseDensity) != Double.doubleToLongBits(other.baseDensity))
			return false;
		if (Double.doubleToLongBits(baseDurability) != Double.doubleToLongBits(other.baseDurability))
			return false;
		if (Double.doubleToLongBits(baseMaterialCost) != Double.doubleToLongBits(other.baseMaterialCost))
			return false;
		if (index != other.index)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MaterialStats [index=" + index + ", name=" + name + ", baseDensity=" + baseDensity + ", baseDurability="
				+ baseDurability + ", baseCreditCost=" + baseCreditCost + ", baseMaterialCost=" + baseMaterialCost
				+ "]";
	}

}
