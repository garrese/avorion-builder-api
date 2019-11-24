package avuilder4j.design.base;

import avuilder4j.design.sub.Orientation;
import avuilder4j.util.values.Colors;
import avuilder4j.util.values.Mats;
import avuilder4j.util.values.Types;

@SuppressWarnings("rawtypes")
public abstract class BlockPlanIndexerGeneric<B extends BlockPlanGeneric, S extends BlockPlanStructureGeneric<B>>
		extends CuboidIndexerGeneric<B, S> {

	protected String defaultColor = Colors.MATERIAL_00_IRON;
	protected Integer defaultMaterial = Mats.IRON;
	protected Integer defaultType = Types.BLANK_HULL;
	protected Orientation defaultOrientation = new Orientation();

	@Override
	public B createBlock() {
		B block = super.createBlock();
		block.setColor(defaultColor);
		block.setMaterial(defaultMaterial);
		block.setType(defaultType);
		block.setOrientation(defaultOrientation);
		return block;
	}

	/**
	 * Gets the {@link #defaultColor}.
	 * 
	 * @return the {@link #defaultColor}.
	 */
	public String getDefaultColor() { return defaultColor; }

	/**
	 * Sets the {@link #defaultColor}.
	 * 
	 * @param defaultColor the {@link #defaultColor} to set.
	 */
	public void setDefaultColor(String defaultColor) { this.defaultColor = defaultColor; }

	/**
	 * Gets the {@link #defaultMaterial}.
	 * 
	 * @return the {@link #defaultMaterial}.
	 */
	public Integer getDefaultMaterial() { return defaultMaterial; }

	/**
	 * Sets the {@link #defaultMaterial}.
	 * 
	 * @param defaultMaterial the {@link #defaultMaterial} to set.
	 */
	public void setDefaultMaterial(Integer defaultMaterial) { this.defaultMaterial = defaultMaterial; }

	/**
	 * Gets the {@link #defaultType}.
	 * 
	 * @return the {@link #defaultType}.
	 */
	public Integer getDefaultType() { return defaultType; }

	/**
	 * Sets the {@link #defaultType}.
	 * 
	 * @param defaultType the {@link #defaultType} to set.
	 */
	public void setDefaultType(Integer defaultType) { this.defaultType = defaultType; }

	/**
	 * Gets the {@link #defaultOrientation}.
	 * 
	 * @return the {@link #defaultOrientation}.
	 */
	public Orientation getDefaultOrientation() { return defaultOrientation; }

	/**
	 * Sets the {@link #defaultOrientation}.
	 * 
	 * @param defaultOrientation the {@link #defaultOrientation} to set.
	 */
	public void setDefaultOrientation(Orientation defaultOrientation) {
		this.defaultOrientation = defaultOrientation;
	}

}
