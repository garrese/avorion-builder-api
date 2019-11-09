package avuilder4j.dtos;

/**
 * Immutable Avorion block type reference.
 */
public class TypeStatsFilled extends TypeStats {

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
	protected ValuesByIndex<Double> creditCostMod;

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

	protected Double processingMod;

	protected Double volumeMod;

	public TypeStatsFilled() {}

	public TypeStatsFilled(Integer index) {
		this.index = index;
	}

	/**
	 * Gets the {@link #densityMod}.
	 * 
	 * @return the {@link #densityMod}.
	 */
	@Override
	public Double getDensityMod() { return densityMod; }

	/**
	 * Gets the {@link #durabilityMod}.
	 * 
	 * @return the {@link #durabilityMod}.
	 */
	@Override
	public Double getDurabilityMod() { return durabilityMod; }

	/**
	 * Gets the {@link #creditCostMod}.
	 * 
	 * @return the {@link #creditCostMod}.
	 */
	@Override
	public Double getCreditCostMod(Integer materialIndex) {
		return creditCostMod.getValue(materialIndex);
	}

	/**
	 * Gets the {@link #materialCostMod}.
	 * 
	 * @return the {@link #materialCostMod}.
	 */
	@Override
	public Double getMaterialCostMod() { return materialCostMod; }

	/**
	 * Gets the {@link #mechanics}.
	 * 
	 * @return the {@link #mechanics}.
	 */
	@Override
	public Double getMechanics() { return mechanics; }

	/**
	 * Gets the {@link #engineers}.
	 * 
	 * @return the {@link #engineers}.
	 */
	@Override
	public Double getEngineers() { return engineers; }

	/**
	 * Gets the {@link #processingMod}.
	 * 
	 * @return the {@link #processingMod}.
	 */
	@Override
	public Double getProcessingMod() { return processingMod; }

	/**
	 * Gets the {@link #volumeMod}.
	 * 
	 * @return the {@link #volumeMod}.
	 */
	@Override
	public Double getVolumeMod() { return volumeMod; }

	/**
	 * Sets the {@link #densityMod}.
	 * 
	 * @param densityMod the {@link #densityMod} to set.
	 */
	public void setDensityMod(Double densityMod) {
		checkClosed();
		this.densityMod = densityMod;
	}

	/**
	 * Sets the {@link #durabilityMod}.
	 * 
	 * @param durabilityMod the {@link #durabilityMod} to set.
	 */
	public void setDurabilityMod(Double durabilityMod) {
		checkClosed();
		this.durabilityMod = durabilityMod;
	}

	/**
	 * Sets the {@link #creditCostMod}.
	 * 
	 * @param creditCostMod the {@link #creditCostMod} to set.
	 */
	public void setCreditCostMod(ValuesByIndex<Double> creditCostMod) {
		checkClosed();
		this.creditCostMod = creditCostMod;
	}

	/**
	 * Sets the {@link #creditCostMod}.
	 * 
	 * @param creditCostMod the {@link #creditCostMod} to set.
	 */
	public void setCreditCostMod(Double creditCostMod) {
		checkClosed();
		this.creditCostMod = new ValuesByIndex<Double>(creditCostMod);
	}

	/**
	 * Sets the {@link #materialCostMod}.
	 * 
	 * @param materialCostMod the {@link #materialCostMod} to set.
	 */
	public void setMaterialCostMod(Double materialCostMod) {
		checkClosed();
		this.materialCostMod = materialCostMod;
	}

	/**
	 * Sets the {@link #mechanics}.
	 * 
	 * @param mechanics the {@link #mechanics} to set.
	 */
	public void setMechanics(Double mechanics) {
		checkClosed();
		this.mechanics = mechanics;
	}

	/**
	 * Sets the {@link #engineers}.
	 * 
	 * @param engineers the {@link #engineers} to set.
	 */
	public void setEngineers(Double engineers) {
		checkClosed();
		this.engineers = engineers;
	}

	/**
	 * Sets the {@link #processingMod}.
	 * 
	 * @param processingMod the {@link #processingMod} to set.
	 */
	public void setProcessingMod(Double processingMod) {
		checkClosed();
		this.processingMod = processingMod;
	}

	/**
	 * Sets the {@link #volumeMod}.
	 * 
	 * @param volumeMod the {@link #volumeMod} to set.
	 */
	public void setVolumeMod(Double volumeMod) { this.volumeMod = volumeMod; }

	@Override
	public String toString() {
		return "TypeStatsFilled [index=" + index + ", shape=" + shape + ", closed=" + closed + ", densityMod="
				+ densityMod + ", durabilityMod=" + durabilityMod + ", creditCostMod=" + creditCostMod
				+ ", materialCostMod=" + materialCostMod + ", mechanics=" + mechanics + ", engineers=" + engineers
				+ ", processingMod=" + processingMod + ", volumeMod=" + volumeMod + "]";
	}

}
