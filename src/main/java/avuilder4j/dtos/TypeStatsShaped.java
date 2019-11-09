package avuilder4j.dtos;

public class TypeStatsShaped extends TypeStats {

	protected TypeStatsFilled filled;

	public TypeStatsShaped() {}

	public TypeStatsShaped(Integer index) {
		setIndex(index);
	}

	public TypeStatsShaped(Integer index, Integer shape, TypeStatsFilled filled) {
		setIndex(index);
		setShape(shape);
		this.filled = filled;
	}

	@Override
	public Integer getShape() { return shape; }

	@Override
	public Double getDensityMod() { return filled.getDensityMod(); }

	@Override
	public Double getDurabilityMod() { return filled.getDurabilityMod(); }

	@Override
	public Double getCreditCostMod(Integer materialIndex) {
		return filled.getCreditCostMod(materialIndex);
	}

	@Override
	public Double getMaterialCostMod() { return filled.getMaterialCostMod(); }

	@Override
	public Double getMechanics() { return filled.getMechanics(); }

	@Override
	public Double getEngineers() { return filled.getEngineers(); }

	@Override
	public Double getProcessingMod() { return filled.getProcessingMod(); }

	@Override
	public Double getVolumeMod() { return filled.getVolumeMod(); }

	/**
	 * Gets the {@link #filled}.
	 * 
	 * @return the {@link #filled}.
	 */
	public TypeStatsFilled getFilled() { return filled; }

	/**
	 * Sets the {@link #filled}.
	 * 
	 * @param filled the {@link #filled} to set.
	 */
	public void setFilled(TypeStatsFilled filled) {
		checkClosed();
		this.filled = filled;
	}

	@Override
	public String toString() {
		return "TypeStatsShaped [index=" + index + ", shape=" + shape + ", closed=" + closed + ", filled=" + filled
				+ "]";
	}

}
