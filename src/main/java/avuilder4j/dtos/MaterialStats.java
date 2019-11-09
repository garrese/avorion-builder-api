package avuilder4j.dtos;

import avuilder4j.dtos.base.Closable;
import avuilder4j.dtos.base.Indexable;

/**
 * Immutable Avorion material reference.
 */
public class MaterialStats extends Closable implements Indexable {

	/**
	 * Material's index in game.
	 */
	protected Integer index;
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

	public MaterialStats() {}

	public MaterialStats(int index) {
		this.index = index;
	}

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
	@Override
	public Integer getIndex() { return index; }

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

	/**
	 * Sets the {@link #index}.
	 * 
	 * @param index the {@link #index} to set.
	 */
	public void setIndex(int index) {
		checkClosed();
		this.index = index;
	}

	/**
	 * Sets the {@link #name}.
	 * 
	 * @param name the {@link #name} to set.
	 */
	public void setName(String name) {
		checkClosed();
		this.name = name;
	}

	/**
	 * Sets the {@link #baseDensity}.
	 * 
	 * @param baseDensity the {@link #baseDensity} to set.
	 */
	public void setBaseDensity(double baseDensity) {
		checkClosed();
		this.baseDensity = baseDensity;
	}

	/**
	 * Sets the {@link #baseDurability}.
	 * 
	 * @param baseDurability the {@link #baseDurability} to set.
	 */
	public void setBaseDurability(double baseDurability) {
		checkClosed();
		this.baseDurability = baseDurability;
	}

	/**
	 * Sets the {@link #baseCreditCost}.
	 * 
	 * @param baseCreditCost the {@link #baseCreditCost} to set.
	 */
	public void setBaseCreditCost(double baseCreditCost) {
		checkClosed();
		this.baseCreditCost = baseCreditCost;
	}

	/**
	 * Sets the {@link #baseMaterialCost}.
	 * 
	 * @param baseMaterialCost the {@link #baseMaterialCost} to set.
	 */
	public void setBaseMaterialCost(double baseMaterialCost) {
		checkClosed();
		this.baseMaterialCost = baseMaterialCost;
	}

}
