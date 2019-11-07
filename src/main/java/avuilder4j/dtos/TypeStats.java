package avuilder4j.dtos;

/**
 * Immutable Avorion block type reference.
 */
public class TypeStats {

	/**
	 * Block type index in game.
	 */
	protected int index;

	/**
	 * Block's shape modification to volume
	 */
	protected double shapeVolMod;

	/**
	 * Material density modificator of the block type.
	 */
	protected Double densityMod;

	/**
	 * Material durability modificator of the block type.
	 */
	protected Double durabilityMod;

	/**
	 * Material credit cost modificator of the block type.
	 */
	protected Double creditCostMod;

	/**
	 * Material cost modificator of the block type.
	 */
	protected Double materialCostMod;

	/**
	 * Mechanics cost in crew/m^3.
	 */
	protected Double mechanics;

	/**
	 * Engineers cost in crew/m^3.
	 */
	protected Double engineers;

	/**
	 * Block type reference constructor.
	 * 
	 * @param index           the {@link #index}
	 * @param shapeVolMod     the {@link #shapeVolMod}
	 * @param densityMod      the {@link #densityMod}
	 * @param durabilityMod   the {@link #durabilityMod}
	 * @param creditCostMod   the {@link #creditCostMod}
	 * @param materialCostMod the {@link #materialCostMod}
	 * @param mechanics       the {@link #mechanics}
	 * @param engineers       the {@link #engineers}
	 */
	public TypeStats(int index, double shapeVolMod, Double densityMod, Double durabilityMod, Double creditCostMod,
			Double materialCostMod, Double mechanics, Double engineers) {
		super();
		this.index = index;
		this.shapeVolMod = shapeVolMod;
		this.densityMod = densityMod;
		this.durabilityMod = durabilityMod;
		this.creditCostMod = creditCostMod;
		this.materialCostMod = materialCostMod;
		this.mechanics = mechanics;
		this.engineers = engineers;
	}

	/**
	 * Gets the {@link #index}.
	 * 
	 * @return the {@link #index}.
	 */
	public int getIndex() { return index; }

	/**
	 * Gets the {@link #shapeVolMod}.
	 * 
	 * @return the {@link #shapeVolMod}.
	 */
	public double getShapeVolMod() { return shapeVolMod; }

	/**
	 * Gets the {@link #densityMod}.
	 * 
	 * @return the {@link #densityMod}.
	 */
	public Double getDensityMod() { return densityMod; }

	/**
	 * Gets the {@link #durabilityMod}.
	 * 
	 * @return the {@link #durabilityMod}.
	 */
	public Double getDurabilityMod() { return durabilityMod; }

	/**
	 * Gets the {@link #creditCostMod}.
	 * 
	 * @return the {@link #creditCostMod}.
	 */
	public Double getCreditCostMod() { return creditCostMod; }

	/**
	 * Gets the {@link #materialCostMod}.
	 * 
	 * @return the {@link #materialCostMod}.
	 */
	public Double getMaterialCostMod() { return materialCostMod; }

	/**
	 * Gets the {@link #mechanics}.
	 * 
	 * @return the {@link #mechanics}.
	 */
	public Double getMechanics() { return mechanics; }

	/**
	 * Gets the {@link #engineers}.
	 * 
	 * @return the {@link #engineers}.
	 */
	public Double getEngineers() { return engineers; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditCostMod == null) ? 0 : creditCostMod.hashCode());
		result = prime * result + ((densityMod == null) ? 0 : densityMod.hashCode());
		result = prime * result + ((durabilityMod == null) ? 0 : durabilityMod.hashCode());
		result = prime * result + ((engineers == null) ? 0 : engineers.hashCode());
		result = prime * result + index;
		result = prime * result + ((materialCostMod == null) ? 0 : materialCostMod.hashCode());
		result = prime * result + ((mechanics == null) ? 0 : mechanics.hashCode());
		long temp;
		temp = Double.doubleToLongBits(shapeVolMod);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		TypeStats other = (TypeStats) obj;
		if (creditCostMod == null) {
			if (other.creditCostMod != null)
				return false;
		} else if (!creditCostMod.equals(other.creditCostMod))
			return false;
		if (densityMod == null) {
			if (other.densityMod != null)
				return false;
		} else if (!densityMod.equals(other.densityMod))
			return false;
		if (durabilityMod == null) {
			if (other.durabilityMod != null)
				return false;
		} else if (!durabilityMod.equals(other.durabilityMod))
			return false;
		if (engineers == null) {
			if (other.engineers != null)
				return false;
		} else if (!engineers.equals(other.engineers))
			return false;
		if (index != other.index)
			return false;
		if (materialCostMod == null) {
			if (other.materialCostMod != null)
				return false;
		} else if (!materialCostMod.equals(other.materialCostMod))
			return false;
		if (mechanics == null) {
			if (other.mechanics != null)
				return false;
		} else if (!mechanics.equals(other.mechanics))
			return false;
		if (Double.doubleToLongBits(shapeVolMod) != Double.doubleToLongBits(other.shapeVolMod))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TypeStats [index=" + index + ", shapeVolMod=" + shapeVolMod + ", densityMod=" + densityMod
				+ ", durabilityMod=" + durabilityMod + ", creditCostMod=" + creditCostMod + ", materialCostMod="
				+ materialCostMod + ", mechanics=" + mechanics + ", engineers=" + engineers + "]";
	}

}
